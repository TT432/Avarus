package com.nmmoc7.avarus.machine.impls;

import com.nmmoc7.avarus.machine.AvarusMachineIoTypes;
import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.machine.api.MachineIo;
import com.nmmoc7.avarus.machine.api.MachineIoType;
import com.nmmoc7.avarus.machine.multiblock.blockentities.MultiBlockMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author DustW
 **/
public class BaseMachine<SELF extends CapabilityProvider<SELF> & Machine<SELF> & INBTSerializable<CompoundTag>> extends CapabilityProvider<SELF> implements Machine<SELF>, INBTSerializable<CompoundTag>{
    private Map<BlockPos, MachineIoType<?, ?, ?>> blocks = new HashMap<>();
    private BlockPos core;
    private MultiBlockMachineBlockEntity blockEntity;
    private boolean needSync;

    protected BaseMachine(MultiBlockMachineBlockEntity blockEntity, Class<SELF> baseClass,
                          String[][] structure, Map<Character, MachineIoType<?, ?, ?>> mapping) {
        super(baseClass);

        for (int y = 0; y < structure.length; y++) {
            int currentY = structure.length - y - 1;

            for (int x = 0; x < structure[currentY].length; x++) {
                String zValue = structure[currentY][x];

                for (int z = 0; z < zValue.length(); z++) {

                    if (zValue.charAt(z) == 'C') {
                        core = new BlockPos(x, currentY, z);
                    }
                    else if (zValue.charAt(z) != ' ') {
                        blocks.put(new BlockPos(x, currentY, z), mapping.getOrDefault(zValue.charAt(z), AvarusMachineIoTypes.EMPTY.get()));
                    }
                }
            }
        }

        if (core == null) {
            throw new IllegalArgumentException("Structure must have a core block!");
        }

        blocks = blocks.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().offset(core.multiply(-1)), entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        core = core.offset(core.multiply(-1));

        this.blockEntity = blockEntity;
    }

    @Override
    public Map<BlockPos, MachineIoType<?, ?, ?>> bodyPositions() {
        return blocks;
    }

    @Override
    public MultiBlockMachineBlockEntity getBlockEntity() {
        return blockEntity;
    }

    Map<MachineIoType<?, ?, ?>, LazyOptional<INBTSerializable<CompoundTag>>> capCache = new HashMap<>();

    protected LazyOptional<INBTSerializable<CompoundTag>> getOrPut(MachineIoType<?, ?, ?> type) {
        if (capCache.get(type) == null) {
            capCache.put(type, type.create().cast());
        }

        return capCache.get(type);
    }

    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @NotNull BlockPos pos) {
        BlockPos corePos = getBlockEntity().getBlockPos();
        MachineIoType<?, ?, ?> type = blocks.get(pos.offset(corePos.multiply(-1)));
        return type == null ? LazyOptional.empty() : cap == type.getCapability() ? getOrPut(type).cast() : LazyOptional.empty();
    }

    @Override
    public BlockPos getCorePosition() {
        return core;
    }

    @Override
    public void onCreate(Player player) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void saveUpdateTag(CompoundTag tag) {

    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {

    }

    @Override
    public boolean isNeedSync() {
        return needSync;
    }

    @Override
    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean onUse(Player pPlayer, InteractionHand pHand, BlockPos pos) {
        return false;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag result = new CompoundTag();
        ListTag capCache = new ListTag();

        this.capCache.forEach((key, value) -> {
            CompoundTag tag = new CompoundTag();
            tag.putString("key", key.getRegistryName().toString());
            tag.put("value", value.resolve().get().serializeNBT());
            capCache.add(tag);
        });

        result.put("capCache", capCache);
        return result;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ListTag capCache = nbt.getList("capCache", Tag.TAG_COMPOUND);
        capCache.forEach(a -> {
            if (a instanceof CompoundTag tag) {
                MachineIoType<?, ?, ?> key = AvarusMachineIoTypes.REGISTRY.get()
                        .getValue(new ResourceLocation(tag.getString("key")));

                if (key != null) {
                    CompoundTag valueTag = tag.getCompound("value");
                    LazyOptional<MachineIo<?, ?>> value = key.create();

                    if (value.resolve().isPresent()) {
                        value.resolve().get().deserializeNBT(valueTag);
                    }

                    BaseMachine.this.capCache.put(key, value.cast());
                }
            }
        });
    }
}
