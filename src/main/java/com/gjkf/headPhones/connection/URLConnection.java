package com.gjkf.headPhones.connection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class URLConnection extends Thread{

	public static boolean shouldBeRunning = true;

	public String url;

	public Player player = null;

	public java.net.URLConnection urlConnection;

	public URLConnection(String link){
		this.setDaemon(true);
		this.setName("HeadPhonesRadio Url Connector Thread");

		this.url = link;
	}

	@Override
	public void run(){
		try{
			init();
		//	player = new Player (urlConnection.getInputStream());

			if(shouldBeRunning){
				connect();
			}else{
				disconnect();
				kill();
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void cancel(){
		this.shouldBeRunning = false;
	}

	public void begin(){
		this.shouldBeRunning = true;
	}

	public void connect() throws IOException, JavaLayerException{
		this.urlConnection.connect();
		this.player.play();
	}
	
	public void disconnect() throws IOException{
		this.player.close();
		this.urlConnection.getInputStream().close();
	}

	public void init() throws MalformedURLException, IOException, JavaLayerException{
		if(urlConnection == null){
			urlConnection = new URL(url).openConnection();
			System.err.println(urlConnection.toString());
		}
		if(player == null){
			player = new Player (urlConnection.getInputStream());
			System.err.println(player.toString());
		}
		connect();
		begin();
	}

	public void kill() throws IOException{
		urlConnection = null;
		player = null;
	}
	
}