package org.litnhjacuzzi.elytratoggle.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;

public class ElytraToggleConfig 
{
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CLIENT;
    
    public static ForgeConfigSpec.BooleanValue ELYTRA_ENABLED;
    
    private static void initialize() {
    	ELYTRA_ENABLED = CLIENT_BUILDER.define("elytratoggle.elytraenabled", true);
    }
    
    public static void loadConfig(String path) {
    	CommentedFileConfig file = CommentedFileConfig.builder(new File(path))
    			.sync().autosave().writingMode(WritingMode.REPLACE).build();
    	file.load();
    	CLIENT.setConfig(file);
    }
    
    public static void toggleElytra() {
    	ELYTRA_ENABLED.set(!ELYTRA_ENABLED.get());
    }
    
    static {
    	initialize();
    	CLIENT = CLIENT_BUILDER.build();
    }
}
