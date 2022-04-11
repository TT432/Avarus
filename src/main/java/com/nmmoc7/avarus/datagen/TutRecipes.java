package com.nmmoc7.avarus.datagen;

import com.google.common.collect.Sets;
import com.nmmoc7.avarus.item.AvarusItems;
import com.nmmoc7.avarus.recipes.BaseRecipe;
import com.nmmoc7.avarus.recipes.PlateRecipe;
import com.nmmoc7.avarus.recipes.RecipeSerializers;
import com.nmmoc7.avarus.utils.json.JsonUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author DustW
 **/
public class TutRecipes extends RecipeProvider {

    public TutRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    List<BaseRecipe<?>> recipes = new ArrayList<>();

    @Override
    public void run(HashCache pCache) {
        super.run(pCache);
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        recipes.forEach(recipe -> {
            if (!set.add(recipe.getId())) {
                throw new IllegalStateException("Duplicate recipe " + recipe.getId());
            } else {
                saveRecipe(pCache, recipe, path.resolve("data/" + recipe.getId().getNamespace() + "/recipes/" + recipe.getId().getPath() + ".json"));
            }
        });
    }

    private static void saveRecipe(HashCache pCache, BaseRecipe<?> recipe, Path pPath) {
        try {
            String s = JsonUtils.INSTANCE.pretty.toJson(recipe);
            String s1 = SHA1.hashUnencodedChars(s).toString();
            if (!Objects.equals(pCache.getHash(pPath), s1) || !Files.exists(pPath)) {
                Files.createDirectories(pPath.getParent());
                BufferedWriter bufferedwriter = Files.newBufferedWriter(pPath);

                try {
                    bufferedwriter.write(s);
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

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(AvarusItems.COLORFUL_POWDER.get()),
                AvarusItems.COLORFUL_ALLOY.get(),
                .2F,
                100
        ).unlockedBy("has_stone", has(Blocks.DIORITE)).save(consumer);

        addPlateRecipe(
                Ingredient.of(Items.IRON_INGOT),
                new ItemStack(AvarusItems.IRON_PLATE.get()),
                1
        );
    }

    protected void addPlateRecipe(Ingredient input, ItemStack output, int costPerHammer) {
        PlateRecipe result = new PlateRecipe();
        result.output = output;
        result.input = input;
        result.costPerHammer = costPerHammer;
        result.setID(defaultName(output.getItem()));
        result.type = RecipeSerializers.PLATE_RECIPE.get().getRegistryName().toString();
        recipes.add(result);
    }

    protected ResourceLocation defaultName(Item item) {
        return item.getRegistryName();
    }
}
