package com.nmmoc7.avarus.item.tools;

import com.nmmoc7.avarus.recipes.ToolTypeRecipe;
import com.nmmoc7.avarus.utils.tool.ToolUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(Dist.CLIENT)
public class TooltipsHandler {
    public TooltipsHandler() {

    }

    @SubscribeEvent
    public static void tooltips(ItemTooltipEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ItemStack stack = event.getItemStack();
        ToolTypeRecipe recipe = ToolUtils.INSTANCE.getToolInfo(mc.level, stack.getItem());

        if (recipe != null) {
            List<Component> tooltip = event.getToolTip();
            tooltip.add(new TranslatableComponent("tool." + recipe.toolType.replace(":", "."), recipe.level));
        }
    }
}
