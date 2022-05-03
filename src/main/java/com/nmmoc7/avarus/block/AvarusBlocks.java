package com.nmmoc7.avarus.block;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.fluids.AvarusFluids;
import com.nmmoc7.avarus.machine.multiblock.block.MultiBlockMachineBodyBlock;
import com.nmmoc7.avarus.machine.multiblock.block.MultiBlockMachineCoreBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
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

    // fluids

    public static final RegistryObject<LiquidBlock> WATER_STEAM = BLOCKS.register("water", () ->
            new LiquidBlock(AvarusFluids.WATER_STEAM, defaultFluid()));

    // machines

    public static final RegistryObject<MultiBlockMachineCoreBlock> MULTI_BLOCK_MACHINE_CORE = BLOCKS.register("multi_block_machine_core", () ->
            new MultiBlockMachineCoreBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));

    public static final RegistryObject<MultiBlockMachineBodyBlock> MULTI_BLOCK_MACHINE_BODY = BLOCKS.register("multi_block_machine_body", () ->
            new MultiBlockMachineBodyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));

    // ores

    public static final RegistryObject<Block> AQUAMARINE_ORE = BLOCKS.register("aquamarine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                    .requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> DEEPSLATE_AQUAMARINE_ORE = BLOCKS.register("deepslate_aquamarine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                    .requiresCorrectToolForDrops().strength(1.5F, 6.0F)));




    protected static BlockBehaviour.Properties defaultFluid() {
        return BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops();
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
