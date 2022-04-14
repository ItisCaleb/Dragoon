package com.itiscaleb.capability.dragoon;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DragoonAbilityProvider implements ICapabilitySerializable<CompoundNBT> {
    private IDragoonAbility ability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == DragoonAbility.CAPABILITY){
            return LazyOptional.of(this::getInstance).cast();
        }
        return LazyOptional.empty();
    }

    @Nonnull
    public IDragoonAbility getInstance(){
        if(ability == null){
            this.ability = new DragoonAbility();
        }
        return this.ability;
    }


    @Override
    public CompoundNBT serializeNBT() {
        return getInstance().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getInstance().deserializeNBT(nbt);
    }
}
