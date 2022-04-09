package com.itiscaleb.command;

import com.itiscaleb.capability.dragoon.DragoonAbility;
import com.itiscaleb.capability.dragoon.DragoonAbilityProvider;
import com.itiscaleb.network.DragoonAbilityPacket;
import com.itiscaleb.network.NetworkHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class DragoonCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("setStage")
                .requires(s->s.hasPermissionLevel(2))
                .then(Commands.argument("num", IntegerArgumentType.integer(0,2))
                        .executes(context -> setStage(context.getSource(), IntegerArgumentType.getInteger(context,"num"))))
        );
    }
    private static int setStage(CommandSource source, int stage) throws CommandSyntaxException {
        ServerPlayerEntity p = source.asPlayer();
        p.getCapability(DragoonAbility.CAPABILITY,null)
                .ifPresent(ability->{
                    ability.setStage(stage);
                    ability.syncClient(p);
                });
        return 1;
    }
}
