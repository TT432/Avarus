package com.nmmoc7.avarus.recipes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nmmoc7.avarus.recipes.base.BaseRecipe;
import com.nmmoc7.avarus.recipes.register.RecipeSerializers;
import com.nmmoc7.avarus.recipes.register.RecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

/**
 * @author DustW
 **/
public class ToolTypeRecipe extends BaseRecipe<ToolTypeRecipe> {
    @Expose
    public String tool;
    @Expose
    @SerializedName("tool_type")
    public String toolType;
    @Expose
    public int level;

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.TOOL_TYPE_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.TOOL_TYPE.get();
    }
}
