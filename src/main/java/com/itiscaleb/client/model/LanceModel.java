package com.itiscaleb.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.client.ForgeHooksClient;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class LanceModel implements IBakedModel {

    private final IBakedModel inventoryModel;
    private final IBakedModel handModel;

    public LanceModel(@Nonnull IBakedModel inventory,@Nonnull IBakedModel hand){
        inventoryModel = inventory;
        handModel = hand;
    }

    @Override
    public IBakedModel handlePerspective(TransformType cameraTransformType, MatrixStack mat) {
        IBakedModel model = inventoryModel;
        if (cameraTransformType == TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == TransformType.FIRST_PERSON_RIGHT_HAND
                || cameraTransformType == TransformType.THIRD_PERSON_LEFT_HAND || cameraTransformType == TransformType.THIRD_PERSON_RIGHT_HAND) {
            model = handModel;
        }
        return ForgeHooksClient.handlePerspective(model,cameraTransformType, mat);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return inventoryModel.getQuads(state,side,rand);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return inventoryModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return inventoryModel.isGui3d();
    }

    @Override
    public boolean isSideLit() {
        return inventoryModel.isSideLit();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return inventoryModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return inventoryModel.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return inventoryModel.getOverrides();
    }
}
