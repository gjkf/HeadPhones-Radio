package com.gjkf.headPhones.items;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.reference.References;
import com.gjkf.headPhones.utility.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RadioCrystal extends Item{

	public RadioCrystal(){
		setMaxStackSize(1);
		setHasSubtypes(true);
		this.setTextureName(References.MODID+":radioCrystal");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
			if (player.isSneaking()) {
				player.openGui(Main.instance, References.GUI_CRYSTAL_ID, world, (int)player.posX, (int)player.posY, (int)player.posZ);
				Main.log.info("Succesfully opened GUI");
			}
		return itemStack;
	}

}