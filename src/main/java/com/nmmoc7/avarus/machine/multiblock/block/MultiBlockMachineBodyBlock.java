package com.nmmoc7.avarus.machine.multiblock.block;

import com.nmmoc7.avarus.blockentities.AvarusBlockEntityTypes;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBodyBlockEntity;
import com.nmmoc7.avarus.utils.ITickAble;
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
public class MultiBlockMachineBodyBlock extends BaseEntityBlock {
    public MultiBlockMachineBodyBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MultiBlockMachineBodyBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity tile = pLevel.getBlockEntity(pPos);

        if (tile instanceof MultiBlockMachineBodyBlockEntity body) {
            if (body.use(pPlayer, pHand)) {
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof MultiBlockMachineBodyBlockEntity body) {
            body.getCore().onRemoved();
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (BlockEntityTicker<T>) ITickAble.getTicker(pLevel, AvarusBlockEntityTypes.MULTI_BLOCK_MACHINE_BODY.get(), pBlockEntityType);
    }
}
