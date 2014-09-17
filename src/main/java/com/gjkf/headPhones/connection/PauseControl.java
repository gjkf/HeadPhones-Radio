package com.gjkf.headPhones.connection;

public class PauseControl{
	private boolean needToPause;

	public synchronized void pausePoint() throws InterruptedException{
		while(needToPause){
			wait();
		}
	}

	public synchronized void pause(){
		needToPause = true;
	}

	public synchronized void unpause(){
		needToPause = false;
		this.notifyAll();
	}
}