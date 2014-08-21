package com.gjkf.headPhones.proxy;

import com.gjkf.headPhones.client.settings.KeyBindings;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(KeyBindings.play);
	}

}