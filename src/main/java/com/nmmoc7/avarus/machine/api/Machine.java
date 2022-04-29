package com.nmmoc7.avarus.machine.api;

import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

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
    Map<BlockPos, MachineIoType<?, ?, ?>> bodyPositions();

    /**
     * 用于获取 block entity
     * @return block entity
     */
    MultiBlockMachineBlockEntity getBlockEntity();

    /**
     * 使用坐标获取 capability
     * @param cap capability
     * @param pos body 的 pos
     * @param <T> capability 的类型
     * @return    符合条件返回具体内容，否则 LazyOptional#empty
     */
    <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull BlockPos pos);

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
     * @param pos     使用的位置
     * @return        是否成功
     */
    boolean onUse(Player pPlayer, InteractionHand pHand, BlockPos pos);

    void tick();

    default SELF self() {
        return (SELF) this;
    }

    default boolean canCreate() {
        return bodyPositions().keySet().stream().allMatch(pos ->
                getBlockEntity().getLevel().getBlockState(getBlockEntity().getBlockPos().offset(pos)).isAir());
    }

    List<ItemStack> getDropItems();
}
