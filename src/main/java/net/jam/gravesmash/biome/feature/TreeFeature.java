package net.jam.gravesmash.biome.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class TreeFeature extends Feature<DefaultFeatureConfig> {
    StructureWorldAccess world;

    public TreeFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        this.world = world;

        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, position).down();
        //Block test = world.getBlockState(topPos).getBlock();
        //if (test != Blocks.GRASS_BLOCK)
        //    return false;



        BlockPos pos = topPos;

        // Base and roots
        setLog(pos);
        Direction dir = Direction.random(world.getRandom());
        pos = pos.offset(dir);
        setLog(pos);
        setLog(pos.offset(Direction.DOWN));

        Direction dir2 = Direction.random(world.getRandom());
        while (dir2 == dir)
            dir2 = Direction.random(world.getRandom());

        pos = topPos.offset(dir2).offset(Direction.DOWN);
        setLog(pos);

        pos = topPos;
        // Trunk
        for (int i = 0; i < 3; i++) {
            pos = pos.offset(Direction.UP);
            setLog(pos);
        }

        BlockPos branchBase = pos;
        pos = branchBase;

        // Branch West
        pos = pos.offset(Direction.WEST);
        setLog(pos);
        spreadLeaves(pos);
        pos = pos.offset(Direction.WEST).offset(Direction.NORTH);
        setLog(pos);
        pos = pos.offset(Direction.WEST).offset(Direction.UP);
        setLog(pos);
        spreadLeaves(pos);

        // Branch South
        pos = branchBase.offset(Direction.SOUTH).offset(Direction.UP);
        setLog(pos);
        setLog(pos.offset(Direction.UP).offset(Direction.WEST));
        pos = pos.offset(Direction.SOUTH).offset(Direction.DOWN);
        setLog(pos);
        spreadLeaves(pos);
        pos = pos.offset(Direction.SOUTH);
        setLog(pos);
        setLog(pos.offset(Direction.SOUTH).offset(Direction.UP));
        setLog(pos.offset(Direction.SOUTH).offset(Direction.UP).offset(Direction.SOUTH));
        spreadLeaves(pos);

        // Branch East
        pos = branchBase.offset(Direction.UP).offset(Direction.EAST);
        setLog(pos);
        pos = pos.offset(Direction.UP).offset(Direction.EAST);
        setLog(pos);
        spreadLeaves(pos);
        pos = pos.offset(Direction.SOUTH);
        setLog(pos);
        pos = pos.offset(Direction.UP).offset(Direction.SOUTH).offset(Direction.EAST);
        setLog(pos);
        setLog(pos.offset(Direction.EAST));
        spreadLeaves(pos);

        // Branch North
        pos = branchBase.offset(Direction.UP).offset(Direction.NORTH);
        setLog(pos);
        spreadLeaves(pos);
        pos = pos.offset(Direction.UP).offset(Direction.NORTH);
        setLog(pos);
        setLog(pos.offset(Direction.NORTH));
        spreadLeaves(pos);

        // Branch NE
        pos = branchBase.offset(Direction.UP, 2).offset(Direction.NORTH).offset(Direction.EAST);
        setLog(pos);
        spreadLeaves(pos);
        pos = pos.offset(Direction.UP).offset(Direction.NORTH).offset(Direction.EAST);
        setLog(pos);
        setLog(pos.offset(Direction.NORTH));
        spreadLeaves(pos);


        return true;
    }

    public void setLog(BlockPos pos) {
        this.world.setBlockState(pos, Blocks.OAK_LOG.getDefaultState(), 3);
    }

    public void spreadLeaves(BlockPos origin) {
        BlockPos pos = origin;

        for (int x = -3; x < 4; x++) {
            for (int y = -0; y < 4; y++) {
                for (int z = -3; z < 4; z++) {
                    if (canPlace(x, y, z)) {    // (x + z != 0) && x + z != -6 && x + z + y != 9) {
                        pos = origin.offset(Direction.EAST, x).offset(Direction.NORTH, z).offset(Direction.UP, y);
                        if (world.getBlockState(pos).getBlock() != Blocks.OAK_LOG)
                            world.setBlockState(pos, Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 2), 10);
                    }
                }
            }
        }
    }


    public boolean canPlace(int x, int y, int z) {
        if ((maxed(x) && maxed(z))) { // x + z != 6 && x+z != -6 && x+z )
            return false;
        } else
            return true;




    }

    public boolean maxed(int i) {
        return i == 3 || i == -3;
    }


}
