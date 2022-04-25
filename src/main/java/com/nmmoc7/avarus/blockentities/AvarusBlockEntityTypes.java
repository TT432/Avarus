package com.nmmoc7.avarus.blockentities;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.block.AvarusBlocks;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBodyBlockEntity;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class AvarusBlockEntityTypes {
    private static final DeferredRegister<BlockEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Avarus.MOD_ID);

    public static final RegistryObject<BlockEntityType<MultiBlockMachineBlockEntity>> MULTI_BLOCK_MACHINE = register("multi_block_machine",
            () -> BlockEntityType.Builder.of(MultiBlockMachineBlockEntity::new,
                    AvarusBlocks.MULTI_BLOCK_MACHINE_CORE.get()));

    public static final RegistryObject<BlockEntityType<MultiBlockMachineBodyBlockEntity>> MULTI_BLOCK_MACHINE_BODY = register("multi_block_machine_body",
            () -> BlockEntityType.Builder.of(MultiBlockMachineBodyBlockEntity::new,
                    AvarusBlocks.MULTI_BLOCK_MACHINE_BODY.get()));

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, Supplier<BlockEntityType.Builder<T>> builder) {
        return TYPES.register(name, () -> builder.get().build(Util.fetchChoiceType(References.BLOCK_ENTITY, name)));
    }

    public static void register(IEventBus bus) {
        TYPES.register(bus);
    }
}
