package com.itiscaleb.client.gui;

import com.itiscaleb.Dragoon;
import com.itiscaleb.capability.dragoon.DragoonAbility;
import com.itiscaleb.capability.dragoon.IDragoonAbility;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.concurrent.atomic.AtomicReference;

public class DragoonConfig extends Screen {

    SliderPercentageOption sliderOption;
    Widget sliderBar;
    IDragoonAbility ability;


    public DragoonConfig() {
        super(new StringTextComponent("Ability Setting Menu"));
    }

    @Override
    protected void init() {
        ability = getAbility();
        this.sliderOption = new SliderPercentageOption(Dragoon.MODID+"sliderbar",
                0,
                ability.getStage().jumpBoost,
                0.1f,
                (setting)-> (double) ability.getJumpForce(),
                (gameSettings, aDouble) -> ability.setJumpForce(aDouble.floatValue()),
                (gameSettings, sliderPercentageOption) ->
                        ITextComponent.getTextComponentOrEmpty(String.format("Jump Force: +%.1f",sliderPercentageOption.get(gameSettings))));
        this.sliderBar = sliderOption.createWidget(Minecraft.getInstance().gameSettings,this.width / 2 - 100, 120, 200);
        this.children.add(sliderBar);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.sliderBar.render(matrixStack,mouseX,mouseY,partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        ability.syncServer();
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private IDragoonAbility getAbility(){
        AtomicReference<IDragoonAbility> a = new AtomicReference<>();
        Minecraft.getInstance().player.getCapability(DragoonAbility.CAPABILITY).ifPresent(a::set);
        return a.get();
    }
}
