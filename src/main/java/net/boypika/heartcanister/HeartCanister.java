package net.boypika.heartcanister;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.boypika.heartcanister.config.HeartCanisterConfig;
import net.boypika.heartcanister.effects.GreenHeartEffect;
import net.boypika.heartcanister.effects.RedHeartEffect;
import net.boypika.heartcanister.effects.YellowHeartEffect;
import net.boypika.heartcanister.potion.ModPotions;
import net.boypika.heartcanister.trinkets.GreenHeartCanisterItem;
import net.boypika.heartcanister.trinkets.RedHeartCanisterItem;
import net.boypika.heartcanister.trinkets.YellowHeartCanisterItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.boypika.heartcanister.util.ItemGroup.HEARTCANISTERGROUP;
import static net.minecraft.item.Items.register;

public class HeartCanister implements ModInitializer {
    public static HeartCanisterConfig CONFIG = HeartCanisterConfig.createAndLoad();
    public static final Item JEWELED;
    public static final Item NECROTIC;
    public static final Item CANISTER;
    public static final Item REDCANISTER;
    public static final Item REDHEART;
    public static final Item YELLOWCANISTER;
    public static final Item YELLOWHEART;
    public static final Item GREENCANISTER;
    public static final Item GREENHEART;
    public static final InstantStatusEffect GREENHEARTEFFECT = registerEffect(new Identifier("heartcanister", "green_heart"), new GreenHeartEffect(StatusEffectCategory.BENEFICIAL, 56133));
    public static final InstantStatusEffect YELLOWHEARTEFFECT = registerEffect(new Identifier("heartcanister","yellow_heart"), new YellowHeartEffect(StatusEffectCategory.BENEFICIAL, 16776451));
    public static final InstantStatusEffect REDHEARTEFFECT = registerEffect(new Identifier("heartcanister","red_heart"), new RedHeartEffect(StatusEffectCategory.BENEFICIAL, 16318464));

    static {
        JEWELED = register(new Identifier("heartcanister", "jeweled_apple"), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(64).food(new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 0), 1).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200, 0), 1).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 4),1).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,0),1).alwaysEdible().build())));
        NECROTIC = register(new Identifier("heartcanister", "necrotic_bone"), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(64)));
        CANISTER = register(new Identifier("heartcanister", "empty_canister"), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.CanisterStackCount())));
        REDCANISTER = register(new Identifier("heartcanister", "red_canister"), new RedHeartCanisterItem(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.CanisterStackCount())));
        REDHEART = register(new Identifier("heartcanister", "red_heart"), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.HeartStackCount()).food(new FoodComponent.Builder().hunger(0).saturationModifier(0).statusEffect(new StatusEffectInstance(REDHEARTEFFECT, 1, 0), 1).alwaysEdible().build())));
        YELLOWCANISTER = register(new Identifier("heartcanister", "yellow_canister"), new YellowHeartCanisterItem(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.CanisterStackCount())));
        YELLOWHEART = register(new Identifier("heartcanister", "yellow_heart"), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.HeartStackCount()).food(new FoodComponent.Builder().hunger(0).saturationModifier(0).statusEffect(new StatusEffectInstance(YELLOWHEARTEFFECT, 1, 0), 1).alwaysEdible().build())));
        GREENCANISTER = register(new Identifier("heartcanister", "green_canister"), new GreenHeartCanisterItem(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.CanisterStackCount())));
        GREENHEART = register(new Identifier("heartcanister", "green_heart"), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.HeartStackCount()).food(new FoodComponent.Builder().hunger(0).saturationModifier(0).statusEffect(new StatusEffectInstance(GREENHEARTEFFECT, 1, 0), 1).alwaysEdible().build())));
    }
    private static InstantStatusEffect registerEffect(Identifier id, InstantStatusEffect entry) {
        return  Registry.register(Registries.STATUS_EFFECT, id, entry);
    }
    private static final Identifier DUNGEON
            = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier FORTRESS
            = new Identifier("minecraft", "chests/nether_bridge");


    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if(DUNGEON.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CONFIG.nestedLootChance.RedHeartLootChance()))
                        .with(ItemEntry.builder(REDHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(CONFIG.nestedLootChance.RedHeartLootChance(), CONFIG.nestedLootChance.RedHeartLootChance())).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (DUNGEON.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CONFIG.nestedLootChance.YellowHeartDungeonLootChance()))
                        .with(ItemEntry.builder(YELLOWHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(CONFIG.nestedLootChance.YellowHeartDungeonLootChance(), CONFIG.nestedLootChance.YellowHeartDungeonLootChance())).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (FORTRESS.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CONFIG.nestedLootChance.YellowHeartFortressLootChance()))
                        .with(ItemEntry.builder(YELLOWHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(CONFIG.nestedLootChance.YellowHeartFortressLootChance(), CONFIG.nestedLootChance.YellowHeartFortressLootChance())).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (FORTRESS.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CONFIG.nestedLootChance.GreenHeartLootChance()))
                        .with(ItemEntry.builder(GREENHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(CONFIG.nestedLootChance.GreenHeartLootChance(), CONFIG.nestedLootChance.GreenHeartLootChance())).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
    @Override
    public void onInitialize() {
        modifyLootTables();
        ModPotions.registerPotionRecipes();
        HEARTCANISTERGROUP.initialize();
    }
}
