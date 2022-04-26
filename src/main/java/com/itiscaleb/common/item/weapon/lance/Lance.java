package com.itiscaleb.common.item.weapon.lance;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.itiscaleb.Dragoon;
import com.itiscaleb.DragoonGroup;
import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class Lance extends Item implements IVanishable {

    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;


    public Lance(int attackDamage, String registryName) {
        super(new Properties().maxStackSize(1).group(DragoonGroup.Instance));
        this.attackDamage = attackDamage;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.2d, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        this.setRegistryName(Dragoon.MODID,registryName);
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(handIn == Hand.MAIN_HAND){
            playerIn.setActiveHand(handIn);
        }
        return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
    }



    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, World worldIn, @Nonnull LivingEntity entityLiving, int timeLeft) {
        if(worldIn.isRemote){
            PlayerEntity playerIn = (PlayerEntity) entityLiving;
            playerIn.getCapability(DragoonAbility.CAPABILITY)
                    .ifPresent(ability -> {
                        if(ability.isSoulEquiped()){
                            SkillMateriaCrystal crystal;
                            if(playerIn.isCrouching()){
                                crystal = ability.getSkill(1);
                            }else if(!playerIn.isOnGround()){
                                crystal = ability.getSkill(2);
                            }else if(playerIn.isSprinting()){
                                crystal = ability.getSkill(3);
                            }else crystal = ability.getSkill(0);
                            if(crystal!=null){
                                if(crystal.executeSkill(playerIn)){
                                    playerIn.getCooldownTracker().setCooldown(this,30);
                                }
                            }
                        }
                    });
        }
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(@Nonnull ItemStack stack, World worldIn, @Nonnull LivingEntity entityLiving) {
        if(worldIn.isRemote){
            PlayerEntity playerIn = (PlayerEntity) entityLiving;
            playerIn.getCapability(DragoonAbility.CAPABILITY)
                    .ifPresent(ability -> {
                        if(ability.isSoulEquiped()){
                            SkillMateriaCrystal crystal = ability.getSkill(4);
                            if(crystal.executeSkill(playerIn)){
                                playerIn.getCooldownTracker().setCooldown(this,30);
                            }
                        }
                    });
        }
        return stack;
    }


    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 0.5f;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }
}
