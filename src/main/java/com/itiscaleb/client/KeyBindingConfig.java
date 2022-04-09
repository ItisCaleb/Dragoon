package com.itiscaleb.client;

import com.itiscaleb.Dragoon;
import com.itiscaleb.client.gui.DragoonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBindingConfig {
    public static KeyBinding abilityMenu = new KeyBinding("Open Ability Menu",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "key.category."+ Dragoon.MODID);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (abilityMenu.isPressed()) {
            Minecraft.getInstance().displayGuiScreen(new DragoonConfig());
        }
    }
}
