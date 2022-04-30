package sgolden.module.newm.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.minecraft.world.level.block.Block.UPDATE_CLIENTS;
import static sgolden.module.newm.util.RegistryCollections.BlockCollection.BLOCK_EXP_POOL;
import static sgolden.module.newm.util.RegistryCollections.BlockEntityTypeCollection.BLOCK_EXP_POOL_ENTITY_TYPE;
import static sgolden.module.newm.util.RegistryCollections.BlockEntityTypeCollection.RegistryBlockEntities;

public class BlockExpPoolEntity extends BlockEntity
{
    private int counter;

    public BlockExpPoolEntity(BlockPos pos, BlockState state) {
        super(BLOCK_EXP_POOL_ENTITY_TYPE, pos, state);
        counter = 0;
    }

    public static BlockEntityType<BlockExpPoolEntity> BuildEntityType()
    {
        BlockEntityType<?> entityType = BlockEntityType.Builder
                .of(BlockExpPoolEntity::new, BLOCK_EXP_POOL)
                .build(null)
                .setRegistryName("exp_pool");
        RegistryBlockEntities.add(entityType);
        return (BlockEntityType<BlockExpPoolEntity>) entityType;
    }

    public int use(Player player) {
        counter++;
        update();
        showMessage(player);
        return counter;
    }

    public void showMessage(Player player)
    {
        player.sendMessage(new TextComponent(String.valueOf(counter)), player.getUUID());
    }

    public void update()
    {
        setChanged();
        sync();
    }

    public void sync()
    {
        if (!level.isClientSide)
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), UPDATE_CLIENTS);
    }

    public int getCounter()
    {
        return counter;
    }

    public int setCounter(int num)
    {
        counter = num;
        return counter;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }

    @ParametersAreNonnullByDefault
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        counter = tag.getInt("counter");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.putInt("counter", counter);
    }
}