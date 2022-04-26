package com.itiscaleb;

import com.itiscaleb.util.ClientSkillScheduler;
import com.itiscaleb.util.ServerSkillScheduler;
import com.itiscaleb.util.SkillScheduler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Dragoon.MODID)
public class Dragoon {

    // Directly reference a log4j logger.
    public static final String MODID = "dragoon";
    public static final Logger LOGGER = LogManager.getLogger();
    @OnlyIn(Dist.CLIENT)
    static final SkillScheduler clientScheduler = new ClientSkillScheduler();
    static final SkillScheduler serverScheduler = new ServerSkillScheduler();

    public Dragoon() {

    }

    public static SkillScheduler getScheduler(World world) {
        return (!world.isRemote)?serverScheduler:clientScheduler;
    }
}
