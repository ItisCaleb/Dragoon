package com.itiscaleb;

import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import com.itiscaleb.common.capability.dragoon.IDragoonAbility;
import com.itiscaleb.common.item.DragoonItems;
import com.itiscaleb.network.NetworkHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.registries.IForgeRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

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
}
