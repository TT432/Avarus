package com.nmmoc7.avarus.machine.multiblock.blockentities;

import com.nmmoc7.avarus.blockentities.AvarusBlockEntityTypes;
import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.utils.TickAble;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 **/
public class MultiBlockMachineBodyBlockEntity extends BlockEntity implements TickAble {
    public MultiBlockMachineBodyBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AvarusBlockEntityTypes.MULTI_BLOCK_MACHINE_BODY.get(), pWorldPosition, pBlockState);
    }

    MultiBlockMachineBlockEntity core;

    protected Machine<?> getMachine() {
        return getCore().getMachine();
    }

    public MultiBlockMachineBlockEntity getCore() {
        return core;
    }

    public void setCore(MultiBlockMachineBlockEntity core) {
        this.core = core;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getMachine() != null ? getMachine().getCapability(cap, getBlockPos()) : LazyOptional.empty();
    }

    public boolean use(Player pPlayer, InteractionHand pHand) {
        return getCore().use(pPlayer, pHand, getBlockPos());
    }

    @Override
    public void tick() {

    }
}
