package com.nmmoc7.avarus.machine.io;

/**
 * @author DustW
 **/
public interface MachineIoType<TYPE, VALUE_TYPE> {
    /**
     * @return 返回 slot 的数量
     */
    int getSize();

    /**
     * 重要：他的结果应该是不可变的
     * @return 当前接口能获取到的
     */
    TYPE getSlot(int slot);

    /**
     * 获取空闲空间
     * @return 还能够装多少
     */
    VALUE_TYPE getFreeSpace(int slot);

    /**
     * 针对单个 slot 的添加操作
     * @param slot  slot
     * @param value 要添加的内容
     * @param mode  模式
     * @return      添加后剩余的，并未能添加进的数值
     */
    TYPE add(int slot, TYPE value, IOMode mode);

    /**
     * 自动搜索空闲并添加进去
     * @param value 要添加的内容
     * @param mode  模式
     * @return      添加后剩余的，并未能添加进的数值
     */
    TYPE add(TYPE value, IOMode mode);

    /**
     * 针对单个 slot 的移出操作
     * @param slot  slot
     * @param value 要移除的数值
     * @param mode  模式
     * @return      移除后成功移除的数值
     */
    TYPE remove(int slot, VALUE_TYPE value, IOMode mode);

    /**
     * 自动搜索内容并返回成功移除的内容
     * @param type  要移除的内容类型
     * @param value 要移除的数值
     * @param mode  模式
     * @return      移除后成功移除的数值
     */
    TYPE remove(TYPE type, VALUE_TYPE value, IOMode mode);

    enum IOMode {
        /** 执行 */
        EXECUTE,
        /** 模拟 */
        SIMULATE;

        public boolean simulate() {return this == SIMULATE;}
        public boolean execute() {return this == EXECUTE;}
    }
}
