package com.itiscaleb.client;

import com.itiscaleb.Dragoon;
import com.itiscaleb.item.weapon.lance.Lance;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModelHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onPreRender(RenderPlayerEvent.Pre e){
        PlayerEntity p = e.getPlayer();
        if(p.getHeldItemMainhand().getItem() instanceof Lance){
            setBodyVisible(e.getRenderer().getEntityModel(), false);
        }
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRender(RenderPlayerEvent.Post e){
        PlayerEntity p = e.getPlayer();
        if(p.getHeldItemMainhand().getItem() instanceof Lance){
            lancePose(e);
        }
    }

    public static void setBodyVisible(PlayerModel<AbstractClientPlayerEntity> model, boolean visible){
        model.bipedBody.showModel = visible;
        /*model.bipedLeftArm.showModel = visible;
        model.bipedRightArm.showModel = visible;
        model.bipedLeftLeg.showModel = visible;
        model.bipedRightLeg.showModel = visible;*/
    }

    public static void lancePose(RenderPlayerEvent e){
        PlayerModel<AbstractClientPlayerEntity> model = e.getRenderer().getEntityModel();
        IVertexBuilder builder = e.getBuffers().getBuffer(RenderType.getEntitySolid(((AbstractClientPlayerEntity)e.getPlayer()).getLocationSkin()));
        int light = e.getRenderer().getRenderManager().getPackedLight(e.getPlayer(), e.getPartialRenderTick());
        MatrixStack stack = e.getMatrixStack();
        setBodyVisible(model,true);
        stack.push();
        //model.bipedBody.rotateAngleY = -77.5f;
        model.bipedBody.render(stack, builder,light,OverlayTexture.NO_OVERLAY);
        stack.pop();
        /*
        stack.push();
        model.bipedRightArm.rotateAngleX = -15.8026f;
        model.bipedRightArm.rotateAngleY = -74.1152f;
        model.bipedRightArm.rotateAngleZ = 51.0742f;
        model.bipedRightArm.render(stack,builder,light,655360);
        stack.pop();

        stack.push();
        model.bipedLeftArm.rotateAngleX = 59.0793f;
        model.bipedLeftArm.rotateAngleY = -76.4621f;
        model.bipedLeftArm.rotateAngleZ = -14.9392f;
        model.bipedLeftArm.render(stack,builder,light,655360);
        stack.pop();

        stack.push();
        model.bipedLeftLeg.rotateAngleY = -72.5f;
        model.bipedLeftLeg.render(stack,builder,light,655360);
        stack.pop();

        stack.push();
        model.bipedRightLeg.rotateAngleY = -77.5f;
        model.bipedRightLeg.render(stack,builder,light,655360);
        stack.pop();*/
    }
}
