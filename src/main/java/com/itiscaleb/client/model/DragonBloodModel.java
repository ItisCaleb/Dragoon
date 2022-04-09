package com.itiscaleb.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class DragonBloodModel extends BipedModel<LivingEntity> {

    private final ModelRenderer armorHead;
    private final ModelRenderer side;
    private final ModelRenderer side2;
    private final ModelRenderer horn;
    private final ModelRenderer horn2;
    private final ModelRenderer armorBody;
    private final ModelRenderer armorRightArm;
    private final ModelRenderer armorLeftArm;
    private final ModelRenderer armorLeftLeg;
    private final ModelRenderer armorLeftBoot;
    private final ModelRenderer armorRightLeg;
    private final ModelRenderer armorRightBoot;

    public DragonBloodModel(float size) {
        super(size,0 ,128,128);
        armorHead = new ModelRenderer(this);
        armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.addChild(armorHead);
        armorHead.setTextureOffset(32, 39).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
        armorHead.setTextureOffset(86, 68).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(86, 68).addBox(3.0F, -4.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(86, 68).addBox(-4.0F, -4.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(30, 56).addBox(-1.0F, 0.0F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(31, 77).addBox(-5.0F, -9.0F, -3.0F, 1.0F, 8.0F, 7.0F, 0.0F, false);
        armorHead.setTextureOffset(22, 59).addBox(-7.0F, -10.0F, -3.0F, 1.0F, 8.0F, 10.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 51).addBox(-6.0F, -11.0F, -3.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
        armorHead.setTextureOffset(67, 49).addBox(-5.0F, -11.0F, 6.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(54, 50).addBox(5.0F, -11.0F, -3.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
        armorHead.setTextureOffset(68, 62).addBox(6.0F, -10.0F, -2.0F, 1.0F, 9.0F, 8.0F, 0.0F, false);
        armorHead.setTextureOffset(44, 62).addBox(-7.0F, -10.0F, -2.0F, 1.0F, 9.0F, 8.0F, 0.0F, false);
        armorHead.setTextureOffset(60, 16).addBox(6.0F, -9.0F, -3.0F, 1.0F, 7.0F, 10.0F, 0.0F, false);
        armorHead.setTextureOffset(15, 77).addBox(4.0F, -9.0F, -3.0F, 1.0F, 8.0F, 7.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 76).addBox(-4.0F, -3.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(88, 46).addBox(-3.0F, -1.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(72, 16).addBox(3.0F, -3.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 14).addBox(4.0F, -5.0F, -5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(9, 88).addBox(1.0F, 0.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(88, 14).addBox(2.0F, -1.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(88, 36).addBox(-2.0F, 0.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(7, 14).addBox(-5.0F, -5.0F, -5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(40, 67).addBox(-1.0F, 1.0F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(34, 67).addBox(-1.0F, 2.0F, -7.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(59, 22).addBox(-2.0F, 1.0F, -6.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(34, 63).addBox(-4.0F, 0.0F, -6.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(28, 50).addBox(-5.0F, -10.0F, -4.0F, 10.0F, 1.0F, 8.0F, 0.0F, false);
        armorHead.setTextureOffset(18, 71).addBox(-5.0F, -10.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(12, 71).addBox(-4.0F, -9.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(6, 71).addBox(-3.0F, -8.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 7).addBox(-2.0F, -8.0F, -8.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 71).addBox(-1.0F, -6.0F, -10.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(56, 56).addBox(-1.0F, -3.0F, -9.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(33, 6).addBox(-1.0F, -4.0F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 24).addBox(-2.0F, -5.0F, -7.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(50, 12).addBox(-3.0F, -6.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(28, 45).addBox(-3.0F, -7.0F, -5.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 0).addBox(1.0F, -8.0F, -8.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(6, 51).addBox(-2.0F, -6.0F, -9.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 51).addBox(1.0F, -6.0F, -9.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(59, 16).addBox(2.0F, -8.0F, -7.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(22, 61).addBox(3.0F, -9.0F, -6.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(28, 61).addBox(4.0F, -10.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(65, 16).addBox(0.0F, -6.0F, -10.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(6, 84).addBox(-1.0F, -8.0F, -9.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 57).addBox(-2.0F, -9.0F, -7.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(29, 35).addBox(-2.0F, -10.0F, -6.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(60, 35).addBox(-4.0F, -10.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(34, 59).addBox(-4.0F, -11.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(60, 33).addBox(-4.0F, -12.0F, -4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(34, 65).addBox(-3.0F, -9.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(65, 6).addBox(-4.0F, -9.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(22, 66).addBox(-2.0F, -9.0F, -8.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(51, 88).addBox(2.0F, -2.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(47, 88).addBox(-3.0F, -2.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 71).addBox(-5.0F, -1.0F, -4.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        armorHead.setTextureOffset(52, 70).addBox(4.0F, -1.0F, -4.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
        armorHead.setTextureOffset(12, 56).addBox(-4.0F, -1.0F, 5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(87, 41).addBox(-4.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(4, 47).addBox(3.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(74, 79).addBox(5.0F, -10.0F, -4.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(64, 70).addBox(-6.0F, -10.0F, -4.0F, 1.0F, 9.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(29, 27).addBox(-5.0F, -11.0F, -4.0F, 10.0F, 1.0F, 11.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 27).addBox(-4.0F, -11.0F, -3.0F, 8.0F, 1.0F, 13.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 14).addBox(-5.0F, -11.0F, -3.0F, 10.0F, 1.0F, 12.0F, 0.0F, false);
        armorHead.setTextureOffset(65, 0).addBox(-6.0F, -10.0F, 7.0F, 12.0F, 4.0F, 2.0F, 0.0F, false);
        armorHead.setTextureOffset(28, 41).addBox(-3.0F, -10.0F, 9.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(32, 22).addBox(-2.0F, -5.0F, 8.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(12, 59).addBox(-4.0F, -6.0F, 8.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(12, 51).addBox(-5.0F, -6.0F, 7.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(33, 0).addBox(-6.0F, -12.0F, -2.0F, 12.0F, 1.0F, 8.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 42).addBox(-5.0F, -13.0F, -2.0F, 10.0F, 1.0F, 8.0F, 0.0F, false);
        armorHead.setTextureOffset(59, 9).addBox(-4.0F, -14.0F, 0.0F, 8.0F, 1.0F, 6.0F, 0.0F, false);
        armorHead.setTextureOffset(48, 9).addBox(-3.0F, -15.0F, 5.0F, 6.0F, 1.0F, 2.0F, 0.0F, false);
        armorHead.setTextureOffset(33, 9).addBox(-3.0F, -15.0F, 2.0F, 6.0F, 1.0F, 3.0F, 0.0F, false);
        armorHead.setTextureOffset(51, 85).addBox(-4.0F, -15.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(62, 45).addBox(5.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(24, 80).addBox(4.0F, -14.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(5, 82).addBox(3.0F, -15.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(13, 82).addBox(-5.0F, -14.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(9, 82).addBox(-6.0F, -13.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(17, 82).addBox(-3.0F, -16.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(28, 48).addBox(2.0F, -16.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 21).addBox(-2.0F, -16.0F, 5.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
        armorHead.setTextureOffset(58, 39).addBox(-3.0F, -14.0F, -1.0F, 6.0F, 1.0F, 9.0F, 0.0F, false);
        armorHead.setTextureOffset(32, 14).addBox(-4.0F, -13.0F, -2.0F, 8.0F, 1.0F, 11.0F, 0.0F, false);
        armorHead.setTextureOffset(0, 0).addBox(-5.0F, -12.0F, -3.0F, 10.0F, 1.0F, 13.0F, 0.0F, false);

        side = new ModelRenderer(this);
        side.setRotationPoint(0.0F, 24.0F, 2.0F);
        armorHead.addChild(side);
        side.setTextureOffset(5, 88).addBox(-9.0F, -31.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side.setTextureOffset(86, 33).addBox(-9.0F, -32.0F, 1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(47, 79).addBox(-11.0F, -31.0F, 1.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        side.setTextureOffset(81, 21).addBox(-10.0F, -31.0F, 2.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        side.setTextureOffset(86, 19).addBox(-11.0F, -32.0F, 2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(31, 77).addBox(-12.0F, -33.0F, 3.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(84, 77).addBox(-13.0F, -34.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(86, 11).addBox(-14.0F, -34.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(46, 65).addBox(-9.0F, -31.0F, 1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(7, 33).addBox(-8.0F, -30.0F, 0.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(87, 87).addBox(-8.0F, -29.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side.setTextureOffset(78, 87).addBox(-9.0F, -31.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side.setTextureOffset(70, 87).addBox(-12.0F, -31.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side.setTextureOffset(86, 6).addBox(-12.0F, -30.0F, 2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side.setTextureOffset(87, 64).addBox(-12.0F, -32.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side.setTextureOffset(87, 26).addBox(-13.0F, -33.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side.setTextureOffset(86, 85).addBox(-13.0F, -32.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        side2 = new ModelRenderer(this);
        side2.setRotationPoint(0.0F, 0.0F, 0.0F);
        side.addChild(side2);
        side2.setTextureOffset(86, 74).addBox(8.0F, -31.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side2.setTextureOffset(0, 86).addBox(8.0F, -32.0F, 1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(79, 39).addBox(10.0F, -31.0F, 1.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        side2.setTextureOffset(81, 6).addBox(9.0F, -31.0F, 2.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        side2.setTextureOffset(82, 85).addBox(10.0F, -32.0F, 2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(7, 27).addBox(11.0F, -33.0F, 3.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(84, 60).addBox(12.0F, -34.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(65, 85).addBox(13.0F, -34.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(7, 7).addBox(8.0F, -31.0F, 1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(7, 0).addBox(7.0F, -30.0F, 0.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(86, 71).addBox(7.0F, -29.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side2.setTextureOffset(70, 84).addBox(8.0F, -31.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side2.setTextureOffset(78, 60).addBox(11.0F, -31.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side2.setTextureOffset(59, 85).addBox(11.0F, -30.0F, 2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        side2.setTextureOffset(24, 77).addBox(11.0F, -32.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side2.setTextureOffset(77, 22).addBox(12.0F, -33.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        side2.setTextureOffset(86, 22).addBox(12.0F, -32.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        horn = new ModelRenderer(this);
        horn.setRotationPoint(0.0F, 6.0F, 0.0F);
        armorHead.addChild(horn);
        horn.setTextureOffset(59, 16).addBox(-7.0F, -18.0F, 1.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        horn.setTextureOffset(83, 65).addBox(-8.0F, -19.0F, 3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(32, 14).addBox(-8.0F, -18.0F, 2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        horn.setTextureOffset(12, 76).addBox(-9.0F, -19.0F, 6.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        horn.setTextureOffset(58, 39).addBox(-8.0F, -24.0F, 7.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        horn.setTextureOffset(0, 33).addBox(-8.0F, -24.0F, 10.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        horn.setTextureOffset(84, 16).addBox(-8.0F, -21.0F, 8.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(18, 76).addBox(-9.0F, -23.0F, 9.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        horn.setTextureOffset(47, 85).addBox(-8.0F, -25.0F, 8.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(0, 82).addBox(-9.0F, -24.0F, 7.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        horn.setTextureOffset(85, 43).addBox(-10.0F, -23.0F, 7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(84, 37).addBox(-7.0F, -25.0F, 9.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(38, 14).addBox(-7.0F, -24.0F, 8.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        horn.setTextureOffset(82, 26).addBox(-5.0F, -26.0F, 10.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        horn.setTextureOffset(53, 85).addBox(-5.0F, -27.0F, 12.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(63, 85).addBox(-5.0F, -28.0F, 13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        horn.setTextureOffset(80, 30).addBox(-6.0F, -26.0F, 9.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        horn.setTextureOffset(56, 50).addBox(-9.0F, -19.0F, 3.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        horn.setTextureOffset(35, 27).addBox(-9.0F, -19.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        horn.setTextureOffset(78, 84).addBox(-9.0F, -16.0F, 3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(78, 79).addBox(-9.0F, -23.0F, 6.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        horn.setTextureOffset(0, 14).addBox(-10.0F, -20.0F, 3.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
        horn.setTextureOffset(63, 81).addBox(-10.0F, -18.0F, 4.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        horn.setTextureOffset(84, 82).addBox(-10.0F, -17.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(12, 76).addBox(-10.0F, -22.0F, 5.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        horn.setTextureOffset(24, 77).addBox(-10.0F, -21.0F, 4.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        horn.setTextureOffset(72, 16).addBox(-9.0F, -21.0F, 4.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        horn.setTextureOffset(73, 33).addBox(-9.0F, -20.0F, 3.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        horn.setTextureOffset(78, 60).addBox(-11.0F, -20.0F, 4.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        horn.setTextureOffset(85, 30).addBox(-11.0F, -19.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn.setTextureOffset(0, 76).addBox(-11.0F, -21.0F, 5.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        horn2 = new ModelRenderer(this);
        horn2.setRotationPoint(0.0F, 0.0F, 0.0F);
        horn.addChild(horn2);
        horn2.setTextureOffset(0, 51).addBox(6.0F, -18.0F, 1.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        horn2.setTextureOffset(68, 81).addBox(7.0F, -19.0F, 3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(29, 27).addBox(7.0F, -18.0F, 2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        horn2.setTextureOffset(6, 76).addBox(8.0F, -19.0F, 6.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        horn2.setTextureOffset(0, 41).addBox(7.0F, -24.0F, 7.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        horn2.setTextureOffset(32, 14).addBox(7.0F, -24.0F, 10.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        horn2.setTextureOffset(60, 81).addBox(7.0F, -21.0F, 8.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(63, 39).addBox(8.0F, -23.0F, 9.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        horn2.setTextureOffset(40, 81).addBox(7.0F, -25.0F, 8.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(55, 81).addBox(8.0F, -24.0F, 7.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        horn2.setTextureOffset(80, 35).addBox(9.0F, -23.0F, 7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(40, 77).addBox(6.0F, -25.0F, 9.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(29, 27).addBox(6.0F, -24.0F, 8.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        horn2.setTextureOffset(81, 11).addBox(4.0F, -26.0F, 10.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        horn2.setTextureOffset(79, 45).addBox(4.0F, -27.0F, 12.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(57, 85).addBox(4.0F, -28.0F, 13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        horn2.setTextureOffset(79, 16).addBox(5.0F, -26.0F, 9.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        horn2.setTextureOffset(33, 0).addBox(8.0F, -19.0F, 3.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        horn2.setTextureOffset(0, 27).addBox(8.0F, -19.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        horn2.setTextureOffset(44, 79).addBox(8.0F, -16.0F, 3.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(78, 65).addBox(8.0F, -23.0F, 6.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        horn2.setTextureOffset(0, 0).addBox(9.0F, -20.0F, 3.0F, 1.0F, 2.0F, 5.0F, 0.0F, false);
        horn2.setTextureOffset(72, 22).addBox(9.0F, -18.0F, 4.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        horn2.setTextureOffset(58, 45).addBox(9.0F, -17.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(12, 71).addBox(9.0F, -22.0F, 5.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        horn2.setTextureOffset(0, 33).addBox(9.0F, -21.0F, 4.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        horn2.setTextureOffset(0, 27).addBox(8.0F, -21.0F, 4.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        horn2.setTextureOffset(0, 7).addBox(8.0F, -20.0F, 3.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        horn2.setTextureOffset(0, 71).addBox(10.0F, -20.0F, 4.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        horn2.setTextureOffset(0, 47).addBox(10.0F, -19.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        horn2.setTextureOffset(22, 61).addBox(10.0F, -21.0F, 5.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        armorBody = new ModelRenderer(this);
        armorBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.addChild(armorBody);


        armorRightArm = new ModelRenderer(this);
        armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightArm.addChild(armorRightArm);


        armorLeftArm = new ModelRenderer(this);
        armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftArm.addChild(armorLeftArm);


        armorLeftLeg = new ModelRenderer(this);
        armorLeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftLeg.addChild(armorLeftLeg);


        armorLeftBoot = new ModelRenderer(this);
        armorLeftBoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftLeg.addChild(armorLeftBoot);


        armorRightLeg = new ModelRenderer(this);
        armorRightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightLeg.addChild(armorRightLeg);


        armorRightBoot = new ModelRenderer(this);
        armorRightBoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightLeg.addChild(armorRightBoot);

    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.render(matrixStack,buffer,packedLight,packedOverlay,red,green,blue,alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}