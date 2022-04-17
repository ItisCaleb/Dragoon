package com.itiscaleb.network;

import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DragoonAbilityPacket {

    private final CompoundNBT nbt;

    public DragoonAbilityPacket(PacketBuffer buffer){
        nbt = buffer.readCompoundTag();
    }

    public DragoonAbilityPacket(DragoonAbility ability){
        nbt = ability.serializeNBT();
    }

    void encode(PacketBuffer buffer){
        buffer.writeCompoundTag(nbt);
    }

    void handle(Supplier<NetworkEvent.Context> context){
        if(context.get().getSender() == null){
            //server -> client
            context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ()-> () -> {
                PlayerEntity p = Minecraft.getInstance().player;
                p.getCapability(DragoonAbility.CAPABILITY)
                        .ifPresent((ability)-> ability.deserializeNBT(nbt));
            }));
        }else {
            //client -> server
            context.get().enqueueWork(()->{
                context.get().getSender()
                        .getCapability(DragoonAbility.CAPABILITY)
                        .ifPresent(ability -> {
                            //make sure server don't accept stage and equiped change
                            DragoonAbility saved = new DragoonAbility();
                            saved.deserializeNBT(ability.serializeNBT());
                            ability.deserializeNBT(nbt);
                            ability.setSoulEquiped(saved.isSoulEquiped());
                            ability.setStage(saved.getStageNum());
                        });
            });
        }

        context.get().setPacketHandled(true);
    }

}
