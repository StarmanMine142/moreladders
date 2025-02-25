package boczek.moreladders;

import boczek.moreladders.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class MoreLaddersGroup {
    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, MoreLadders.id("more-ladders"), FabricItemGroup.builder()
                .displayName(Text.literal("More Ladders")).icon(() -> new ItemStack(ModBlocks.SPRUCE_LADDER))
                        .entries((displayContext, entries) -> {
                            entries.add(ModBlocks.SPRUCE_LADDER);
                            entries.add(ModBlocks.BIRCH_LADDER);
                            entries.add(ModBlocks.CHAIN_LADDER);
                            entries.add(ModBlocks.ACACIA_LADDER);
                            entries.add(ModBlocks.CRIMSON_LADDER);
                            entries.add(ModBlocks.WARPED_LADDER);
                            entries.add(ModBlocks.DARK_OAK_LADDER);
                            entries.add(ModBlocks.IRON_LADDER);
                            entries.add(ModBlocks.JUNGLE_LADDER);
                            entries.add(ModBlocks.CHERRY_LADDER);
                            entries.add(ModBlocks.BAMBOO_LADDER);
                            entries.add(ModBlocks.MANGROVE_LADDER);
                            entries.add(ModBlocks.GOLD_LADDER);
                            entries.add(ModBlocks.COPPER_LADDER);
                            entries.add(ModBlocks.EXPOSED_COPPER_LADDER);
                            entries.add(ModBlocks.WEATHERED_COPPER_LADDER);
                            entries.add(ModBlocks.OXIDIZED_COPPER_LADDER);
                            entries.add(ModBlocks.WAXED_COPPER_LADDER);
                            entries.add(ModBlocks.WAXED_EXPOSED_COPPER_LADDER);
                            entries.add(ModBlocks.WAXED_WEATHERED_COPPER_LADDER);
                            entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_LADDER);
                        }).build());
    }
}
