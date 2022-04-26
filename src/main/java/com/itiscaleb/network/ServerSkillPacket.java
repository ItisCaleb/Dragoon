package com.itiscaleb.network;

import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import com.itiscaleb.common.item.DragoonItems;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerSkillPacket {
    private final int entityId;
    private final String skillName;

    public ServerSkillPacket(PacketBuffer buffer){
        entityId = buffer.readInt();
        skillName = buffer.readString();
    }

    public ServerSkillPacket(int entityId, String skillName){
        this.entityId = entityId;
        this.skillName = skillName;
    }

    void encode(PacketBuffer buffer){
        buffer.writeInt(entityId);
        buffer.writeString(skillName);
    }

    void handle(Supplier<NetworkEvent.Context> context){
        if(context.get().getDirection().equals(NetworkDirection.PLAY_TO_SERVER)){
            context.get().enqueueWork(()->{
                PlayerEntity player = context.get().getSender();
                SkillMateriaCrystal crystal = DragoonItems.SKILL_MATERIA.get(skillName);
                if(crystal!=null){
                    crystal.executeServerSkill(player, (LivingEntity) player.getEntityWorld().getEntityByID(entityId));
                }
            });
        }

        context.get().setPacketHandled(true);
    }
}
