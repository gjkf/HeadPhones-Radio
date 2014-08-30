package com.gjkf.headPhones.items;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.connection.RadioManager;
import com.gjkf.headPhones.connection.RadioManager.RadioStation;
import com.gjkf.headPhones.reference.References;
import com.gjkf.lib.colour.Colours;
import com.gjkf.lib.colour.Colours.ColorMeta;
import com.gjkf.lib.helper.NBTHelper;
import com.google.common.base.Strings;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class RadioCrystal extends Item{

	private static final String TAG_HIDDEN = "Hidden";
	private static final String TAG_URL = "URL";
	
	public static boolean isPlaying;

	public RadioCrystal(){
		setMaxStackSize(1);
		setHasSubtypes(true);
		this.setTextureName(References.MODID + ":radioCrystal");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		if (player.isSneaking()) {
			player.openGui(Main.instance, References.GUI_CRYSTAL_ID, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		return itemStack;
	}

	public ItemStack createStack(String url, String name, Iterable<String> attributes) {
		boolean hidden = false;

		for (String attribute : attributes) {
			attribute = StringUtils.strip(attribute);
				attribute = attribute.toLowerCase();

				if ("hidden".equals(attribute)) hidden = true;
				else throw new IllegalArgumentException("Unknown tuned crystal attribute: " + attribute);
		}

		ItemStack result = new ItemStack(this, 1);
		NBTHelper.setString(result, TAG_URL, url);
		if (!Strings.isNullOrEmpty(name)) result.setStackDisplayName(name);
		if (hidden) NBTHelper.setBoolean(result, TAG_HIDDEN, true);
		return result;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(Item item, CreativeTabs tab, List result) {
		for (RadioStation station : RadioManager.instance.getRadioStations())
			result.add(station.getStack());
	}

	public static String getUrl(ItemStack stack){
		if (NBTHelper.getString(stack, TAG_URL) == null) return "";
		String url = NBTHelper.getString(stack, TAG_URL);
		if (Strings.isNullOrEmpty(url)) return "";
		String updatedUrl = RadioManager.instance.updateURL(url);
		if (!updatedUrl.equals(url)) NBTHelper.setString(stack, TAG_URL, updatedUrl);
		return updatedUrl;
	}
	
}