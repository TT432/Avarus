package com.nmmoc7.avarus.power;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * @author DustW
 **/
public class PowerManager extends SavedData {
    private static final String NAME = "POWER_MANAGER";

    public static PowerManager get(Level level) {
        if (level instanceof ServerLevel world) {
            return  world.getDataStorage().computeIfAbsent(PowerManager::read, PowerManager::new, NAME);
        } else {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
    }

    private final PowerGraph graph = new PowerGraph();

    public PowerGraph getGraph() {
        return graph;
    }

    //todo: implement
    public static PowerManager read(CompoundTag tag) {
        return null;
    }


    //todo: implement
    @Override
    public CompoundTag save(CompoundTag pCompoundTag) {
        return null;
    }
}
