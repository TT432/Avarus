package com.nmmoc7.avarus.worldgen.feature.ore;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.block.AvarusBlocks;
import com.nmmoc7.avarus.worldgen.feature.builders.OreBuilder;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;

/**
 * @author DustW
 **/
public class OreList {
    public static final Holder<PlacedFeature> RED_DIRT =
            new OreBuilder(Avarus.MOD_ID, "red_dirt")
                    .addModifiers(commonOrePlacement(6, PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT))
                    .fromConfiguration(OreFeatures.ORE_IRON.value().config())
                    .replace(new BlockMatchTest(Blocks.DIRT), AvarusBlocks.RED_DIRT.get())
                    .replace(OreFeatures.STONE_ORE_REPLACEABLES, AvarusBlocks.RED_DIRT.get())
                    .build();

    public static final Holder<PlacedFeature> GREEN_DIRT =
            new OreBuilder(Avarus.MOD_ID, "green_dirt")
                    .addModifiers(commonOrePlacement(6, PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT))
                    .fromConfiguration(OreFeatures.ORE_IRON.value().config())
                    .replace(new BlockMatchTest(Blocks.DIRT), AvarusBlocks.GREEN_DIRT.get())
                    .replace(OreFeatures.STONE_ORE_REPLACEABLES, AvarusBlocks.GREEN_DIRT.get())
                    .build();

    public static final Holder<PlacedFeature> BLUE_DIRT =
            new OreBuilder(Avarus.MOD_ID, "blue_dirt")
                    .addModifiers(commonOrePlacement(6, PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT))
                    .fromConfiguration(OreFeatures.ORE_IRON.value().config())
                    .replace(new BlockMatchTest(Blocks.DIRT), AvarusBlocks.BLUE_DIRT.get())
                    .replace(OreFeatures.STONE_ORE_REPLACEABLES, AvarusBlocks.BLUE_DIRT.get())
                    .build();

    public static final Holder<PlacedFeature> YELLOW_DIRT =
            new OreBuilder(Avarus.MOD_ID, "yellow_dirt")
                    .addModifiers(commonOrePlacement(6, PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT))
                    .fromConfiguration(OreFeatures.ORE_IRON.value().config())
                    .replace(new BlockMatchTest(Blocks.DIRT), AvarusBlocks.YELLOW_DIRT.get())
                    .replace(OreFeatures.STONE_ORE_REPLACEABLES, AvarusBlocks.YELLOW_DIRT.get())
                    .build();

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier height) {
        return orePlacement(CountPlacement.of(count), height);
    }

    private static List<PlacementModifier> rareOrePlacement(int average, PlacementModifier height) {
        return orePlacement(RarityFilter.onAverageOnceEvery(average), height);
    }
}
