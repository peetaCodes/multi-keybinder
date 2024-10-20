package com.peeta.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class MultiKeyBinder implements ModInitializer {
	private static KeyBinding walkForwardCombo;

	@Override
	public void onInitialize() {
		walkForwardCombo = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.custom.walk_forward_combo", // Nome della combinazione di tasti
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_W, // Tasto principale "W"
				"category.custom.controls" // Categoria della combinazione
		));

		// Evento per catturare la combinazione di tasti
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (walkForwardCombo.isPressed() && InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)
					&& InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_CONTROL)) {
				client.player.setSprinting(true);
				client.player.input.movementForward = 1.0F;
			}
		});
	}
}
