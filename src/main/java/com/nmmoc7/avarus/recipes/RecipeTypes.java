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

    public static RegistryObject<RecipeType<PlateRecipe>> plate = register("plate_recipe");

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
