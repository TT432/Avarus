package com.nmmoc7.avarus.client.renderers.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.nmmoc7.avarus.client.machine.api.MachineClient;
import com.nmmoc7.avarus.client.machine.api.MachineClientRegistry;
import com.nmmoc7.avarus.client.rendertype.AvarusRenderType;
import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class MultiBlockMachineBlockEntityRenderer implements BlockEntityRenderer<MultiBlockMachineBlockEntity> {
    public MultiBlockMachineBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
    }

    @Override
    public boolean shouldRenderOffScreen(MultiBlockMachineBlockEntity pBlockEntity) {
        return true;
    }

    @Override
    public void render(MultiBlockMachineBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (blockEntity.getMachine() != null) {
            if (blockEntity.isCreated()) {
                MachineClient<?> machineClient = MachineClientRegistry.getClient(blockEntity.getMachineType());

                if (machineClient == null) {
                    return;
                }

                machineClient.render(blockEntity, blockEntity.getMachine(), partialTick, poseStack, bufferSource, packedLight, packedOverlay);
            }
            else {
                renderBodyLines(blockEntity, poseStack);
            }
        }
    }

    private static void line(VertexConsumer builder, Matrix4f positionMatrix, BlockPos pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2, boolean red) {
        float redColor = red ? 1 : 0;
        float blueColor = red ? 0 : 1;

        builder.vertex(positionMatrix, pos.getX()+dx1, pos.getY()+dy1, pos.getZ()+dz1)
                .color(redColor, 0.0f, blueColor, 1.0f)
                .endVertex();
        builder.vertex(positionMatrix, pos.getX()+dx2, pos.getY()+dy2, pos.getZ()+dz2)
                .color(redColor, 0.0f, blueColor, 1.0f)
                .endVertex();
    }

    private static void renderBodyLines(MultiBlockMachineBlockEntity core, PoseStack matrixStack) {
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer builder = buffer.getBuffer(AvarusRenderType.OVERLAY_LINES);

        matrixStack.pushPose();

        Matrix4f matrix = matrixStack.last().pose();

        Machine<?> machine = core.getMachine();

        boolean red = !machine.canCreate();

        List<BlockPos> list = new ArrayList<>(machine.bodyPositions());
        list.add(machine.getCorePosition());

        for (BlockPos pos : list) {
            line(builder, matrix, pos, 0, 0, 0, 1, 0, 0, red);
            line(builder, matrix, pos, 0, 1, 0, 1, 1, 0, red);
            line(builder, matrix, pos, 0, 0, 1, 1, 0, 1, red);
            line(builder, matrix, pos, 0, 1, 1, 1, 1, 1, red);

            line(builder, matrix, pos, 0, 0, 0, 0, 0, 1, red);
            line(builder, matrix, pos, 1, 0, 0, 1, 0, 1, red);
            line(builder, matrix, pos, 0, 1, 0, 0, 1, 1, red);
            line(builder, matrix, pos, 1, 1, 0, 1, 1, 1, red);

            line(builder, matrix, pos, 0, 0, 0, 0, 1, 0, red);
            line(builder, matrix, pos, 1, 0, 0, 1, 1, 0, red);
            line(builder, matrix, pos, 0, 0, 1, 0, 1, 1, red);
            line(builder, matrix, pos, 1, 0, 1, 1, 1, 1, red);
        }

        matrixStack.popPose();

        RenderSystem.disableDepthTest();
        buffer.endBatch(AvarusRenderType.OVERLAY_LINES);
    }
}
