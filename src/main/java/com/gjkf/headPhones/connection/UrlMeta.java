package com.gjkf.headPhones.connection;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.Iterator;
import java.util.Scanner;

import com.gjkf.headPhones.utility.LogHelper;

import com.google.common.base.Splitter;

public class UrlMeta {

	private static final String MIME_M3U_APP = "application/x-mpegurl";
	private static final String MIME_M3U_AUDIO = "audio/x-mpegurl";

	private static final String MIME_PLS = "audio/x-scpls";

	public enum Status {
		NOT_YET_RESOLVED(false, "not_ready"),
		UNKOWN_ERROR(false, "unknown_error"),
		CANT_CONNECT(false, "cant_connect"),
		INVALID_URL(false, "invalid_url"),
		NOT_FOUND(false, "url_not_found"),
		MALFORMED_DATA(false, "malformed_data"),
		OK(true, "");

		public final boolean valid;
		public final String message;

		private Status(boolean valid, String message) {
			this.valid = valid;
			this.message = "openblocks.misc.radio." + message;
		}
	}

	private Status status = Status.NOT_YET_RESOLVED;
	private String url;
	private String contentType;

	public UrlMeta(String url) {
		this.url = url;
	}

	public Status getStatus() {
		return status;
	}

	public String getUrl() {
		return url;
	}

	public String getContentType() {
		return contentType;
	}

	public synchronized void markAsFailed() {
		status = Status.UNKOWN_ERROR;
	}

	public synchronized void resolve() {
		if (status == Status.NOT_YET_RESOLVED) status = resolveImpl();
	}

	private Status resolveImpl() {
		LogHelper.info("Resolving URL %s");
		HttpURLConnection connection = null;
		try {
			while (true) {
				URL url;
				try {
					url = new URL(null, this.url, new IcyConnectionHandler());
				} catch (MalformedURLException e) {
					return Status.INVALID_URL;
				}
				connection = (HttpURLConnection)url.openConnection();
				connection.setRequestProperty("User-Agent", "OpenMods/0.0 Minecraft/1.6.4");
				connection.setRequestProperty("Host", url.getHost() + ":" + url.getPort());

				try {
					connection.connect();
				} catch (ConnectException e) {
					LogHelper.warn("Can't connect to %s");
					return Status.CANT_CONNECT;
				}

				final int responseCode = connection.getResponseCode();
				switch (responseCode) {
					case 200:
						return processStream(connection);
					case 301:
					case 302:
					case 303:
					case 307: {
						final String redirectedUrl = connection.getHeaderField("location");
						LogHelper.debug("Redirection to URL %s (code: %d)");
						connection.disconnect();
						this.url = redirectedUrl;
						continue;
					}
					case 404:
						return Status.NOT_FOUND;
					default:
						LogHelper.warn("Invalid status code from url %s: %d");
						return Status.UNKOWN_ERROR;
				}
			}
		} catch (Throwable t) {
			LogHelper.warn("Exception during opening url %s");
		} finally {
			if (connection != null) connection.disconnect();
		}
		return Status.UNKOWN_ERROR;
	}

	private Status processStream(HttpURLConnection connection) {
		String contentType = connection.getContentType();
		LogHelper.debug("URL %s has content type %s");
		if (contentType.equals(MIME_PLS)) return parsePLS(connection);
		else if (contentType.equals(MIME_M3U_APP) || contentType.equals(MIME_M3U_AUDIO)) return parseM3U(connection);

		this.contentType = contentType;
		return Status.OK;
	}

	private Status parsePLS(HttpURLConnection connection) {
		LogHelper.debug("Parsing PLS file at URL %s");
		Scanner scanner = null;
		try {
			Reader reader = new InputStreamReader(connection.getInputStream());
			scanner = new Scanner(reader);
			String header = scanner.nextLine();
			if (!header.equals("[playlist]")) {
				LogHelper.warn("Invalid header '%s' in pls file %s");
				return Status.MALFORMED_DATA;
			}

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Iterable<String> split = Splitter.on('=').split(line);
				Iterator<String> it = split.iterator();
				String name = it.next();
				if (name.startsWith("File")) {
					String value = it.next();
					LogHelper.debug("Trying playlist %s entry %s = %s)");
					url = value;
					Status result = resolveImpl();
					if (result.valid) return result;
				}
			}
		} catch (Throwable t) {
			LogHelper.warn("Can't parse playlist file %s");
		} finally {
			if (scanner != null) scanner.close();
		}
		return Status.UNKOWN_ERROR;
	}

	private Status parseM3U(HttpURLConnection connection) {
		LogHelper.info("Parsing M3U file at URL %s");
		Scanner scanner = null;
		try {
			Reader reader = new InputStreamReader(connection.getInputStream());
			scanner = new Scanner(reader);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("#")) continue;
				LogHelper.debug("Trying playlist %s entry %s)");
				url = line;
				Status result = resolveImpl();
				if (result.valid) return result;
			}
		} catch (Throwable t) {
			LogHelper.warn("Can't parse playlist file %s");
		} finally {
			if (scanner != null) scanner.close();
		}
		return Status.UNKOWN_ERROR;
	}
	
}