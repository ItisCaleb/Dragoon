package com.itiscaleb.common.item.misc.materia.dragoon;

import com.itiscaleb.common.item.misc.materia.SkillHelper;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

public class TrueThrustMateria extends SkillMateriaCrystal {


    public TrueThrustMateria(String name, Rarity rarity) {
        super(name, rarity);
    }

    @Override
    public boolean executeServerSkill(PlayerEntity playerIn, LivingEntity entityIn) {
        SkillHelper.executeFrontSquare(playerIn,1.5d,6d,entity ->
                SkillHelper.attackEntity(playerIn,entity,5f));
        return true;
    }
}
