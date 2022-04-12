package com.nmmoc7.avarus;

import com.nmmoc7.avarus.block.AvarusBlocks;
import com.nmmoc7.avarus.item.AvarusItems;
import com.nmmoc7.avarus.item.tools.HammerRecipeHandler;
import com.nmmoc7.avarus.item.tools.TooltipsHandler;
import com.nmmoc7.avarus.recipes.RecipeSerializers;
import com.nmmoc7.avarus.recipes.RecipeTypes;
import com.nmmoc7.avarus.worldgen.AvarusRegion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;

@Mod(Avarus.MOD_ID)
public class Avarus {
    public static final String MOD_ID = "avarus";

    public static final CreativeModeTab MOD_TAB = new CreativeModeTab(Avarus.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AvarusItems.RED_DIRT.get());
        }
    };

    public Avarus() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);

        AvarusBlocks.register(bus);
        AvarusItems.register(bus);

        RecipeSerializers.register(bus);
        RecipeTypes.register(bus);

        new HammerRecipeHandler();
        new TooltipsHandler();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->
        {
            // Given we only add two biomes, we should keep our weight relatively low.
            Regions.register(new AvarusRegion(new ResourceLocation(MOD_ID, "overworld"), 2));

            // Register our surface rules
            //SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, TestSurfaceRuleData.makeRules());
        });
    }
}
