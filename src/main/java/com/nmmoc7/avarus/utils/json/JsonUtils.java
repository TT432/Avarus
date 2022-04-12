package com.nmmoc7.avarus.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nmmoc7.avarus.utils.json.serializer.IngredientSerializer;
import com.nmmoc7.avarus.utils.json.serializer.ItemStackSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author DustW
 **/
public enum JsonUtils {
    /**  */
    INSTANCE;
    public final Gson normal;
    public final Gson pretty;
    public final Gson noExpose;

    JsonUtils() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Ingredient.class, new IngredientSerializer());
        builder.registerTypeAdapter(ItemStack.class, new ItemStackSerializer());
        noExpose = builder.create();

        builder.excludeFieldsWithoutExposeAnnotation();
        normal = builder.create();

        builder.setPrettyPrinting();
        pretty = builder.create();
    }
}
