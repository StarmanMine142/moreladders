package boczek.moreladders.block;

import boczek.moreladders.MoreLadders;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {
    public static final Block SPRUCE_LADDER = registerNormal("spruce_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block BIRCH_LADDER = registerNormal("birch_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block DARK_OAK_LADDER = registerNormal("dark_oak_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block JUNGLE_LADDER = registerNormal("jungle_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block WARPED_LADDER = registerNormal("warped_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block CRIMSON_LADDER = registerNormal("crimson_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block IRON_LADDER = registerNormal("iron_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.METAL).requiresTool());
    public static final Block ACACIA_LADDER = registerNormal("acacia_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block CHAIN_LADDER = registerNormal("chain_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.CHAIN).requiresTool());
    public static final Block CHERRY_LADDER = registerNormal("cherry_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block BAMBOO_LADDER = registerNormal("bamboo_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.BAMBOO));
    public static final Block MANGROVE_LADDER = registerNormal("mangrove_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.LADDER));
    public static final Block GOLD_LADDER = registerNormal("gold_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).sounds(BlockSoundGroup.METAL).requiresTool());
    public static final Block COPPER_LADDER = registerOxidizable("copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).ticksRandomly(), Oxidizable.OxidationLevel.UNAFFECTED);
    public static final Block EXPOSED_COPPER_LADDER = registerOxidizable("exposed_copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).ticksRandomly(), Oxidizable.OxidationLevel.EXPOSED);
    public static final Block WEATHERED_COPPER_LADDER = registerOxidizable("weathered_copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER).ticksRandomly(), Oxidizable.OxidationLevel.WEATHERED);
    public static final Block OXIDIZED_COPPER_LADDER = registerOxidizable("oxidized_copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER), Oxidizable.OxidationLevel.OXIDIZED);
    public static final Block WAXED_COPPER_LADDER = registerOxidizable("waxed_copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER), Oxidizable.OxidationLevel.UNAFFECTED);
    public static final Block WAXED_EXPOSED_COPPER_LADDER = registerOxidizable("waxed_exposed_copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER), Oxidizable.OxidationLevel.EXPOSED);
    public static final Block WAXED_WEATHERED_COPPER_LADDER = registerOxidizable("waxed_weathered_copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER), Oxidizable.OxidationLevel.WEATHERED);
    public static final Block WAXED_OXIDIZED_COPPER_LADDER = registerOxidizable("waxed_oxidized_copper_ladder", AbstractBlock.Settings.copy(Blocks.LADDER), Oxidizable.OxidationLevel.OXIDIZED);

    private static Block registerNormal(String name, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, MoreLadders.id(name));
        return register(name, new LadderBlock(settings.registryKey(key)));
    }

    private static Block registerOxidizable(String name, AbstractBlock.Settings settings, Oxidizable.OxidationLevel level) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, MoreLadders.id(name));
        return register(name, new OxidizableLadder(settings.registryKey(key), level));
    }

    private static Block register(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, MoreLadders.id(name));
        Registry.register(Registries.ITEM, MoreLadders.id(name), new BlockItem(block, new Item.Settings().registryKey(key).useBlockPrefixedTranslationKey()));
        return Registry.register(Registries.BLOCK, MoreLadders.id(name), block);
    }

    public static void initialize() {
        registerOxidizableBlockPair(COPPER_LADDER, EXPOSED_COPPER_LADDER);
        registerOxidizableBlockPair(EXPOSED_COPPER_LADDER, WEATHERED_COPPER_LADDER);
        registerOxidizableBlockPair(WEATHERED_COPPER_LADDER, OXIDIZED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_LADDER, WAXED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_LADDER, WAXED_EXPOSED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_LADDER, WAXED_WEATHERED_COPPER_LADDER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_LADDER, WAXED_OXIDIZED_COPPER_LADDER);
    }

    // https://github.com/FabricMC/fabric/commit/25d1a6769131730083de120eeee149ff2bbef54c (not implemented for <1.21.4)
    private static void registerOxidizableBlockPair(Block less, Block more) {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(less, more);
        refreshRandomTickCache(less);
        refreshRandomTickCache(more);
    }

    private static void refreshRandomTickCache(Block block) {
        block.getStateManager().getStates().forEach(state -> ((RandomTickCacheRefresher) state).moreladders$refreshRandomTickCache());
    }

    public interface RandomTickCacheRefresher {
        void moreladders$refreshRandomTickCache();
    }
}