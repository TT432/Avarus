package com.nmmoc7.avarus.recipes;

import com.nmmoc7.avarus.Avarus;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class RecipeTypes {
    private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registry.RECIPE_TYPE.key(), Avarus.MOD_ID);

    public static final RegistryObject<RecipeType<PlateRecipe>> PLATE = register("plate_recipe");
    public static final RegistryObject<RecipeType<ToolTypeRecipe>> TOOL_TYPE = register("tool_type");

    private static <TYPE extends BaseRecipe<TYPE>> RegistryObject<RecipeType<TYPE>> register(String name) {
        return TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return new ResourceLocation(Avarus.MOD_ID, name).toString();
            }
        });
    }

    public static void register(IEventBus bus) {
        TYPES.register(bus);
    }
}
