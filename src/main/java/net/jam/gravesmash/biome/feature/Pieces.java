package net.jam.gravesmash.biome.feature;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class Pieces {

    public static final StructurePieceType TREE_PIECE = TreeGenerator.TreePiece::new;
    private static final StructureFeature<DefaultFeatureConfig> TREE_STRUCTURE = new TreeFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> TREE_CONFIGURED = TREE_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
        
    public static void register() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier("gravesmash", "tree1_piece"), TREE_PIECE);
        FabricStructureBuilder.create(new Identifier("gravesmash", "tree1_structure"), TREE_STRUCTURE)
            .step(GenerationStep.Feature.SURFACE_STRUCTURES)
            .defaultConfig(32, 8, 12345)
            .adjustsSurface()
            .register();

        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier("gravesmash", "tree1_structure"),
                TREE_CONFIGURED);
    }
}
