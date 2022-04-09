package com.nmmoc7.avarus.datagen;

import com.nmmoc7.avarus.block.AvarusBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author DustW
 **/
public class TutBlockTags extends BlockTagsProvider {

    public TutBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, DataGenerators.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(AvarusBlocks.RED_DIRT.get())
                .add(AvarusBlocks.GREEN_DIRT.get())
                .add(AvarusBlocks.YELLOW_DIRT.get())
                .add(AvarusBlocks.BLUE_DIRT.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(AvarusBlocks.RED_DIRT.get())
                .add(AvarusBlocks.GREEN_DIRT.get())
                .add(AvarusBlocks.YELLOW_DIRT.get())
                .add(AvarusBlocks.BLUE_DIRT.get());
    }

    @Override
    public String getName() {
        return DataGenerators.MOD_ID + " Tags";
    }
}