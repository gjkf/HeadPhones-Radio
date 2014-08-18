package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.gjkf.headPhones.connection.Link;
import com.gjkf.headPhones.items.RadioCrystalContainer;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiGJTextField;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class RadioCrystalGui1 extends GuiScreen{

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

	private GuiGJTextField textField;
	private GuiGJTextField insertField;
	
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

	public RadioCrystalGui1(Container inv, EntityPlayer ply){
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

			buttonList.add(new GuiGJButton(width / 2 + 47, height / 2 + 54, 20, 20, ">"));
			buttonList.add(new GuiGJButton(width / 2 - 21, height / 2 + 54, 20, 20, "<"));
			buttonList.add(new GuiGJButton(width / 2 - 91, height / 2 + 54, 44, 20,"Connect"));
			buttonList.add(new GuiGJButton(width / 2 + 1, height / 2 + 54, 44, 20, "Add"));
			buttonList.add(new GuiGJButton(width / 2 + 1, height / 2 + 24, 44, 20, "Delete"));
			buttonList.add(new GuiGJButton(width / 2 - 95, height / 2 + 24, 52, 20, "Favourite"));
			buttonList.add(new GuiGJButton(width - 22, 2, 20, 20, "X"));

			textField  = new GuiGJTextField(this.width / 2 - 75, height/2 - 60, 150, 60, "");
			insertField  = new GuiGJTextField(this.width / 2 - 75, height/2 - 60, 150, 60, "");
		}
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_){
		textField.drawBackground();
		textField.drawText();
		insertField.drawBackground();
		insertField.drawText();
	}

	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
}