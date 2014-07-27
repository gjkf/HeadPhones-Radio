package com.gjkf.headPhones.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gjkf.headPhones.items.HeadPhonesContainer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HeadPhonesGui extends GuiContainer{

	private static final ResourceLocation location = new ResourceLocation("headphonesradio", "textures/gui/headPhonesGui.png");
	public Container headPhones;
	private InventoryPlayer playerInv;
	private int inventoryRows;
	
	public HeadPhonesGui(Container inv1, InventoryPlayer inv2) {
		super(new HeadPhonesContainer(inv1,inv2));
		this.headPhones = inv1;
		this.playerInv = inv2;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(location);
        int k = 125;
        int l = 50;
        this.drawTexturedModalRect(k, l + 1, 0, 0, this.xSize, 256);
    }
	
}