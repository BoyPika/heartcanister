package net.boypika.heartcanister.config;

import top.offsetmonkey538.monkeyconfig538.Config;

public class MyConfigModel extends Config {
    public TrinketsNest nestedTrinketObject = new TrinketsNest();
    public static class TrinketsNest{
        public boolean GiveHealthOnEquip = false;
        public boolean TakeHealthOnUnequip = true;
    }
    public HeartsNest nestedHeartObject = new HeartsNest();
    public static class HeartsNest{
        public float RedHealthHealAmountWhenAte = 20f;
        public float YellowHealthHealAmountWhenAte = 40f;
        public float GreenHealthHealAmountWhenAte = 60f;
        public int SingleHealthCanisterHealthValue = 2;
    }
    public LootNest nestedLootChance = new LootNest();
    public static class LootNest{
        public float RedHeartLootChance = 1F;
        public float YellowHeartDungeonLootChance = 0.5F;
        public float YellowHeartFortressLootChance = 1F;
        public float GreenHeartLootChance = 0.5F;
    }
    public StackNest nestedStackCount = new StackNest();
    public static class StackNest{
        public int CanisterStackCount = 10;
        public int HeartStackCount = 10;
    }

}