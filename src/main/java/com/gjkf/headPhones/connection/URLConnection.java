package com.gjkf.headPhones.connection;

import java.io.IOException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class URLConnection extends Thread{

	public static boolean shouldBeRunning = true;
	
	public String url;
	
	public Player player = null;
	
	public URLConnection(String link){
		this.setDaemon(true);
		this.setName("headPhonesRadio Url Connecter Thread");
		
		this.url = link;
	}

	@Override
	public void run(){
		while(!this.isInterrupted()){
			try{
				// Connection
				java.net.URLConnection urlConnection = new URL(url).openConnection();

				// Connecting
				urlConnection.connect();

				// Playing
				player = new Player (urlConnection.getInputStream());
				player.play();
				/*if(!shouldBeRunning){
					player.close();
				}*/
			}catch(Exception e){
				e.printStackTrace();
				if(e.equals(new InterruptedException())){
					player.close();
				}
			}
		}
		
	}
	
	public void cancel(){
		this.shouldBeRunning = false;
	}
	
}