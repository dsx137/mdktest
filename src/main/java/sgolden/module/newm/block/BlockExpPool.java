package sgolden.module.newm.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sgolden.module.newm.block.entity.BlockExpPoolEntity;

import javax.annotation.ParametersAreNonnullByDefault;

import static sgolden.module.newm.util.RegistryCollections.BlockEntityTypeCollection.BLOCK_EXP_POOL_ENTITY_TYPE;

public class BlockExpPool extends AbstractEntityBlock
{

    public BlockExpPool() {
        super(GetProperties());
        this.setRegistryName("exp_pool");
    }

    public static Block.Properties GetProperties()
    {
        return Properties
                .of(Material.GLASS)
                .strength(0)
                .sound(SoundType.GLASS);
    }

    @ParametersAreNonnullByDefault
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BLOCK_EXP_POOL_ENTITY_TYPE.create(pos, state);
    }

    @NotNull
    @ParametersAreNonnullByDefault
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        BlockExpPoolEntity blockEntity = (BlockExpPoolEntity) level.getBlockEntity(pos);
        if (blockEntity != null)
        {
            if (!level.isClientSide && hand == InteractionHand.MAIN_HAND)
            {
                player.sendMessage(new TextComponent("Server:"), player.getUUID());
                blockEntity.use(player);
            } else
            {
                player.sendMessage(new TextComponent("Client:"), player.getUUID());
                blockEntity.showMessage(player);
            }
            return InteractionResult.SUCCESS;
        } else
        {
            return InteractionResult.FAIL;
        }
    }

//    @Nullable
//    @Override
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
//        return BaseEntityBlock.createTickerHelper(pBlockEntityType, BLOCK_EXP_POOL_ENTITY_TYPE,
//                pLevel.isClientSide ? BlockExpPoolEntity::clientTick : BlockExpPoolEntity::serverTick);
//    }

}
