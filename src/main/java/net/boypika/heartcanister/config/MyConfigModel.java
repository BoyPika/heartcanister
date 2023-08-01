package net.boypika.heartcanister.config;

import io.wispforest.owo.config.annotation.Config;

@Config(wrapperName = "HeartCanisterConfig", name = "heartcanister")
public class MyConfigModel {
    public boolean GiveHealthOnEquip = false;
    public boolean TakeHealthOnUnequip = true;
    public float RedHeathHealAmountWhenAte = 20f;
    public float YellowHealthHealAmountWhenAte = 40f;
    public float GreenHealthHealAmountWhenAte = 60f;
    public float RedHeartLootChance = 1F;
    public float YellowHeartDungeonLootChance = 0.5F;
    public float YellowHeartFortressLootChance = 1F;
    public float GreenHeartLootChance = 0.5F;
    public int SingleHealthCanisterHealthValue = 2;
    public int CanisterStackCount = 10;
    public int HeartStackCount = 10;
}
