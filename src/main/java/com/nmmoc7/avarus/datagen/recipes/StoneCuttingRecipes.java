package com.nmmoc7.avarus.datagen.recipes;

import com.google.gson.JsonObject;
import com.nmmoc7.avarus.item.AvarusItems;
import com.nmmoc7.avarus.utils.json.JsonUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author DustW
 **/
public class StoneCuttingRecipes extends AvarusRecipes {
    @Override
    protected void addRecipes() {
        addRecipe(1, Ingredient.of(Items.COPPER_INGOT), AvarusItems.COPPER_WIRE.get(), 7);
        addRecipe(2, Ingredient.of(AvarusItems.COPPER_PLATE.get()), AvarusItems.COPPER_WIRE.get(), 3);

        addRecipe(1, Ingredient.of(Items.COPPER_INGOT), AvarusItems.COPPER_PLATE.get(), 2);
    }

    /**
     * {
     *   "type": "minecraft:stonecutting",
     *   "ingredient": {
     *     "item": "minecraft:bricks"
     *   },
     *   "result": "minecraft:brick_slab",
     *   "count": 2
     * }
     */
    protected void addRecipe(int resultId, Ingredient ingredient, Item result, int count) {
        JsonObject json = new JsonObject();
        json.add("ingredient", JsonUtils.INSTANCE.normal.toJsonTree(ingredient));
        json.addProperty("result", result.getRegistryName().toString());
        json.addProperty("count", count);
        json.addProperty("type", "minecraft:stonecutting");

        ResourceLocation name = result.getRegistryName();

        addRecipe(new ResourceLocation(name.getNamespace(), name.getPath() + "_stonecutting" + resultId),
                JsonUtils.INSTANCE.pretty.toJson(json), "stonecutting");
    }
}
