package com.itiscaleb.common.item.armor.dragonblood;

import com.itiscaleb.Dragoon;
import com.itiscaleb.DragoonGroup;
import com.itiscaleb.client.model.DragonBloodModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class DragonBloodArmor extends ArmorItem {

    private final String registry;

    public DragonBloodArmor(EquipmentSlotType slot, String registry) {
        super(new DragonBloodMaterial(),
                slot,
                new Item.Properties()
                        .maxStackSize(1).group(DragoonGroup.Instance)
        );
        this.registry = registry;
        this.setRegistryName(Dragoon.MODID, registry);
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) new DragonBloodModel(1.0F);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        return Dragoon.MODID+":textures/armor/"+registry+".png";
    }
}
