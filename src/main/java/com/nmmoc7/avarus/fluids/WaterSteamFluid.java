package com.nmmoc7.avarus.fluids;

import com.nmmoc7.avarus.block.AvarusBlocks;
import com.nmmoc7.avarus.item.AvarusItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

/**
 * @author DustW
 **/
public abstract class WaterSteamFluid extends ForgeFlowingFluid {
    protected static Properties properties() {
        return new Properties(
                AvarusFluids.WATER_STEAM,
                AvarusFluids.WATER_STEAM_FLOWING,
                FluidAttributes
                        .builder(
                                new ResourceLocation(""),
                                new ResourceLocation("")
                        )
                        .sound(SoundEvents.BUCKET_FILL)
        )
                .bucket(AvarusItems.WATER_STEAM_FLUID_UNIT)
                .block(AvarusBlocks.WATER_STEAM);
    }

    protected WaterSteamFluid() {
        super(properties());
    }

    public static Source source() {
        return new Source(properties());
    }

    public static Flowing flowing() {
        return new Flowing(properties());
    }
}
