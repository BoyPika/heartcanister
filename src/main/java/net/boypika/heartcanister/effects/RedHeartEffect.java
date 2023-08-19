package net.boypika.heartcanister.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import static net.boypika.heartcanister.HeartCanister.config;
public class RedHeartEffect extends InstantStatusEffect {
    public RedHeartEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int pAmplifier) {
        if (entity != null && !entity.isDead()){
            entity.heal(config().nestedHeartObject.RedHealthHealAmountWhenAte);
        }
        super.applyUpdateEffect(entity, pAmplifier);
    }
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}