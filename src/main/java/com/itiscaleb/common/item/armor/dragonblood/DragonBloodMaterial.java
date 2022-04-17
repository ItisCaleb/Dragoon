package com.itiscaleb.common.item.armor.dragonblood;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class DragonBloodMaterial implements IArmorMaterial {
    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        switch (slotIn){
            case HEAD:
            case CHEST:
            case LEGS:
            case FEET:
                return 2000;
            default:
                return 0;
        }
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        switch (slotIn){
            case HEAD:
                return 4;
            case CHEST:
            case LEGS:
                return 7;
            case FEET:
                return 6;
            default:
                return 0;
        }
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return null;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }

    @Override
    public String getName() {
        return "dragon_blood";
    }

    @Override
    public float getToughness() {
        return 2;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
