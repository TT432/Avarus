package com.nmmoc7.avarus.item.tools;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

/**
 * @author DustW
 **/
public class HammerItem extends Item {
    public HammerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        var result = itemStack.copy();
        result.hurt(1, new Random(), null);
        return result;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return stack.getDamageValue() != stack.getMaxDamage();
    }
}
