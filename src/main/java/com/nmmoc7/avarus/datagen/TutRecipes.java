package com.nmmoc7.avarus.datagen;

import com.nmmoc7.avarus.item.AvarusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

/**
 * @author DustW
 **/
public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder
                .shaped(AvarusItems.COLORFUL_POWDER.get())
                .define('R', AvarusItems.RED_DIRT.get())
                .define('B', AvarusItems.BLUE_DIRT.get())
                .define('G', AvarusItems.GREEN_DIRT.get())
                .define('Y', AvarusItems.YELLOW_DIRT.get())
                .define('C', Items.IRON_INGOT)
                .pattern("RRG")
                .pattern("YCG")
                .pattern("YBB")
                .unlockedBy("has_stone", has(Blocks.DIORITE)).save(consumer);

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(AvarusItems.COLORFUL_POWDER.get()),
                AvarusItems.COLORFUL_ALLOY.get(),
                .2F,
                100
        ).unlockedBy("has_stone", has(Blocks.DIORITE)).save(consumer);
    }
}
