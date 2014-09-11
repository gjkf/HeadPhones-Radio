package com.gjkf.headPhones.handler;

import net.minecraft.item.ItemStack;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.client.gui.LinkGui;
import com.gjkf.headPhones.client.settings.KeyBindings;
import com.gjkf.headPhones.connection.URLConnection;
import com.gjkf.headPhones.items.RadioCrystal;
import com.gjkf.headPhones.reference.Key;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler { 

	public boolean playing;

	public LinkGui linkGui = null;

	private static Key getPressedKeyBinding(){
		if(KeyBindings.play.isPressed()){
			return Key.PLAY;
		}

		return Key.UNKNOWN;
	}

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event){

		if(getPressedKeyBinding() == Key.PLAY){

			linkGui = new LinkGui();
			playing = linkGui.isPlaying();

			Main.log.info("Is Playing: " + linkGui.isPlaying());

			Main.log.info("Is Playing(Link): " + linkGui.isPlaying);
			try{
				if(playing){
					linkGui.setPlaying(false);
					Main.log.info("Stopped Playing");
					playing = false;
				//	linkGui.urlConnection.setPriority(linkGui.urlConnection.getPriority() - 1);
					linkGui.urlConnection.interrupt();
					linkGui.urlConnection.stop();
					linkGui.urlConnection.cancel();
				}else{
					linkGui.setPlaying(true);
					Main.log.info("Started Playing");
					playing = true;
				//	linkGui.urlConnection.start();
				//	linkGui.urlConnection.setPriority(java.lang.Thread.MIN_PRIORITY);
					linkGui.urlConnection.run();
				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}

	}

	public void setPlaying(boolean state){
		this.playing = state;
	}

}