package com.itiscaleb;

import com.itiscaleb.item.DragoonItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DragoonGroup extends ItemGroup {
    public static DragoonGroup Instance = new DragoonGroup();
    public DragoonGroup() {
        super(Dragoon.MODID+".items");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(DragoonItems.DragonBloodHelmet);
    }
}
