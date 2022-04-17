package com.itiscaleb.common.item.misc;

import com.itiscaleb.Dragoon;
import com.itiscaleb.DragoonGroup;
import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SoulOfTheDragoon extends Item implements ICurioItem{

    public SoulOfTheDragoon() {
        super(new Properties().maxStackSize(1).rarity(Rarity.RARE).group(DragoonGroup.Instance));
        setRegistryName(Dragoon.MODID, "soul_of_the_dragoon");
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        LivingEntity entity = slotContext.getWearer();
        if(entity instanceof ServerPlayerEntity){
            ServerPlayerEntity p = (ServerPlayerEntity) entity;
            p.getCapability(DragoonAbility.CAPABILITY).ifPresent(
                    ability -> {
                        ability.setSoulEquiped(true);
                        ability.syncClient(p);
                    }
            );
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack originalStack, ItemStack newStack) {
        LivingEntity entity = slotContext.getWearer();
        if(entity instanceof ServerPlayerEntity){
            ServerPlayerEntity p = (ServerPlayerEntity) entity;
            p.getCapability(DragoonAbility.CAPABILITY).ifPresent(
                    ability -> {
                        ability.setSoulEquiped(false);
                        ability.syncClient(p);
                    }
            );
        }
    }
}
