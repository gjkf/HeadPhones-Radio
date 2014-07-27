package com.gjkf.headPhones;

import java.util.EnumMap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import com.gjkf.headPhones.client.gui.GuiHandler;
import com.gjkf.headPhones.creativeTab.HeadPhonesCreativeTab;
import com.gjkf.headPhones.handler.ConfigurationHandler;
import com.gjkf.headPhones.items.HeadPhones;
import com.gjkf.headPhones.items.RadioCrystal;
import com.gjkf.headPhones.proxy.CommonProxy;
import com.gjkf.headPhones.reference.References;
import com.gjkf.headPhones.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = References.MODID, name = References.MOD_NAME,version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS)
public class Main {
	
	@Instance(References.MODID)
	public static Main instance;
	
	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static EnumMap<Side, FMLEmbeddedChannel> channels;
	
	public static int headPhonesId;

	public static CreativeTabs tab = new HeadPhonesCreativeTab(CreativeTabs.getNextID(), "HeadPhonesTab");
	
	public static ArmorMaterial plastic = new EnumHelper().addArmorMaterial("plastic", 100, new int[]{1,0,0,0}, 0);
	
	public static Item headPhones = new HeadPhones(plastic, headPhonesId, 0).setUnlocalizedName("headPhones")
			.setCreativeTab(tab);
	
	public static Item radioCrystal = new RadioCrystal().setUnlocalizedName("radioCrystal").setCreativeTab(tab);
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ConfigurationHandler.register();
		
		GameRegistry.registerItem(headPhones, "headPhones");
		
		GameRegistry.registerItem(radioCrystal, "radioCrystal");
		
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_11);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_13);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_cat);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_chirp);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_far);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_mall);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_mellohi);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_stal);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_strad);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_wait);
		GameRegistry.addRecipe(new ItemStack(headPhones), "xxx","xax",'x', Blocks.log, 'a', Items.record_ward);
		
		LogHelper.info("Pre Initialization Complete!!!");
	}

	@EventHandler
    public void init(FMLInitializationEvent event){
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		LogHelper.info("Initialization Complete!!!");
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event){
		
		LogHelper.info("Post Initialization Complete!!!");
	}
	
}