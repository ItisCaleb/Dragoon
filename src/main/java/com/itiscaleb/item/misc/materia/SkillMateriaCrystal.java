package com.itiscaleb.item.misc.materia;

import com.itiscaleb.Dragoon;
import com.itiscaleb.DragoonGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class SkillMateriaCrystal extends Item {
    public SkillMateriaCrystal(String name,Rarity rarity) {
        super(new Properties().maxStackSize(1).rarity(rarity).group(DragoonGroup.Instance));
        setRegistryName(Dragoon.MODID,name);
    }

    public boolean executeSkill(PlayerEntity player){
        return true;
    }

}
