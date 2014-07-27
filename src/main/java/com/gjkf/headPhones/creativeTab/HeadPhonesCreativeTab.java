package com.gjkf.headPhones.creativeTab;

import com.gjkf.headPhones.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class HeadPhonesCreativeTab extends CreativeTabs{

	public HeadPhonesCreativeTab(int p_i1853_1_, String p_i1853_2_) {
		super(p_i1853_1_, p_i1853_2_);
	}

	@Override
	public Item getTabIconItem(){
		return Main.headPhones;
	}
	
}
