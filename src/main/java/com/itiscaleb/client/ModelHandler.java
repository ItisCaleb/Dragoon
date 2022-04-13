package com.itiscaleb.client;

import com.itiscaleb.item.weapon.lance.Lance;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModelHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onPreRender(RenderPlayerEvent.Pre e){
        PlayerEntity p = e.getPlayer();
        if(p.getHeldItemMainhand().getItem() instanceof Lance){
            if(p.getPose() == Pose.STANDING || p.getPose() == Pose.CROUCHING){
                int light = e.getRenderer().getRenderManager().getPackedLight(e.getPlayer(), e.getPartialRenderTick());
                MatrixStack stack = e.getMatrixStack();
                AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) p;
                float tick = e.getPartialRenderTick();
                Setup.renderer.render(player,player.getYaw(tick),tick,stack,e.getBuffers(),light);
                e.setCanceled(true);
            }
        }
    }
}
