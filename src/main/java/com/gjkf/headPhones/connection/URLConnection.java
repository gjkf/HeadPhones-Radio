package com.gjkf.headPhones.connection;

import java.io.IOException;
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
		this.setName("headPhonesRadio Url Connecter Thread");

		this.url = link;
	}

	@Override
	public void run(){
		try{
			// Connection
			urlConnection = new URL(url).openConnection();

			// Connecting
			urlConnection.connect();

			// Playing
			player = new Player (urlConnection.getInputStream());
			//player.play();
			this.connect();
			
			/*if(!shouldBeRunning){
				player.close();
			}*/
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

}