package com.gjkf.headPhones.client.gui;

import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import net.minecraft.entity.player.EntityPlayer;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.connection.URLConnection;
import com.gjkf.headPhones.handler.KeyInputEventHandler;
import com.gjkf.headPhones.handler.URLsHandler;
import com.gjkf.headPhones.reference.References;
import com.gjkf.lib.gui.GuiGJButton;
import com.gjkf.lib.gui.GuiGJTextField;
import com.gjkf.lib.gui.GuiScreenWidget;
import com.gjkf.lib.util.CommonUtils;

public class LinkGui extends GuiScreenWidget{

	public EntityPlayer player;

	private GuiGJTextField insertField;

	private RadioCrystalGui gui;

	public char[] allowedChar = new char[44];

// It's "static" in order to maintain the values
	public static ArrayList<String> link = new ArrayList<String>();
	
	public static ArrayList<String> read = new ArrayList<String>();
	
	private static boolean is2nd = false;
	public static boolean isPlaying = false;

	private String allowedChars = "abcdefghijklmnopqrstuvwxyz!/()=?.:1234567890";

	public int midX;
	public int midY;
	public int index;
	
	public URLsHandler handler;
	
	public static URLConnection urlConnection;
	
	public KeyInputEventHandler key;

	public LinkGui(EntityPlayer ply, int index){
		super();
		this.player = ply;
		this.index = index;

		gui = new RadioCrystalGui(player);
		if(is2nd == false){
			for(int j = 0; j < 4; j++){
				link.add(j, "--");
			}
		}

	}

	public LinkGui(){
		if(is2nd == false){
			for(int j = 0; j < 4; j++){
				link.add("--");
				read.add("--");
				Main.log.info(read);
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

		// Sets the value of the insert field
		if(!link.get(index).equals("--")){
			insertField.setText(link.get(index));
		}else{
			insertField.setText("");
		}
		
		insertField.setFocused(true);
		
		handler = new URLsHandler(/*link*/);
		
		handler.initWriter();
		handler.initReader();
		
		Main.log.info("Successfully initialized the writer and reader");
		
		key = new KeyInputEventHandler();

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

			/*
			 * This will use my URLConnection class in order to connect to the given link
			 */
			
			try{
				urlConnection = new URLConnection(insertField.getText());
				
				/*
				 * Starts the thread that connects to the given radio and plays the music
				 */
				
				urlConnection.start();
				
				setPlaying(!isPlaying());
				
				Main.log.info("Link PLaying(meth): " + this.isPlaying());
				Main.log.info("Link PLaying(var): " + this.isPlaying);
				
				key.setPlaying(isPlaying());
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			Main.log.info("Connecting to: " + insertField.getText());

		}else if(ident.equals("saveLink")){

			link.set(index, insertField.getText());
			
			String s;
			
			for(int i = 0; i < link.size();i++){
				Main.list.add(0, link.get(i));
				s = handler.readAll();
				read.set(i, s);
				Main.log.info("Read[]: " + read);
				Main.log.info("Read: " + s);
				
				handler.writeLink(link.get(index));
				
				Main.log.info(link.get(index));
				Main.log.info("Reader: " + s);
			}
			
			player.openGui(Main.instance, References.GUI_CRYSTAL_ID, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);

		}else if(ident.equals("resetLink")){

			link.set(index, "--");
			insertField.setText("");
			insertField.setFocused(true);

		}else if(ident.equals("quitGui")){

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
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	public void setPlaying(boolean state){
		this.isPlaying = state;
	}

}