package sgolden.module.newm.block;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;

import static sgolden.module.newm.util.RegistryCollections.BlockCollection.RegistryBlocks;

public abstract class AbstractEntityBlock extends BaseEntityBlock
{
    public AbstractEntityBlock(Block.Properties properties) {
        super(properties);
        RegistryBlocks.add(this);
    }
}
