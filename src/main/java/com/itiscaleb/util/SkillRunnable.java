package com.itiscaleb.util;

import net.minecraft.entity.LivingEntity;

import java.util.Comparator;

public class SkillRunnable{
    LivingEntity caster;
    String skillName;
    long scheduleTime;
    boolean isCancel = false;
    SkillRunnableWrapper runnable;
    SkillRunnable(LivingEntity caster, String skillName, long scheduleTime, SkillRunnableWrapper runnable){
        this.caster = caster;
        this.skillName = skillName;
        this.scheduleTime = scheduleTime;
        this.runnable = runnable;
    }


    public boolean equals(Object object) {
        if (!(object instanceof SkillRunnable)) {
            return false;
        } else {
            SkillRunnable runnable = (SkillRunnable) object;
            return this.caster.equals(runnable.caster) && skillName.equals(runnable.skillName);
        }
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public static Comparator<SkillRunnable> compare(){
        return Comparator.comparingLong(runnable-> runnable.scheduleTime);
    }

    public interface SkillRunnableWrapper{
        void run(SkillRunnable runnable);
    }
}
