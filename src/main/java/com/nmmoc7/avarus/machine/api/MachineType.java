package com.nmmoc7.avarus.machine.api;

import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * 需要注册，详见: {@link com.nmmoc7.avarus.machine.AvarusMachineTypes}
 * @author DustW
 **/
public class MachineType<TYPE extends CapabilityProvider<TYPE> & Machine<TYPE> & INBTSerializable<CompoundTag>> extends ForgeRegistryEntry<MachineType<?>> {
    private final MachineCreator<TYPE> provider;

    public MachineType(MachineCreator<TYPE> provider) {
        this.provider = provider;
    }

    public Lazy<Machine<TYPE>> newMachine(MultiBlockMachineBlockEntity blockEntity) {
        return Lazy.of(() -> provider.create(blockEntity));
    }

    @Override
    public int hashCode() {
        return getRegistryName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof MachineType other && other.getRegistryName().equals(this.getRegistryName()));
    }
}
