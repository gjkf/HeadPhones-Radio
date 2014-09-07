package com.gjkf.headPhones.connection;

import java.io.IOException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class URLConnection extends Thread{

	public String url;
	
	public URLConnection(String link){
		this.setDaemon(true);
		this.setName("headPhonesRadio Url Connecter Thread");
		
		this.url = link;
	}

	@Override
	public void run(){
		
		try{
			// Connection
			java.net.URLConnection urlConnection = new URL(url).openConnection();

			// Connecting
			urlConnection.connect();

			// Playing
			Player player = new Player (urlConnection.getInputStream());
			player.play();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}