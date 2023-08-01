package net.boypika.heartcanister.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import static net.boypika.heartcanister.HeartCanister.CONFIG;
public class YellowHeartEffect extends InstantStatusEffect {
    public YellowHeartEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int pAmplifier) {
        if (entity != null && !entity.isDead()){
            entity.heal(CONFIG.nestedHeartObject.YellowHealthHealAmountWhenAte());
        }
        super.applyUpdateEffect(entity, pAmplifier);
    }
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}