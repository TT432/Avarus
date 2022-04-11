package com.nmmoc7.avarus.item.tools;

import net.minecraft.world.item.Item;

/**
 * @author DustW
 **/
public class HammerItem extends Item {
    private final int hammerLevel;

    public HammerItem(int hammerLevel, Properties pProperties) {
        super(pProperties);
        this.hammerLevel = hammerLevel;
    }

    public int getHammerLevel() {
        return this.hammerLevel;
    }
}
