package com.nmmoc7.avarus.machine.api;

import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

/**
 * @author DustW
 **/
public interface IMachine<SELF extends CapabilityProvider<SELF> & IMachine<SELF> & INBTSerializable<CompoundTag>> {
    /**
     * 用于在创建时自动在对应位置添加 body 方块
     * @return 相对位置列表
     */
    List<BlockPos> bodyPositions();

    BlockPos getCorePosition();

    /**
     * 放置之后的处理
     */
    void onCreate(MultiBlockMachineBlockEntity blockEntity, Player player);

    /**
     * 摧毁后的处理
     */
    void onDestroy();

    boolean onUse(Player pPlayer, InteractionHand pHand);

    void tick();

    default SELF self() {
        return (SELF) this;
    }

    default boolean canCreate(MultiBlockMachineBlockEntity blockEntity) {
        return bodyPositions().stream().allMatch(pos -> blockEntity.getLevel().getBlockState(blockEntity.getBlockPos().offset(pos)).isAir());
    }
}
