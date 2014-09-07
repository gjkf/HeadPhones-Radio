package com.gjkf.headPhones.handler;

import net.minecraft.item.ItemStack;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.client.gui.LinkGui;
import com.gjkf.headPhones.client.settings.KeyBindings;
import com.gjkf.headPhones.items.RadioCrystal;
import com.gjkf.headPhones.reference.Key;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	public LinkGui linkGui = new LinkGui(); 
	
	public boolean playing = linkGui.isPlaying();
	
	private static Key getPressedKeyBinding(){
		if(KeyBindings.play.isPressed()){
			return Key.PLAY;
		}
		
		return Key.UNKNOWN;
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event){
		
		if(getPressedKeyBinding() == Key.PLAY){
			
			Main.log.info("Is Playing: " + linkGui.isPlaying());

			
			if(linkGui.isPlaying()){
				Main.log.info("Stopped Playing");
				linkGui.urlConnection.interrupt();
				linkGui.urlConnection.stop();
				playing = false;
			}else if(!linkGui.isPlaying()){
				Main.log.info("Started Playing");
				linkGui.urlConnection.start();
				playing = true;
			}
			
		}
		
	}
	
	public void setPlaying(boolean state){
		this.playing = state;
	}
	
}