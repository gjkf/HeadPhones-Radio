package com.gjkf.headPhones.items;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.client.gui.RadioCrystalGui;
import com.gjkf.headPhones.reference.References;
import com.gjkf.lib.gui.GuiScreenWidget;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RadioCrystal extends Item{

	public static boolean isPlaying;

	public RadioCrystal(){
		setMaxStackSize(1);
		setHasSubtypes(true);
		this.setTextureName(References.MODID+":radioCrystal");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		if (player.isSneaking()) {
			player.openGui(Main.instance, References.GUI_CRYSTAL_ID, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		return itemStack;
	}

}