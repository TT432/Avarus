package com.nmmoc7.avarus.machine.impls;

import com.nmmoc7.avarus.machine.api.IMachine;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DustW
 **/
public class BaseMachine<SELF extends CapabilityProvider<SELF> & IMachine<SELF> & INBTSerializable<CompoundTag>> extends CapabilityProvider<SELF> implements IMachine<SELF>, INBTSerializable<CompoundTag>{
    private List<BlockPos> blocks = new ArrayList<>();
    private BlockPos core;

    protected BaseMachine(Class<SELF> baseClass, String[][] structure) {
        super(baseClass);

        for (int y = 0; y < structure.length; y++) {
            int currentY = structure.length - y - 1;

            for (int x = 0; x < structure[currentY].length; x++) {
                String zValue = structure[currentY][x];

                for (int z = 0; z < zValue.length(); z++) {
                    if (zValue.charAt(z) == 'B') {
                        blocks.add(new BlockPos(x, currentY, z));
                    }
                    else if (zValue.charAt(z) == 'C') {
                        core = new BlockPos(x, currentY, z);
                    }
                }
            }
        }

        if (core == null) {
            throw new IllegalArgumentException("Structure must have a core block!");
        }

        blocks = blocks.stream().map(pos -> pos.offset(core.multiply(-1))).collect(Collectors.toList());
        core = core.offset(core.multiply(-1));
    }

    @Override
    public List<BlockPos> bodyPositions() {
        return blocks;
    }

    @Override
    public BlockPos getCorePosition() {
        return core;
    }

    @Override
    public void onCreate(MultiBlockMachineBlockEntity blockEntity, Player player) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void tick() {

    }

    @Override
    public boolean onUse(Player pPlayer, InteractionHand pHand) {
        return false;
    }

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}
