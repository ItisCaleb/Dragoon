package com.itiscaleb.common.item.misc.materia.dragoon;

import com.itiscaleb.common.item.misc.materia.SkillHelper;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;

public class TrueThrustMateria extends SkillMateriaCrystal {
    public TrueThrustMateria() {
        super("true_thrust", Rarity.UNCOMMON);
    }

    @Override
    public boolean executeSkill(PlayerEntity player){
        SkillHelper.executeFrontSquare(player,1.5d,6d,entity ->
                entity.attackEntityFrom(DamageSource.causePlayerDamage(player),5f));
        return true;
    }
}