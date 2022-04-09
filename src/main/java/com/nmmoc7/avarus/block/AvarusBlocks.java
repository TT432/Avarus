package com.nmmoc7.avarus.block;

import com.nmmoc7.avarus.Avarus;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class AvarusBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Avarus.MOD_ID);

    private static final BlockBehaviour.Properties DIRT = BlockBehaviour.Properties.of(Material.DIRT).strength(1F).sound(SoundType.GRAVEL);

    public static final RegistryObject<Block> RED_DIRT = BLOCKS.register("red_dirt", () -> new Block(DIRT));
    public static final RegistryObject<Block> GREEN_DIRT = BLOCKS.register("green_dirt", () -> new Block(DIRT));
    public static final RegistryObject<Block> YELLOW_DIRT = BLOCKS.register("yellow_dirt", () -> new Block(DIRT));
    public static final RegistryObject<Block> BLUE_DIRT = BLOCKS.register("blue_dirt", () -> new Block(DIRT));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
