package net.jam.gravesmash.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class Screens {
    public static final ScreenHandlerType<SpookyCraftingTableScreenHandler> SPOOKY_CRAFTING_TABLE_SCREEN_HANDLER;

    static {
        SPOOKY_CRAFTING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("gravesmash", "spooky_crafting_table"),
                SpookyCraftingTableScreenHandler::new);
    }
}
