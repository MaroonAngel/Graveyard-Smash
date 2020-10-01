package net.jam.gravesmash.biome.feature;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import static net.minecraft.world.gen.feature.Feature.RANDOM_PATCH;

public class Features {

    public static ConfiguredFeature<?, ?> PUMPKIN_PATCH;
    public static ConfiguredFeature<?, ?> PUMPKIN_BUSH;

    public static void register() {

        PUMPKIN_PATCH = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "pumpkin_patch"),
                (ConfiguredFeature) Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.PUMPKIN.getDefaultState()),
                SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getDefaultState().getBlock())).cannotProject().build())
                .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(25));

        PUMPKIN_BUSH = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "pumpkin_bush"),
                Feature.TREE.configure((new net.minecraft.world.gen.feature.TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
                        new StraightTrunkPlacer(1, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

    }

}
