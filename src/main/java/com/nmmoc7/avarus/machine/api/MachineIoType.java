package com.nmmoc7.avarus.machine.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistryEntry;

/**
 * 需要注册，详见：{@link com.nmmoc7.avarus.machine.AvarusMachineIoTypes}
 * @author DustW
 **/
public class MachineIoType<T, VT, CT> extends ForgeRegistryEntry<MachineIoType<?, ?, ?>> {
    MachineIoCreator<T, VT> creator;
    Capability<CT> capability;

    public MachineIoType(MachineIoCreator<T, VT> creator, Capability<CT> capability) {
        this.creator = creator;
        this.capability = capability;
    }

    public LazyOptional<MachineIo<?, ?>> create() {
        return LazyOptional.of(() -> creator.create());
    }

    public Capability<CT> getCapability() {
        return capability;
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
