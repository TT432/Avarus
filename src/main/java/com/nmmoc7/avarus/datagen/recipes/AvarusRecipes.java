package com.nmmoc7.avarus.datagen.recipes;

import com.nmmoc7.avarus.recipes.base.BaseRecipe;
import com.nmmoc7.avarus.utils.json.JsonUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public abstract class AvarusRecipes {
    private final Map<ResourceLocation, Map.Entry<String, String>> recipes = new HashMap<>();

    protected abstract void addRecipes();

    protected final void addRecipe(ResourceLocation name, String recipe, String subPath) {
        recipes.put(name, new HashMap.SimpleEntry<>(recipe, subPath));
    }

    public Map<ResourceLocation, Map.Entry<String, String>> getRecipes() {
        addRecipes();
        return recipes;
    }

    protected ResourceLocation defaultName(Item item) {
        return item.getRegistryName();
    }

    protected <TYPE extends BaseRecipe<TYPE>> String baseRecipe(BaseRecipe<TYPE> recipe) {
        return JsonUtils.INSTANCE.pretty.toJson(recipe);
    }
}
