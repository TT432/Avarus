package com.nmmoc7.avarus.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nmmoc7.avarus.Avarus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BakedModelHandler {
    private static final ResourceLocation TEST_MACHINE_NAME = prefix("machine/test");
    private static final ResourceLocation MACHINE_CORE_BLOCK = prefix("custom/machine_core_block");

    public static BakedModel testMachine;
    public static BakedModel machineCoreBlock;

    public static void render(BakedModel bakedModel, MultiBufferSource bufferSource, BlockEntity blockEntity,
                              PoseStack poseStack, int packedLight, int packedOverlay) {

        VertexConsumer buffer = bufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(blockEntity.getBlockState(), false));

        poseStack.pushPose();
        Minecraft.getInstance().getBlockRenderer().getModelRenderer()
                .renderModel(poseStack.last(), buffer, blockEntity.getBlockState(),
                        bakedModel, 1, 1, 1, packedLight, packedOverlay);
        poseStack.popPose();
    }

    @SubscribeEvent
    public static void registerModelUnBake(ModelRegistryEvent e) {
        ForgeModelBakery.addSpecialModel(TEST_MACHINE_NAME);
        ForgeModelBakery.addSpecialModel(MACHINE_CORE_BLOCK);
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent evt) {
        testMachine = evt.getModelRegistry().get(TEST_MACHINE_NAME);
        machineCoreBlock = evt.getModelRegistry().get(MACHINE_CORE_BLOCK);
    }

    private static ResourceLocation prefix(String path) {
        return new ResourceLocation(Avarus.MOD_ID, path);
    }
}
