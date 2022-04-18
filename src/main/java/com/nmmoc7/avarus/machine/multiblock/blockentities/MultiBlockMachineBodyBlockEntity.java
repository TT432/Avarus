package com.nmmoc7.avarus.machine.multiblock.blockentities;

import com.nmmoc7.avarus.blockentities.AvarusBlockEntityTypes;
import com.nmmoc7.avarus.machine.api.IMachine;
import com.nmmoc7.avarus.utils.CompoundTagUtils;
import com.nmmoc7.avarus.utils.ITickAble;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author DustW
 **/
public class MultiBlockMachineBodyBlockEntity extends BlockEntity implements ITickAble {
    public MultiBlockMachineBodyBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AvarusBlockEntityTypes.MULTI_BLOCK_MACHINE_BODY.get(), pWorldPosition, pBlockState);
    }

    MultiBlockMachineBlockEntity core;
    BlockPos corePos;

    protected IMachine<?> getMachine() {
        return core.getMachine();
    }

    public MultiBlockMachineBlockEntity getCore() {
        return core;
    }

    public void setCore(MultiBlockMachineBlockEntity core) {
        this.core = core;
    }

    @Override
    public void tick() {
        if (corePos != null && core == null) {
            if (level.getBlockEntity(corePos) instanceof MultiBlockMachineBlockEntity core) {
                this.core = core;
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("core", CompoundTagUtils.toTag(core.getBlockPos()));
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        corePos = CompoundTagUtils.toPos(pTag.getCompound("core"));
    }

    public boolean use(Player pPlayer, InteractionHand pHand) {
        return core.use(pPlayer, pHand);
    }
}
