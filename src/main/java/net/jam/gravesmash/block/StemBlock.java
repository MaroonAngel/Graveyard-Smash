package net.jam.gravesmash.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class StemBlock extends net.minecraft.block.StemBlock {

    protected final GourdBlock gourdBlock;

    protected StemBlock(GourdBlock gourdBlock, Settings settings) {
        super(gourdBlock, settings);
        this.gourdBlock = gourdBlock;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            //float f = CropBlock.getAvailableMoisture(this, world, pos);
            if (random.nextInt((int)(25.0F / 7) + 1) == 0) {
                int i = (Integer)state.get(AGE);
                if (i < 7) {
                    state = (BlockState)state.with(AGE, i + 1);
                    world.setBlockState(pos, state, 2);
                } else {
                    Direction direction = Direction.Type.HORIZONTAL.random(random);
                    BlockPos blockPos = pos.offset(direction);
                    BlockState blockState = world.getBlockState(blockPos.down());
                    if (world.getBlockState(blockPos).isAir() && (blockState.isOf(net.minecraft.block.Blocks.FARMLAND) || blockState.isOf(net.minecraft.block.Blocks.DIRT) || blockState.isOf(net.minecraft.block.Blocks.COARSE_DIRT) || blockState.isOf(net.minecraft.block.Blocks.PODZOL) || blockState.isOf(net.minecraft.block.Blocks.GRASS_BLOCK))) {
                        world.setBlockState(blockPos, this.gourdBlock.getDefaultState());
                        world.setBlockState(pos, (BlockState)this.gourdBlock.getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction));
                        world.setBlockState(blockPos, modifyGourdBlockState(this.gourdBlock.getDefaultState().with(HorizontalFacingBlock.FACING, direction), direction));
                    }
                }
            }

        }
    }

    public BlockState modifyGourdBlockState(BlockState gourd, Direction dir) {
        if (dir == Direction.NORTH)
            return gourd.with(ResizedPumpkinBlock.SOUTH, true);
        if (dir == Direction.SOUTH)
            return gourd.with(ResizedPumpkinBlock.NORTH, true);
        if (dir == Direction.EAST)
            return gourd.with(ResizedPumpkinBlock.WEST, true);
        if (dir == Direction.WEST)
            return gourd.with(ResizedPumpkinBlock.EAST, true);

        return gourd;
    }


}
