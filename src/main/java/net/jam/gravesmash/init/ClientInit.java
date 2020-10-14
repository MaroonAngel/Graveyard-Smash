package net.jam.gravesmash.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.jam.gravesmash.block.Blocks;
import net.jam.gravesmash.screen.Screens;
import net.jam.gravesmash.screen.SpookyCraftingTableScreen;
import net.minecraft.client.render.RenderLayer;

public class ClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.WHITE_PUMPKIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.WHITE_PUMPKIN_SEEDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_WHITE_PUMPKIN_SEEDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_WHITE_PUMPKIN_NORTH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_WHITE_PUMPKIN_EAST, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_WHITE_PUMPKIN_WEST, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_WHITE_PUMPKIN_SOUTH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.GREEN_PUMPKIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.GREEN_PUMPKIN_SEEDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_GREEN_PUMPKIN_SEEDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_GREEN_PUMPKIN_NORTH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_GREEN_PUMPKIN_EAST, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_GREEN_PUMPKIN_WEST, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ATTACHED_GREEN_PUMPKIN_SOUTH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.AUTUMN_LEAVES, RenderLayer.getCutout());

        ScreenRegistry.register(Screens.SPOOKY_CRAFTING_TABLE_SCREEN_HANDLER, SpookyCraftingTableScreen::new);
    }
}
