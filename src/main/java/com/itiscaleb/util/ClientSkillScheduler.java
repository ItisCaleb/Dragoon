package com.itiscaleb.util;

import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import java.util.Iterator;

public class ClientSkillScheduler extends SkillScheduler{

    public ClientSkillScheduler(){
        MinecraftForge.EVENT_BUS.addListener(this::onClientTick);
    }

    public void onClientTick(TickEvent.ClientTickEvent event){
        World world = Minecraft.getInstance().world;
        if(!tickList.containsKey(world)){
            tickList.put(world, Sets.newTreeSet(SkillRunnable.compare()));
        }
        Iterator<SkillRunnable> iterator = tickList.get(world).iterator();

        while (iterator.hasNext()){
            SkillRunnable runnable = iterator.next();
            if(runnable.scheduleTime > world.getGameTime()){
                break;
            }else if(!runnable.isCancel){
                iterator.remove();
                runnable.runnable.run(runnable);
            }else {
                iterator.remove();
            }
        }
    }

}
