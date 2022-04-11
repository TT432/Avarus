package com.nmmoc7.avarus.datagen;

import com.nmmoc7.avarus.item.AvarusItemTags;
import com.nmmoc7.avarus.item.AvarusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author DustW
 **/
public class TutItemTags extends ItemTagsProvider {

    public TutItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, DataGenerators.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(AvarusItemTags.COLORFUL_DIRT)
                .add(AvarusItems.RED_DIRT.get())
                .add(AvarusItems.GREEN_DIRT.get())
                .add(AvarusItems.BLUE_DIRT.get())
                .add(AvarusItems.YELLOW_DIRT.get());

        tag(AvarusItemTags.HAMMER_ITEMS)
                .add(AvarusItems.IRON_HAMMER.get())
                .add(AvarusItems.COLORFUL_ALLOY_HAMMER.get())
                .add(Items.IRON_SWORD);
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}
