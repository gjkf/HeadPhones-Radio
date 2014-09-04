package com.gjkf.headPhones.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.gjkf.headPhones.Main;

public class URLConnection{

	public URL url;

	public BufferedReader in = null;

	public URLConnection(String link){

		try{
			url = new URL(link);
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}

		try{
			java.net.URLConnection connection = url.openConnection();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String inLine;

			while((inLine = in.readLine()) != null){
				
				/*
				 * Not exactly what I want...
				 */
				
				Main.log.info("InLine(URL): " + inLine);
			}

		}catch(IOException e){
			e.printStackTrace();
		}

	}

}