package com.nmmoc7.avarus.client.rendertype;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;

import java.util.OptionalDouble;

/**
 * https://github.com/McJty/YouTubeModding14/blob/1.18/src/main/java/com/mcjty/mytutorial/client/MyRenderType.java
 * @author mcjty
 **/
public class AvarusRenderType extends RenderType {
    public AvarusRenderType(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }

    private static final LineStateShard THICK_LINES = new LineStateShard(OptionalDouble.of(6.0D));

    public static final RenderType OVERLAY_LINES = RenderType.create("overlay_lines",
            DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.LINES, 256,
            false, false,
            RenderType.CompositeState.builder().setLineState(THICK_LINES)
                    .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                    .setShaderState(RENDERTYPE_LINES_SHADER)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setTextureState(NO_TEXTURE)
                    .setDepthTestState(NO_DEPTH_TEST)
                    .setCullState(NO_CULL)
                    .setLightmapState(NO_LIGHTMAP)
                    .setWriteMaskState(COLOR_WRITE)
                    .createCompositeState(false));
}
