package com.gjkf.headPhones.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.client.gui.HeadPhonesGui;
import com.gjkf.headPhones.reference.References;
import com.gjkf.headPhones.utility.LogHelper;

public class HeadPhones extends ItemArmor{

	public HeadPhonesContainer container;
	
	public HeadPhones(ArmorMaterial material, int id, int placement) {
		super(material, id, placement);

		if(placement == 0){
			this.setTextureName(References.MODID+":headPhones");
		}
	}

	@Override
	public String getArmorTexture(ItemStack  stack, Entity entity, int slot, String type){
		if(stack.getItem() == Main.headPhones){
			return References.MODID+":textures/models/armor/headPhonesArmor_1.png";
		}else{
			return null;
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if (player.isSneaking()){
			player.openGui(Main.instance, References.GUI_ID, world, (int)player.posX, (int)player.posY, (int)player.posZ);
			LogHelper.info("Succesfully opened GUI");
		}

		return stack;
	}

}