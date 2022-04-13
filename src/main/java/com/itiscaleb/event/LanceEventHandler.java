package com.itiscaleb.event;

import com.itiscaleb.item.weapon.lance.Lance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LanceEventHandler {
    @SubscribeEvent
    public static void onPlayerChangeLance(LivingEquipmentChangeEvent e){
        if(e.getSlot() == EquipmentSlotType.MAINHAND && e.getEntityLiving() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) e.getEntityLiving();
            ItemStack stack = e.getTo();
            if (stack.getItem() instanceof Lance){
                player.getAttributeManager().reapplyModifiers(Lance.getAttributes());
            }else {
                player.getAttributeManager().removeModifiers(Lance.getAttributes());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerUseOffHand(PlayerInteractEvent e){
        if(e.getPlayer().getHeldItemMainhand().getItem() instanceof Lance){
            if(e.getHand() == Hand.OFF_HAND){
                e.setCanceled(true);
            }
        }
    }
}
