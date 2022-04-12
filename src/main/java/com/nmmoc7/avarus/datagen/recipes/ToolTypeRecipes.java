package com.nmmoc7.avarus.datagen.recipes;

import com.nmmoc7.avarus.item.AvarusItems;
import com.nmmoc7.avarus.recipes.RecipeSerializers;
import com.nmmoc7.avarus.recipes.ToolTypeRecipe;
import com.nmmoc7.avarus.utils.tool.ToolUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * @author DustW
 **/
public class ToolTypeRecipes extends AvarusRecipes {
    @Override
    protected void addRecipes() {
        addToolType(AvarusItems.IRON_HAMMER.get(), ToolUtils.HAMMER_TOOL_TYPE, 1);
        addToolType(AvarusItems.COLORFUL_ALLOY_HAMMER.get(), ToolUtils.HAMMER_TOOL_TYPE, 2);
        addToolType(AvarusItems.NETHERITE_HAMMER.get(), ToolUtils.HAMMER_TOOL_TYPE, 3);
    }

    protected void addToolType(Item tool, ResourceLocation type, int level) {
        ToolTypeRecipe recipe = new ToolTypeRecipe();
        recipe.setID(defaultName(tool));
        recipe.type = RecipeSerializers.TOOL_TYPE_RECIPE.get().getRegistryName().toString();

        recipe.toolType = type.toString();
        recipe.tool = tool.getRegistryName().toString();
        recipe.level = level;

        addRecipe(defaultName(tool), baseRecipe(recipe), "tool_type/" + type.getPath());
    }
}
