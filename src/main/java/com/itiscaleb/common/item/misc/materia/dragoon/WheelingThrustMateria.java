package com.itiscaleb.common.item.misc.materia.dragoon;

import com.itiscaleb.common.item.misc.materia.SkillHelper;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;

public class WheelingThrustMateria extends SkillMateriaCrystal {

    public WheelingThrustMateria(String name, Rarity rarity) {
        super(name, rarity);
    }

    @Override
    public boolean executeServerSkill(PlayerEntity playerIn, LivingEntity entityIn) {
        SkillHelper.executeCircular(playerIn,4, -45,45,entity ->
                SkillHelper.attackEntity(playerIn,entity,5));
        return super.executeServerSkill(playerIn, entityIn);
    }
}
