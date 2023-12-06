package net.boypika.heartcanister.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.boypika.heartcanister.HeartCanister.*;

public class ModItemGroup {
    public static final ItemGroup HEARTCANISTERGROUP = new ItemGroup.Builder(null, -1)
            .icon(() -> REDCANISTER.getDefaultStack())
            .displayName(Text.translatable("itemGroup.heartcanister.hearts"))
            .noScrollbar()
            .entries(((displayContext, entries) -> {
                if (FabricLoader.getInstance().isModLoaded("tconstruct")){
                    entries.add(CANISTER);
                    entries.add(REDHEART);
                    entries.add(YELLOWHEART);
                    entries.add(GREENHEART);
                    entries.add(REDCANISTER);
                    entries.add(YELLOWCANISTER);
                    entries.add(GREENCANISTER);
                } else {
                    entries.add(JEWELED);
                    entries.add(NECROTIC);
                    entries.add(CANISTER);
                    entries.add(REDHEART);
                    entries.add(YELLOWHEART);
                    entries.add(GREENHEART);
                    entries.add(REDCANISTER);
                    entries.add(YELLOWCANISTER);
                    entries.add(GREENCANISTER);
                }

            }))
            .build();
    public static void registerModGroup(){
        Registry.register(Registries.ITEM_GROUP, new Identifier("heartcanister", "hearts"), HEARTCANISTERGROUP);
    }
}
