package net.jam.gravesmash.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class StemBlock extends PlantBlock implements Fertilizable {

    public static final IntProperty AGE;
    protected static final VoxelShape[] AGE_TO_SHAPE;
    private final GourdBlock gourdBlock;

    protected StemBlock(GourdBlock gourdBlock, Settings settings) {
        super(settings);
        this.gourdBlock = gourdBlock;
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[(Integer)state.get(AGE)];
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(net.minecraft.block.Blocks.FARMLAND);
    }

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
                        //world.setBlockState(blockPos, this.gourdBlock.getDefaultState());
                        world.setBlockState(blockPos, getGourdState(this.gourdBlock.getDefaultState(), direction));
                        world.setBlockState(pos, (BlockState)this.gourdBlock.getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction));
                    }
                }
            }

        }
    }

    public BlockState getGourdState(BlockState gourd, Direction dir) {
        if (gourd.getBlock() instanceof WhitePumpkinBlock) {
            if (dir == Direction.NORTH)
                return Blocks.ATTACHED_WHITE_PUMPKIN_SOUTH.getDefaultState();
            if (dir == Direction.SOUTH)
                return Blocks.ATTACHED_WHITE_PUMPKIN_NORTH.getDefaultState();
            if (dir == Direction.EAST)
                return Blocks.ATTACHED_WHITE_PUMPKIN_WEST.getDefaultState();
            if (dir == Direction.WEST)
                return Blocks.ATTACHED_WHITE_PUMPKIN_EAST.getDefaultState();
        } else {
            if (dir == Direction.NORTH)
                return Blocks.ATTACHED_GREEN_PUMPKIN_SOUTH.getDefaultState();
            if (dir == Direction.SOUTH)
                return Blocks.ATTACHED_GREEN_PUMPKIN_NORTH.getDefaultState();
            if (dir == Direction.EAST)
                return Blocks.ATTACHED_GREEN_PUMPKIN_WEST.getDefaultState();
            if (dir == Direction.WEST)
                return Blocks.ATTACHED_GREEN_PUMPKIN_EAST.getDefaultState();
        }
        return net.minecraft.block.Blocks.AIR.getDefaultState();
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

    @Environment(EnvType.CLIENT)
    protected Item getPickItem() {
        if (this.gourdBlock == net.minecraft.block.Blocks.PUMPKIN) {
            return Items.PUMPKIN_SEEDS;
        } else {
            return this.gourdBlock == net.minecraft.block.Blocks.MELON ? Items.MELON_SEEDS : null;
        }
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        Item item = this.getPickItem();
        return item == null ? ItemStack.EMPTY : new ItemStack(item);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return (Integer)state.get(AGE) != 7;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(7, (Integer)state.get(AGE) + MathHelper.nextInt(world.random, 2, 5));
        BlockState blockState = (BlockState)state.with(AGE, i);
        world.setBlockState(pos, blockState, 2);
        if (i == 7) {
            blockState.randomTick(world, pos, world.random);
        }

    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    public GourdBlock getGourdBlock() {
        return this.gourdBlock;
    }

    static {
        AGE = Properties.AGE_7;
        AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 2.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 4.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 6.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 10.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 12.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 14.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D)};
    }


}
