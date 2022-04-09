package com.nmmoc7.avarus.item;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.block.AvarusBlocks;
import com.nmmoc7.avarus.item.debug.ClearBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

    public static final RegistryObject<Item> COLORFUL_ALLOY = ITEMS.register("colorful_alloy",
            () -> new Item(defaultProperties()));

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
