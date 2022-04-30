package sgolden.module.newm.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

import static sgolden.module.newm.util.RegistryCollections.BlockCollection.RegistryBlocks;

public class AbstractBlock
{
    public static abstract class AbstractEntityBlock extends BaseEntityBlock
    {
        public AbstractEntityBlock(Block.Properties properties) {
            super(properties);
            RegistryBlocks.add(this);
        }
    }

    public static abstract class AbstractGlassEntityBlock extends GlassBlock implements EntityBlock
    {
        public AbstractGlassEntityBlock() {
            super(GetProperties());
            RegistryBlocks.add(this);
        }

        public static Block.Properties GetProperties()
        {
            return Properties.copy(Blocks.GLASS)
                    .noOcclusion()
                    .isValidSpawn(BlockExpPool::neverAllowSpawn)
                    .isRedstoneConductor(BlockExpPool::isSolid)
                    .isSuffocating(BlockExpPool::isSolid)
                    .isViewBlocking(BlockExpPool::isSolid);
        }

        //
//        @Override
//        public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
//            return world.getMaxLightLevel();
//        }
        @ParametersAreNonnullByDefault
        @Override
        public RenderShape getRenderShape(BlockState pState) {
            return RenderShape.ENTITYBLOCK_ANIMATED;
        }

        protected static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
            return false;
        }

        protected static boolean isSolid(BlockState state, BlockGetter reader, BlockPos pos) {
            return false;
        }
    }
}
