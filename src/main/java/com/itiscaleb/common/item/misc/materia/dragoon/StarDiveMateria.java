package com.itiscaleb.common.item.misc.materia.dragoon;

import com.itiscaleb.Dragoon;
import com.itiscaleb.common.item.misc.materia.SkillHelper;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class StarDiveMateria extends SkillMateriaCrystal {
    public StarDiveMateria(String name, Rarity rarity) {
        super(name, rarity);
    }

    @Override
    public boolean executeSkill(PlayerEntity playerIn) {
        World world = playerIn.getEntityWorld();
        playerIn.setMotion(playerIn.getLookVec().x*0.5,8,playerIn.getLookVec().z*0.5);
        Dragoon.getScheduler(world).scheduleTick(playerIn,"Star Dive",20,(runnable)->{
            playerIn.setMotion(playerIn.getMotion().x*0.5,-80,playerIn.getMotion().z*0.5);
            if (playerIn.getMotion().y < 0 && playerIn.getEntityWorld().getBlockState(playerIn.getPosition().down()).isAir()){
                Dragoon.getScheduler(world).reScheduleTick(runnable,2);
            }else {
                super.executeSkill(playerIn);
            }
        });
        return true;
    }

    @Override
    public boolean executeServerSkill(PlayerEntity playerIn, LivingEntity entityIn) {
        SkillHelper.executeCircle(playerIn,playerIn.getPositionVec(),10,(entity1)-> SkillHelper.attackEntity(playerIn,entity1,30f));
        return true;
    }

}
