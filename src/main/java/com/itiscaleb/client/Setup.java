package com.itiscaleb.client;

import com.itiscaleb.Dragoon;
import com.itiscaleb.client.model.LanceModel;
import com.itiscaleb.client.renderer.LancePlayerRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class Setup {

    public static LancePlayerRenderer renderer;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e){
        ClientRegistry.registerKeyBinding(KeyBindingConfig.abilityMenu);
        renderer = new LancePlayerRenderer(Minecraft.getInstance().getRenderManager());
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent e){
        ModelLoader.addSpecialModel(new ModelResourceLocation(Dragoon.MODID+":wooden_lance_hand","inventory"));
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent e){
        ModelResourceLocation inv = new ModelResourceLocation(Dragoon.MODID+":wooden_lance","inventory");
        IBakedModel invModel = e.getModelRegistry().get(inv);
        ModelResourceLocation hand = new ModelResourceLocation(Dragoon.MODID+":wooden_lance_hand","inventory");
        IBakedModel handModel = e.getModelRegistry().get(hand);
        e.getModelRegistry().put(inv, new LanceModel(invModel,handModel));
    }

}
