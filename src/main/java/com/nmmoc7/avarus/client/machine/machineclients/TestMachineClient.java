package com.nmmoc7.avarus.client.machine.machineclients;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.nmmoc7.avarus.client.machine.api.MachineClient;
import com.nmmoc7.avarus.client.models.BakedModelHandler;
import com.nmmoc7.avarus.machine.impls.test.TestMachine;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import com.nmmoc7.avarus.utils.ClientTickHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;

/**
 * @author DustW
 **/
public class TestMachineClient extends MachineClient<TestMachine> {
    @Override
    public void render(MultiBlockMachineBlockEntity blockEntity, TestMachine machine, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        double time = ClientTickHandler.ticksInGame + partialTick;

        VertexConsumer buffer = bufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(blockEntity.getBlockState(), false));

        poseStack.pushPose();

        poseStack.translate(0, 0.5, 0);

        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Vector3f.YP.rotationDegrees((float) time % 360));
        poseStack.translate(-0.5, -0.5, -0.5);
        poseStack.translate(0F, (float) Math.sin(time / 20.0) * 0.05F, 0F);
        BakedModel cube = BakedModelHandler.testMachine;
        Minecraft.getInstance().getBlockRenderer().getModelRenderer()
                .renderModel(poseStack.last(), buffer, blockEntity.getBlockState(),
                        cube, 1, 1, 1, packedLight, packedOverlay);
        poseStack.popPose();
    }
}
