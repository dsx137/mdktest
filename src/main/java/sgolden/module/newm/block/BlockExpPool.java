package sgolden.module.newm.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sgolden.module.newm.block.entity.BlockExpPoolEntity;

import javax.annotation.ParametersAreNonnullByDefault;

import static sgolden.module.newm.util.RegistryCollections.BlockEntityTypeCollection.BLOCK_EXP_POOL_ENTITY_TYPE;

public class BlockExpPool extends AbstractBlock.AbstractGlassEntityBlock
{
//    private static final VoxelShape shape;
//
//    static
//    {
////        VoxelShape base = Block.box(0, 0, 0, 16, 1, 16);
////        VoxelShape column1 = Block.box(0, 1, 0, 1, 15, 1);
////        VoxelShape column2 = Block.box(15, 1, 0, 16, 15, 1);
////        VoxelShape column3 = Block.box(0, 1, 15, 1, 15, 16);
////        VoxelShape column4 = Block.box(15, 1, 15, 16, 15, 16);
////        VoxelShape top = Block.box(0, 15, 0, 16, 16, 16);
////        shape = Shapes.or(base, column1, column2, column3, column4, top);
//        shape = Block.box(0, 0, 0, 16, 16, 16);
//    }

    public BlockExpPool() {
        super();
        this.setRegistryName("exp_pool");
    }

    @ParametersAreNonnullByDefault
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BLOCK_EXP_POOL_ENTITY_TYPE.create(pos, state);
    }

//    @Override
//    public RenderShape getRenderShape(BlockState state) {
//        return RenderShape.MODEL;
//    }

//    @Override
//    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
//        return shape;
//    }

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
