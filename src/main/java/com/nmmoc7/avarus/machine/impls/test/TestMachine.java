package com.nmmoc7.avarus.machine.impls.test;

import com.nmmoc7.avarus.machine.impls.BaseMachine;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

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
                C
                """.split("\n")
        });
    }

    @Override
    public void onCreate(Player player) {
        player.displayClientMessage(new TextComponent("Machine created!"), false);
    }

    @Override
    public boolean onUse(Player pPlayer, InteractionHand pHand) {
        pPlayer.displayClientMessage(new TextComponent("使用了机器"), false);
        return true;
    }
}
