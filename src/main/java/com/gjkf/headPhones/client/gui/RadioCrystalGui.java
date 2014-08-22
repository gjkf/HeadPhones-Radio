package com.gjkf.headPhones.client.gui;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;

import com.gjkf.headPhones.Main;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiGJTextField;
import com.gjkf.lib.gui.GuiScreenWidget;

public class RadioCrystalGui extends GuiScreenWidget{

	private GuiGJTextField insertField;
	private GuiGJTextField listField;

	private int pageNumber = 0;

	private String allowedChars = "abcdefghijklmnopqrstuvwxyz!/()=?.";

	public String insertFieldText;
	public ArrayList<String> listFieldText;
	public ArrayList<String> favList;

	public char[] allowedChar = new char[33];

	public static int minX = getMinX();
	public static int minY = getMinY();

	public RadioCrystalGui(EntityPlayer ply){
		super(minX, minY);
	}

	@Override
	public void initGui(){

		for(int i = 0; i<allowedChars.length();i++){
			allowedChar[i] = allowedChars.charAt(i);
			//Debug	Main.log.info(allowedChar[i]);
		}

	}

	@Override
	public void addWidgets(){

		add(insertField = new  GuiGJTextField(minX + 140, minY + 10, 150, 15, "").setAllowedCharacters(allowedChar).setMaxStringLength(255));
		add(listField = new GuiGJTextField(minX + 140, minY + 50, 150, 120, "").setAllowedCharacters(allowedChar));

		add(new GuiGJButton(minX + 140, minY + 180, 20, 20, "<").setActionCommand("priorPage"));
		add(new GuiGJButton(minX + 270, minY + 180, 20, 20, ">").setActionCommand("nextPage"));
		add(new GuiGJButton(minX + 45, minY + 50, 44, 20, "Connect").setActionCommand("connect"));
		add(new GuiGJButton(minX + 45, minY + 100, 44, 20, "Add").setActionCommand("addLink"));
		add(new GuiGJButton(minX + 350, minY + 100, 44, 20, "Delete").setActionCommand("deleteLink"));
		add(new GuiGJButton(minX + 344, minY + 50, 55, 20, "Favourite").setActionCommand("setFav"));
		add(new GuiGJButton(minX + 400, minY + 5, 20, 20, "X").setActionCommand("quitGui"));


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
			//TODO make the currSelectedLink variable
		//	listFieldText.remove(0);
		}else if(ident.equals("setFav")){
			//TODO make the currSelectedLink variable
		//	favList.add("Test Fav");
		//	Main.log.info(favList);
		}else if(ident.equals("quitGui")){
			mc.displayGuiScreen(null);
		}

	}

	public boolean doesGuiPauseGame(){
		return false;
	}

}