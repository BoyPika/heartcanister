package net.boypika.heartcanister.trinkets;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.boypika.heartcanister.HeartCanister.config;

public class HeartItem extends Item {
    public HeartItem(Settings settings) {
        super(settings);
    }
    public static class RedHeartItem extends Item{

        public RedHeartItem(Settings settings) {
            super(settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("text.heartcanister.hearttooltip1").formatted(Formatting.GRAY).append(Text.literal(" "+config().nestedHeartObject.RedHealthHealAmountWhenAte+" ").formatted(Formatting.BLUE).append(Text.translatable("text.heartcanister.hearttooltip2").formatted(Formatting.GRAY))));
        }
    }
    public static class YellowHeartItem extends Item{

        public YellowHeartItem(Settings settings) {
            super(settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("text.heartcanister.hearttooltip1").formatted(Formatting.GRAY).append(Text.literal(" "+config().nestedHeartObject.YellowHealthHealAmountWhenAte+" ").formatted(Formatting.BLUE).append(Text.translatable("text.heartcanister.hearttooltip2").formatted(Formatting.GRAY))));
        }
    }
    public static class GreenHeartItem extends Item{

        public GreenHeartItem(Settings settings) {
            super(settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("text.heartcanister.hearttooltip1").formatted(Formatting.GRAY).append(Text.literal(" "+config().nestedHeartObject.GreenHealthHealAmountWhenAte+" ").formatted(Formatting.BLUE).append(Text.translatable("text.heartcanister.hearttooltip2").formatted(Formatting.GRAY))));
        }
    }
}
