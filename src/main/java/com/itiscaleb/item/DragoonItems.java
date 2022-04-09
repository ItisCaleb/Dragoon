package com.itiscaleb.item;

import com.itiscaleb.item.armor.dragonblood.DragonBloodArmor;
import com.itiscaleb.item.misc.SoulOfTheDragoon;
import com.itiscaleb.item.weapon.lance.WoodenLance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

public class DragoonItems {
    //armors
    public static final Item DragonBloodHelmet = new DragonBloodArmor(EquipmentSlotType.HEAD,"dragon_blood_helmet");
    public static final Item DragonBloodChestplate = new DragonBloodArmor(EquipmentSlotType.CHEST,"dragon_blood_chestplate");
    public static final Item DragonBloodLegs = new DragonBloodArmor(EquipmentSlotType.LEGS,"dragon_blood_legs");
    public static final Item DragonBloodBoots = new DragonBloodArmor(EquipmentSlotType.FEET,"dragon_blood_boots");

    //items
    public static final Item SoulOfTheDragoon = new SoulOfTheDragoon();
    public static final Item WoodenLance = new WoodenLance();
}
