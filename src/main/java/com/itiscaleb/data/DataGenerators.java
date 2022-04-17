package com.itiscaleb.data;


import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void genData(GatherDataEvent e){
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();
        if(e.includeClient()){
            generator.addProvider(new ItemModels(generator,helper));
        }
    }
}
