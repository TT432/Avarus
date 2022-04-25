package com.nmmoc7.avarus.machine.multiblock.block;

import com.nmmoc7.avarus.blockentities.AvarusBlockEntityTypes;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import com.nmmoc7.avarus.utils.TickAble;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 **/
public class MultiBlockMachineCoreBlock extends BaseEntityBlock {
    public MultiBlockMachineCoreBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MultiBlockMachineBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity tile = pLevel.getBlockEntity(pPos);

        if (tile instanceof MultiBlockMachineBlockEntity core && core.isCreated()) {
            if (core.use(pPlayer, pHand, pPos)) {
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof MultiBlockMachineBlockEntity core) {
            core.onRemoved();
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return TickAble.getTicker(pLevel, AvarusBlockEntityTypes.MULTI_BLOCK_MACHINE.get(), pBlockEntityType);
    }
}
