package com.nmmoc7.avarus.machine;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.machine.api.EmptyMachineIo;
import com.nmmoc7.avarus.machine.api.MachineIoCreator;
import com.nmmoc7.avarus.machine.api.MachineIoType;
import com.nmmoc7.avarus.machine.io.ItemStackMachineIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class AvarusMachineIoTypes {
    private static final ResourceLocation MACHINE_IO_TYPE_NAME = new ResourceLocation(Avarus.MOD_ID, "machine_io_type");
    private static final DeferredRegister<MachineIoType<?, ?, ?>> TYPES = DeferredRegister.create(MACHINE_IO_TYPE_NAME, Avarus.MOD_ID);

    public static final RegistryObject<MachineIoType<Integer, Integer, Object>> EMPTY =
            register("empty", null, EmptyMachineIo::new);

    public static final RegistryObject<MachineIoType<ItemStack, Integer, IItemHandler>> ITEM_STACK_IO =
            register("item_stack_io", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, ItemStackMachineIo::new);

    private static <T, VT, CT> RegistryObject<MachineIoType<T, VT, CT>> register(
            String name,
            Capability<CT> capability,
            MachineIoCreator<T, VT> machineCreator
    ) {
        return TYPES.register(name, () -> new MachineIoType<>(machineCreator, capability));
    }

    public static Supplier<IForgeRegistry<MachineIoType<?, ?, ?>>> REGISTRY;

    public static void register(IEventBus bus) {
        REGISTRY = TYPES.makeRegistry(c(MachineIoType.class), RegistryBuilder<MachineIoType<?, ?, ?>>::new);
        TYPES.register(bus);
    }

    private static <T> Class<T> c(Class<?> cls) { return (Class<T>)cls; }
}
