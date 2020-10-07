package net.jam.gravesmash.mixin;

import net.jam.gravesmash.entity.Entities;
import net.jam.gravesmash.entity.JackEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Iterator;

@Mixin(CarvedPumpkinBlock.class)
public abstract class CarvedPumpkinBlockMixin extends HorizontalFacingBlock {
    protected CarvedPumpkinBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method="onBlockAdded", at=@At("TAIL"))
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            this.trySpawnJack(world, pos);
        }
    }

    private void trySpawnJack(World world, BlockPos pos) {

        if (world.getBlockState(pos.down()).getBlock() == net.jam.gravesmash.block.Blocks.PEDESTAL) {
            world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());

            JackEntity jack = (JackEntity) Entities.JACK.create(world);
            if (jack != null) {
                jack.refreshPositionAndAngles((double) pos.getX() + 0.5D, (double) pos.getY() + 0.05D, (double) pos.getZ() + 0.5D, 0, 0);
                world.spawnEntity(jack);
            }
        }
    }


}
