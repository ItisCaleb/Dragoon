package com.itiscaleb.common.item.misc.materia;

import com.itiscaleb.Dragoon;
import com.itiscaleb.DragoonGroup;
import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class SkillMateriaCrystal extends Item {
    public SkillMateriaCrystal(String name,Rarity rarity) {
        super(new Properties().maxStackSize(1).rarity(rarity).group(DragoonGroup.Instance));
        setRegistryName(Dragoon.MODID,name+"_materia");
    }

    public boolean executeSkill(PlayerEntity player){
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        ResourceLocation materia = stack.getItem().getRegistryName();
        AtomicReference<ActionResult<ItemStack>> result = new AtomicReference<>();
        playerIn.getCapability(DragoonAbility.CAPABILITY).ifPresent(ability->{
            boolean success = ability.addLearnedSkill(materia.toString());
            if(success){
                result.set(ActionResult.resultConsume(ItemStack.EMPTY));
                if (worldIn.isRemote) {
                    playerIn.sendMessage(
                            new StringTextComponent("You successfully learned ").appendSibling(new TranslationTextComponent("skill.dragoon."+materia.getPath()))
                            ,UUID.randomUUID());
                }
            }else{
                result.set(ActionResult.resultConsume(stack));
                if (worldIn.isRemote){
                    playerIn.sendMessage(
                            new StringTextComponent("You have already learned ").appendSibling(new TranslationTextComponent("skill.dragoon."+materia.getPath()))
                            ,UUID.randomUUID());
                }
            }
        });
        return result.get();
    }
}
