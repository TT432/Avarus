package com.nmmoc7.avarus.recipes;

import com.nmmoc7.avarus.Avarus;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class RecipeSerializers {
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Avarus.MOD_ID);

    public static final RegistryObject<AvarusBaseSerializer<?>> PLATE_RECIPE =
            SERIALIZER.register(name("plate"), () -> new AvarusBaseSerializer<>(PlateRecipe.class));

    private static String name(String name) {
        return name + "_recipe_serializer";
    }

    public static void register(IEventBus bus) {
        SERIALIZER.register(bus);
    }
}
