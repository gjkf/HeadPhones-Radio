package com.gjkf.headPhones.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.gjkf.headPhones.items.HeadPhonesContainer;
import com.gjkf.headPhones.reference.References;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (ID == References.GUI_ID){
			return new HeadPhonesGui(HeadPhonesContainer.headPhones, player.inventory);
		}
		return null;
	}
	
}
