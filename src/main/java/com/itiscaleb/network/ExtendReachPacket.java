package com.itiscaleb.network;

import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ExtendReachPacket {
    private final int entityId;
    private final float attackDamage;

    public ExtendReachPacket(PacketBuffer buffer){
        entityId = buffer.readInt();
        attackDamage = buffer.readFloat();
    }

    public ExtendReachPacket(int entityId, float attackDamage){
        this.entityId = entityId;
        this.attackDamage = attackDamage;
    }

    void encode(PacketBuffer buffer){
        buffer.writeInt(entityId);
        buffer.writeFloat(attackDamage);
    }

    void handle(Supplier<NetworkEvent.Context> context){
        if(context.get().getSender() != null){
            context.get().enqueueWork(()-> context.get().getSender().getEntityWorld().getEntityByID(entityId)
                    .attackEntityFrom(DamageSource.causePlayerDamage(context.get().getSender()),attackDamage));
        }

        context.get().setPacketHandled(true);
    }
}
