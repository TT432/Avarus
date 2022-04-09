package com.nmmoc7.avarus.item.debug;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

/**
 * @author DustW
 **/
public class ClearBlockItem extends Item {
    public ClearBlockItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Block block = pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock();

        for (int i = -100; i < 100; i++) {
            for (int j = -100; j < 100; j++) {
                for (int k = -100; k < 100; k++) {
                    BlockPos pos = pContext.getClickedPos().offset(i, j, k);
                    if (pContext.getLevel().getBlockState(pos).getBlock() == block) {
                        pContext.getLevel().setBlock(
                                pos,
                                Blocks.AIR.defaultBlockState(),
                                0
                        );
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}
