package com.itiscaleb.event;

import com.itiscaleb.Dragoon;
import com.itiscaleb.capability.dragoon.DragoonAbility;
import com.itiscaleb.capability.dragoon.DragoonAbilityProvider;
import com.itiscaleb.command.DragoonCommand;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DragoonAbilityHandler {


    //register things
    @SubscribeEvent
    public static void onRegisterCommand(RegisterCommandsEvent e){
        DragoonCommand.register(e.getDispatcher());
    }

    @SubscribeEvent
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> e){
        if(e.getObject() instanceof PlayerEntity){
            e.addCapability(new ResourceLocation(Dragoon.MODID,"dragoon_ability"), new DragoonAbilityProvider());
        }
    }


    //dragoon logic
    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent e){
        if (e.getEntityLiving() instanceof ClientPlayerEntity) {
            PlayerEntity p = (PlayerEntity) e.getEntityLiving();
            p.getCapability(DragoonAbility.CAPABILITY,null)
                    .ifPresent(ability->{
                        if(ability.isSoulEquiped()){
                            p.sendMessage(new StringTextComponent(ability.getStage().jumpBoost+""),UUID.randomUUID());
                            p.setMotion(p.getMotion().add(0,ability.getJumpForce(),0));
                        }
                    });
        }
    }

    @SubscribeEvent
    public static void onPlayerFall(LivingFallEvent e){
        if(e.getEntityLiving() instanceof PlayerEntity){
            PlayerEntity p = (PlayerEntity) e.getEntityLiving();
            p.getCapability(DragoonAbility.CAPABILITY,null)
                    .ifPresent(ability -> {
                        if(ability.isSoulEquiped()) {
                            switch (ability.getStage()){
                                case Dragoon:
                                    e.setDistance(Math.max(0,e.getDistance()-10f));
                                case AzureDragoon:
                                    e.setDistance(Math.max(0,e.getDistance()-40f));
                            }
                        }
                    });
        }
    }


    //persist capability logic
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone e)
    {
        e.getPlayer().getCapability(DragoonAbility.CAPABILITY, null)
                .ifPresent((ability)-> e.getOriginal().getCapability(DragoonAbility.CAPABILITY, null)
                        .ifPresent(oldAbility-> ability.deserializeNBT(oldAbility.serializeNBT())));
    }
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent e){
        if(!e.getPlayer().world.isRemote){
            ServerPlayerEntity p = (ServerPlayerEntity) e.getPlayer();
            p.getCapability(DragoonAbility.CAPABILITY)
                    .ifPresent((ability -> ability.syncClient(p)));
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e){
        if(!e.getPlayer().world.isRemote){
            ServerPlayerEntity p = (ServerPlayerEntity) e.getPlayer();
            p.getCapability(DragoonAbility.CAPABILITY)
                    .ifPresent((ability -> ability.syncClient(p)));
        }
    }

    @SubscribeEvent
    public static void onPlayerChangeDim(PlayerEvent.PlayerChangedDimensionEvent e){
        if(!e.getPlayer().world.isRemote){
            ServerPlayerEntity p = (ServerPlayerEntity) e.getPlayer();
            p.getCapability(DragoonAbility.CAPABILITY)
                    .ifPresent((ability -> ability.syncClient(p)));
        }
    }
}

