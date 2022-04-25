package com.nmmoc7.avarus.machine.io;

import com.nmmoc7.avarus.machine.api.MachineIo;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author DustW
 **/
public class ItemStackMachineIo extends ItemStackHandler implements MachineIo<ItemStack, Integer> {
    public ItemStackMachineIo() {
        super(16);
    }

    public IItemHandler getItemHandler() {
        return this;
    }

    @Override
    public int getSize() {
        return getItemHandler().getSlots();
    }

    @Override
    public ItemStack getSlot(int slot) {
        return getItemHandler().getStackInSlot(slot);
    }

    @Override
    public Integer getFreeSpace(int slot) {
        return getItemHandler().getSlotLimit(slot) - getItemHandler().getStackInSlot(slot).getCount();
    }

    @Override
    public ItemStack add(int slot, ItemStack value, IOMode mode) {
        return getItemHandler().insertItem(slot, value, mode.simulate());
    }

    @Override
    public ItemStack add(ItemStack value, IOMode mode) {
        ItemStack result = value.copy();

        for (int i = 0; i < getSize(); i++) {
            if (result.isEmpty()) {
                return ItemStack.EMPTY;
            }

            ItemStack stack = getSlot(i);

            if (stack.isEmpty() || getItemHandler().isItemValid(i, value)) {
                result = add(i, result, mode);
            }
        }

        return result;
    }

    @Override
    public ItemStack remove(int slot, Integer value, IOMode mode) {
        return getItemHandler().extractItem(slot, value, mode.simulate());
    }

    @Override
    public ItemStack remove(ItemStack itemStack, Integer value, IOMode mode) {
        value = Mth.clamp(value, 0, 64);
        int removedItem = 0;

        for (int i = 0; i < getSize(); i++) {
            if (removedItem >= value) {
                return new ItemStack(itemStack.getItem(), removedItem);
            }

            ItemStack stack = getSlot(i);

            if (stack.getItem() == itemStack.getItem()) {
                removedItem += remove(i, value - removedItem, mode).getCount();
            }
        }

        return new ItemStack(itemStack.getItem(), removedItem);
    }
}
