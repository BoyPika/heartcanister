package net.boypika.heartcanister.util;

import net.boypika.heartcanister.HeartCanister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, HeartCanister.JEWELED).pattern(" d ").pattern("dad").pattern(" d ")
                    .input('d', Items.DIAMOND)
                    .input('a', Items.APPLE).criterion(FabricRecipeProvider.hasItem(Items.DIAMOND), FabricRecipeProvider.conditionsFromItem(Items.DIAMOND)).offerTo(exporter);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, HeartCanister.NECROTIC).pattern("ccc").pattern("cbc").pattern("ccc")
                    .input('b', Items.BONE)
                    .input('c', Items.COAL).criterion(FabricRecipeProvider.hasItem(Items.BONE), FabricRecipeProvider.conditionsFromItem(Items.BONE)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, HeartCanister.REDCANISTER).input(HeartCanister.REDHEART).input(HeartCanister.CANISTER).input(Items.GOLDEN_APPLE).criterion(FabricRecipeProvider.hasItem(HeartCanister.REDHEART), FabricRecipeProvider.conditionsFromItem(HeartCanister.REDHEART)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, HeartCanister.GREENCANISTER).input(HeartCanister.GREENHEART).input(HeartCanister.CANISTER).input(Items.NETHER_STAR).criterion(FabricRecipeProvider.hasItem(HeartCanister.GREENHEART), FabricRecipeProvider.conditionsFromItem(HeartCanister.GREENHEART)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, HeartCanister.CANISTER).pattern("iii")
                .input('i', Items.IRON_INGOT).criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT), FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);
    }
}
