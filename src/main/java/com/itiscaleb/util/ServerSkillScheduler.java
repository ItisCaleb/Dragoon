package com.itiscaleb.util;

import com.google.common.collect.Sets;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import java.util.Iterator;

public class ServerSkillScheduler extends SkillScheduler{


    public ServerSkillScheduler(){
        MinecraftForge.EVENT_BUS.addListener(this::onServerTick);
    }

    public void onServerTick(TickEvent.WorldTickEvent event){
        if(!tickList.containsKey(event.world)){
            tickList.put(event.world, Sets.newTreeSet(SkillRunnable.compare()));
        }
        Iterator<SkillRunnable> iterator = tickList.get(event.world).iterator();

        while (iterator.hasNext()){
            SkillRunnable runnable = iterator.next();
            if(runnable.scheduleTime > event.world.getGameTime()){
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
