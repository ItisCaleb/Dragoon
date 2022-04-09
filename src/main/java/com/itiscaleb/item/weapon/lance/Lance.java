package com.itiscaleb.item.weapon.lance;

import com.itiscaleb.Dragoon;
import com.itiscaleb.DragoonGroup;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class Lance extends Item implements IVanishable {

    public Lance(int attackDamage, String registryName) {
        super(new Properties().maxStackSize(1).group(DragoonGroup.Instance).maxDamage(attackDamage));
        this.setRegistryName(Dragoon.MODID,registryName);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote()){
            playerIn.setActiveHand(handIn);
            playerIn.sendMessage(new StringTextComponent("bruh"), UUID.randomUUID());
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
