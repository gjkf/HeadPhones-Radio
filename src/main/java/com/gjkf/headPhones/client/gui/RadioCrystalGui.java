package com.gjkf.headPhones.client.gui;

import java.io.File;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import com.gjkf.lib.config.ConfigFile;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiGJTextField;
import com.gjkf.lib.gui.GuiScreenWidget;
import com.gjkf.lib.util.CommonUtils;

public class RadioCrystalGui extends GuiScreenWidget{

	private GuiGJTextField insertField;
	private GuiGJTextField listField;

	private String allowedChars = "abcdefghijklmnopqrstuvwxyz!/()=?%";
	
	public static int xCenter = getXCenter();
	public static int yCenter = getYCenter();

	public RadioCrystalGui(Container inv, EntityPlayer ply){
		super(xCenter*2, yCenter*2);
	}

	@Override
	public void initGui(){

		for(int i = 0; i<allowedChars.length();i++){
			allowedChar[i] = allowedChars.charAt(i);
		}

	}

	@Override
	public void addWidgets(){

		add(insertField = new  GuiGJTextField(this.width / 2 - 75, height/2 - 60, 150, 60, ""));
		add(listField = new GuiGJTextField(this.width / 2 - 105, height/2 - 60, 150, 120, "").setAllowedCharacters(allowedChar));

		add(new GuiGJButton(width / 2 + 47, height / 2 + 54, 20, 20, ">"));
		add(new GuiGJButton(width / 2 - 21, height / 2 + 54, 20, 20, "<"));
		add(new GuiGJButton(width / 2 - 91, height / 2 + 54, 44, 20, "Connect"));
		add(new GuiGJButton(width / 2 + 1, height / 2 + 54, 44, 20, "Add"));
		add(new GuiGJButton(width / 2 + 1, height / 2 + 24, 44, 20, "Delete"));
		add(new GuiGJButton(width / 2 - 95, height / 2 + 24, 52, 20, "Favourite"));
		add(new GuiGJButton(width - 22, 2, 20, 20, "X"));


	}

	public boolean doesGuiPauseGame(){
		return false;
	}

	public char[] allowedChar;/*{

		allowedChar[0] = 'a',
		allowedChar[1] = 'b',
		allowedChar[2] = 'c',
		allowedChar[3] = 'd',
		allowedChar[4] = 'e',
		allowedChar[5] = 'f',
		allowedChar[6] = 'g',
		allowedChar[7] = 'h',
		allowedChar[8] = 'i',
		allowedChar[9] = 'j',
		allowedChar[10] = 'k',
		allowedChar[11] = 'l',
		allowedChar[12] = 'm',
		allowedChar[13] = 'n',
		allowedChar[14] = 'o',
		allowedChar[15] = 'p',
		allowedChar[16] = 'q',
		allowedChar[17] = 'r',
		allowedChar[18] = 's',
		allowedChar[19] = 't',
		allowedChar[20] = 'u',
		allowedChar[21] = 'v',
		allowedChar[22] = 'w',
		allowedChar[23] = 'x',
		allowedChar[24] = 'y',
		allowedChar[25] = 'z',
		allowedChar[26] = '!',
		allowedChar[27] = '"',
		allowedChar[28] = '/',
		allowedChar[29] = '(',
		allowedChar[30] = ')',
		allowedChar[31] = '=',
		allowedChar[32] = '?',

	}*/

}