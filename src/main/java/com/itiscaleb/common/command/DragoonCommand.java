package com.itiscaleb.common.command;

import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.UUID;

public class DragoonCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("setStage")
                .requires(s->s.hasPermissionLevel(2))
                .then(Commands.argument("num", IntegerArgumentType.integer(0,2))
                        .executes(context -> setStage(context.getSource(), IntegerArgumentType.getInteger(context,"num"))))
        );
        dispatcher.register(Commands.literal("clearSkill")
                .requires(s->s.hasPermissionLevel(2))
                .executes(context -> clearSkill(context.getSource())));
        dispatcher.register(Commands.literal("listSkill")
                .requires(s->s.hasPermissionLevel(2))
                .executes(context -> getSkill(context.getSource())));
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

    private static int clearSkill(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity p = source.asPlayer();
        p.getCapability(DragoonAbility.CAPABILITY,null)
                .ifPresent(ability->{
                    ability.cleanLearnedSkill();
                    ability.syncClient(p);
                });
        return 1;
    }
    private static int getSkill(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity p = source.asPlayer();
        p.getCapability(DragoonAbility.CAPABILITY,null)
                .ifPresent(ability->{
                    for (String skill : ability.getLearnedSkill()){
                        p.sendMessage(new StringTextComponent(skill), UUID.randomUUID());
                    }
                });
        return 1;
    }
}
