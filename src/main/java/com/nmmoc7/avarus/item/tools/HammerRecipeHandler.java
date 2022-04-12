package com.nmmoc7.avarus.item.tools;

import com.nmmoc7.avarus.item.AvarusItemTags;
import com.nmmoc7.avarus.recipes.PlateRecipe;
import com.nmmoc7.avarus.recipes.ToolTypeRecipe;
import com.nmmoc7.avarus.utils.AvarusRecipeUtils;
import com.nmmoc7.avarus.utils.tool.ToolUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Collections;

/**
 * @author DustW
 **/
public class HammerRecipeHandler {
    public HammerRecipeHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Nonnull
    ItemStack result = ItemStack.EMPTY;
    boolean needRemove;

    @SubscribeEvent
    public void hammer(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        Player player = event.getPlayer();

        if (player.level.isClientSide) {
            return;
        }

        if (left.is(AvarusItemTags.HAMMER_ITEMS) && left.isDamageableItem()) {
            ItemStack right = event.getRight();

            int damage = left.getDamageValue();
            int maxDamage = left.getMaxDamage();

            ToolTypeRecipe toolInfo = ToolUtils.INSTANCE.getToolInfo(player.level, left.getItem());

            if (!toolInfo.toolType.equals(ToolUtils.HAMMER_TOOL_TYPE.toString())) {
                return;
            }

            PlateRecipe recipe = AvarusRecipeUtils.getPlateRecipe(player.level, toolInfo.level, Collections.singletonList(right));

            if (recipe == null) {
                return;
            }

            int cost = right.getCount() * recipe.costPerHammer;

            if (maxDamage - damage < cost) {
                return;
            }

            result = new ItemStack(recipe.output.getItem(), right.getCount() * recipe.output.getCount());

            ItemStack result = left.copy();
            result.setDamageValue(damage + cost);

            if (maxDamage - result.getDamageValue() <= 1) {
                needRemove = true;
            }

            event.setOutput(result);
            event.setCost(1);
        }
    }

    @SubscribeEvent
    public void result(AnvilRepairEvent event) {
        Player player = event.getPlayer();

        if (player.level.isClientSide) {
            return;
        }

        if (!result.isEmpty()) {
            event.setBreakChance(0);

            if (!player.addItem(result)) {
                ItemEntity entity = player.drop(result, true);

                if (entity != null) {
                    player.level.addFreshEntity(entity);
                }
            }

            player.giveExperienceLevels(1);

            if (needRemove) {
                player.level.playSound(player, player, SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
                event.getItemResult().shrink(1);
                needRemove = false;
            }

            result = ItemStack.EMPTY;
        }
    }
}
