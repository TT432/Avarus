package com.nmmoc7.avarus.datagen;

import com.nmmoc7.avarus.datagen.recipes.*;
import com.nmmoc7.avarus.item.AvarusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author DustW
 **/
public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected List<AvarusRecipes> recipes = new ArrayList<>();

    @Override
    public void run(HashCache pCache) {
        super.run(pCache);

        recipes.forEach(avarusRecipes -> {
            avarusRecipes.getRecipes().forEach((name, entry) -> save(pCache, name, entry));
        });
    }

    protected void save(HashCache pCache, ResourceLocation name, Map.Entry<String, String> entry) {
        String json = entry.getKey();
        String subPath = entry.getValue();

        Path path = this.generator.getOutputFolder();

        saveRecipe(pCache, json, path.resolve("data/" + name.getNamespace() + "/recipes/" + subPath + "/" + name.getPath() + ".json"));
    }

    private static void saveRecipe(HashCache pCache, String recipe, Path pPath) {
        try {
            String s1 = SHA1.hashUnencodedChars(recipe).toString();
            if (!Objects.equals(pCache.getHash(pPath), s1) || !Files.exists(pPath)) {
                Files.createDirectories(pPath.getParent());
                BufferedWriter bufferedwriter = Files.newBufferedWriter(pPath);

                try {
                    bufferedwriter.write(recipe);
                } catch (Throwable throwable1) {
                    try {
                        bufferedwriter.close();
                    } catch (Throwable throwable) {
                        throwable1.addSuppressed(throwable);
                    }

                    throw throwable1;
                }

                bufferedwriter.close();
            }

            pCache.putNew(pPath, s1);
        } catch (IOException ignored) {
        }
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

        ShapedRecipeBuilder
                .shaped(AvarusItems.IRON_HAMMER.get())
                .define('M', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .pattern("MMM")
                .pattern("MSM")
                .pattern(" S ")
                .unlockedBy("has_stone", has(Blocks.DIORITE)).save(consumer);

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(AvarusItems.COLORFUL_POWDER.get()),
                AvarusItems.COLORFUL_ALLOY.get(),
                .2F,
                100
        ).unlockedBy("has_stone", has(Blocks.DIORITE)).save(consumer);

        addAvarusRecipes();
    }

    protected void addAvarusRecipes() {
        recipes.add(new PlateRecipes());
        recipes.add(new ToolTypeRecipes());
        recipes.add(new UpgradeRecipes());
        recipes.add(new StoneCuttingRecipes());
    }
}
