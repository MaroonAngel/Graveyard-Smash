package net.jam.gravesmash.item;

import net.jam.gravesmash.GraveSmash;
import net.jam.gravesmash.armor.CustomArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    // Seeds
    public static final Item WHITE_PUMPKIN_SEEDS = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item GREEN_PUMPKIN_SEEDS = new Item(new Item.Settings().group(ItemGroup.MISC));

    // Jack
    public static final Item AUTUMN_ESSENCE = new Item(new Item.Settings().group(ItemGroup.MISC));

    // Confuzzling Armor Shenanigans
    public static final ArmorMaterial customArmorMaterial = new CustomArmorMaterial();
    public static final Item AUTUMN_HELMET = new ArmorItem(customArmorMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item AUTUMN_CHESTPLATE = new ArmorItem(customArmorMaterial, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item AUTUMN_LEGGINGS = new ArmorItem(customArmorMaterial, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item AUTUMN_STOMPERS = new ArmorItem(customArmorMaterial, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

    // Registry
    public static void register() {
    //Registry.register(Registry.ITEM, new Identifier("gravesmash", "white_pumpkin_seeds"), (WHITE_PUMPKIN_SEEDS));
    //Registry.register(Registry.ITEM, new Identifier("gravesmash", "green_pumpkin_seeds"), (GREEN_PUMPKIN_SEEDS));
    
    Registry.register(Registry.ITEM, new Identifier("gravesmash", "autumn_essence"), (AUTUMN_ESSENCE));
    Registry.register(Registry.ITEM, new Identifier("gravesmash", "autumn_helmet"), (AUTUMN_HELMET));
    Registry.register(Registry.ITEM, new Identifier("gravesmash", "autumn_chestplate"), (AUTUMN_CHESTPLATE));
    Registry.register(Registry.ITEM, new Identifier("gravesmash", "autumn_leggings"), (AUTUMN_LEGGINGS));
    Registry.register(Registry.ITEM, new Identifier("gravesmash", "autumn_stompers"), (AUTUMN_STOMPERS));
 }
}

