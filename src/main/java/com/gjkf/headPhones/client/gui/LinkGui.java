package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.reference.References;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiGJTextField;
import com.gjkf.lib.gui.GuiScreenWidget;

public class LinkGui extends GuiScreenWidget{

	public EntityPlayer player;
	
	private GuiGJTextField insertField;
	
	private RadioCrystalGui gui;
	
	public char[] allowedChar = new char[33];
	
	public ArrayList<String> link = new ArrayList<String>();
	
	private String allowedChars = "abcdefghijklmnopqrstuvwxyz!/()=?.";
	
	public int midX;
	public int midY;
	public int index;

	public LinkGui(EntityPlayer ply, int index){
		super();
		this.player = ply;
		this.index = index;
		
		gui = new RadioCrystalGui(player);
		
		for(int j = 0; j < 4; j++){
			link.add(j, "--");
		}
	}

	public LinkGui(){
		for(int j = 0; j < 4; j++){
			link.add(j, "--");
		}
	}
	
	@Override
	public void initGui(){
		for(int i = 0; i<allowedChars.length();i++){
			allowedChar[i] = allowedChars.charAt(i);
		//D	Main.log.info(allowedChar[i]);
		}
		
		midX = getMid(width);
		midY = getMid(height);
		
		Main.log.info("MidX/MidY: " + midX + " " + midY);
		Main.log.info("Width/Height: " + width +" " + height);
	}
	
	@Override
	public void addWidgets(){
		
		add(insertField = new GuiGJTextField(midX + 10, midY + 60, 150, 15, "").setAllowedCharacters(allowedChar).setMaxStringLength(100));
		add(new GuiGJButton(midX - 45, midY + 50, 44, 20, "Connect").setActionCommand("connectTo"));
		add(new GuiGJButton(midX - 45, midY + 100, 44, 20, "Save").setActionCommand("saveLink"));
		add(new GuiGJButton(midX +350, midY + 100, 44, 20, "Reset").setActionCommand("resetLink"));
		add(new GuiGJButton(midX * 2 + 50, midY/2 + 30, 20, 20, "X").setActionCommand("quitGui"));
		
	}
	
	@Override
	public void actionPerformed(String ident, Object ... params){
		if(ident.equals("connectTo")){
			Main.log.info("Connecting to: " + insertField.getText());
		}else if(ident.equals("saveLink")){
			Main.log.info(index);
			Main.log.info(link.get(index));
			link.add(index, insertField.getText());
			Main.log.info(link.get(index));
		}else if(ident.equals("resetLink")){
			
		}else if(ident.equals("quitGui")){
			Main.log.info(gui.is2nd);
			gui.set2nd(true);
			Main.log.info(gui.is2nd);
			gui.initGui();
			player.openGui(Main.instance, References.GUI_CRYSTAL_ID, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

}