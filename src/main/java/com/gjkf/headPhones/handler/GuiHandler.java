package com.gjkf.headPhones.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.gjkf.headPhones.client.gui.HeadPhonesGui;
import com.gjkf.headPhones.client.gui.LinkGui;
import com.gjkf.headPhones.client.gui.RadioCrystalGui;
import com.gjkf.headPhones.items.HeadPhonesContainer;
import com.gjkf.headPhones.items.RadioCrystalContainer;
import com.gjkf.headPhones.reference.References;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == References.GUI_HEADPHONES_ID){
			return new HeadPhonesContainer(HeadPhonesContainer.headPhones, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (ID == References.GUI_HEADPHONES_ID){
			return new HeadPhonesGui(HeadPhonesContainer.headPhones, player.inventory);
		}else if(ID == References.GUI_CRYSTAL_ID){
			return new RadioCrystalGui(player);
		}else if(ID == References.GUI_LINK_ID_0){
			return new LinkGui(player, 0);
		}else if(ID == References.GUI_LINK_ID_1){
			return new LinkGui(player, 1);
		}else if(ID == References.GUI_LINK_ID_2){
			return new LinkGui(player, 2);
		}else if(ID == References.GUI_LINK_ID_3){
			return new LinkGui(player, 3);
		}
		return null;
	}
	
}