package com.itiscaleb.common.capability.dragoon;

import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;

public interface IDragoonAbility extends INBTSerializable<CompoundNBT> {

    boolean isSoulEquiped();

    void setSoulEquiped(boolean equiped);

    void setStage(int stage);

    void setSkillSlot(int index, SkillMateriaCrystal skill);

    SkillMateriaCrystal getSkill(int index);

    SkillMateriaCrystal[] getSkills();

    boolean addLearnedSkill(String skill);

    ArrayList<String> getLearnedSkill();

    void cleanLearnedSkill();

    int getStageNum();

    float getJumpForce();

    void setJumpForce(float force);

    DragoonAbility.DragoonStage getStage();

    void syncClient(ServerPlayerEntity p);

    void syncServer();
}
