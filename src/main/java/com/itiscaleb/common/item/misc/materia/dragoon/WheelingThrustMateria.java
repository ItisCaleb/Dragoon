package com.itiscaleb.common.item.misc.materia.dragoon;

import com.itiscaleb.common.item.misc.materia.SkillHelper;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;

public class WheelingThrustMateria extends SkillMateriaCrystal {
    public WheelingThrustMateria() {
        super("wheeling_thrust", Rarity.UNCOMMON);
    }

    @Override
    public boolean executeSkill(PlayerEntity player) {
        SkillHelper.executeCircular(player,4, -45,45,entity ->
                entity.attackEntityFrom(DamageSource.causePlayerDamage(player),4));
        return true;
    }
}
