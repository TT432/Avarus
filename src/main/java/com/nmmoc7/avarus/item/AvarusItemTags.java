package com.nmmoc7.avarus.item;

import com.nmmoc7.avarus.Avarus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author DustW
 **/
public class AvarusItemTags {
    public static final TagKey<Item> COLORFUL_DIRT = ItemTags.create(new ResourceLocation(Avarus.MOD_ID, "colorful_dirt"));
}
