package com.itiscaleb.capability.dragoon;

import com.itiscaleb.item.misc.materia.SkillMateriaCrystal;
import com.itiscaleb.network.DragoonAbilityPacket;
import com.itiscaleb.network.NetworkHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class DragoonAbility implements IDragoonAbility {

    @CapabilityInject(IDragoonAbility.class)
    public static final Capability<IDragoonAbility> CAPABILITY = null;

    private boolean isSoulEquiped;
    private int dragoonStage;
    private float jumpforce;
    private ArrayList<Integer> skillList;

    public DragoonAbility(){
        isSoulEquiped = false;
        dragoonStage = 0;
        jumpforce = 0f;
        skillList = new ArrayList<>(5);
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

    @Override
    public void setSkillList(ArrayList<Integer> list) {
        skillList = list;
    }

    public SkillMateriaCrystal getSkill() {
        return null;
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
        nbt.putIntArray("SkillList",skillList);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setSoulEquiped(nbt.getBoolean("IsSoulEquiped"));
        setStage(nbt.getInt("DragoonStage"));
        skillList = new ArrayList<>();
        for (int v : nbt.getIntArray("SkillList")){
            skillList.add(v);
        }
        setSkillList(skillList);
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
