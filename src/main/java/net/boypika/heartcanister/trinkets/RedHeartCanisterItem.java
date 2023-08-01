package net.boypika.heartcanister.trinkets;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

import static net.boypika.heartcanister.HeartCanister.CONFIG;

public class RedHeartCanisterItem extends TrinketItem{
    public RedHeartCanisterItem(Settings settings) {
        super(settings);
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "heartcanister:health", CONFIG.nestedHeartObject.SingleHealthCanisterHealthValue() * stack.getCount() , EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }
    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (CONFIG.nestedTrinketObject.GiveHealthOnEquip()) {
            if (entity.getHealth() >= 20f) {
                entity.setHealth(entity.getHealth() + 20f);
            }
        }
    }
    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (CONFIG.nestedTrinketObject.TakeHealthOnUnequip()){
            if (entity.getHealth() > 20f) {
                entity.setHealth(entity.getMaxHealth());
            }
        }
    }
}
