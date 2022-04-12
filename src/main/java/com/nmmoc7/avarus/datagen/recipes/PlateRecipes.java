package com.nmmoc7.avarus.datagen.recipes;

import com.nmmoc7.avarus.item.AvarusItems;
import com.nmmoc7.avarus.recipes.PlateRecipe;
import com.nmmoc7.avarus.recipes.RecipeSerializers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author DustW
 **/
public class PlateRecipes extends AvarusRecipes {
    @Override
    protected void addRecipes() {
        addPlateRecipe(
                Ingredient.of(Items.IRON_INGOT),
                new ItemStack(AvarusItems.IRON_PLATE.get()),
                1,
                1
        );

        addPlateRecipe(
                Ingredient.of(Items.COPPER_INGOT),
                new ItemStack(AvarusItems.COPPER_PLATE.get()),
                1,
                1
        );

        addPlateRecipe(
                Ingredient.of(AvarusItems.COLORFUL_ALLOY.get()),
                new ItemStack(AvarusItems.COLORFUL_ALLOY_INGOT.get()),
                2,
                2
        );
    }

    protected void addPlateRecipe(Ingredient input, ItemStack output, int costPerHammer, int requiredLevel) {
        PlateRecipe result = new PlateRecipe();
        result.setID(defaultName(output.getItem()));
        result.type = RecipeSerializers.PLATE_RECIPE.get().getRegistryName().toString();

        result.output = output;
        result.input = input;
        result.costPerHammer = costPerHammer;
        result.requiredLevel = requiredLevel;

        addRecipe(defaultName(output.getItem()), baseRecipe(result), "plate/level" + requiredLevel);
    }
}
