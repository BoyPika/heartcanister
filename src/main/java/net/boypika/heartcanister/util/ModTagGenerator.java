package net.boypika.heartcanister.util;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ModTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    private static final TagKey<Item> NECROTIC_BONE = TagKey.of(RegistryKeys.ITEM, new Identifier("heartcanister:necrotic_bone"));
    private static final TagKey<Item> JEWELED_APPLE = TagKey.of(RegistryKeys.ITEM, new Identifier("heartcanister:jeweled_apple"));
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(NECROTIC_BONE)
                .addOptional(new Identifier("heartcanister", "necrotic_bone"))
                .addOptional(new Identifier("tconstruct", "necrotic_bone"));
        getOrCreateTagBuilder(JEWELED_APPLE)
                .addOptional(new Identifier("heartcanister", "jeweled_apple"))
                .addOptional(new Identifier("tconstruct", "jeweled_apple"));
    }
}
