package com.nmmoc7.avarus.recipes.register;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.recipes.PlateRecipe;
import com.nmmoc7.avarus.recipes.ToolTypeRecipe;
import com.nmmoc7.avarus.recipes.base.AvarusBaseSerializer;
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
            SERIALIZER.register("plate", () -> new AvarusBaseSerializer<>(PlateRecipe.class));

    public static final RegistryObject<AvarusBaseSerializer<?>> TOOL_TYPE_RECIPE =
            SERIALIZER.register("tool_type", () -> new AvarusBaseSerializer<>(ToolTypeRecipe.class));

    public static void register(IEventBus bus) {
        SERIALIZER.register(bus);
    }
}
