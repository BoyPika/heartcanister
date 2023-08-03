package net.boypika.heartcanister.potion;

import net.boypika.heartcanister.HeartCanister;
import net.boypika.heartcanister.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Potion RED_HEART;
    public static final Potion YELLOW_HEART;
    public static final Potion GREEN_HEART;

    static {
        RED_HEART = registerPotion("red_heart", new Potion(new StatusEffectInstance(HeartCanister.REDHEARTEFFECT, 1, 0)));
        YELLOW_HEART = registerPotion("yellow_heart", new Potion(new StatusEffectInstance(HeartCanister.YELLOWHEARTEFFECT, 1, 0)));
        GREEN_HEART = registerPotion("green_heart", new Potion(new StatusEffectInstance(HeartCanister.GREENHEARTEFFECT, 1,0)));
    }

    public static Potion registerPotion(String name, Potion potion) {
        return Registry.register(Registry.POTION, new Identifier("heartcanister", name), potion);
    }


    public static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, HeartCanister.REDHEART, RED_HEART);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, HeartCanister.YELLOWHEART, YELLOW_HEART);
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, HeartCanister.GREENHEART, GREEN_HEART);
    }

}
