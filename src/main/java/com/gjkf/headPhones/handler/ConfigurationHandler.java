package com.gjkf.headPhones.handler;

import java.io.File;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.reference.References;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static Configuration config;

	public static boolean randomSpawn = true;
	public static boolean shouldBeSpawnedInDungeons = true;
	public static boolean shouldBeSpawnedInMineshafts = true;
	public static boolean shouldBeSpawnedInPyramids = true;
	public static boolean shouldBeSpawnedInJungleChest = true;
	public static boolean shouldBeSpawnedInJungleDispenser = true;
	public static boolean shouldBeSpawnedInStrongholdCorridor = true;
	public static boolean shouldBeSpawnedInStrongholdCrossing = true;
	public static boolean shouldBeSpawnedInStrongholdLibrary = true;
	public static boolean shouldBeSpawnedInVillageBlacksmith = true;

	public static void init(File configFile){

		if(config == null){
			config = new Configuration(configFile);
			loadConfigurations();
		}

	}

	private static void loadConfigurations(){

		randomSpawn = config.getBoolean("radnomSpawn", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in loot chests?");

		shouldBeSpawnedInDungeons = config.getBoolean("shouldBeSpawnedInDungeons", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in dungeon's chests?");

		shouldBeSpawnedInMineshafts = config.getBoolean("shouldBeSpawnedInMineshafts", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in mineshaft's chests?");

		shouldBeSpawnedInPyramids = config.getBoolean("shouldBeSpawnedInPyramids", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in pyramid's chests?");

		shouldBeSpawnedInJungleChest = config.getBoolean("shouldBeSpawnedInJungleChest", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in jungle temple's chests?");

		shouldBeSpawnedInJungleDispenser = config.getBoolean("shouldBeSpawnedInJungleDispenser", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in jungle temple's dispensers?");

		shouldBeSpawnedInStrongholdCorridor = config.getBoolean("shouldBeSpawnedInStrongholdCorridors", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in stronghold's corridor's chests?");

		shouldBeSpawnedInStrongholdCrossing = config.getBoolean("shouldBeSpawnedInStrongholdCrossing", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in stronghold's crossing's chests?");

		shouldBeSpawnedInStrongholdLibrary = config.getBoolean("shouldBeSpawnedInStrongholdLibrary", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in stronghold's library's chests?");

		shouldBeSpawnedInVillageBlacksmith = config.getBoolean("shouldBeSpawnedInVillageBlacksmith", Configuration.CATEGORY_GENERAL, true,
				"Should the Radio Crystals be randomly generated in village's blacksmith's chest?");

		if(config.hasChanged()){
			config.save();
		}
	}

	@SubscribeEvent
	public void onConfigrationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){

		if(event.modID.equalsIgnoreCase(References.MODID)){
			loadConfigurations();
		}

	}

	public static void register(){
		if(randomSpawn){
			WeightedRandomChestContent drop = new WeightedRandomChestContent(new ItemStack(Main.radioCrystal), 1, 1000, 16);
			if(shouldBeSpawnedInDungeons){
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(drop);
			}
			if(shouldBeSpawnedInMineshafts){
				ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(drop);
			}
			if(shouldBeSpawnedInPyramids){
				ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(drop);
			}
			if(shouldBeSpawnedInJungleChest){
				ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(drop);
			}
			if(shouldBeSpawnedInJungleDispenser){
				ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(drop);
			}
			if(shouldBeSpawnedInStrongholdCorridor){
				ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(drop);
			}
			if(shouldBeSpawnedInStrongholdCrossing){
				ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(drop);
			}
			if(shouldBeSpawnedInStrongholdLibrary){
				ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(drop);
			}
			if(shouldBeSpawnedInVillageBlacksmith){
				ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(drop);
			}
		}else{
			shouldBeSpawnedInDungeons = false;
			shouldBeSpawnedInMineshafts = false;
			shouldBeSpawnedInPyramids = false;
			shouldBeSpawnedInJungleChest = false;
			shouldBeSpawnedInJungleDispenser = false;
			shouldBeSpawnedInStrongholdCorridor = false;
			shouldBeSpawnedInStrongholdCrossing = false;
			shouldBeSpawnedInStrongholdLibrary = false;
			shouldBeSpawnedInVillageBlacksmith = false;
		}
	}

}