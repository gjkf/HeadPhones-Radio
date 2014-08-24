package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

import com.gjkf.headPhones.Main;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiGJTextField;
import com.gjkf.lib.gui.GuiScreenWidget;

public class RadioCrystalGui1 extends GuiScreenWidget{

	private GuiGJTextField insertField;
	private GuiGJTextField listField;

	private int pageNumber = 0;
	
	public int midX;
	public int midY;
	
	private String allowedChars = "abcdefghijklmnopqrstuvwxyz!/()=?.";
	public String insertFieldText;
	public ArrayList<String> listFieldText;
	public ArrayList<String> favList;
	
	public char[] allowedChar = new char[33];

	public RadioCrystalGui1(EntityPlayer ply){
		super();
	}

	@Override
	public void initGui(){

		for(int i = 0; i<allowedChars.length();i++){
			allowedChar[i] = allowedChars.charAt(i);
			//Debug	Main.log.info(allowedChar[i]);
		}
		
		midX = getMid(width);
		midY = getMid(height);
		
	}

	@Override
	public void addWidgets(){

		add(insertField = new  GuiGJTextField(midX + 10, midY - 10, 150, 15, "").setAllowedCharacters(allowedChar).setMaxStringLength(255));
		add(listField = new GuiGJTextField(midX + 10, midY + 50, 150, 120, "").setAllowedCharacters(allowedChar));

		add(new GuiGJButton(midX + 140, midY + 180, 20, 20, "<").setActionCommand("priorPage"));
		add(new GuiGJButton(midX + 270, midY + 180, 20, 20, ">").setActionCommand("nextPage"));
		add(new GuiGJButton(midX - 45, midY + 50, 44, 20, "Connect").setActionCommand("connect"));
		add(new GuiGJButton(midX - 45, midY + 100, 44, 20, "Add").setActionCommand("addLink"));
		add(new GuiGJButton(midX +350, midY + 100, 44, 20, "Delete").setActionCommand("deleteLink"));
		add(new GuiGJButton(midX + 344, midY + 50, 55, 20, "Favourite").setActionCommand("setFav"));
		add(new GuiGJButton(midX * 2 + 50, midY/2 + 30, 20, 20, "X").setActionCommand("quitGui"));

	}

	@Override
	public void actionPerformed(String ident, Object ... params){

		if(ident.equals("connect")){
			Main.log.info("Connecting to: " + listFieldText);
		}else if(ident.equals("priorPage")){
			if(pageNumber > 0){
				pageNumber--;
				Main.log.info("current page: " + pageNumber);
			}
		}else if(ident.equals("nextPage")){
			pageNumber++;
			Main.log.info("Current page: " + pageNumber);
		}else if(ident.equals("addLink")){
			if(insertFieldText != null){
				listFieldText.add(insertFieldText);
			} 
		}else if(ident.equals("deleteLink")){
		//	TODO make the currSelectedLink variable
		//	listFieldText.remove(0);
		}else if(ident.equals("setFav")){
		//	TODO make the currSelectedLink variable
		//	favList.add("Test Fav");
		}else if(ident.equals("quitGui")){
			mc.displayGuiScreen(null);
		}

	}

	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

}