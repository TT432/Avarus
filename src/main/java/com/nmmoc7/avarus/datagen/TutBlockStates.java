package com.nmmoc7.avarus.datagen;

import com.nmmoc7.avarus.block.AvarusBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author DustW
 **/
public class TutBlockStates extends BlockStateProvider {

    public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, DataGenerators.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(AvarusBlocks.RED_DIRT.get());
        simpleBlock(AvarusBlocks.GREEN_DIRT.get());
        simpleBlock(AvarusBlocks.BLUE_DIRT.get());
        simpleBlock(AvarusBlocks.YELLOW_DIRT.get());
    }
}