package org.litnhjacuzzi.elytratoggle.handler;

import java.lang.reflect.Field;

import org.litnhjacuzzi.elytratoggle.ElytraToggle;
import org.litnhjacuzzi.elytratoggle.config.ElytraToggleConfig;
import org.litnhjacuzzi.elytratoggle.keybind.KeyBind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElytraToggle.MODID, value = Dist.CLIENT)
public class ElytraToggleHandler 
{
	private static final int ELYTRA_MAX_DAMAGE = 432;
	
	private static Field maxDamageField;
	
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent e) {
		Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;
		if(player != null && player.input.jumping) {
			ItemStack itemStack = player.getItemBySlot(EquipmentSlotType.CHEST);
			if(itemStack.getItem() instanceof ElytraItem) {
				Item elytraItem = itemStack.getItem();
				boolean elytraEnabled = ElytraToggleConfig.ELYTRA_ENABLED.get();
				int maxDamage = itemStack.getMaxDamage();
				if(!elytraEnabled && maxDamage != 0) {
					try {
						maxDamageField.set(elytraItem, 0);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}else if(elytraEnabled && maxDamage != ELYTRA_MAX_DAMAGE) {
					try {
						maxDamageField.set(elytraItem, ELYTRA_MAX_DAMAGE);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onKeyInput(InputEvent.KeyInputEvent e) {
		Minecraft mc = Minecraft.getInstance();
		ClientPlayerEntity player = mc.player;
		if(KeyBind.toggleElytra.consumeClick()) {
			ElytraToggleConfig.toggleElytra();
			boolean elytraEnabled = ElytraToggleConfig.ELYTRA_ENABLED.get();
			if(player != null) player.displayClientMessage(getText(elytraEnabled), true);
		}
	}
	
	private static ITextComponent getText(boolean enabled) {
		return new TranslationTextComponent("toggleelytra", new TranslationTextComponent("toggle.enable." + enabled)
				.setStyle(Style.EMPTY.applyFormat(enabled ? TextFormatting.GREEN : TextFormatting.RED)));
	}
	
	static {
		try {
			maxDamageField = Item.class.getDeclaredField("field_77699_b");
			maxDamageField.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
