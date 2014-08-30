package com.gjkf.headPhones.handler;

import net.minecraft.item.ItemStack;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.client.settings.KeyBindings;
import com.gjkf.headPhones.connection.RadioManager.RadioStation;
import com.gjkf.headPhones.items.RadioCrystal;
import com.gjkf.headPhones.reference.Key;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	private static Key getPressedKeyBinding(){
		if(KeyBindings.play.isPressed()){
			return Key.PLAY;
		}
		
		return Key.UNKNOWN;
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event){
		
		if(getPressedKeyBinding() == Key.PLAY){
			Main.log.info("Started Playing");
		}
		
	}
	
}