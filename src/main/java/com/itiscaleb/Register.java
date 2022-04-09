package com.itiscaleb;

import com.itiscaleb.capability.dragoon.DragoonAbility;
import com.itiscaleb.capability.dragoon.DragoonAbilityProvider;
import com.itiscaleb.capability.dragoon.IDragoonAbility;
import com.itiscaleb.client.KeyBindingConfig;
import com.itiscaleb.item.DragoonItems;
import com.itiscaleb.network.NetworkHandler;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.registries.IForgeRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Register {

    @SubscribeEvent
    public static void onFMLSetup(FMLCommonSetupEvent e){
        CapabilityManager.INSTANCE.register(IDragoonAbility.class,
                new DragoonAbility.Storage(),DragoonAbility::new);
        NetworkHandler.register();
    }

    @SubscribeEvent
    public static void enqueueIMC(final InterModEnqueueEvent evt) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.CHARM.getMessageBuilder().build());
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> e){
        IForgeRegistry<Item> registry = e.getRegistry();
        registerDragonBloodArmor(registry);
        registry.register(DragoonItems.SoulOfTheDragoon);
        registry.register(DragoonItems.WoodenLance);
    }

    private static void registerDragonBloodArmor(final IForgeRegistry<Item> reg){
        reg.register(DragoonItems.DragonBloodHelmet);
        reg.register(DragoonItems.DragonBloodChestplate);
        reg.register(DragoonItems.DragonBloodLegs);
        reg.register(DragoonItems.DragonBloodBoots);
    }
}
