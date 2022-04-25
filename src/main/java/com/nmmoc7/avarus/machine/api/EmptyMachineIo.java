package com.nmmoc7.avarus.machine.api;

import net.minecraft.nbt.CompoundTag;

/**
 * @author DustW
 **/
public class EmptyMachineIo implements MachineIo<Integer, Integer> {
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Integer getSlot(int slot) {
        return 0;
    }

    @Override
    public Integer getFreeSpace(int slot) {
        return 0;
    }

    @Override
    public Integer add(int slot, Integer value, IOMode mode) {
        return 0;
    }

    @Override
    public Integer add(Integer value, IOMode mode) {
        return 0;
    }

    @Override
    public Integer remove(int slot, Integer value, IOMode mode) {
        return 0;
    }

    @Override
    public Integer remove(Integer integer, Integer value, IOMode mode) {
        return 0;
    }

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}
