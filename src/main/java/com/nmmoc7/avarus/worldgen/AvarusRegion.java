package com.nmmoc7.avarus.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

/**
 * @author DustW
 **/
public class AvarusRegion extends Region {
    public AvarusRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addBiome(
                mapper,
                new Climate.ParameterPoint(
                        FULL_RANGE,
                        FULL_RANGE,
                        FULL_RANGE,
                        FULL_RANGE,
                        // depth
                        Climate.Parameter.span(-1, 0),
                        FULL_RANGE,
                        0
                ),
                BiomesRegister.TEST
        );
    }
}
