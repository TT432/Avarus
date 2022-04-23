package com.nmmoc7.avarus.machine.api;

import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author DustW
 **/
@FunctionalInterface
public interface MachineCreator<TYPE extends CapabilityProvider<TYPE> & Machine<TYPE> & INBTSerializable<CompoundTag>> {
    /**
     * 创建一个新的机器
     * @param core  block entity
     * @return      机器
     */
    Machine<TYPE> create(MultiBlockMachineBlockEntity core);
}
