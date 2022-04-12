package com.nmmoc7.avarus.utils.tool;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.recipes.RecipeTypes;
import com.nmmoc7.avarus.recipes.ToolTypeRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public enum ToolUtils {
    /**  */
    INSTANCE;

    public static final ResourceLocation HAMMER_TOOL_TYPE = new ResourceLocation(Avarus.MOD_ID, "hammer");

    private final Map<Item, ToolTypeRecipe> recipes = new HashMap<>();
    boolean init = false;

    public ToolTypeRecipe getToolInfo(Level level, Item item) {
        if (!init) {
            level.getRecipeManager().getAllRecipesFor(RecipeTypes.TOOL_TYPE.get()).forEach(recipe -> {
                recipes.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(recipe.tool)), recipe);
            });

            init = true;
        }

        return recipes.get(item);
    }
}
