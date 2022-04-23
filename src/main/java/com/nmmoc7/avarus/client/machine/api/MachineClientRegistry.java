package com.nmmoc7.avarus.client.machine.api;

import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.machine.api.MachineType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class MachineClientRegistry {
    private static final Map<MachineType<?>, MachineClient<?>> registry = new HashMap<>();

    public static <TYPE extends CapabilityProvider<TYPE> & Machine<TYPE> & INBTSerializable<CompoundTag>> void register(
            MachineType<TYPE> machineType,
            MachineClient<TYPE> machineClient
    ) {
        if (registry.containsKey(machineType)) {
            throw new IllegalArgumentException("the MachineType already have MachineClient : " + machineType.getRegistryName().toString());
        }

        registry.put(machineType, machineClient);
    }

    @Nullable
    public static <TYPE extends CapabilityProvider<TYPE> & Machine<TYPE> & INBTSerializable<CompoundTag>> MachineClient<TYPE> getClient(
            MachineType<TYPE> machineType
    ) {
        try {
            return (MachineClient<TYPE>) registry.get(machineType);
        }
        catch (ClassCastException e) {
            return null;
        }
    }
}
