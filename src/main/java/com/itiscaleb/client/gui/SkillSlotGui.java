package com.itiscaleb.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AbstractGui;

public class SkillSlotGui extends AbstractGui {

    private final float offsetX;
    private final float offsetY;
    private boolean isUsed = false;

    SkillSlotGui(float offsetX, float offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void draw(MatrixStack stack, int x, int y){
        stack.push();
        if(isUsed){
            RenderSystem.blendColor(-1f,-1f,-1f,1f);
        }
        int color = isUsed?-16777216:-1;
        blit(stack,x,y,offsetX,offsetY,32,32,160,160);
        hLine(stack,x-1,x+33,y-1,color);
        hLine(stack,x-1,x+33,y+33,color);
        vLine(stack,x-1,y-1,y+33,color);
        vLine(stack,x+33,y-1,y+33,color);
        stack.pop();
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public SkillSlotGui clone(){
        return new SkillSlotGui(offsetX,offsetY);
    }
}
