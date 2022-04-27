package com.nmmoc7.avarus.power;

import net.minecraft.core.BlockPos;

/**
 * @author DustW
 **/
public class PowerNode {
    BlockPos pos;
    PowerMode mode;

    public PowerNode(BlockPos pos, PowerMode mode) {
        this.pos = pos;
        this.mode = mode;
        visited = false;
    }

    protected boolean visited;

    @Override
    public int hashCode() {
        return pos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof PowerNode pm && pm.pos.equals(pos);
    }

    enum PowerMode {
        SEND,
        RECEIVE,
        BOTH,
        RELAY;

        boolean send() { return this == SEND; }
        boolean receive() { return this == RECEIVE; }
        boolean both() { return this == BOTH; }
        boolean relay() { return this == RELAY; }
    }
}
