package com.gjkf.headPhones.connection;

import net.minecraft.item.ItemStack;

import com.gjkf.headPhones.items.RadioCrystal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Link{

	public static String[] linkName = new String[0];
	public ItemStack crystal;

	public Link(ItemStack radioCrystal, String[] linkName){

		crystal = radioCrystal;
		this.linkName = linkName;

		if(radioCrystal.getItem().getClass() == RadioCrystal.class){
			if(linkName != null){
				// Debug
				for(int i = 0; i<linkName.length;i++){
					System.out.println(linkName[i]);
				}
			}

		}

	}
	
	public String getLinkNameAt(int position, String[] linkName){
		
		if(linkName != null){
			return linkName[position];
		}
		
		return null;
	}

}