package com.nmmoc7.avarus.utils;

import com.nmmoc7.avarus.recipes.BaseRecipe;
import com.nmmoc7.avarus.recipes.PlateRecipe;
import com.nmmoc7.avarus.recipes.RecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DustW
 **/
public class AvarusRecipeUtils {
    public static <T extends BaseRecipe<T>> List<T> getRecipe(Level level, RecipeType<T> type, List<ItemStack> itemStacks) {
        return level.getRecipeManager().getAllRecipesFor(type).stream().filter(s -> s.matches(itemStacks)).collect(Collectors.toList());
    }

    @Nullable
    public static PlateRecipe getPlateRecipe(Level level, int hammerLevel, List<ItemStack> itemStacks) {
        List<PlateRecipe> list = getRecipe(level, RecipeTypes.PLATE.get(), itemStacks);

        int maxLevel = 0;
        PlateRecipe recipe = null;
        for (PlateRecipe plateRecipe : list) {
            if (plateRecipe.requiredLevel > maxLevel && plateRecipe.requiredLevel <= hammerLevel) {
                recipe = plateRecipe;
            }
        }

        return recipe;
    }
}
