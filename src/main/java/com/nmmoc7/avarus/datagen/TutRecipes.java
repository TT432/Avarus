package com.nmmoc7.avarus.datagen;

import com.google.gson.JsonObject;
import com.nmmoc7.avarus.item.AvarusItems;
import com.nmmoc7.avarus.recipes.BaseRecipe;
import com.nmmoc7.avarus.recipes.PlateRecipe;
import com.nmmoc7.avarus.recipes.RecipeSerializers;
import com.nmmoc7.avarus.recipes.ToolTypeRecipe;
import com.nmmoc7.avarus.utils.json.JsonUtils;
import com.nmmoc7.avarus.utils.tool.ToolUtils;
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
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.block.Blocks;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashMap;
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

    Map<ResourceLocation, Map.Entry<String, String>> toolTypes = new HashMap<>();
    Map<ResourceLocation, Map.Entry<String, String>> smithings = new HashMap<>();
    Map<ResourceLocation, Map.Entry<String, String>> plates = new HashMap<>();

    @Override
    public void run(HashCache pCache) {
        super.run(pCache);

        toolTypes.forEach((name, entry) -> save(pCache, name, entry));
        smithings.forEach((name, entry) -> save(pCache, name, entry));
        plates.forEach((name, entry) -> save(pCache, name, entry));
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

        addPlateRecipe(
                Ingredient.of(Items.IRON_INGOT),
                new ItemStack(AvarusItems.IRON_PLATE.get()),
                1,
                1
        );

        addPlateRecipe(
                Ingredient.of(Items.COPPER_INGOT),
                new ItemStack(AvarusItems.COPPER_PLATE.get()),
                1,
                1
        );

        addPlateRecipe(
                Ingredient.of(AvarusItems.COLORFUL_ALLOY.get()),
                new ItemStack(AvarusItems.COLORFUL_ALLOY_INGOT.get()),
                2,
                2
        );

        addToolType(AvarusItems.IRON_HAMMER.get(), ToolUtils.HAMMER_TOOL_TYPE, 1);
        addToolType(AvarusItems.COLORFUL_ALLOY_HAMMER.get(), ToolUtils.HAMMER_TOOL_TYPE, 2);
        addToolType(AvarusItems.NETHERITE_HAMMER.get(), ToolUtils.HAMMER_TOOL_TYPE, 3);

        addUpgradeRecipe(new UpgradeRecipe(
                new ResourceLocation(""),
                Ingredient.of(AvarusItems.COLORFUL_ALLOY_HAMMER.get()),
                Ingredient.of(Items.NETHERITE_INGOT),
                new ItemStack(AvarusItems.NETHERITE_HAMMER.get())
        ));
    }

    protected void addUpgradeRecipe(UpgradeRecipe recipe) {
        JsonObject json = JsonUtils.INSTANCE.noExpose.toJsonTree(recipe).getAsJsonObject();
        json.remove("id");
        json.addProperty("type", "minecraft:smithing");

        smithings.put(defaultName(recipe.getResultItem().getItem()), new AbstractMap.SimpleEntry<>(JsonUtils.INSTANCE.pretty.toJson(json), "smithing"));
    }

    protected void addPlateRecipe(Ingredient input, ItemStack output, int costPerHammer, int requiredLevel) {
        PlateRecipe result = new PlateRecipe();
        result.setID(defaultName(output.getItem()));
        result.type = RecipeSerializers.PLATE_RECIPE.get().getRegistryName().toString();

        result.output = output;
        result.input = input;
        result.costPerHammer = costPerHammer;
        result.requiredLevel = requiredLevel;

        plates.put(defaultName(output.getItem()), new AbstractMap.SimpleEntry<>(baseRecipe(result), "plate/level" + requiredLevel));
    }

    protected void addToolType(Item tool, ResourceLocation type, int level) {
        ToolTypeRecipe recipe = new ToolTypeRecipe();
        recipe.setID(defaultName(tool));
        recipe.type = RecipeSerializers.TOOL_TYPE_RECIPE.get().getRegistryName().toString();

        recipe.toolType = type.toString();
        recipe.tool = tool.getRegistryName().toString();
        recipe.level = level;

        toolTypes.put(defaultName(tool), new AbstractMap.SimpleEntry<>(baseRecipe(recipe), "tool_type/" + type.getPath()));
    }

    protected ResourceLocation defaultName(Item item) {
        return item.getRegistryName();
    }

    protected <TYPE extends BaseRecipe<TYPE>> String baseRecipe(BaseRecipe<TYPE> recipe) {
        return JsonUtils.INSTANCE.pretty.toJson(recipe);
    }
}
