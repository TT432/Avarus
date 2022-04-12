package com.nmmoc7.avarus.datagen.recipes;

import com.google.gson.JsonObject;
import com.nmmoc7.avarus.item.AvarusItems;
import com.nmmoc7.avarus.utils.json.JsonUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.UpgradeRecipe;

/**
 * @author DustW
 **/
public class UpgradeRecipes extends AvarusRecipes {
    @Override
    protected void addRecipes() {
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

        addRecipe(defaultName(recipe.getResultItem().getItem()),JsonUtils.INSTANCE.pretty.toJson(json), "smithing");
    }
}
