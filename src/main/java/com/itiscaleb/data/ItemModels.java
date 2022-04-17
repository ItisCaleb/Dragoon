package com.itiscaleb.data;

import com.itiscaleb.Dragoon;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Dragoon.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> item.getRegistryName().getNamespace().equals(Dragoon.MODID))
                .forEach(this::addGeneratedModel);
    }

    private void addGeneratedModel(Item item) {
        String name = item.getRegistryName().getPath();
        withExistingParent("item/" + name, "item/generated").texture("layer0", new ResourceLocation(Dragoon.MODID, "item/" + name));
    }
}
