package com.gjkf.headPhones.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class HeadPhonesContainer extends Container{

	private InventoryPlayer playerInv;
	public static Container headPhones;
	private int numSlots = 1;

	public HeadPhonesContainer(Container inv1, InventoryPlayer inv2){
		this.headPhones = inv1;
		this.playerInv = inv2;
		
		// My item
		this.addSlotToContainer(new Slot(inv2, 1, 80, 36));       
		
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
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_){
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player){
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_){
		super.onContainerClosed(p_75134_1_);
		this.playerInv.closeInventory();
	}
	
}