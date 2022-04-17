package com.itiscaleb.common.item.misc.materia;

import com.itiscaleb.Dragoon;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class SkillHelper {
    public interface Skill{
        boolean skillHandler(LivingEntity entity);
    }
    final static Vector3d LEFT_CORNER = new Vector3d(-1,-1,-1);
    final static Vector3d RIGHT_CORNER = new Vector3d(1,1,1);

    public static List<LivingEntity> getEntityInRange(PlayerEntity player, double range){
        Vector3d p1 = LEFT_CORNER.mul(range,1,range).add(player.getPositionVec());
        Vector3d p2 = RIGHT_CORNER.mul(range,1,range).add(player.getPositionVec());
        return player.getEntityWorld().getEntitiesWithinAABB(LivingEntity.class,new AxisAlignedBB(p1,p2));
    }
    public static void executeFrontSquare(PlayerEntity player, double width, double length, Skill skill){
        Vector3d p1 = new Vector3d(-width/2,-1d,0d);
        Vector3d p2 = new Vector3d(width/2,1d,length);
        AxisAlignedBB box = new AxisAlignedBB(p1,p2);
        List<LivingEntity> list = SkillHelper.getEntityInRange(player,Math.max(width,length));
        double yaw = (player.getYaw(1)  * Math.PI)/180;
        for (LivingEntity entity:list){
            if(!entity.isEntityEqual(player)){
                Vector3d vec = entity.getPositionVec().subtract(player.getPositionVec()).rotateYaw((float) yaw);
                if(box.contains(vec)){
                    skill.skillHandler(entity);
                }
            }
        }
    }

    public static void executeSquare(PlayerEntity player, double width ,double length, Skill skill){
        Vector3d p1 = new Vector3d(-width/2,-1d,-length/2);
        Vector3d p2 = new Vector3d(width/2,1d,length/2);
        AxisAlignedBB box = new AxisAlignedBB(p1,p2);
        List<LivingEntity> list = SkillHelper.getEntityInRange(player,Math.max(width,length));
        for (LivingEntity entity:list){
            if(!entity.isEntityEqual(player)){
                Vector3d vec = entity.getPositionVec().subtract(player.getPositionVec());
                if(box.contains(vec)){
                    skill.skillHandler(entity);
                }
            }
        }
    }

    public static void executeCircle(PlayerEntity player, double range, Skill skill){
        List<LivingEntity> list = SkillHelper.getEntityInRange(player,range);
        double sqRange = range * range;
        for (LivingEntity entity:list){
            if(!entity.isEntityEqual(player)){
                if(player.getDistanceSq(entity) <= sqRange){
                    skill.skillHandler(entity);
                }
            }
        }
    }

    /**
     *
     * @param player caster
     * @param range radius
     * @param min min angle [-180,180]
     * @param max max angle [-180,180]
     */
    public static void executeCircular(PlayerEntity player, double range,double min, double max , Skill skill){
        List<LivingEntity> list = SkillHelper.getEntityInRange(player,range);
        double sqRange = range * range;
        Vector3d lookVec = player.getLookVec().mul(1,0,1);
        min = min / 180 * Math.PI;
        max = max / 180 * Math.PI;
        for (LivingEntity entity:list){
            if(!entity.isEntityEqual(player)){
                Vector3d vec = entity.getPositionVec().subtract(player.getPositionVec());
                //cosΘ = vec·lookvec/(|vec|*|lookvec|)
                double cos = vec.dotProduct(lookVec)/vec.length()/lookVec.length();
                double rad = Math.acos(cos);
                if(player.getDistanceSq(entity) <= sqRange && min < rad && rad < max){
                    skill.skillHandler(entity);
                }
            }
        }
    }
}
