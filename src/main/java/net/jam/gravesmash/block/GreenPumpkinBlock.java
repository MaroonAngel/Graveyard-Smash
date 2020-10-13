package net.jam.gravesmash.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class GreenPumpkinBlock extends ResizedPumpkinBlock {
    public GreenPumpkinBlock(Settings settings) {
        super(settings);
    }

    @Override
    public StemBlock getStem() {
        return Blocks.GREEN_PUMPKIN_SEEDS;
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return Blocks.ATTACHED_WHITE_PUMPKIN_SEEDS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return Block.createCuboidShape(2, 0, 2, 14, 8, 14);
    }


}
