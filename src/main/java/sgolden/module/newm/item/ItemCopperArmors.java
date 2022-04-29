package sgolden.module.newm.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import sgolden.module.newm.creativetab.TabSgoldentestmod;
import sgolden.module.newm.util.ModArmorMaterial;

import java.util.List;

import static net.minecraft.world.item.Item.Properties;
import static sgolden.module.newm.item.MultiTabItemTypes.MArmorItem;
import static sgolden.module.newm.util.RegistryCollections.ItemCollection.*;

public class ItemCopperArmors
{
    public static Properties GetProperties() {return new Properties();}

    public static void tabLoader()
    {
        List<MultiTabItemTypes.MItemLike> regList;
        regList = List.of(COPPER_HELMET, COPPER_CHESTPLATE, COPPER_LEGGINGS, COPPER_BOOTS);
        TAB_ADD_MITEM.tabReg(regList, CreativeModeTab.TAB_COMBAT, 17);
        TAB_ADD_MITEM.tabReg(regList, CreativeModeTab.TAB_SEARCH).tabReg(regList, TabSgoldentestmod.TAB_SGOLDENTESTMOD);
    }

    public static class copperHelmet extends MArmorItem
    {
        public copperHelmet() {
            super(ModArmorMaterial.COPPER, EquipmentSlot.HEAD, GetProperties());
            this.setRegistryName("copper_helmet");
        }
    }

    public static class copperChestPlate extends MArmorItem
    {
        public copperChestPlate() {
            super(ModArmorMaterial.COPPER, EquipmentSlot.CHEST, GetProperties());
            this.setRegistryName("copper_chestplate");
        }
    }

    public static class copperLeggings extends MArmorItem
    {
        public copperLeggings() {
            super(ModArmorMaterial.COPPER, EquipmentSlot.LEGS, GetProperties());
            this.setRegistryName("copper_leggings");
        }
    }

    public static class copperBoots extends MArmorItem
    {
        public copperBoots() {
            super(ModArmorMaterial.COPPER, EquipmentSlot.FEET, GetProperties());
            this.setRegistryName("copper_boots");
        }
    }
}
