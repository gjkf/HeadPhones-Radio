package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.reference.References;
import com.gjkf.lib.colour.Colours;
import com.gjkf.lib.gui.GuiDraw;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiScreenWidget;

public class RadioCrystalGui extends GuiScreenWidget{

	public EntityPlayer player;

	public LinkGui linkGui = new LinkGui();

	public GuiGJButton btn0, btn1, btn2, btn3;

	public int midX;
	public int midY;

	public RadioCrystalGui(EntityPlayer ply){
		super();
		this.player = ply;
	}

	@Override
	public void initGui(){
		Main.log.info("LinkGui: " + linkGui.link);
		
		midX = getMid(width);
		midY = getMid(height);

	//D	Main.log.info("MidX/MidY: " + midX + " " + midY);
	//D	Main.log.info("Width/Height: " + width +" " + height);

	//D	Main.log.info("Link0: " + linkGui.getLinkAt(0));
	//D	Main.log.info("Link1: " + linkGui.getLinkAt(1));
	//D	Main.log.info("Link2: " + linkGui.getLinkAt(2));
	//D	Main.log.info("Link3: " + linkGui.getLinkAt(3));
		
		btn0 = new GuiGJButton(midX + 62, midY + 35, 44, 20, linkGui.getLinkAt(0)).setActionCommand("openLinkGui0");
		btn1 = new GuiGJButton(midX + 62, midY + 65, 44, 20, linkGui.getLinkAt(1)).setActionCommand("openLinkGui1");
		btn2 = new GuiGJButton(midX + 62, midY + 95, 44, 20, linkGui.getLinkAt(2)).setActionCommand("openLinkGui2");
		btn3 = new GuiGJButton(midX + 62, midY + 125, 44, 20, linkGui.getLinkAt(3)).setActionCommand("openLinkGui3");
		
		/*  bho
		if(is2nd){
			Main.log.info("Is2nd: " + is2nd);
			Main.log.info("Link0: " + linkGui.getLinkAt(0));
			Main.log.info("Link1: " + linkGui.getLinkAt(1));
			Main.log.info("Link2: " + linkGui.getLinkAt(2));
			Main.log.info("Link3: " + linkGui.getLinkAt(3));
			
			btn0.setText(linkGui.getLinkAt(0));
			btn1.setText(linkGui.getLinkAt(1));
			btn2.setText(linkGui.getLinkAt(2));
			btn3.setText(linkGui.getLinkAt(3));
		}
		*/

	}

	@Override
	public void addWidgets(){

		add(btn0);
		add(btn1);
		add(btn2);
		add(btn3);

	}

	@Override
	public void actionPerformed(String ident, Object ... params){
		
		if(ident.equals("openLinkGui0")){
			player.openGui(Main.instance, References.GUI_LINK_ID_0, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}else if(ident.equals("openLinkGui1")){
			player.openGui(Main.instance, References.GUI_LINK_ID_1, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}else if(ident.equals("openLinkGui2")){
			player.openGui(Main.instance, References.GUI_LINK_ID_2, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}else if(ident.equals("openLinkGui3")){
			player.openGui(Main.instance, References.GUI_LINK_ID_3, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
	}

	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

}