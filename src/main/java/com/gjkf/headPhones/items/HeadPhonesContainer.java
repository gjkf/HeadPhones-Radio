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
		this.addSlotToContainer(new Slot(playerInv, 1, 80, 36));       

		// Player Inventory
		for(int row = 0; row < 3; row++){
			for (int column = 0; column < 9; ++column){
				this.addSlotToContainer(new Slot(playerInv, column + row * 9 + 9, 8 + column * 18, 72 + row * 18));
			}
		}

		// Player Hotbar
		for(int i = 0; i < 9; ++i){
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 130));
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