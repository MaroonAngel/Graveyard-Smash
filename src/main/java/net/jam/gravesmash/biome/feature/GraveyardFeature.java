package net.jam.gravesmash.biome.feature;

import com.mojang.serialization.Codec;
import net.jam.gravesmash.lib.Directions;
import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class GraveyardFeature extends Feature<DefaultFeatureConfig> {
    StructureWorldAccess world;

    public GraveyardFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        this.world = world;

        int rowsX = world.getRandom().nextInt(4) + 4;
        int rowsY = (world.getRandom().nextInt(10) / 2) + 7;

        BlockPos testPos = findGrass(world.getTopPosition(Heightmap.Type.WORLD_SURFACE, position).down());
        BlockPos pos = testPos != null ? testPos : world.getTopPosition(Heightmap.Type.WORLD_SURFACE, position).down();

        Direction dir = Directions.random(world.getRandom());


        // Analyze Ground
        int yOrigin = pos.getY();

        if (world.getBlockState(pos) == Blocks.WATER.getDefaultState())
            return false;


        int waterAmt = 0;

        for (int x = 0; x < rowsX*4; x++) {
            for (int y = -rowsY; y <= rowsY; y++) {
                BlockPos test = pos.offset(dir, x).offset(dir.rotateYCounterclockwise(), y);   //   new BlockPos(pos.offset(dir, x), yOrigin, pos.getZ());
                BlockPos top = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, test).down();
                if (top.getY() < yOrigin - 4 || top.getY() > yOrigin + 4) {
                    return false;
                }
                if (world.getBlockState(top) == Blocks.WATER.getDefaultState())
                    waterAmt++;

                if (waterAmt > (rowsX * rowsY) / 5)
                    return false;

            }
        }


        // Generate Pedestal

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                world.setBlockState(pos.offset(dir.rotateYCounterclockwise(), x).offset(dir, y), Blocks.POLISHED_ANDESITE.getDefaultState(), 3);
                world.setBlockState(pos.offset(dir.rotateYCounterclockwise(), x).offset(dir, y).up(), Blocks.AIR.getDefaultState(), 3);
                world.setBlockState(pos.offset(dir.rotateYCounterclockwise(), x).offset(dir, y).up().up(), Blocks.AIR.getDefaultState(), 3);
            }
        }

        world.setBlockState(pos.up(), net.jam.gravesmash.block.Blocks.PEDESTAL.getDefaultState(), 3);

        BlockPos rowPos = pos; // = pos.offset(dir, 3);


        // Generate Fencing
        rowPos = getTop(rowPos.offset(dir.getOpposite(), 3));

        for (int y = -rowsY-2; y <= rowsY+2; y++) {
            boolean bl = Math.abs(y) == (rowsY / 2) + 1 || Math.abs(y) == rowsY + 2 || y == 0;
            buildUp(findTop(getTop(rowPos.offset(dir.rotateYCounterclockwise(), y))), bl ? Blocks.STONE_BRICKS.getDefaultState()
                    : Blocks.IRON_BARS.getDefaultState().with(getPaneDir(dir.rotateYCounterclockwise()), true).with(getPaneDir(dir.rotateYClockwise()), true));

            if (y != 0 && y != -1 && y != 1)
            buildUp(findTop(getTop(rowPos.offset(dir.rotateYCounterclockwise(), y).offset(dir, rowsX*4+8))), bl ? Blocks.STONE_BRICKS.getDefaultState()
                    : Blocks.IRON_BARS.getDefaultState().with(getPaneDir(dir.rotateYCounterclockwise()), true).with(getPaneDir(dir.rotateYClockwise()), true));
        }

        for (int x = 0; x < rowsX*4+8; x++) {
            boolean bl = x % 4 == 0;
            buildUp(findTop(getTop(rowPos.offset(dir, x).offset(dir.rotateYCounterclockwise(), rowsY+2))), bl ? Blocks.STONE_BRICKS.getDefaultState()
                    : Blocks.IRON_BARS.getDefaultState().with(getPaneDir(dir), true).with(getPaneDir(dir.getOpposite()), true));

            buildUp(findTop(getTop(rowPos.offset(dir, x).offset(dir.rotateYCounterclockwise(), -rowsY-2))), bl ? Blocks.STONE_BRICKS.getDefaultState()
                    : Blocks.IRON_BARS.getDefaultState().with(getPaneDir(dir), true).with(getPaneDir(dir.getOpposite()), true));
        }

        // Gate
        BlockPos gatePos = findTop(rowPos.offset(dir, rowsX*4+8)).up();

        world.setBlockState(gatePos, Blocks.AIR.getDefaultState(), 3);
        world.setBlockState(gatePos.up(), Blocks.AIR.getDefaultState(), 3);
        world.setBlockState(gatePos.up(2), Blocks.STONE_BRICKS.getDefaultState(), 3);

        gatePos = findTop(gatePos.offset(dir.rotateYCounterclockwise(), 1)).up();
        world.setBlockState(gatePos, Blocks.STONE_BRICK_WALL.getDefaultState(), 3);
        world.setBlockState(gatePos.up(), Blocks.STONE_BRICK_WALL.getDefaultState(), 3);
        world.setBlockState(gatePos.up(2), Blocks.STONE_BRICKS.getDefaultState(), 3);

        gatePos = findTop(gatePos.offset(dir.rotateYCounterclockwise(), -2)).up();
        world.setBlockState(gatePos, Blocks.STONE_BRICK_WALL.getDefaultState(), 3);
        world.setBlockState(gatePos.up(), Blocks.STONE_BRICK_WALL.getDefaultState(), 3);
        world.setBlockState(gatePos.up(2), Blocks.STONE_BRICKS.getDefaultState(), 3);



        // Path
        for (int x = 3; x < rowsX*4+4; x++) {
            for (int y = -1; y <= 1; y++) {
                rowPos = pos.offset(dir, x).offset(dir.rotateYCounterclockwise(), y);

                world.setBlockState(findTop(rowPos).up(), Blocks.AIR.getDefaultState(), 3);
                world.setBlockState(findTop(rowPos), Blocks.GRASS_PATH.getDefaultState(), 0);

            }
        }

        /*
        // Lay Grass
        for (int x = 0; x < rowsX*3+2; x++) {
            for (int y = -rowsY - 1; y <= rowsY + 1; y++) {
                rowPos = pos.offset(dir, x).offset(dir.rotateYCounterclockwise(), y);
                if (world.getBlockState(rowPos).getBlock() != Blocks.POLISHED_ANDESITE)
                    world.setBlockState(rowPos, Blocks.GRASS_BLOCK.getDefaultState(), 3);

                generateGrassBeneath(rowPos);
            }
        }
         */

        rowPos = pos;

        // Generate Graves
        for (int x = 0; x < rowsX; x++) {
            rowPos = rowPos.offset(dir, 4);
            for (int y = -rowsY/2; y <= rowsY/2; y++) {
                BlockPos newPos = rowPos.offset(dir.rotateYCounterclockwise(), y*2);

                if (newPos != world.getTopPosition(Heightmap.Type.WORLD_SURFACE, newPos).down()) {
                    newPos = findGrass(newPos);
                    if (newPos == null)
                        continue;
                }
                if (y == 0)
                    continue;

                world.setBlockState(newPos.offset(Direction.UP), Blocks.POLISHED_ANDESITE.getDefaultState(), 3);
                world.setBlockState(newPos.offset(Direction.UP, 2), Blocks.POLISHED_ANDESITE.getDefaultState(), 3);

                world.setBlockState(newPos, Blocks.COARSE_DIRT.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir), Blocks.COARSE_DIRT.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir, 2), Blocks.COARSE_DIRT.getDefaultState(), 3);

                world.setBlockState(newPos.offset(dir).up(), Blocks.AIR.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir, 2).up(), Blocks.AIR.getDefaultState(), 3);

                // Check nearby blocks
                /*
                world.setBlockState(newPos.offset(dir).offset(dir.rotateYCounterclockwise()), Blocks.GRASS_BLOCK.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir, 2).offset(dir.rotateYCounterclockwise()), Blocks.GRASS_BLOCK.getDefaultState(), 3);

                world.setBlockState(newPos.offset(dir).offset(dir.rotateYClockwise()), Blocks.GRASS_BLOCK.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir, 2).offset(dir.rotateYClockwise()), Blocks.GRASS_BLOCK.getDefaultState(), 3);

                world.setBlockState(newPos.offset(dir).down(), Blocks.DIRT.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir, 2).down(), Blocks.DIRT.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir).offset(dir.rotateYCounterclockwise()).down(), Blocks.DIRT.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir, 2).offset(dir.rotateYCounterclockwise()).down(), Blocks.DIRT.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir).offset(dir.rotateYClockwise()).down(), Blocks.DIRT.getDefaultState(), 3);
                world.setBlockState(newPos.offset(dir, 2).offset(dir.rotateYClockwise()).down(), Blocks.DIRT.getDefaultState(), 3);

                 */
            }
        }

        rowPos = pos;

        // Generate Loot
        for (int x = 0; x < rowsX; x++) {
            rowPos = rowPos.offset(dir, 4);
            for (int y = -rowsY/2; y <= rowsY/2; y++) {
                BlockPos newPos = rowPos.offset(dir.rotateYCounterclockwise(), y*2);

                if (newPos != world.getTopPosition(Heightmap.Type.WORLD_SURFACE, newPos).down()) {
                    newPos = findDirt(newPos);
                    if (newPos == null)
                        continue;
                }
                if (y == 0)
                    continue;

                newPos = newPos.down();


                world.setBlockState(newPos.offset(dir), Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, dir), 3);
                ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(newPos.offset(dir));
                if (chest != null)
                    for (int i = 0; i < random.nextInt(5)+3; i++)
                        getChestLoot(chest, random);

                world.setBlockState(newPos.offset(dir, 2), Blocks.COARSE_DIRT.getDefaultState(), 3);


            }
        }


        return true;
    }

    private void getChestLoot(ChestBlockEntity chest, Random random) {

        for (int i = 0; i < random.nextInt(4)+1; i++) {
            float chance = random.nextInt(10000) / 100f;

            if (chance < 3)
                chest.setStack(random.nextInt(26), new ItemStack(Items.DIAMOND, random.nextInt(2)+1));
            else if (chance < 3.25)
                chest.setStack(random.nextInt(26), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1));
            else if (chance < 8)
                chest.setStack(random.nextInt(26), new ItemStack(Items.EMERALD, random.nextInt(9)+1));
            else if (chance < 50)
                chest.setStack(random.nextInt(26), new ItemStack(Items.ROTTEN_FLESH, random.nextInt(4)+1));
            else
                chest.setStack(random.nextInt(26), new ItemStack(Items.BONE, random.nextInt(5)+1));


        }
    }

    private BlockPos getTop(BlockPos pos) {
        BlockPos test = findGrass(world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos).down());

        return  test != null ? test : world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos).down();
    }

    private BooleanProperty getPaneDir(Direction dir) {
        if (dir == Direction.NORTH)
            return HorizontalConnectingBlock.NORTH;
        if (dir == Direction.SOUTH)
            return HorizontalConnectingBlock.SOUTH;
        if (dir == Direction.EAST)
            return HorizontalConnectingBlock.EAST;
        else
            return HorizontalConnectingBlock.WEST;
    }

    protected void buildUp(BlockPos pos, BlockState type) {
        world.setBlockState(pos.up(), type, 3);
        world.setBlockState(pos.up().up(), type, 3);
        world.setBlockState(pos.up().up().up(), type, 3);
    }

    protected BlockPos findGrass(BlockPos pos) {
        BlockPos testPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        for (int i = 0; i <= 4; i++)
        {
            testPos = testPos.down();
            if (world.getBlockState(testPos).getBlock() == Blocks.GRASS_BLOCK)
                return testPos;
        }

        return null;
    }

    protected BlockPos findDirt(BlockPos pos) {
        BlockPos testPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        for (int i = 0; i <= 4; i++)
        {
            testPos = testPos.down();
            if (world.getBlockState(testPos).getBlock() == Blocks.COARSE_DIRT)
                return testPos;
        }

        return null;
    }

    protected BlockPos findTop(BlockPos pos) {
        BlockPos testPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        for (int i = 0; i <= 12; i++)
        {
            testPos = testPos.down();
            if (world.getBlockState(testPos).getBlock() == Blocks.GRASS_BLOCK)
                return testPos;
        }

        return pos;
    }

    protected boolean generateGrassBeneath(BlockPos pos) {
        BlockPos testPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos).down();
        if (testPos.getY() < pos.getY()) {
            for (int y = testPos.getY(); y < pos.getY(); y++) {
                BlockPos position = new BlockPos(testPos.getX(), y, testPos.getZ());
                world.setBlockState(position, Blocks.DIRT.getDefaultState(), 3);
            }
            return true;
        }

        return false;
    }
}
