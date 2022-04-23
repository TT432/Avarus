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
 * 使用 machine 需要注册 machine type，详见 {@link com.nmmoc7.avarus.machine.AvarusMachineTypes}.
 * 默认实现：{@link com.nmmoc7.avarus.machine.impls.BaseMachine}
 * @author DustW
 **/
public interface Machine<SELF extends CapabilityProvider<SELF> & Machine<SELF> & INBTSerializable<CompoundTag>> {
    /**
     * 用于在创建时自动在对应位置添加 body 方块
     * @return 相对位置列表
     */
    List<BlockPos> bodyPositions();

    /**
     * 用于获取 block entity
     * @return block entity
     */
    MultiBlockMachineBlockEntity getBlockEntity();

    /**
     * 获取核心方块的位置，计算偏移量
     * @return 核心方块的位置
     */
    BlockPos getCorePosition();

    /**
     * 建造之后的处理
     * @param player      尝试建造的玩家
     */
    void onCreate(Player player);

    /**
     * 摧毁后的处理
     */
    void onDestroy();

    /**
     * 获取方块更新的 tag，会在每次更新时调用
     * @param tag 总 tag
     */
    void saveUpdateTag(CompoundTag tag);

    /**
     * 处理方块更新的 tag，会在每次更新时调用
     * @param tag 处理方块更新的 tag
     */
    void handleUpdateTag(CompoundTag tag);

    /**
     * 是否需要同步
     * 使用位置 {@link MultiBlockMachineBlockEntity}
     * @return 是否需要同步
     */
    boolean isNeedSync();

    /**
     * 设置同步状态
     * @param needSync 是否需要同步
     */
    void setNeedSync(boolean needSync);

    /**
     * 在玩家 use 的时候调用
     * @param pPlayer 玩家
     * @param pHand   使用的手
     * @return        是否成功
     */
    boolean onUse(Player pPlayer, InteractionHand pHand);

    void tick();

    default SELF self() {
        return (SELF) this;
    }

    default boolean canCreate() {
        return bodyPositions().stream().allMatch(pos -> getBlockEntity().getLevel().getBlockState(getBlockEntity().getBlockPos().offset(pos)).isAir());
    }
}
