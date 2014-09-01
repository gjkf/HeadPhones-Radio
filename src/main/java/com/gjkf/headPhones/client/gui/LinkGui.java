package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import net.minecraft.entity.player.EntityPlayer;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.handler.URLsHandler;
import com.gjkf.headPhones.reference.References;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiGJTextField;
import com.gjkf.lib.gui.GuiScreenWidget;

public class LinkGui extends GuiScreenWidget{

	public EntityPlayer player;

	private GuiGJTextField insertField;

	private RadioCrystalGui gui;

	public char[] allowedChar = new char[44];

	// static in order to maintain the values during the update process
	public static ArrayList<String> link = new ArrayList<String>();
	
	private static boolean is2nd = false;

	private String allowedChars = "abcdefghijklmnopqrstuvwxyz!/()=?.:1234567890";

	public int midX;
	public int midY;
	public int index;
	
	public URLsHandler handler;

	public LinkGui(EntityPlayer ply, int index){
		super();
		this.player = ply;
		this.index = index;

		gui = new RadioCrystalGui(player);
		if ( is2nd == false ){
			for(int j = 0; j < 4; j++){
				link.add(j, "--");
			}
		}

	}

	public LinkGui(){
		if (is2nd == false){
			for(int j = 0; j < 4; j++){
				//	link.set(j, "--");
				link.add("--");
			}
			is2nd = true;
		}

	}

	@Override
	public void initGui(){

		for(int i = 0; i<allowedChars.length();i++){
			allowedChar[i] = allowedChars.charAt(i);
		}

		midX = getMid(width);
		midY = getMid(height);
		
		insertField = new GuiGJTextField(midX + 10, midY + 60, 150, 15, "").setAllowedCharacters(allowedChar).setMaxStringLength(100);

		// sets the value of the insert field
		if(!link.get(index).equals("--")){
			insertField.setText(link.get(index));
		}else{
			insertField.setText("");
		}
		
		insertField.setFocused(true);
		
		handler = new URLsHandler(link);
		
		Main.log.info("UrlsHandler(urls)(save): " + URLsHandler.urls);

		//D	Main.log.info("MidX/MidY: " + midX + " " + midY);
		//D	Main.log.info("Width/Height: " + width +" " + height);
	}

	@Override
	public void addWidgets(){

		add(insertField);
		add(new GuiGJButton(midX - 45, midY +  50, 44, 20, "Connect").setActionCommand("connectTo"));
		add(new GuiGJButton(midX - 45, midY + 100, 44, 20, "Save").setActionCommand("saveLink"));
		add(new GuiGJButton(midX + 35, midY + 100, 44, 20, "Reset").setActionCommand("resetLink"));
		add(new GuiGJButton(midX * 2 + 50, midY/2 + 30, 20, 20, "X").setActionCommand("quitGui"));

	}

	@Override
	public void actionPerformed(String ident, Object ... params){
		if(ident.equals("connectTo")){

			Main.log.info("Connecting to: " + insertField.getText());

		}else if(ident.equals("saveLink")){

			//D	Main.log.info(index);
	//D		Main.log.info("LinkAt (Before): " + index + " " + link.get(index));

			link.set(index, insertField.getText());

	//D		Main.log.info("LinkAt (After): " + link.get(index));
	//D		Main.log.info("LinkGui: " + link);

			for(int i = 0; i < link.size();i++){
				Main.list.add(0, link.get(i));
				
				Main.log.info(Main.list);
			}
			
			handler.writeLink(link.get(index) + "\n");
			
			player.openGui(Main.instance, References.GUI_CRYSTAL_ID, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);

		}else if(ident.equals("resetLink")){

			link.set(index, "--");
			insertField.setText("");
			insertField.setFocused(true);

		}else if(ident.equals("quitGui")){

	//D		Main.log.info("LinkAt (Before): " + link.get(index));
	//D		Main.log.info("IF (Before): " + insertField.getText());

	//D		Main.log.info("IF (After): " + insertField.getText());
	//D		Main.log.info("LinkAt (After): " + link.get(index));

			player.openGui(Main.instance, References.GUI_CRYSTAL_ID, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);

		}
	}

	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

	public String getLinkAt(int index){
		return link.get(index);
	}

}