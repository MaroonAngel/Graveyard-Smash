package net.jam.gravesmash.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    // Pumpkins
    public static final Block WHITE_PUMPKIN = new WhitePumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final Block GREEN_PUMPKIN = new WhitePumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));

    // Registry
    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "white_pumpkin"), WHITE_PUMPKIN);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "white_pumpkin"), new BlockItem(WHITE_PUMPKIN, new Item.Settings().group(ItemGroup.DECORATIONS)));

    }
}