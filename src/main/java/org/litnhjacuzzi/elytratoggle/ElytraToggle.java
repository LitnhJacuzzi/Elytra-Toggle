package org.litnhjacuzzi.elytratoggle;

import org.litnhjacuzzi.elytratoggle.config.ElytraToggleConfig;
import org.litnhjacuzzi.elytratoggle.keybind.KeyBind;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("elytratoggle")
public class ElytraToggle {
	public static final String MODID = "elytratoggle";
	
	public ElytraToggle() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ElytraToggleConfig.CLIENT);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		
		ElytraToggleConfig.loadConfig(FMLPaths.CONFIGDIR.get().resolve("elytratoggle-client.toml").toString());
	}
	
	private void doClientStuff(FMLClientSetupEvent event) {
		KeyBind.register();
	}
}
