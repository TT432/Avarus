package com.nmmoc7.avarus.item.machine;

import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.machine.api.MachineType;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class BlueprintItem<TYPE extends CapabilityProvider<TYPE> & Machine<TYPE> & INBTSerializable<CompoundTag>> extends Item {
    Supplier<MachineType<TYPE>> machineType;
    String machineName;

    public BlueprintItem(String machineName, RegistryObject<MachineType<TYPE>> machineType, Properties pProperties) {
        super(pProperties);
        this.machineType = machineType;
        this.machineName = machineName;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof MultiBlockMachineBlockEntity machineCore) {
            if (machineCore.getMachine() == null) {
                machineCore.setMachine(machineType.get(), new ItemStack(this));
            }
        }

        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(stack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(new TranslatableComponent("item.avarus.blueprint.tooltip." + machineName));
    }
}
