package boczek.moreladders.mixin;

import boczek.moreladders.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

// https://github.com/FabricMC/fabric/commit/25d1a6769131730083de120eeee149ff2bbef54c (not implemented for <1.21.4)
@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin extends State<Block, BlockState> implements ModBlocks.RandomTickCacheRefresher {
    @Shadow
    private boolean ticksRandomly;

    private AbstractBlockStateMixin(Block owner, Reference2ObjectArrayMap<Property<?>, Comparable<?>> propertyMap, MapCodec<BlockState> codec) {
        super(owner, propertyMap, codec);
    }

    @Override
    public void moreladders$refreshRandomTickCache() {
        this.ticksRandomly = ((AbstractBlockMixin) this.owner).callHasRandomTicks(this.asBlockState());
    }

    @Shadow
    protected BlockState asBlockState() {
        return null;
    }
}
