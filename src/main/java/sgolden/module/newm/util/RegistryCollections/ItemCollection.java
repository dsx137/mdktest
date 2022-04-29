package sgolden.module.newm.util.RegistryCollections;

import net.minecraft.world.item.Item;
import sgolden.module.newm.item.ItemCookedEgg;
import sgolden.module.newm.item.ItemCopperArmors;
import sgolden.module.newm.item.ItemDirtBall;
import sgolden.module.newm.item.blockitem.ItemBlockExpPool;

import java.util.ArrayList;
import java.util.List;

import static sgolden.module.newm.item.ItemCopperTools.*;
import static sgolden.module.newm.item.MultiTabItemTypes.MArmorItem;
import static sgolden.module.newm.item.MultiTabItemTypes.TabAddMItem;

public class ItemCollection
{
    public static final List<Item> RegistryItems = new ArrayList<>();
    public static final TabAddMItem TAB_ADD_MITEM = new TabAddMItem();
    public static final ItemDirtBall DIRT_BALL = new ItemDirtBall();
    public static final ItemCopperSword COPPER_SWORD = new ItemCopperSword();
    public static final ItemCopperShovel COPPER_SHOVEL = new ItemCopperShovel();
    public static final ItemCopperPickaxe COPPER_PICKAXE = new ItemCopperPickaxe();
    public static final ItemCopperAxe COPPER_AXE = new ItemCopperAxe();
    public static final ItemCopperHoe COPPER_HOE = new ItemCopperHoe();
    public static final MArmorItem COPPER_HELMET = new ItemCopperArmors.copperHelmet();
    public static final MArmorItem COPPER_CHESTPLATE = new ItemCopperArmors.copperChestPlate();
    public static final MArmorItem COPPER_LEGGINGS = new ItemCopperArmors.copperLeggings();
    public static final MArmorItem COPPER_BOOTS = new ItemCopperArmors.copperBoots();
    public static final ItemCookedEgg ITEM_COOKED_EGG = new ItemCookedEgg();
    //BlockItems
    public static final ItemBlockExpPool ITEM_BLOCK_EXP_POOL = new ItemBlockExpPool();

    //        public static final ItemHayBlock ITEM_HAY_BLOCK = new ItemHayBlock();

}
