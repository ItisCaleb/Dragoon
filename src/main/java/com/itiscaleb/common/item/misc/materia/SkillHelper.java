package com.itiscaleb.common.item.misc.materia;

import com.itiscaleb.Dragoon;
import com.itiscaleb.network.ExtendReachPacket;
import com.itiscaleb.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

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

    public static List<LivingEntity> getEntityInRange(World world, Vector3d pos, double range){
        Vector3d p1 = LEFT_CORNER.mul(range,1,range).add(pos);
        Vector3d p2 = RIGHT_CORNER.mul(range,1,range).add(pos);
        return world.getEntitiesWithinAABB(LivingEntity.class,new AxisAlignedBB(p1,p2));
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

    /**
     * Execute base on player
     */
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
    /**
     * Execute base on position given
     */
    public static void executeSquare(PlayerEntity player, Vector3d pos , double width ,double length, Skill skill){
        Vector3d p1 = new Vector3d(-width/2,-1d,-length/2);
        Vector3d p2 = new Vector3d(width/2,1d,length/2);
        AxisAlignedBB box = new AxisAlignedBB(p1,p2);
        List<LivingEntity> list = SkillHelper.getEntityInRange(player.getEntityWorld(),pos,Math.max(width,length));
        for (LivingEntity entity:list){
            if(!entity.isEntityEqual(player)){
                Vector3d vec = entity.getPositionVec().subtract(pos);
                if(box.contains(vec)){
                    skill.skillHandler(entity);
                }
            }
        }
    }

    /**
     * Execute base on player
     */
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
     * Execute base on position given
     */
    public static void executeCircle(PlayerEntity player, Vector3d pos, double range, Skill skill){
        List<LivingEntity> list = SkillHelper.getEntityInRange(player.getEntityWorld(), pos, range);
        double sqRange = range * range;
        for (LivingEntity entity:list){
            if(!entity.isEntityEqual(player)){
                if(pos.squareDistanceTo(entity.getPositionVec()) <= sqRange){
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
    public static void executeCircular(PlayerEntity player, double range,double min, double max, Skill skill){
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

    /**
     * Get first target within range
     */
    public static void executeRayTraceEntity(PlayerEntity player, double range, Skill skill){
        Vector3d eyePosition = player.getEyePosition(1);
        Vector3d start = player.getLookVec();
        Vector3d end = eyePosition.add(start.x * range, start.y * range, start.z * range);
        AxisAlignedBB axisalignedbb = player.getBoundingBox().expand(start.scale(range)).grow(1.0D, 1.0D, 1.0D);
        EntityRayTraceResult r = ProjectileHelper.rayTraceEntities(player,eyePosition,end,axisalignedbb, entity -> !entity.isSpectator() && entity.canBeCollidedWith(),range*range);
        if(r!=null){
            if(r.getEntity() instanceof LivingEntity){
                skill.skillHandler((LivingEntity) r.getEntity());
            }
        }
    }

    /**
     * Get first block within range
     */
    public static Vector3d getRayTraceBlock(PlayerEntity player, double range){
        Vector3d eyePosition = player.getEyePosition(1);
        Vector3d start = player.getLookVec();
        Vector3d end = eyePosition.add(start.x * range, start.y * range, start.z * range);
        BlockRayTraceResult r = player.getEntityWorld().rayTraceBlocks(new RayTraceContext(start, end, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE,player));
        BlockPos pos = r.getPos();
        if(!player.getEntityWorld().getBlockState(pos).isAir()){
            return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
        }
        return null;
    }
}
