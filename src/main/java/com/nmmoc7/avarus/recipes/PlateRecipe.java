package com.nmmoc7.avarus.recipes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nmmoc7.avarus.recipes.base.BaseRecipe;
import com.nmmoc7.avarus.recipes.register.RecipeSerializers;
import com.nmmoc7.avarus.recipes.register.RecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

/**
 * @author DustW
 **/
public class PlateRecipe extends BaseRecipe<PlateRecipe> {
    @Expose
    public Ingredient input;
    @Expose
    public ItemStack output;
    @Expose
    @SerializedName("cost_per_hammer")
    public int costPerHammer;
    @Expose
    @SerializedName("required_level")
    public int requiredLevel;

    @Override
    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.PLATE_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.PLATE.get();
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return input.test(inputs.get(0));
    }
}
