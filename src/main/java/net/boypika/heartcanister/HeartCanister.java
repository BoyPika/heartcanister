package net.boypika.heartcanister;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.boypika.heartcanister.config.HeartCanisterConfig;
import net.boypika.heartcanister.effects.GreenHeartEffect;
import net.boypika.heartcanister.effects.RedHeartEffect;
import net.boypika.heartcanister.effects.YellowHeartEffect;
import net.boypika.heartcanister.potion.ModPotions;
import net.boypika.heartcanister.trinkets.HeartCanisterItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.effect.*;
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
        JEWELED = registerItem("jeweled_apple", 64, new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 0), 1).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200, 0), 1).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 4),1).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,0),1).alwaysEdible().build());
        NECROTIC = registerItem("necrotic_bone",64, null);
        CANISTER = registerItem("empty_canister", CONFIG.nestedStackCount.CanisterStackCount(), null);
        REDHEART = registerHeart("red_heart", REDHEARTEFFECT);
        YELLOWHEART = registerHeart("yellow_heart", YELLOWHEARTEFFECT);
        GREENHEART = registerHeart("green_heart", GREENHEARTEFFECT);
        REDCANISTER = registerHeartCanister("red_canister");
        YELLOWCANISTER = registerHeartCanister("yellow_canister");
        GREENCANISTER = registerHeartCanister("green_canister");
    }
    private static InstantStatusEffect registerEffect(Identifier id, InstantStatusEffect entry) {
        return  Registry.register(Registries.STATUS_EFFECT, id, entry);
    }
    private static Item registerItem(String name, int stackCount, FoodComponent food){
        return Registry.register(Registries.ITEM, new Identifier("heartcanister", name), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(stackCount).food(food)));
    }
    private static Item registerHeart(String name, StatusEffect effect){
        return Registry.register(Registries.ITEM, new Identifier("heartcanister", name), new Item(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.HeartStackCount()).food(new FoodComponent.Builder().hunger(0).saturationModifier(0).statusEffect(new StatusEffectInstance(effect, 1, 0), 1).alwaysEdible().build())));
    }
    private static Item registerHeartCanister(String name){
     return Registry.register(Registries.ITEM, new Identifier("heartcanister", name), new HeartCanisterItem(new OwoItemSettings().group(HEARTCANISTERGROUP).maxCount(CONFIG.nestedStackCount.CanisterStackCount())));
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
