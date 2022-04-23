package com.nmmoc7.avarus.machine;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.machine.api.MachineCreator;
import com.nmmoc7.avarus.machine.api.MachineType;
import com.nmmoc7.avarus.machine.impls.test.TestMachine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class AvarusMachineTypes {
    private static final ResourceLocation MACHINE_TYPE_NAME = new ResourceLocation(Avarus.MOD_ID, "machine_type");
    private static final DeferredRegister<MachineType<?>> TYPES = DeferredRegister.create(MACHINE_TYPE_NAME, Avarus.MOD_ID);

    public static final RegistryObject<MachineType<TestMachine>> TEST = register("test", TestMachine::new);

    private static <TYPE extends CapabilityProvider<TYPE> & Machine<TYPE> & INBTSerializable<CompoundTag>> RegistryObject<MachineType<TYPE>> register(
            String name,
            MachineCreator<TYPE> machineCreator
    ) {
        return TYPES.register(name, () -> new MachineType<>(machineCreator));
    }

    public static Supplier<IForgeRegistry<MachineType<?>>> REGISTRY;

    public static void register(IEventBus bus) {
        REGISTRY = TYPES.makeRegistry(c(MachineType.class), RegistryBuilder<MachineType<?>>::new);
        TYPES.register(bus);
    }

    private static <T> Class<T> c(Class<?> cls) { return (Class<T>)cls; }
}
