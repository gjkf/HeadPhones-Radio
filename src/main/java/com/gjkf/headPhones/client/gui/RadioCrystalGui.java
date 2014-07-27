package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.gjkf.headPhones.handler.LinkHandler;
import com.gjkf.headPhones.items.RadioCrystalContainer;

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

	private final int ID_CATEGORIES_START = 30;

	private String category = "";
	private String currentDisplay;
	private GuiTextField textField;
	private String selectedButtonName = "";
	private int favourite;

	private final int VIEW_COLOURIZER = 1;
	private final int VIEW_CATEGORIES = 2;
	private final int VIEW_CATEGORY = 3;

	private boolean hasClicked = false;
	private boolean justClickedButton = false;
	private boolean confirmed = false;
	private boolean adding = false;
	private boolean renaming = false;

	private boolean enabledTextField = false;

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
	public int colourR;
	public int colourG;
	public int colourB;
	public int alpha;

	public String prevLinkName;
	public int prevcolourR;
	public int prevcolourG;
	public int prevcolourB;
	public int prevalpha;

	public int view;

	public Random rand;

	public static final ResourceLocation radioCrystalGui = new ResourceLocation("headphonesradio", "textures/gui/radioCrystalGui.png");

	public RadioCrystalGui(Container inv,EntityPlayer ply){
		super();
		this.entityPlayer = ply;

		ArrayList<String> list = new ArrayList<String>();


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
			buttonList.add(new GuiButton(ID_CONNECT, width / 2 + 16, height / 2 + 54, 44, 20, I18n.format("gui.connect")));

			addToolButton(ID_NONE);
			addToolButton(ID_FAVOURITE);
			addToolButton(ID_CATEGORIES);

			buttonList.add(new GuiButton(ID_CLOSE, width - 22, 2, 20, 20, "X"));

			pageNumber = 0;

			textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 65, height - 24, 150, 20);
			textField.setMaxStringLength(255);
			textField.setText(StatCollector.translateToLocal("headphonesradio.gui.search"));
			textField.setVisible(enabledTextField);
			textField.setTextColor(0xAAAAAA);

		}
	}

	public void addToolButton(int id){
		boolean enabled = false;
		for(int i = 0; i < enabledButtons.size(); i++){
			if(enabledButtons.get(i).equalsIgnoreCase(Integer.toString(id - (ID_NONE - 1)))){
				buttonList.add(new GuiButton(id, width / 2 + 89, height / 2 - 85 + (i * 21), 20, 20, ""));
				enabled = true;
				break;
			}
		}
		if(!enabled){
			GuiButton btn = new GuiButton(id, width - 24, height / 2 - 93 + ((id - 8) * 21), 20, 20, "");
			btn.visible = false;
			buttonList.add(btn);
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

	@Override
	protected void keyTyped(char c, int i){
		textField.textboxKeyTyped(c, i);
		if(textField.isFocused()){
			onSearch();
		}
		if (i == 1){
			if(textField.isFocused()){
				textField.setText("");
				textField.setFocused(false);
				ontextFieldInteract();
			}else{
				exitWithoutUpdate();

				this.mc.setIngameFocus();
			}
		}
		if(!textField.isFocused()){
			GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
			if(i == Keyboard.KEY_R){
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				randomize();
			}
			else if(i == Keyboard.KEY_TAB || i == gameSettings.keyBindChat.getKeyCode())
			{
				textField.setFocused(true);
				ontextFieldInteract();
			}else if(i == Keyboard.KEY_H && !(hat.hatName.equalsIgnoreCase("") && view == VIEW_HATS)){
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				toggleHatsColourizer();
			}else if(i == Keyboard.KEY_N && !hat.hatName.equalsIgnoreCase("")){
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				removeHat();
			}else if(i == Keyboard.KEY_C){
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				showCategories();
			}else if(i == Keyboard.KEY_F){
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				if((view == VIEW_HATS || view == VIEW_CATEGORY) && isShiftKeyDown()){
					String s = "";

					for(String hat1 : availableLinks){
						if(hat1.toLowerCase().equalsIgnoreCase(hat.hatName)){
							s = hat1;
						}
					}

					if(!s.equalsIgnoreCase("")){
						if(LinkHandler.isInFavourites(s)){
							LinkHandler.removeFromCategory(s, "Favourites");
						}else{
							LinkHandler.addToCategory(s, "Favourites");
						}

						if(view == VIEW_CATEGORY && category.equalsIgnoreCase("Favourites")){
							showCategory("Favourites");
						}
						favourite = 6;
					}
				}else{
					showCategory("Favourites");
				}
			}else if(i == Keyboard.KEY_P){
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				personalize();
			}
			if(view == VIEW_HATS){
				if((i == gameSettings.keyBindLeft.getKeyCode() || i == Keyboard.KEY_LEFT) && pageNumber > 0){
					mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
					switchPage(true);
				}else if((i == gameSettings.keyBindRight.getKeyCode() || i == Keyboard.KEY_RIGHT) && !((pageNumber + 1) * 6 >= linksToShow.size())){
					mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
					switchPage(false);
				}
			}
		}else{
			if(i == Keyboard.KEY_RETURN && (adding || renaming) && view == VIEW_CATEGORIES && !invalidFolderName){
				if(adding && addCategory(textField.getText().trim()) || renaming && renameCategory(selectedButtonName, textField.getText().trim())){
					textField.setText("");
					ontextFieldInteract();

					updateButtonList();
				}
			}
		}
	}

	public void onSearch(){
		if(adding || renaming){
			invalidFolderName = false;
			textField.setTextColor(14737632);
			for(String s : categories){
				if(s.equalsIgnoreCase(textField.getText())){
					textField.setTextColor(0xFF5555);
					invalidFolderName = true;
				}
			}
			for(String s : invalidChars){
				if(textField.getText().contains(s)){
					textField.setTextColor(0xFF5555);
					invalidFolderName = true;
				}
			}
			if(textField.getText().equalsIgnoreCase("Favourites") || textField.getText().equalsIgnoreCase(StatCollector.translateToLocal("hats.gui.allHats")) || textField.getText().equalsIgnoreCase(StatCollector.translateToLocal("hats.gui.addNew"))){
				textField.setTextColor(0xFF5555);
				invalidFolderName = true;
			}
			if(textField.getText().equalsIgnoreCase("")){
				invalidFolderName = true;    			
			}
			for(int k = 0; k < buttonList.size(); k++){
				GuiButton btn = (GuiButton)buttonList.get(k);
				if(btn.id == ID_ADD){
					btn.enabled = !invalidFolderName;
					break;
				}
			}
		}else{
			if(textField.getText().equalsIgnoreCase("") || !hasClicked && textField.getText().equalsIgnoreCase(StatCollector.translateToLocal("hats.gui.search"))){
				textField.setTextColor(14737632);
				linksToShow = new ArrayList<String>(view == VIEW_HATS ? availableLinks : view == VIEW_CATEGORY ? categoryLink : categories);
				Collections.sort(linksToShow);
				if(view == VIEW_CATEGORIES){
					linksToShow.add(0, StatCollector.translateToLocal("hats.gui.allHats"));
					linksToShow.add(StatCollector.translateToLocal("hats.gui.addNew"));
				}
			}else{
				String query = textField.getText();
				ArrayList<String> matches = new ArrayList<String>();
				for(String s : (view == VIEW_HATS ? availableLinks : view == VIEW_CATEGORY ? categoryLink : categories)){
					if(view == VIEW_CATEGORIES && (s.equalsIgnoreCase(StatCollector.translateToLocal("hats.gui.allHats")) || s.equalsIgnoreCase(StatCollector.translateToLocal("hats.gui.addNew")))){
						continue;
					}
					if(s.toLowerCase().startsWith(query.toLowerCase())){
						if(!matches.contains(s)){
							matches.add(s);
						}
					}else{
						String[] split = s.split(" ");
						for(String s1 : split){
							if(s1.toLowerCase().startsWith(query.toLowerCase())){
								if(!matches.contains(s)){
									matches.add(s);
								}
								break;
							}
						}
					}
				}
				if(matches.size() == 0){
					textField.setTextColor(0xFF5555);
					linksToShow = new ArrayList<String>(view == VIEW_HATS ? availableLinks : view == VIEW_CATEGORY ? categoryLink : categories);
					Collections.sort(linksToShow);
					if(view == VIEW_CATEGORIES){
						linksToShow.add(0, StatCollector.translateToLocal("hats.gui.allHats"));
						linksToShow.add(StatCollector.translateToLocal("hats.gui.addNew"));
					}
				}else{
					textField.setTextColor(14737632);
					pageNumber = 0;
					linksToShow = new ArrayList<String>(matches);
					Collections.sort(linksToShow);
				}
			}

			updateButtonList();
		}
	}
}