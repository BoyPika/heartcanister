package net.boypika.heartcanister;

import net.boypika.heartcanister.config.MyConfigModel;
import net.boypika.heartcanister.effects.GreenHeartEffect;
import net.boypika.heartcanister.effects.RedHeartEffect;
import net.boypika.heartcanister.effects.YellowHeartEffect;
import net.boypika.heartcanister.potion.ModPotions;
import net.boypika.heartcanister.trinkets.HeartCanisterItem;
import net.boypika.heartcanister.trinkets.HeartItem;
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
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import top.offsetmonkey538.monkeyconfig538.ConfigManager;

import static net.boypika.heartcanister.util.ModItemGroup.HEARTCANISTERGROUP;

public class HeartCanister implements ModInitializer {
    public static Item JEWELED;
    public static Item NECROTIC;
    public static Item CANISTER;
    public static Item REDCANISTER;
    public static Item REDHEART;
    public static Item YELLOWCANISTER;
    public static Item YELLOWHEART;
    public static Item GREENCANISTER;
    public static Item GREENHEART;
    public static final InstantStatusEffect GREENHEARTEFFECT = registerEffect(Identifier("green_heart"), new GreenHeartEffect(StatusEffectCategory.BENEFICIAL, 56133));
    public static final InstantStatusEffect YELLOWHEARTEFFECT = registerEffect(Identifier("yellow_heart"), new YellowHeartEffect(StatusEffectCategory.BENEFICIAL, 16776451));
    public static final InstantStatusEffect REDHEARTEFFECT = registerEffect(Identifier("red_heart"), new RedHeartEffect(StatusEffectCategory.BENEFICIAL, 16318464));
    public static void registerItems() {
        JEWELED = registerItem("jeweled_apple", 64, new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 0), 1).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200, 0), 1).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 4),1).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,0),1).alwaysEdible().build());
        NECROTIC = registerItem("necrotic_bone",64, null);
        CANISTER = registerItem("empty_canister", config().nestedStackCount.CanisterStackCount, null);
        REDHEART = register(Identifier("red_heart"), (new HeartItem.RedHeartItem(registerHeart(REDHEARTEFFECT))));
        YELLOWHEART = register(Identifier("yellow_heart"), (new HeartItem.YellowHeartItem(registerHeart(YELLOWHEARTEFFECT))));
        GREENHEART = register(Identifier("green_heart"), new HeartItem.GreenHeartItem(registerHeart(GREENHEARTEFFECT)));
        REDCANISTER = registerHeartCanister("red_canister");
        YELLOWCANISTER = registerHeartCanister("yellow_canister");
        GREENCANISTER = registerHeartCanister("green_canister");
    }
    public static Item register(Identifier id, Item item) {
        return register(RegistryKey.of(Registry.ITEM.getKey(), id), item);
    }
    public static Item register(RegistryKey<Item> key, Item item) {
        return Registry.register(Registry.ITEM, key.getValue(), item);
    }
    private static InstantStatusEffect registerEffect(Identifier id, InstantStatusEffect entry) {
        return  Registry.register(Registry.STATUS_EFFECT, id, entry);
    }
    private static Item registerItem(String name, int stackCount, FoodComponent food){
        return Registry.register(Registry.ITEM, Identifier(name), new Item(new Item.Settings().group(HEARTCANISTERGROUP).maxCount(stackCount).food(food)));
    }
    public static Item.Settings registerHeart(StatusEffect effect){
        return new Item.Settings().group(HEARTCANISTERGROUP).maxCount(config().nestedStackCount.HeartStackCount).food(new FoodComponent.Builder().hunger(0).saturationModifier(0).statusEffect(new StatusEffectInstance(effect, 1, 0), 1).alwaysEdible().build());
    }
    public static Identifier Identifier(String name){
        return new Identifier("heartcanister", name);
    }
    private static Item registerHeartCanister(String name){
        return Registry.register(Registry.ITEM, Identifier(name), new HeartCanisterItem(new Item.Settings().group(HEARTCANISTERGROUP).maxCount(config().nestedStackCount.CanisterStackCount)));
    }
    private static final Identifier DUNGEON
            = new Identifier("minecraft", "chests/simple_dungeon");
    private static final Identifier FORTRESS
            = new Identifier("minecraft", "chests/nether_bridge");
    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (DUNGEON.equals(id)){
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.RedHeartLootChance))
                        .with(ItemEntry.builder(REDHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.RedHeartLootChance, config().nestedLootChance.RedHeartLootChance)));
                tableBuilder.pool(pool);
            }
            if (DUNGEON.equals(id)){
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.YellowHeartDungeonLootChance))
                        .with(ItemEntry.builder(YELLOWHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.YellowHeartDungeonLootChance, config().nestedLootChance.YellowHeartDungeonLootChance)));
                tableBuilder.pool(pool);
            }
            if (FORTRESS.equals(id)){
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.YellowHeartFortressLootChance))
                        .with(ItemEntry.builder(YELLOWHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.YellowHeartFortressLootChance, config().nestedLootChance.YellowHeartFortressLootChance)));
                tableBuilder.pool(pool);
            }
            if (FORTRESS.equals(id)){
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.GreenHeartLootChance))
                        .with(ItemEntry.builder(GREENHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.GreenHeartLootChance, config().nestedLootChance.GreenHeartLootChance)));
                tableBuilder.pool(pool);
            }
        }));
    }
    @Override
    public void onInitialize() {
        ConfigManager.init(new MyConfigModel(), "heartcanister");
        registerItems();
        modifyLootTables();
        ModPotions.registerPotionRecipes();
    }
    public static MyConfigModel config() {
        return (MyConfigModel) ConfigManager.get("heartcanister");
    }
}