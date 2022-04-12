package com.nmmoc7.avarus.item;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.block.AvarusBlocks;
import com.nmmoc7.avarus.fluids.AvarusFluids;
import com.nmmoc7.avarus.item.debug.ClearBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class AvarusItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Avarus.MOD_ID);

    // debug items

    public static final RegistryObject<Item> CLEAR_BLOCK = ITEMS.register("clear_block",
            () -> new ClearBlockItem(defaultProperties()));

    // colorful dirt

    public static final RegistryObject<Item> RED_DIRT = formBlock("red_dirt", AvarusBlocks.RED_DIRT, defaultProperties());
    public static final RegistryObject<Item> GREEN_DIRT = formBlock("green_dirt", AvarusBlocks.GREEN_DIRT, defaultProperties());
    public static final RegistryObject<Item> YELLOW_DIRT = formBlock("yellow_dirt", AvarusBlocks.YELLOW_DIRT, defaultProperties());
    public static final RegistryObject<Item> BLUE_DIRT = formBlock("blue_dirt", AvarusBlocks.BLUE_DIRT, defaultProperties());

    // materials

    public static final RegistryObject<Item> COLORFUL_POWDER = ITEMS.register("colorful_powder",
            () -> new Item(defaultProperties()));

    // alloys

    public static final RegistryObject<Item> COLORFUL_ALLOY = ITEMS.register("colorful_alloy",
            () -> new Item(defaultProperties()));

    // ingot

    public static final RegistryObject<Item> COLORFUL_ALLOY_INGOT = ITEMS.register("colorful_alloy_ingot",
            () -> new Item(defaultProperties()));

    // plates

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(defaultProperties()));

    public static final RegistryObject<Item> COLORFUL_ALLOY_PLATE = ITEMS.register("colorful_alloy_plate",
            () -> new Item(defaultProperties()));

    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate",
            () -> new Item(defaultProperties()));

    // wires

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            () -> new Item(defaultProperties()));

    // tools

    // hammers

    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new Item(defaultProperties().defaultDurability(512)));

    public static final RegistryObject<Item> COLORFUL_ALLOY_HAMMER = ITEMS.register("colorful_alloy_hammer",
            () -> new Item( defaultProperties().defaultDurability(2048)));

    public static final RegistryObject<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new Item(defaultProperties().defaultDurability(8192)));

    // buckets

    public static final RegistryObject<Item> EMPTY_FLUID_UNIT = ITEMS.register("empty_fluid_unit",
            () -> new BucketItem(() -> Fluids.EMPTY, defaultProperties()));

    public static final RegistryObject<Item> WATER_STEAM_FLUID_UNIT = ITEMS.register("water_steam_fluid_unit",
            () -> new BucketItem(AvarusFluids.WATER_STEAM, defaultProperties()));










    private static RegistryObject<Item> formBlock(String name, RegistryObject<Block> block, Item.Properties properties) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }

    private static Item.Properties defaultProperties() {
        return new Item.Properties().tab(Avarus.MOD_TAB);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
