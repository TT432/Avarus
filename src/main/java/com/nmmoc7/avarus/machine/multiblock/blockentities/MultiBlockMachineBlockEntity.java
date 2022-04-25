package com.nmmoc7.avarus.machine.multiblock.blockentities;

import com.nmmoc7.avarus.block.AvarusBlocks;
import com.nmmoc7.avarus.blockentities.AvarusBlockEntityTypes;
import com.nmmoc7.avarus.machine.AvarusMachineTypes;
import com.nmmoc7.avarus.machine.api.Machine;
import com.nmmoc7.avarus.machine.api.MachineType;
import com.nmmoc7.avarus.utils.TickAble;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 **/
public class MultiBlockMachineBlockEntity extends BlockEntity implements TickAble {
    public MultiBlockMachineBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AvarusBlockEntityTypes.MULTI_BLOCK_MACHINE.get(), pWorldPosition, pBlockState);
    }

    private Lazy<? extends Machine<?>> machine;
    private MachineType<?> machineType;
    private boolean created;

    private boolean needSync;

    private boolean isNeedSync() {
        return needSync || (getMachine() != null && getMachine().isNeedSync());
    }

    public Machine<?> getMachine() {
        if (machine != null) {
            return machine.get();
        }

        return null;
    }

    public void setMachine(MachineType<?> type) {
        if (!created) {
            machineType = type;
            this.machine = type.newMachine(this);
            needSync = true;
        }
    }

    public boolean isCreated() {
        return created;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getMachine() != null ? getMachine().getCapability(cap, getBlockPos()) : LazyOptional.empty();
    }

    protected boolean canCreate() {
        return !isCreated() && getMachine().canCreate();
    }

    public boolean create(Player player) {
        if (!canCreate()) {
            return false;
        }

        getMachine().bodyPositions().keySet().forEach(pos -> {
            pos = pos.offset(getBlockPos());
            level.setBlock(pos, AvarusBlocks.MULTI_BLOCK_MACHINE_BODY.get().defaultBlockState(), 2);
            ((MultiBlockMachineBodyBlockEntity) level.getBlockEntity(pos)).setCore(this);
        });

        getMachine().onCreate(player);
        created = true;

        needSync = true;

        return true;
    }

    public void onRemoved() {
        if (created) {
            getMachine().onDestroy();

            for (BlockPos bodyPosition : getMachine().bodyPositions().keySet()) {
                level.removeBlock(bodyPosition.offset(getBlockPos()), false);
            }

            level.removeBlock(getBlockPos(), false);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        writeMachineData(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        readMachineData(pTag);

        if (getMachine() != null) {
            getMachine().handleUpdateTag(pTag.getCompound("machineUpdate"));
        }
    }

    protected void writeMachineData(CompoundTag pTag) {
        pTag.putBoolean("created", created);

        if (machineType != null) {
            pTag.putString("type", machineType.getRegistryName().toString());
            CompoundTag machineData = getMachine() == null ? new CompoundTag() : getMachine().self().serializeNBT();
            pTag.put("machineData", machineData);
        }
    }

    protected void readMachineData(CompoundTag pTag) {
        created = pTag.getBoolean("created");

        machineType = AvarusMachineTypes.REGISTRY.get().getValue(new ResourceLocation(pTag.getString("type")));

        if (machineType != null) {
            this.machine = machineType.newMachine(this);
            needSync = true;
            getMachine().self().deserializeNBT(pTag.getCompound("machineData"));
        }
    }

    protected void sync() {
        if (!level.isClientSide) {
            ClientboundBlockEntityDataPacket p = ClientboundBlockEntityDataPacket.create(this);
            ((ServerLevel)this.level).getChunkSource().chunkMap.getPlayers(new ChunkPos(getBlockPos()), false)
                    .forEach(k -> k.connection.send(p));
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag result = new CompoundTag();

        writeMachineData(result);

        CompoundTag machineUpdate = new CompoundTag();

        if (getMachine() != null) {
            getMachine().saveUpdateTag(machineUpdate);
        }

        result.put("machineUpdate", machineUpdate);

        return result;
    }

    @Override
    public void tick() {
        if (isNeedSync()) {
            sync();
            needSync = false;

            if (getMachine() != null) {
                getMachine().setNeedSync(false);
            }

            getMachine().bodyPositions().keySet().forEach(pos -> {
                if (getLevel().getBlockEntity(getBlockPos().offset(pos)) instanceof MultiBlockMachineBodyBlockEntity body) {
                    body.setCore(this);
                }
            });
        }

        if (getMachine() != null && created) {
            getMachine().tick();
        }
    }

    public boolean use(Player pPlayer, InteractionHand pHand, BlockPos pos) {
        return getMachine().onUse(pPlayer, pHand, pos);
    }

    public MachineType<?> getMachineType() {
        return machineType;
    }
}
