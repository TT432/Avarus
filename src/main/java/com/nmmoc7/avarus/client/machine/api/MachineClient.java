package com.nmmoc7.avarus.client.machine.api;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

/**
 * @author DustW
 **/
public abstract class MachineClient<TYPE extends CapabilityProvider<TYPE> & Machine<TYPE> & INBTSerializable<CompoundTag>> {
    public abstract void render(MultiBlockMachineBlockEntity blockEntity, TYPE machine, float partialTick, PoseStack poseStack,
                                MultiBufferSource bufferSource, int packedLight, int packedOverlay);

    public final void render(MultiBlockMachineBlockEntity blockEntity, Machine<?> machine, float partialTick, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (cast(machine) == null) {
            return;
        }

        render(blockEntity, cast(machine), partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }

    @Nullable
    protected final TYPE cast(Machine<?> machine) {
        try {
            return (TYPE) machine;
        }
        catch (Exception e) {
            return null;
        }
    }
}
