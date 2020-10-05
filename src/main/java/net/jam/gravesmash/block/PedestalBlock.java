package net.jam.gravesmash.block;

import javafx.scene.shape.Shape;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PedestalBlock extends Block {

    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        VoxelShape shape = VoxelShapes.union(
                Block.createCuboidShape(4, 0, 4, 12, 1, 12),
                Block.createCuboidShape(5, 1, 5, 11, 2, 11),
                Block.createCuboidShape(6, 2, 6, 10, 12, 10),
                Block.createCuboidShape(5, 12, 5, 11, 13, 11),
                Block.createCuboidShape(0, 13, 0, 16, 16, 16)
        );

        return shape;
    }
}
