package com.itiscaleb.common.capability.dragoon;

import com.itiscaleb.common.item.DragoonItems;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import com.itiscaleb.network.DragoonAbilityPacket;
import com.itiscaleb.network.NetworkHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class DragoonAbility implements IDragoonAbility {

    @CapabilityInject(IDragoonAbility.class)
    public static final Capability<IDragoonAbility> CAPABILITY = null;

    private boolean isSoulEquiped;
    private int dragoonStage;
    private float jumpforce;
    private SkillMateriaCrystal[] skillList;
    private ArrayList<String> learnedSkill;

    public DragoonAbility(){
        isSoulEquiped = false;
        dragoonStage = 0;
        jumpforce = 0f;
        skillList = new SkillMateriaCrystal[5];
        learnedSkill = new ArrayList<>();
    }

    public boolean isSoulEquiped(){
        return isSoulEquiped;
    }

    public void setSoulEquiped(boolean equiped){
        isSoulEquiped = equiped;
    }

    public void setStage(int level) {
        this.dragoonStage = level;
        setJumpForce(Math.min(getJumpForce(),getStage().jumpBoost));
    }

    public void setSkillSlot(int index, SkillMateriaCrystal skill){
        skillList[index] = skill;
    }


    public SkillMateriaCrystal getSkill(int index) {
        return skillList[index];
    }

    public SkillMateriaCrystal[] getSkills(){
        return skillList;
    }

    @Override
    public boolean addLearnedSkill(String skill) {
        if(!learnedSkill.contains(skill)){
            learnedSkill.add(skill);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getLearnedSkill() {
        return learnedSkill;
    }

    @Override
    public void cleanLearnedSkill() {
        learnedSkill.clear();
        for(int i=0;i<5;i++){
            skillList[i] = null;
        }
    }

    public int getStageNum(){
        return dragoonStage;
    }

    public float getJumpForce(){
        return jumpforce;
    }

    public void setJumpForce(float force){
         this.jumpforce = force;
    }



    public DragoonStage getStage(){
        switch (dragoonStage){
            case 0:
                return DragoonStage.Lancer;
            case 1:
                return DragoonStage.Dragoon;
            case 2:
                return DragoonStage.AzureDragoon;
            default:
                return null;
        }
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("IsSoulEquiped", isSoulEquiped());
        nbt.putInt("DragoonStage",getStageNum());
        ListNBT skillList = new ListNBT();
        for(SkillMateriaCrystal crystal : this.skillList){
            if(crystal != null){
                skillList.add(StringNBT.valueOf(crystal.getRegistryName().toString()));
            }
        }
        nbt.put("SkillList",skillList);
        ListNBT learnedSkill = new ListNBT();
        for(String skill : this.learnedSkill){
            learnedSkill.add(StringNBT.valueOf(skill));
        }
        nbt.put("LearnedSkill",learnedSkill);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setSoulEquiped(nbt.getBoolean("IsSoulEquiped"));
        setStage(nbt.getInt("DragoonStage"));
        skillList = new SkillMateriaCrystal[5];
        int i = 0;
        for (INBT s : nbt.getList("SkillList",Constants.NBT.TAG_STRING)){
            StringNBT skill = (StringNBT) s;
            skillList[i] = DragoonItems.SKILL_MATERIA.get(skill.getString());
            i++;
        }
        learnedSkill = new ArrayList<>();
        for (INBT s : nbt.getList("LearnedSkill",Constants.NBT.TAG_STRING)){
            StringNBT skill = (StringNBT) s;
            learnedSkill.add(skill.getString());
        }

    }

    public void syncClient(ServerPlayerEntity p){
        NetworkHandler.Instance.send(PacketDistributor.PLAYER.with(()-> p)
                ,new DragoonAbilityPacket(this));
    }

    public void syncServer(){
        NetworkHandler.Instance.sendToServer(new DragoonAbilityPacket(this));
    }

    public enum DragoonStage{
        Lancer("Lancer",0f),
        Dragoon("Dragoon",0.8f),
        AzureDragoon("The Azure Dragoon",2.5f);

        public String name;
        public float jumpBoost;
        DragoonStage(String name, float maxJumpBoost ) {
            this.name = name;
            this.jumpBoost = maxJumpBoost;
        }
    }

    public static class Storage implements Capability.IStorage<IDragoonAbility>{

        @Nullable
        @Override
        public INBT writeNBT(Capability<IDragoonAbility> capability, IDragoonAbility instance, Direction side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<IDragoonAbility> capability, IDragoonAbility instance, Direction side, INBT nbt) {
            instance.deserializeNBT((CompoundNBT) nbt);
        }
    }
}
