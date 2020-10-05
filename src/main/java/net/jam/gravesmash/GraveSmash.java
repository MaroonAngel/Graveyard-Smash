package net.jam.gravesmash;

import net.fabricmc.api.ModInitializer;
import net.jam.gravesmash.biome.Biomes;
import net.jam.gravesmash.biome.feature.Features;
import net.jam.gravesmash.biome.feature.Pieces;
import net.jam.gravesmash.block.Blocks;
import net.jam.gravesmash.lib.Directions;
import net.minecraft.world.biome.DefaultBiomeCreator;

public class GraveSmash implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Directions.register();

		Features.register();		
		Biomes.register();
		Blocks.register();


		System.out.println("Hello Fabric world!");
	}
}