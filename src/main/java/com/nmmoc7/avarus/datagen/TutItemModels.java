package com.nmmoc7.avarus.datagen;

import com.nmmoc7.avarus.Avarus;
import com.nmmoc7.avarus.item.AvarusItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class TutItemModels extends ItemModelProvider {

    public TutItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Avarus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ForgeRegistries.ITEMS.forEach(item -> {
            if (item.getRegistryName().getNamespace().equals(Avarus.MOD_ID) && item instanceof BlockItem) {
                withExistingParent(item.getRegistryName().getPath(), modLoc("block/" + item.getRegistryName().getPath()));
            }
        });

        simpleTexture(AvarusItems.COLORFUL_ALLOY);
        simpleTexture(AvarusItems.COLORFUL_ALLOY_INGOT);
        simpleTexture(AvarusItems.COLORFUL_POWDER);
        simpleTexture(AvarusItems.IRON_PLATE);
    }

    void simpleTexture(Supplier<Item> itemSupplier) {
        String name = itemSupplier.get().getRegistryName().getPath();

        singleTexture(name,
                mcLoc("item/generated"),
                "layer0", modLoc("item/" + name));
    }
}