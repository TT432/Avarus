package com.nmmoc7.avarus.item.tools;

import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author DustW
 **/
public class ColorfulHammerItem extends HammerItem {
    public ColorfulHammerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();

        BlockPos pos = pContext.getClickedPos();

        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof MultiBlockMachineBlockEntity machineCore) {
            if (machineCore.getMachine() != null) {
                machineCore.create(pContext.getPlayer());
            }
        }

        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslatableComponent("item.avarus.colorful_hammer.tooltip"));
    }
}
