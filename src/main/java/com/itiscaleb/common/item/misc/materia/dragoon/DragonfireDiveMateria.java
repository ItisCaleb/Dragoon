package com.itiscaleb.common.item.misc.materia.dragoon;

import com.itiscaleb.Dragoon;
import com.itiscaleb.common.item.misc.materia.SkillHelper;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class DragonfireDiveMateria extends SkillMateriaCrystal {
    static final double tau = 50*(1 - Math.pow(0.98,20));
    static final double a = 100*(1 - Math.pow(0.91,20));
    static final double dy = 3.92*20;

    public DragonfireDiveMateria(String name, Rarity rarity) {
        super(name, rarity);
    }

    @Override
    public boolean executeSkill(PlayerEntity player) {
        LivingEntity entity = SkillHelper.getRayTraceEntity(player,40f);
        player.setOnGround(false);
        if(entity!=null){
            Vector3d delta = entity.getPositionVec().subtract(player.getPositionVec());
            player.setMotion(delta.x*9/a,(delta.y+dy)/tau - 3.92,delta.z*9/a);
            super.executeSkill(player);
            return true;
        }
        return false;
    }

    @Override
    public boolean executeServerSkill(PlayerEntity player, LivingEntity entity) {
        World world = player.getEntityWorld();
        Dragoon.getScheduler(world).scheduleTick(player,"Dragonfire Dive",20,(runnable)->{
            if(player.isOnGround()){
                SkillHelper.executeCircle(player,5,(entity1)-> SkillHelper.attackEntity(player,entity1,5f));
            }else if(!player.hasNoGravity() && !player.isElytraFlying()){
                Dragoon.getScheduler(world).reScheduleTick(runnable,1);
            }
        });
        return true;
    }
}
