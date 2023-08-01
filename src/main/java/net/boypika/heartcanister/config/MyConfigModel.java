package net.boypika.heartcanister.config;

import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = "heartcanister")
@Config(wrapperName = "HeartCanisterConfig", name = "heartcanister")
public class MyConfigModel {
    @SectionHeader("sectionhead")
    @Nest
    public TrinketsNest nestedTrinketObject = new TrinketsNest();
    public static class TrinketsNest{
        public boolean GiveHealthOnEquip = false;
        public boolean TakeHealthOnUnequip = true;
    }
    @Nest
    public HeartsNest nestedHeartObject = new HeartsNest();
    public static class HeartsNest{
        public float RedHealthHealAmountWhenAte = 20f;
        public float YellowHealthHealAmountWhenAte = 40f;
        public float GreenHealthHealAmountWhenAte = 60f;
        public int SingleHealthCanisterHealthValue = 2;
    }
    @Nest
    public LootNest nestedLootChance = new LootNest();
    public static class LootNest{
        @RangeConstraint(min = 0F, max = 1F)
        public float RedHeartLootChance = 1F;
        @RangeConstraint(min = 0F, max = 1F)
        public float YellowHeartDungeonLootChance = 0.5F;
        @RangeConstraint(min = 0F, max = 1F)
        public float YellowHeartFortressLootChance = 1F;
        @RangeConstraint(min = 0F, max = 1F)
        public float GreenHeartLootChance = 0.5F;
    }
    @Nest
    public StackNest nestedStackCount = new StackNest();
    public static class StackNest{
        @RangeConstraint(min = 1, max = 64)
        public int CanisterStackCount = 10;
        @RangeConstraint(min = 1, max = 64)
        public int HeartStackCount = 10;
    }
}
