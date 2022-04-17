package com.itiscaleb.common.item;

import com.itiscaleb.Dragoon;
import com.itiscaleb.common.item.armor.dragonblood.DragonBloodArmor;
import com.itiscaleb.common.item.misc.SoulOfTheDragoon;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import com.itiscaleb.common.item.misc.materia.dragoon.TrueThrustMateria;
import com.itiscaleb.common.item.misc.materia.dragoon.WheelingThrustMateria;
import com.itiscaleb.common.item.weapon.lance.WoodenLance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DragoonItems {
    public static HashMap<String, SkillMateriaCrystal> SKILL_MATERIA = new HashMap<>();

    //armors
    public static final Item DragonBloodHelmet = new DragonBloodArmor(EquipmentSlotType.HEAD,"dragon_blood_helmet");
    public static final Item DragonBloodChestplate = new DragonBloodArmor(EquipmentSlotType.CHEST,"dragon_blood_chestplate");
    public static final Item DragonBloodLegs = new DragonBloodArmor(EquipmentSlotType.LEGS,"dragon_blood_legs");
    public static final Item DragonBloodBoots = new DragonBloodArmor(EquipmentSlotType.FEET,"dragon_blood_boots");

    //items
    public static final Item SoulOfTheDragoon = new SoulOfTheDragoon();
    public static final Item WoodenLance = new WoodenLance();

    //materia
    public static final Item TrueThrustMateria = new TrueThrustMateria();
    public static final Item WheelingThrustMateria = new WheelingThrustMateria();

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> e){
        IForgeRegistry<Item> reg = e.getRegistry();
        reg.register(DragoonItems.DragonBloodHelmet);
        reg.register(DragoonItems.DragonBloodChestplate);
        reg.register(DragoonItems.DragonBloodLegs);
        reg.register(DragoonItems.DragonBloodBoots);
        reg.register(DragoonItems.SoulOfTheDragoon);
        reg.register(DragoonItems.WoodenLance);
        //materia
        registerMateria(reg,DragoonItems.TrueThrustMateria);
        registerMateria(reg,DragoonItems.WheelingThrustMateria);
    }

    private static void registerMateria(final IForgeRegistry<Item> reg, Item materia){
        reg.register(materia);
        SKILL_MATERIA.put(materia.getRegistryName().toString(),(SkillMateriaCrystal) materia);
    }
}
