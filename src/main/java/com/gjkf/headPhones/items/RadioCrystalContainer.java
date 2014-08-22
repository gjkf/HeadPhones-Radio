package com.gjkf.headPhones.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class RadioCrystalContainer extends Container{

	private InventoryPlayer playerInv;
	public static Container crystal;
	
	public RadioCrystalContainer(Container inv1, InventoryPlayer inv2) {
		this.crystal = inv1;
		this.playerInv = inv2;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_){
		super.onContainerClosed(p_75134_1_);
		this.playerInv.closeInventory();
	}

}
