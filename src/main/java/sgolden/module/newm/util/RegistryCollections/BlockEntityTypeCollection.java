package sgolden.module.newm.util.RegistryCollections;

import net.minecraft.world.level.block.entity.BlockEntityType;
import sgolden.module.newm.block.entity.BlockExpPoolEntity;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityTypeCollection
{
    public static final List<BlockEntityType<?>> RegistryBlockEntities = new ArrayList<>();
    public static final BlockEntityType<BlockExpPoolEntity> BLOCK_EXP_POOL_ENTITY_TYPE = BlockExpPoolEntity.BuildEntityType();
}
