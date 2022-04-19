package com.nmmoc7.avarus.client.models;

import com.nmmoc7.avarus.Avarus;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
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
    public static BakedModel testMachine;

    @SubscribeEvent
    public static void registerModelUnBake(ModelRegistryEvent e) {
        ForgeModelBakery.addSpecialModel(TEST_MACHINE_NAME);
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent evt) {
        testMachine = evt.getModelRegistry().get(TEST_MACHINE_NAME);
    }

    private static ResourceLocation prefix(String path) {
        return new ResourceLocation(Avarus.MOD_ID, path);
    }
}
