package com.nmmoc7.avarus.recipes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

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
        return RecipeTypes.plate.get();
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return input.test(inputs.get(0));
    }

    public static class Builder implements RecipeBuilder {
        @Override
        public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
            return this;
        }

        @Override
        public RecipeBuilder group(@Nullable String pGroupName) {
            return this;
        }

        @Override
        public Item getResult() {
            return null;
        }

        @Override
        public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {

        }
    }
}
