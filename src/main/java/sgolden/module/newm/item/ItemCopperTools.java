package sgolden.module.newm.item;

import net.minecraft.world.item.CreativeModeTab;
import sgolden.module.newm.creativetab.TabSgoldentestmod;
import sgolden.module.newm.util.ModItemTier;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.item.Item.Properties;
import static sgolden.module.newm.item.MultiTabItemTypes.*;
import static sgolden.module.newm.util.RegistryCollections.ItemCollection.*;

public class ItemCopperTools
{
    public static final int durability = 180;

    public static Properties GetProperties() {return new Properties().durability(durability);}

    public static void tabLoader()
    {
        List<MItemLike> regList = List.of(COPPER_SHOVEL, COPPER_PICKAXE, COPPER_AXE, COPPER_HOE);
        TAB_ADD_MITEM.tabReg(regList, CreativeModeTab.TAB_TOOLS, 13);
        var regList_whole = new ArrayList<>(regList);
        regList_whole.add(COPPER_SWORD);
        TAB_ADD_MITEM.tabReg(regList_whole, CreativeModeTab.TAB_SEARCH).tabReg(regList_whole, TabSgoldentestmod.TAB_SGOLDENTESTMOD);
        TAB_ADD_MITEM.tabReg(List.of(COPPER_SWORD), CreativeModeTab.TAB_COMBAT, 6);
    }

    public static class ItemCopperSword extends MSwordItem
    {
        public ItemCopperSword() {
            super(ModItemTier.COPPER, 3, -2F, GetProperties());
            this.setRegistryName("copper_sword");
        }
    }

    public static class ItemCopperShovel extends MShovelItem
    {
        public ItemCopperShovel() {
            super(ModItemTier.COPPER, 2, -3F, GetProperties());
            this.setRegistryName("copper_shovel");
        }
    }

    public static class ItemCopperPickaxe extends MPickaxeItem
    {

        public ItemCopperPickaxe() {
            super(ModItemTier.COPPER, 2, -2.8F, GetProperties());
            this.setRegistryName("copper_pickaxe");
        }
    }

    public static class ItemCopperAxe extends MAxeItem
    {
        public ItemCopperAxe() {
            super(ModItemTier.COPPER, 6, -2.8F, GetProperties());
            this.setRegistryName("copper_axe");
        }
    }

    public static class ItemCopperHoe extends MHoeItem
    {
        public ItemCopperHoe() {
            super(ModItemTier.COPPER, 0, -0.8F, GetProperties());
            this.setRegistryName("copper_hoe");
        }
    }
}
