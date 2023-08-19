package net.boypika.heartcanister.util;

import net.boypika.heartcanister.HeartCanister;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup HEARTCANISTERGROUP = FabricItemGroupBuilder.build(HeartCanister.Identifier("hearts"),
            () -> new ItemStack(HeartCanister.REDCANISTER));
}
