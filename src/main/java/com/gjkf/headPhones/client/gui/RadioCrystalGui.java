package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.gjkf.headPhones.Link;
import com.gjkf.headPhones.handler.LinkHandler;
import com.gjkf.headPhones.items.RadioCrystalContainer;
import com.gjkf.headPhones.utility.LogHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

//TODO https://github.com/iChun/Hats/blob/master/src/main/java/hats/client/gui/GuiHatSelection.java

public class RadioCrystalGui extends GuiScreen{

	private EntityPlayer entityPlayer;

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

	private String category = "";
	private String currentDisplay;
	private GuiTextField textField;
	private String selectedButtonName = "";
	private int favourite;

	private final int VIEW_LINKS = 0;
	private final int VIEW_COLOURIZER = 1;
	private final int VIEW_CATEGORIES = 2;
	private final int VIEW_CATEGORY = 3;

	private boolean invalidFolderName = false;
	private boolean hasClicked = false;
	private boolean justClickedButton = false;
	private boolean addingToCategory = false;
	private boolean confirmed = false;
	private boolean adding = false;
	private boolean renaming = false;
	private boolean deleting = false;

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

	public int pageNumber;

	public String prevLinkName;

	public int view;

	public Random rand;

	public static final ResourceLocation radioCrystalGui = new ResourceLocation("headphonesradio", "textures/gui/radioCrystalGui.png");

	public RadioCrystalGui(Container inv,EntityPlayer ply){
		super();
		this.entityPlayer = ply;

		ArrayList<String> list = new ArrayList<String>();

		view = VIEW_LINKS;
	}

	public void initGui(){
		super.initGui();

		if(entityPlayer == null){
			mc.displayGuiScreen(null);
		}else{
			Keyboard.enableRepeatEvents(true);
			buttonList.clear();

			this.guiLeft = (this.width - this.xSize) / 2;
			this.guiTop = (this.height - this.ySize) / 2;

			buttonList.add(new GuiButton(ID_PAGE_LEFT, width / 2 - 6, height / 2 + 54, 20, 20, "<"));
			buttonList.add(new GuiButton(ID_PAGE_RIGHT, width / 2 + 62, height / 2 + 54, 20, 20, ">"));
			buttonList.add(new GuiButton(ID_CONNECT, width / 2 - 60, height / 2 + 54, 44, 20, I18n.format("gui.connect")));
			buttonList.add(new GuiButton(ID_ADD, width / 2 + 16, height / 2 + 54, 44, 20, I18n.format("gui.add")));
			buttonList.add(new GuiButton(ID_CLOSE, width - 22, 2, 20, 20, "X"));

			pageNumber = 0;

			textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 65, height - 24, 150, 20);
			textField.setMaxStringLength(255);
			textField.setText(StatCollector.translateToLocal("headphonesradio.gui.search"));
			textField.setVisible(enabledTextField);
			textField.setTextColor(0xAAAAAA);

		}
	}
	
	@Override
	public void updateScreen(){
		textField.updateCursorCounter();
		if(textField.isFocused()){
			textField.setVisible(true);
		}else{
			textField.setVisible(enabledTextField);
		}
		if(favourite > 0){
			favourite--;
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
			LogHelper.info("Connecting to: "+ textField.getText());
		}else if(btn.id == ID_PAGE_LEFT){
			pageNumber++;
		}else if(btn.id == ID_PAGE_RIGHT){
			pageNumber--;
		}
		
	}
	
}