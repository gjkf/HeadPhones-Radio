package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.gjkf.headPhones.Link;
import com.gjkf.headPhones.items.RadioCrystalContainer;
import com.gjkf.headPhones.utility.LogHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class RadioCrystalGui extends GuiScreen{

	private final int ID_PAGE_LEFT = 1;
	private final int ID_PAGE_RIGHT = 2;
	private final int ID_CONNECT = 3;
	private final int ID_CONFIRM = 4;
	private final int ID_ADD = 5;
	private final int ID_CANCEL = 6;
	private final int ID_RENAME = 7;
	private final int ID_DELETE = 8;
	private final int ID_FAVOURITE = 9;
	private final int ID_SEARCH = 10;
	private final int ID_NONE = 11;
	private final int ID_CATEGORIES = 12;
	private final int ID_CLOSE = 13;
	private final int ID_FAVOURITES = 14;

	private final int ID_CATEGORIES_START = 30;

	private GuiTextField textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 75, height/2- 60, 150, 60);
	private GuiTextField insertField = new GuiTextField(this.fontRendererObj, this.width / 2 - 75, height/2- 80, 150, 10);
	public String textFieldText = "";
	public String insertFieldText = "";

	private boolean confirmed = false;
	private boolean adding = false;
	private boolean renaming = false;
	private boolean deleting = false;
	private boolean hasClicked = false;

	private boolean enabledTextField = false;

	private static final String[] invalidChars = new String[] { "\\", "*", "?", "\"", "<", ">", "|", "!"};

	public List<String> availableLinks;
	public List<String> linksToShow;
	public List<String> categories;
	public List<String> categoryLink = new ArrayList<String>();
	public List<String> enabledButtons = new ArrayList<String>();

	public EntityPlayer player;
	public Link link;

	protected int xSize = 256;
	protected int ySize = 100;

	protected int guiLeft;
	protected int guiTop;

	public int pageNumber = 0;

	public RadioCrystalGui(Container inv, EntityPlayer ply){
		super();
		this.player = ply;
	}

	public void initGui(){
		super.initGui();

		if(player == null){
			mc.displayGuiScreen(null);
		}else{
			Keyboard.enableRepeatEvents(true);
			buttonList.clear();

			this.guiLeft = (this.width - this.xSize) / 2;
			this.guiTop = (this.height - this.ySize) / 2;

			buttonList.add(new GuiButton(ID_PAGE_LEFT, width / 2 + 47, height / 2 + 54, 20, 20, ">"));
			buttonList.add(new GuiButton(ID_PAGE_RIGHT, width / 2 - 21, height / 2 + 54, 20, 20, "<"));
			buttonList.add(new GuiButton(ID_CONNECT, width / 2 - 91, height / 2 + 54, 44, 20,"Connect"));
			buttonList.add(new GuiButton(ID_ADD, width / 2 + 1, height / 2 + 54, 44, 20, "Add"));
			buttonList.add(new GuiButton(ID_DELETE, width / 2 + 1, height / 2 + 24, 44, 20, "Delete"));
			buttonList.add(new GuiButton(ID_FAVOURITE, width / 2 - 95, height / 2 + 24, 52, 20, "Favourite"));
			buttonList.add(new GuiButton(ID_CLOSE, width - 22, 2, 20, 20, "X"));
		}
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_){

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		initGui();
		textField.drawTextBox();
		textField.setMaxStringLength(255);
		textField.setEnableBackgroundDrawing(true);
		textField.setVisible(true);
		textField.setTextColor(0xFFFFFF);

		insertField.drawTextBox();
		insertField.setMaxStringLength(255);
		insertField.setVisible(true);
		insertField.setEnableBackgroundDrawing(true);
		insertField.setTextColor(0xFFFFFF);
		insertField.setEnabled(true);

	}

	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

	@Override
	public void updateScreen(){
		if(textField.isFocused()){
			textField.setFocused(true);
			textField.setVisible(true);
			insertField.setFocused(true);
			insertField.setVisible(true);
		}else{
			insertField.setFocused(enabledTextField);
			insertField.setVisible(enabledTextField);
			textField.setFocused(enabledTextField);
			textField.setVisible(enabledTextField);
		}
	}

	public void exitAndUpdate(){
		confirmed = true;
		mc.displayGuiScreen(null);
	}

	@Override
	protected void actionPerformed(GuiButton btn){

		if(btn.id == ID_CLOSE){
			exitAndUpdate();
		}else if(btn.id == ID_CONNECT){
			LogHelper.info("Connecting to: "+ textFieldText);
		}else if(btn.id == ID_PAGE_LEFT){
			pageNumber++;
			// Debug
			System.err.println(pageNumber);
		}else if(btn.id == ID_PAGE_RIGHT){
			if(pageNumber>0){				
				pageNumber--;
				// Debug
				System.err.println(pageNumber);
			}
		}else if(btn.id == ID_ADD){
			textFieldText = "Hope it works";
			textField.setCursorPosition(0);
			textField.writeText(textFieldText);
			textField.setText(textFieldText);
		}

	}

	@Override
	protected void keyTyped(char c, int i){
		if (i == 1){
			if(insertField.isFocused()){
				insertField.textboxKeyTyped(c, i);
				insertField.setText(textField.getText()+c);
				onInsertFieldInteract();
			}else{
				insertField.setFocused(true);
			}
		}
	}

	public void onInsertFieldInteract(){
		if(insertField.isFocused()){
			insertField.setTextColor(14737632);
			if(!hasClicked){
				hasClicked = true;
			}
		}else{
			insertField.setTextColor(0xAAAAAA);
			if(insertField.getText().equalsIgnoreCase("")){
				hasClicked = false;
				if(!adding && !renaming){
					insertField.setText("");
				}
			}
		}    	
	}
}