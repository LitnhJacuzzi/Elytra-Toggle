package org.litnhjacuzzi.elytratoggle.keybind;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBind 
{
	public static KeyBinding toggleElytra;
	
	public static void register() {
		toggleElytra = new KeyBinding(new TranslationTextComponent("key.elytratoggle.toggleelytra").getString(), GLFW.GLFW_KEY_R, "key.categories.elytratoggle");
		ClientRegistry.registerKeyBinding(toggleElytra);
	}
}
