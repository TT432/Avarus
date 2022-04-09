package com.nmmoc7.avarus.worldgen.feature.builders;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.LinkedList;
import java.util.List;

/**
 * @author DustW
 **/
public abstract class FeatureBuilder<C extends FeatureConfiguration, SELF extends FeatureBuilder<C, SELF>> {
    protected final List<PlacementModifier> modifiers = new LinkedList<>();
    protected final Feature<C> feature;

    String name;

    public FeatureBuilder(String namespace, String name, Feature<C> feature) {
        this.name = namespace + ":" + name;
        this.feature = feature;
    }

    public SELF addModifiers(PlacementModifier... modifiers) {
        return addModifiers(List.of(modifiers));
    }

    public SELF addModifiers(List<PlacementModifier> modifiers) {
        this.modifiers.addAll(modifiers);
        return self();
    }

    public SELF fromModifier(Holder<PlacedFeature> parent) {
        modifiers.addAll(parent.value().placement());
        return self();
    }

    public SELF replaceModifier(PlacementModifier modifier) {
        return replaceModifier(modifier.getClass(), modifier);
    }

    public SELF replaceModifier(Class<?> type, PlacementModifier modifier) {
        modifiers.removeIf(type::isInstance);
        modifiers.add(modifier);
        return self();
    }

    public abstract SELF fromConfiguration(C parent);
    protected abstract C buildConfiguration();

    public Holder<PlacedFeature> build() {
        return PlacementUtils.register(
                name,
                FeatureUtils.register(
                        name + "_config",
                        feature,
                        buildConfiguration()
                ),
                modifiers);
    }

    protected final SELF self() {
        return (SELF) this;
    }
}
