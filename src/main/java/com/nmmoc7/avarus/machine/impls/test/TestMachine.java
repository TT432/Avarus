package com.nmmoc7.avarus.machine.impls.test;

import com.nmmoc7.avarus.machine.AvarusMachineIoTypes;
import com.nmmoc7.avarus.machine.impls.BaseMachine;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DustW
 **/
public class TestMachine extends BaseMachine<TestMachine> {
    public TestMachine(MultiBlockMachineBlockEntity blockEntity) {
        super(blockEntity, TestMachine.class, new String[][] {
                """
                B
                """.split("\n"),
                """
                CI
                """.split("\n")
        }, Map.ofEntries(
                new AbstractMap.SimpleEntry<>('I', AvarusMachineIoTypes.ITEM_STACK_IO.get())
        ));
    }

    @Override
    public List<ItemStack> getDropItems() {
        List<ItemStack> list = new ArrayList<>();

        getCache(AvarusMachineIoTypes.ITEM_STACK_IO.get()).ifPresent(handler -> {
            for (int i = 0; i < handler.getSize(); i++) {
                list.add(handler.getSlot(i));
            }
        });

        return list;
    }

    @Override
    public void onCreate(Player player) {
        player.displayClientMessage(new TextComponent("Machine created!"), false);
    }

    @Override
    public boolean onUse(Player pPlayer, InteractionHand pHand, BlockPos pos) {
        pPlayer.displayClientMessage(new TextComponent("使用了机器"), false);
        LazyOptional<IItemHandler> cap = getCapability(AvarusMachineIoTypes.ITEM_STACK_IO.get().getCapability(), pos);

        if (cap.resolve().isPresent()) {
            pPlayer.sendMessage(cap.resolve().get().getStackInSlot(0).getDisplayName(), pPlayer.getUUID());
        }

        return true;
    }
}
