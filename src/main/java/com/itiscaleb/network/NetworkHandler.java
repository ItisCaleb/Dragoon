package com.itiscaleb.network;

import com.itiscaleb.Dragoon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel Instance = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Dragoon.MODID,"main"),
            ()->PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    public static void register(){
        Instance.registerMessage(0, DragoonAbilityPacket.class,
                DragoonAbilityPacket::encode,
                DragoonAbilityPacket::new,
                DragoonAbilityPacket::handle);
        Instance.registerMessage(1, AttackEntityPacket.class,
                AttackEntityPacket::encode,
                AttackEntityPacket::new,
                AttackEntityPacket::handle);
        Instance.registerMessage(2, ServerSkillPacket.class,
                ServerSkillPacket::encode,
                ServerSkillPacket::new,
                ServerSkillPacket::handle);
    }
}
