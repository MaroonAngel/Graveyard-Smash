package net.jam.gravesmash.biome;

import net.fabricmc.fabric.mixin.biome.BuiltinBiomesAccessor;
import net.fabricmc.fabric.mixin.biome.VanillaLayeredBiomeSourceAccessor;
import net.jam.gravesmash.biome.feature.Features;
import net.jam.gravesmash.biome.feature.Pieces;
import net.jam.gravesmash.mixin.SetBaseBiomesLayerAccessor;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class Biomes {

    public static final RegistryKey<Biome> PUMPKIN_PATCH_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("gravesmash", "pumpkin_patch"));

    private static final Biome PUMPKIN_PATCH = createPumpkinPatch();

    private static Biome createPumpkinPatch() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        //DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        //DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings);
        DefaultBiomeFeatures.addJungleGrass(generationSettings);
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings);
        DefaultBiomeFeatures.addWaterBiomeOakTrees(generationSettings);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.PUMPKIN_PATCH);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, Features.PUMPKIN_BUSH);
        generationSettings.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Features.TREE_CONFIGURED);
        generationSettings.feature(GenerationStep.Feature.SURFACE_STRUCTURES, Features.GRAVEYARD_CONFIGURED);



        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE)
                .depth(0.1F).scale(0.05f).temperature(0.5f).downfall(0.4F).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).foliageColor(0xff8119)
                        .waterColor(0xc49d6c).waterFogColor(0x9e5f10).fogColor(12638463).grassColor(0xe0852f).skyColor(0xb3fcd8).moodSound(BiomeMoodSound.CAVE)
                        .build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();

    }


    public static void register() {
        Registry.register(BuiltinRegistries.BIOME, PUMPKIN_PATCH_KEY.getValue(), PUMPKIN_PATCH);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(PUMPKIN_PATCH), PUMPKIN_PATCH_KEY);

        List<RegistryKey<Biome>> biomes = new ArrayList<>(VanillaLayeredBiomeSourceAccessor.getBIOMES());
        biomes.add(PUMPKIN_PATCH_KEY);
        VanillaLayeredBiomeSourceAccessor.setBIOMES(biomes);

        SetBaseBiomesLayerAccessor.setTemperateBiomes(
                ArrayUtils.add(SetBaseBiomesLayerAccessor.getTemperateBiomes(), BuiltinRegistries.BIOME.getRawId(PUMPKIN_PATCH)));
    }
}
