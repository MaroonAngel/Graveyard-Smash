package net.jam.gravesmash.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    // Seeds
    public static final Item WHITE_PUMPKIN_SEEDS = new Item(new Item.Settings().group(ItemGroup.MISC));

    // Registry
    public static void register() {
    Registry.register(Registry.ITEM, new Identifier("gravesmash", "white_pumpkin_seeds"), (WHITE_PUMPKIN_SEEDS));
 }
}
