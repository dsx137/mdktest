package sgolden.module.newm.item.blockitem;

import net.minecraft.world.item.CreativeModeTab;
import sgolden.module.newm.creativetab.TabSgoldentestmod;

import static sgolden.module.newm.item.MultiTabItemTypes.MBlockItem;
import static sgolden.module.newm.util.RegistryCollections.BlockCollection.BLOCK_EXP_POOL;

public class ItemBlockExpPool extends MBlockItem
{
    public ItemBlockExpPool() {
        super(BLOCK_EXP_POOL, GetProperties());
        this.setRegistryName("exp_pool");
        tabReg(CreativeModeTab.TAB_SEARCH).tabReg(TabSgoldentestmod.TAB_SGOLDENTESTMOD);
    }

    public static Properties GetProperties()
    {
        return new Properties();
    }
}
