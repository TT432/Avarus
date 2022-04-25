package com.nmmoc7.avarus.machine.api;

/**
 * @author DustW
 **/
@FunctionalInterface
public interface MachineIoCreator<T, VT> {
    /**
     * 创建一个新实例
     * @return 新实例
     */
    MachineIo<T, VT> create();
}
