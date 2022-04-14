package com.itiscaleb.item.weapon.lance;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.itiscaleb.Dragoon;
import com.itiscaleb.DragoonGroup;
import com.itiscaleb.capability.dragoon.DragoonAbility;
import com.itiscaleb.item.DragoonItems;
import com.itiscaleb.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nonnull;
import java.util.UUID;

public class Lance extends Item implements IVanishable {

    public static UUID uuid = UUID.fromString("ce6f87be-be50-47cb-b557-6894dce12c58");

    public Lance(int attackDamage, String registryName) {
        super(new Properties().maxStackSize(1).group(DragoonGroup.Instance).maxDamage(attackDamage));
        this.setRegistryName(Dragoon.MODID,registryName);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(handIn == Hand.MAIN_HAND){
            if(!worldIn.isRemote()){
                playerIn.getCapability(DragoonAbility.CAPABILITY)
                        .ifPresent(ability -> {
                            if(ability.isSoulEquiped()){
                                SkillMateriaCrystal crystal = (SkillMateriaCrystal) DragoonItems.TrueTrustMateria;
                                crystal.executeSkill(playerIn);
                            }
                        });
            }
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 0.5f;
    }

    public static Multimap<Attribute, AttributeModifier> getAttributes() {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        ForgeMod.REACH_DISTANCE.get();
        attributes.put(ForgeMod.REACH_DISTANCE.get()
                ,new AttributeModifier(Lance.uuid,"Lance",7, AttributeModifier.Operation.ADDITION));
        return attributes;
    }
}
