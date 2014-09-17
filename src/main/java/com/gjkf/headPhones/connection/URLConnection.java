package com.gjkf.headPhones.connection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.handler.KeyInputEventHandler;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class URLConnection extends Thread{

	/*
	 * http://streaming202.radionomy.com:80/abacusfm-vintage-jazz
	 */
	
	public static boolean shouldBeRunning = true;

	public String url;

	public Player player = null;

	public java.net.URLConnection urlConnection;
	
	public PauseControl pc;

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
				
				pc.unpause();
				
			//	Main.log.info(shouldBeRunning);
			//	init();
			//	connect();
			}else{
				
				pc.pausePoint();
			//	Main.log.info(shouldBeRunning);
				
			//	disconnect();
			//	kill();
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
		if(checkConnection() && checkPlayer()){
			this.urlConnection.connect();
			this.player.play();
		}else{
			kill();
			init();
			this.urlConnection.connect();
			this.player.play();
		}
	}
	
	public void disconnect() throws IOException, InterruptedException{
		this.player.close();
		this.urlConnection.getInputStream().close();
		cancel();
		pc.pausePoint();
	}

	public void init() throws MalformedURLException, IOException, JavaLayerException{
		if(!checkConnection()){
			urlConnection = new URL(url).openConnection();
			System.err.println(urlConnection.toString());
		}
		if(!checkPlayer()){
			player = new Player (urlConnection.getInputStream());
			System.err.println(player.toString());
		}
		connect();
		begin();
		pc = new PauseControl();
	}

	public void kill() throws IOException{
		urlConnection = null;
		player = null;
	}
	
	public boolean checkPlayer(){
		if(player == null){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkConnection(){
		if(urlConnection == null){
			return false;
		}else{
			return true;
		}
	}
	
}