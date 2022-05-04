package com.nmmoc7.avarus.block.ore;

import com.nmmoc7.avarus.stage.StageManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.Collections;
import java.util.List;

/**
 * @author DustW
 **/
public class AquamarineOre extends Block {
    public AquamarineOre(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        ResourceLocation drop;

        if (StageManager.getManager().getStage().dirt()) {
            drop = Blocks.STONE.getLootTable();
        }
        else {
            drop = getLootTable();
        }

        if (drop == BuiltInLootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootcontext = pBuilder.withParameter(LootContextParams.BLOCK_STATE, pState).create(LootContextParamSets.BLOCK);
            ServerLevel serverlevel = lootcontext.getLevel();
            LootTable loottable = serverlevel.getServer().getLootTables().get(drop);
            return loottable.getRandomItems(lootcontext);
        }
    }
}
