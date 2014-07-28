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
		
		// Player Inventory
		for(int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex){
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex){
				this.addSlotToContainer(new Slot(playerInv, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 72 + inventoryRowIndex * 18));
			}
		}

		// Player Hotbar
		for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex){
			this.addSlotToContainer(new Slot(playerInv, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 130));
		}
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
