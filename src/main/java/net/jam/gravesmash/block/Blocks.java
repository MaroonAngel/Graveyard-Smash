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
    public static final WhitePumpkinBlock WHITE_PUMPKIN = new WhitePumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final CarvedPumpkinBlock CARVED_WHITE_PUMPKIN = new CarvedPumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final StemBlock WHITE_PUMPKIN_SEEDS = new StemBlock(WHITE_PUMPKIN, FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().ticksRandomly().sounds(BlockSoundGroup.CROP));
    public static final AttachedStemBlock ATTACHED_WHITE_PUMPKIN_SEEDS = new AttachedStemBlock(WHITE_PUMPKIN, FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.CROP));
    public static final WhitePumpkinBlock ATTACHED_WHITE_PUMPKIN_NORTH = new WhitePumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final WhitePumpkinBlock ATTACHED_WHITE_PUMPKIN_SOUTH = new WhitePumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final WhitePumpkinBlock ATTACHED_WHITE_PUMPKIN_EAST = new WhitePumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final WhitePumpkinBlock ATTACHED_WHITE_PUMPKIN_WEST = new WhitePumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));

    public static final GreenPumpkinBlock GREEN_PUMPKIN = new GreenPumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final CarvedPumpkinBlock CARVED_GREEN_PUMPKIN = new CarvedPumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final StemBlock GREEN_PUMPKIN_SEEDS = new StemBlock(GREEN_PUMPKIN, FabricBlockSettings.of(Material.PLANT).breakInstantly().ticksRandomly().sounds(BlockSoundGroup.CROP).noCollision());
    public static final AttachedStemBlock ATTACHED_GREEN_PUMPKIN_SEEDS = new AttachedStemBlock(GREEN_PUMPKIN, FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.CROP));
    public static final GreenPumpkinBlock ATTACHED_GREEN_PUMPKIN_NORTH = new GreenPumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final GreenPumpkinBlock ATTACHED_GREEN_PUMPKIN_SOUTH = new GreenPumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final GreenPumpkinBlock ATTACHED_GREEN_PUMPKIN_EAST = new GreenPumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));
    public static final GreenPumpkinBlock ATTACHED_GREEN_PUMPKIN_WEST = new GreenPumpkinBlock(FabricBlockSettings.of(Material.GOURD).strength(1.0f).sounds (BlockSoundGroup.WOOD));

    // Jack
    public static final PedestalBlock PEDESTAL = new PedestalBlock(FabricBlockSettings.of(Material.STONE).strength(2f).sounds(BlockSoundGroup.STONE));
    public static final Block AUTUMN_LEAVES = new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2f).sounds (BlockSoundGroup.GRASS));
    //Spooky Magic
    public static final SpookyCraftingTableBlock SPOOKY_CRAFTING_TABLE = new SpookyCraftingTableBlock(FabricBlockSettings.of(Material.STONE).strength(5.0F).sounds (BlockSoundGroup.STONE));

    // Registry
    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "white_pumpkin"), WHITE_PUMPKIN);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "white_pumpkin"), new BlockItem(WHITE_PUMPKIN, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "white_pumpkin_seeds"), WHITE_PUMPKIN_SEEDS);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_white_pumpkin_seeds"), ATTACHED_WHITE_PUMPKIN_SEEDS);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "white_pumpkin_seeds"), new BlockItem(WHITE_PUMPKIN_SEEDS, new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "carved_white_pumpkin"), CARVED_WHITE_PUMPKIN);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "carved_white_pumpkin"), new BlockItem(CARVED_WHITE_PUMPKIN, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_white_pumpkin_north"), ATTACHED_WHITE_PUMPKIN_NORTH);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_white_pumpkin_south"), ATTACHED_WHITE_PUMPKIN_SOUTH);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_white_pumpkin_east"), ATTACHED_WHITE_PUMPKIN_EAST);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_white_pumpkin_west"), ATTACHED_WHITE_PUMPKIN_WEST);

        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "green_pumpkin"), GREEN_PUMPKIN);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "green_pumpkin"), new BlockItem(GREEN_PUMPKIN, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "green_pumpkin_seeds"), GREEN_PUMPKIN_SEEDS);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_green_pumpkin_seeds"), ATTACHED_GREEN_PUMPKIN_SEEDS);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "green_pumpkin_seeds"), new BlockItem(GREEN_PUMPKIN_SEEDS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "carved_green_pumpkin"), CARVED_GREEN_PUMPKIN);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "carved_green_pumpkin"), new BlockItem(CARVED_GREEN_PUMPKIN, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_green_pumpkin_north"), ATTACHED_GREEN_PUMPKIN_NORTH);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_green_pumpkin_south"), ATTACHED_GREEN_PUMPKIN_SOUTH);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_green_pumpkin_east"), ATTACHED_GREEN_PUMPKIN_EAST);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "attached_green_pumpkin_west"), ATTACHED_GREEN_PUMPKIN_WEST);

        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "pedestal"), PEDESTAL);
        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "autumn_leaves"), AUTUMN_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "autumn_leaves"), new BlockItem(AUTUMN_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));

        Registry.register(Registry.BLOCK, new Identifier("gravesmash", "spooky_crafting_table"), SPOOKY_CRAFTING_TABLE);
        Registry.register(Registry.ITEM, new Identifier("gravesmash", "spooky_crafting_table"), new BlockItem(SPOOKY_CRAFTING_TABLE, new Item.Settings().group(ItemGroup.DECORATIONS)));

    }
}