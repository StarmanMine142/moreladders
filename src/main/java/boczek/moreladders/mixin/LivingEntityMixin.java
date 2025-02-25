package boczek.moreladders.mixin;

import boczek.moreladders.MoreLadders;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Unique
    private static final TagKey<Block> MAKE_TRAPDOOR_CLIMBABLE_LADDERS = TagKey.of(Registries.BLOCK.getKey(), MoreLadders.id("make_trapdoor_climbable_ladders"));

    private LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "canEnterTrapdoor", at = @At("HEAD"), cancellable = true)
    private void canEnterTrapdoor(BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.get(TrapdoorBlock.OPEN)) {
            BlockState blockState = getWorld().getBlockState(pos.down());
            cir.setReturnValue(blockState.isIn(MAKE_TRAPDOOR_CLIMBABLE_LADDERS) && blockState.get(LadderBlock.FACING) == state.get(TrapdoorBlock.FACING));
            return;
        }
        cir.setReturnValue(false);
    }
}