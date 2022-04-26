package com.itiscaleb.common.item;

import com.itiscaleb.common.item.armor.dragonblood.DragonBloodArmor;
import com.itiscaleb.common.item.misc.SoulOfTheDragoon;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import com.itiscaleb.common.item.misc.materia.dragoon.DragonfireDiveMateria;
import com.itiscaleb.common.item.misc.materia.dragoon.StarDiveMateria;
import com.itiscaleb.common.item.misc.materia.dragoon.TrueThrustMateria;
import com.itiscaleb.common.item.misc.materia.dragoon.WheelingThrustMateria;
import com.itiscaleb.common.item.weapon.lance.WoodenLance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
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
    public static final Item TrueThrustMateria = new TrueThrustMateria("true_thrust", Rarity.UNCOMMON);
    public static final Item WheelingThrustMateria = new WheelingThrustMateria("wheeling_thrust", Rarity.UNCOMMON);
    public static final Item DragonFireDiveMateria = new DragonfireDiveMateria("dragonfire_dive",Rarity.EPIC);
    public static final Item StarDiveMateria = new StarDiveMateria("star_dive",Rarity.EPIC);


    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> e){
        IForgeRegistry<Item> reg = e.getRegistry();
        reg.register(DragonBloodHelmet);
        reg.register(DragonBloodChestplate);
        reg.register(DragonBloodLegs);
        reg.register(DragonBloodBoots);
        reg.register(SoulOfTheDragoon);
        reg.register(WoodenLance);
        //materia
        registerMateria(reg,TrueThrustMateria);
        registerMateria(reg,WheelingThrustMateria);
        registerMateria(reg,DragonFireDiveMateria);
        registerMateria(reg,StarDiveMateria);
    }

    private static void registerMateria(final IForgeRegistry<Item> reg, Item materia){
        reg.register(materia);
        SKILL_MATERIA.put(materia.getRegistryName().toString(),(SkillMateriaCrystal) materia);
    }
}
