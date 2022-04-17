package com.itiscaleb.common.event;

import com.itiscaleb.common.item.weapon.lance.Lance;
import com.itiscaleb.network.ExtendReachPacket;
import com.itiscaleb.network.NetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LanceEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onPlayerAttack(PlayerInteractEvent.LeftClickEmpty e){
        if(e.getItemStack().getItem() instanceof Lance){
            Lance lance = (Lance)e.getItemStack().getItem();
            PlayerEntity player = e.getPlayer();
            Vector3d eyePosition = player.getEyePosition(1);
            double d0 = 6f;
            Vector3d start = player.getLookVec();
            Vector3d end = eyePosition.add(start.x * d0, start.y * d0, start.z * d0);
            AxisAlignedBB axisalignedbb = player.getBoundingBox().expand(start.scale(d0)).grow(1.0D, 1.0D, 1.0D);
            EntityRayTraceResult r = ProjectileHelper.rayTraceEntities(player,eyePosition,end,axisalignedbb, entity -> !entity.isSpectator() && entity.canBeCollidedWith(),d0*d0);
            if(r!=null){
                NetworkHandler.Instance.sendToServer(new ExtendReachPacket(r.getEntity().getEntityId(),lance.getAttackDamage()));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerUseOffHand(PlayerInteractEvent e){
        if(e.getPlayer().getHeldItemMainhand().getItem() instanceof Lance){
            if(e.getHand() == Hand.OFF_HAND){
                e.setCanceled(true);
            }
        }
    }
}
