package net.boypika.heartcanister;

import net.boypika.heartcanister.config.ModConfig;
import net.boypika.heartcanister.effects.GreenHeartEffect;
import net.boypika.heartcanister.effects.RedHeartEffect;
import net.boypika.heartcanister.effects.YellowHeartEffect;
import net.boypika.heartcanister.potion.ModPotions;
import net.boypika.heartcanister.trinkets.HeartCanisterItem;
import net.boypika.heartcanister.trinkets.HeartItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.effect.*;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.monkeyconfig538.ConfigManager;

public class HeartCanister implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("heartcanister");
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
        REDHEART = Registry.register(Registries.ITEM, Identifier("red_heart"), (new HeartItem.RedHeartItem(registerHeart(REDHEARTEFFECT))));
        YELLOWHEART = Registry.register(Registries.ITEM, Identifier("yellow_heart"), (new HeartItem.YellowHeartItem(registerHeart(YELLOWHEARTEFFECT))));
        GREENHEART = Registry.register(Registries.ITEM, Identifier("green_heart"), (new HeartItem.GreenHeartItem(registerHeart(GREENHEARTEFFECT))));
        REDCANISTER = registerHeartCanister("red_canister");
        YELLOWCANISTER = registerHeartCanister("yellow_canister");
        GREENCANISTER = registerHeartCanister("green_canister");
    }
    public static InstantStatusEffect registerEffect(Identifier id, InstantStatusEffect entry) {
        return  Registry.register(Registries.STATUS_EFFECT, id, entry);
    }
    public static Item registerItem(String name, int stackCount, FoodComponent food){
        return Registry.register(Registries.ITEM, Identifier(name), new Item(new Item.Settings().maxCount(stackCount).food(food)));
    }
    public static Item.Settings registerHeart(StatusEffect effect){
        return new Item.Settings().maxCount(config().nestedStackCount.HeartStackCount).food(new FoodComponent.Builder().hunger(0).saturationModifier(0).statusEffect(new StatusEffectInstance(effect, 1, 0), 1).alwaysEdible().build());
    }

    public static Identifier Identifier(String name){
        return new Identifier("heartcanister", name);
    }
    public static Item registerHeartCanister(String name){
     return Registry.register(Registries.ITEM, Identifier(name), new HeartCanisterItem(new Item.Settings().maxCount(config().nestedStackCount.CanisterStackCount)));
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
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.RedHeartLootChance))
                        .with(ItemEntry.builder(REDHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.RedHeartLootChance, config().nestedLootChance.RedHeartLootChance)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (DUNGEON.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.YellowHeartDungeonLootChance))
                        .with(ItemEntry.builder(YELLOWHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.YellowHeartDungeonLootChance, config().nestedLootChance.YellowHeartDungeonLootChance)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (FORTRESS.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.YellowHeartFortressLootChance))
                        .with(ItemEntry.builder(YELLOWHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.YellowHeartFortressLootChance, config().nestedLootChance.YellowHeartFortressLootChance)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (FORTRESS.equals(id)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(config().nestedLootChance.GreenHeartLootChance))
                        .with(ItemEntry.builder(GREENHEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(config().nestedLootChance.GreenHeartLootChance, config().nestedLootChance.GreenHeartLootChance)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
    @Override
    public void onInitialize() {
        ConfigManager.init(new ModConfig(), "heartcanister");
        registerItems();
        modifyLootTables();
        ModPotions.registerPotionRecipes();
    }
    public static ModConfig config() {
        return (ModConfig) ConfigManager.get("heartcanister");
    }
    public static final ItemGroup HEARTCANISTERGROUP = FabricItemGroup.builder(new Identifier("heartcanister", "hearts"))
            .icon(() -> REDCANISTER.getDefaultStack())
            .displayName(Text.translatable("itemGroup.heartcanister.hearts"))
            .noScrollbar()
            .entries(((displayContext, entries) -> {
                entries.add(JEWELED);
                entries.add(NECROTIC);
                entries.add(CANISTER);
                entries.add(REDHEART);
                entries.add(YELLOWHEART);
                entries.add(GREENHEART);
                entries.add(REDCANISTER);
                entries.add(YELLOWCANISTER);
                entries.add(GREENCANISTER);
            }))
            .build();
}
