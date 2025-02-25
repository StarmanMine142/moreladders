package boczek.moreladders.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractBlock.class)
public interface AbstractBlockMixin {
    @Invoker
    boolean callHasRandomTicks(BlockState blockState);
}
