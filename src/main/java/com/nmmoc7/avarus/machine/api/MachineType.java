package com.nmmoc7.avarus.machine.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class MachineType<TYPE extends CapabilityProvider<TYPE> & IMachine<TYPE> & INBTSerializable<CompoundTag>> extends ForgeRegistryEntry<MachineType<?>> {
    private final Supplier<IMachine<TYPE>> provider;

    public MachineType(Supplier<IMachine<TYPE>> provider) {
        this.provider = provider;
    }

    public Lazy<IMachine<TYPE>> newMachine() {
        return Lazy.of(provider);
    }
}
