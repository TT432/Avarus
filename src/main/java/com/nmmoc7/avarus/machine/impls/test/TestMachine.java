package com.nmmoc7.avarus.machine.impls.test;

import com.nmmoc7.avarus.machine.AvarusMachineIoTypes;
import com.nmmoc7.avarus.machine.impls.BaseMachine;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        }, Stream.of(
                new AbstractMap.SimpleEntry<>('I', AvarusMachineIoTypes.ITEM_STACK_IO.get())
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, HashMap::new)));
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
