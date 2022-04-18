package com.itiscaleb.client.gui;

import com.itiscaleb.Dragoon;
import com.itiscaleb.client.KeyBindingConfig;
import com.itiscaleb.common.capability.dragoon.DragoonAbility;
import com.itiscaleb.common.capability.dragoon.IDragoonAbility;
import com.itiscaleb.common.item.DragoonItems;
import com.itiscaleb.common.item.misc.materia.SkillMateriaCrystal;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementEntryGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.client.util.InputMappings;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.GuiContainerEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class DragoonConfig extends Screen {

    private static ResourceLocation location = new ResourceLocation(Dragoon.MODID,"textures/skill/dragoon.png");
    private static SkillSlotGui EMPTYSKILL = new SkillSlotGui(-32f,-32f);
    SliderPercentageOption sliderOption;
    Widget sliderBar;
    HashMap<String,SkillSlotGui> skillSlot = new HashMap<>();
    IDragoonAbility ability;
    ArrayList<SkillSlotGui> learnedSkill;
    ArrayList<SkillSlotGui> equipedSkill;
    int selectedIndex = 0;

    public DragoonConfig() {
        super(new StringTextComponent("Ability Setting Menu"));
        skillSlot.put("dragoon:true_thrust_materia",new SkillSlotGui(0f,0f));
        skillSlot.put("dragoon:wheeling_thrust_materia",new SkillSlotGui(32f,0f));
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
        equipedSkill = new ArrayList<>();
        for(SkillMateriaCrystal crystal:ability.getSkills()){
            if(crystal!= null){
                SkillSlotGui gui = skillSlot.get(crystal.getRegistryName().toString());
                gui.setUsed(true);
                equipedSkill.add(gui.clone());
            }else {
                equipedSkill.add(EMPTYSKILL.clone());
            }
        }
        learnedSkill = new ArrayList<>();
        for (String skill: ability.getLearnedSkill()){
            learnedSkill.add(skillSlot.get(skill));
        }
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.sliderBar.render(matrixStack,mouseX,mouseY,partialTicks);
        matrixStack.push();
        this.minecraft.getTextureManager().bindTexture(location);
        int x = this.width / 2 - 100;
        matrixStack.scale(0.5f,0.5f,0.5f);
        for (SkillSlotGui skillSlot : this.equipedSkill){
            skillSlot.draw(matrixStack,x,30);
            x += 38;
        }

        x=this.width / 2 - 100;
        for (SkillSlotGui learnSlot : this.learnedSkill) {
            learnSlot.draw(matrixStack, x, 70);
            x += 38;
        }
        matrixStack.pop();
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = (this.width / 2 - 100)/2;
        int i = 0;
        for (SkillMateriaCrystal ignored :ability.getSkills()){
            if (isSlotSelected(x,15,mouseX,mouseY)){
                selectedIndex = i;
                
            }
            x += 19;
            i++;
        }
        x= (this.width / 2 - 100)/2;
        i=0;
        for (SkillSlotGui learnSlot : this.learnedSkill) {
            if (isSlotSelected(x,35,mouseX,mouseY)){
                ability.setSkillSlot(selectedIndex, DragoonItems.SKILL_MATERIA.get(ability.getLearnedSkill().get(i)));
                equipedSkill.set(selectedIndex,learnSlot.clone());
            }
            x += 19;
            i++;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }


    private boolean isSlotSelected(int x, int y,double mouseX, double mouseY) {
        return this.isPointInRegion(x, y, 16, 16, mouseX, mouseY);
    }

    protected boolean isPointInRegion(int x, int y, int width, int height, double mouseX, double mouseY) {
        int i = 0;
        int j = 0;
        mouseX = mouseX - (double)i;
        mouseY = mouseY - (double)j;
        return mouseX >= (double)(x - 1) && mouseX < (double)(x + width + 1) && mouseY >= (double)(y - 1) && mouseY < (double)(y + height + 1);
    }

    @Override
    public void onClose() {
        ability.syncServer();
        super.onClose();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        InputMappings.Input mouseKey = InputMappings.getInputByCode(keyCode, scanCode);
        if(KeyBindingConfig.abilityMenu.isActiveAndMatches(mouseKey)){
            this.closeScreen();
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
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
