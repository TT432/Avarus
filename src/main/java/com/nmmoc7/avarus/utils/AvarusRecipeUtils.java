package com.nmmoc7.avarus.utils;

import com.nmmoc7.avarus.recipes.BaseRecipe;
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
    @Nullable
    public static <T extends BaseRecipe<T>> T getRecipe(Level level, RecipeType<T> type, List<ItemStack> itemStacks) {
        List<T> maybeResult = level.getRecipeManager().getAllRecipesFor(type).stream().filter(s -> s.matches(itemStacks)).collect(Collectors.toList());
        return maybeResult.isEmpty() ? null : maybeResult.get(0);
    }
}
