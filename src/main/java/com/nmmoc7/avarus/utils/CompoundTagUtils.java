package com.nmmoc7.avarus.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

/**
 * @author DustW
 **/
public class CompoundTagUtils {
    /** 将 BlockPos 转换为 CompoundTag */
    public static CompoundTag toTag(BlockPos pPos) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", pPos.getX());
        tag.putInt("y", pPos.getY());
        tag.putInt("z", pPos.getZ());
        return tag;
    }

    /** 将 CompoundTag 转换为 BlockPos */
    public static BlockPos toPos(CompoundTag pTag) {
        return new BlockPos(pTag.getInt("x"), pTag.getInt("y"), pTag.getInt("z"));
    }
}
