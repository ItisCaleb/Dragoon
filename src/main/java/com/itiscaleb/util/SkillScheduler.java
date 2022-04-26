package com.itiscaleb.util;

import com.google.common.collect.Sets;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.TreeSet;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkillScheduler {

    protected final HashMap<World,TreeSet<SkillRunnable>> tickList = new HashMap<>();

    public void scheduleTick(LivingEntity caster, String skillName, int tick, SkillRunnable.SkillRunnableWrapper runnable){
        World world = caster.getEntityWorld();
        if(!tickList.containsKey(world)){
            tickList.put(world, Sets.newTreeSet(SkillRunnable.compare()));
        }
        SkillRunnable skillRunnable = new SkillRunnable(caster,skillName,tick+world.getGameTime(),runnable);
        tickList.get(world).add(skillRunnable);
    }

    public void reScheduleTick(SkillRunnable runnable,int tick){
        World world = runnable.caster.getEntityWorld();
        SkillRunnable skillRunnable = new SkillRunnable(runnable.caster,runnable.skillName, tick+world.getGameTime(), runnable.runnable);
        tickList.get(world).add(skillRunnable);
    }



}
