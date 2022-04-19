package com.nmmoc7.avarus.machine.setup;

import com.nmmoc7.avarus.blockentities.AvarusBlockEntityTypes;
import com.nmmoc7.avarus.client.renderers.machine.MultiBlockMachineBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

/**
 * @author DustW
 **/
public class MachineClientSetup {
    public static void onClientEvent() {
        BlockEntityRenderers.register(AvarusBlockEntityTypes.MULTI_BLOCK_MACHINE.get(), MultiBlockMachineBlockEntityRenderer::new);
    }
}
