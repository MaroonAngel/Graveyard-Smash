package net.jam.gravesmash.biome.feature;

import com.google.common.collect.ImmutableSet;
import net.jam.gravesmash.GraveSmash;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import static net.minecraft.world.gen.feature.Feature.RANDOM_PATCH;

public class Features {

    public static ConfiguredFeature<?, ?> PUMPKIN_PATCH;
    public static ConfiguredFeature<?, ?> GREEN_PUMPKIN_PATCH;
    public static ConfiguredFeature<?, ?> WHITE_PUMPKIN_PATCH;
    public static ConfiguredFeature<?, ?> PUMPKIN_BUSH;
    public static Feature<DefaultFeatureConfig> TREE = new TreeFeature(DefaultFeatureConfig.CODEC);
    public static ConfiguredFeature<?, ?> TREE_CONFIGURED = TREE.configure(FeatureConfig.DEFAULT)
            .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(15)));

    public static Feature<DefaultFeatureConfig> GRAVEYARD = new GraveyardFeature(DefaultFeatureConfig.CODEC);
    public static ConfiguredFeature<?, ?> GRAVEYARD_CONFIGURED = GRAVEYARD.configure(FeatureConfig.DEFAULT)
            .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(20)));


    public static void register() {

        PUMPKIN_PATCH = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "pumpkin_patch"),
                (ConfiguredFeature) Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.PUMPKIN.getDefaultState()),
                SimpleBlockPlacer.INSTANCE)).tries(48).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getDefaultState().getBlock())).cannotProject().build())
                .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(25));

        GREEN_PUMPKIN_PATCH = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "green_pumpkin_patch"),
                (ConfiguredFeature) Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(net.jam.gravesmash.block.Blocks.GREEN_PUMPKIN.getDefaultState()),
                        SimpleBlockPlacer.INSTANCE)).tries(32).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getDefaultState().getBlock())).cannotProject().build())
                        .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(20));

        WHITE_PUMPKIN_PATCH = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "white_pumpkin_patch"),
                (ConfiguredFeature) Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(net.jam.gravesmash.block.Blocks.WHITE_PUMPKIN.getDefaultState()),
                        SimpleBlockPlacer.INSTANCE)).tries(32).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getDefaultState().getBlock())).cannotProject().build())
                        .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(20));

        PUMPKIN_BUSH = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "pumpkin_bush"),
                Feature.TREE.configure((new net.minecraft.world.gen.feature.TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()), new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
                        new StraightTrunkPlacer(1, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build())
                        .decorate((Decorator.CHANCE.configure(new ChanceDecoratorConfig(40)))));

        Registry.register(Registry.FEATURE, new Identifier("gravesmash", "tree"), TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "tree"), TREE_CONFIGURED);

        Registry.register(Registry.FEATURE, new Identifier("gravesmash", "graveyard"), GRAVEYARD);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("gravesmash", "graveyard"), GRAVEYARD_CONFIGURED);


    }

}
