package sgolden.module.newm.item;

import net.minecraft.world.item.CreativeModeTab;
import sgolden.module.newm.creativetab.TabSgoldentestmod;

import static sgolden.module.newm.item.MultiTabItemTypes.MItem;

public class ItemDirtBall extends MItem
{

    public ItemDirtBall() {
        super(GetProperties());
        this.setRegistryName("dirt_ball");
        tabReg(CreativeModeTab.TAB_SEARCH);
    }

    public static Properties GetProperties()
    {
        return new Properties()
                .tab(TabSgoldentestmod.TAB_SGOLDENTESTMOD)
                .durability(0)
                .stacksTo(16)
                .setNoRepair();
    }
}
