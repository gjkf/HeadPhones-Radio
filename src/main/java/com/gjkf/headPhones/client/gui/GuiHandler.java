package com.gjkf.headPhones.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.gjkf.headPhones.items.HeadPhonesContainer;
import com.gjkf.headPhones.items.RadioCrystalContainer;
import com.gjkf.headPhones.reference.References;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (ID == References.GUI_HEADPHONES_ID){
			return new HeadPhonesGui(HeadPhonesContainer.headPhones, player.inventory);
		}else if(ID == References.GUI_CRYSTAL_ID){
			return new RadioCrystalGui(RadioCrystalContainer.crystal, player);
		}
		return null;
	}
	
}
