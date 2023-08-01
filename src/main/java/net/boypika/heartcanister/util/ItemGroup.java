package net.boypika.heartcanister.util;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.util.Identifier;

import static net.boypika.heartcanister.HeartCanister.REDCANISTER;

public class ItemGroup {
    public static final OwoItemGroup HEARTCANISTERGROUP = OwoItemGroup.builder(new Identifier("heartcanister", "hearts"), () -> Icon.of(REDCANISTER)).build();
}
