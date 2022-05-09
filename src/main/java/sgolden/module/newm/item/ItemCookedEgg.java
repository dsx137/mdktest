package sgolden.module.newm.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import sgolden.module.newm.creativetab.TabSgoldentestmod;

import java.util.List;

import static sgolden.module.newm.item.MultiTabItemTypes.MItem;
import static sgolden.module.newm.util.RegistryCollections.ItemCollection.TAB_ADD_MITEM;

public class ItemCookedEgg extends MItem
{

    public ItemCookedEgg() {
        super(GetProperties());
        this.setRegistryName("cooked_egg");
        tabReg(CreativeModeTab.TAB_SEARCH).tabReg(CreativeModeTab.TAB_FOOD);
    }

    public static Properties GetProperties()
    {
        FoodProperties foodProperties = new FoodProperties.Builder()
                .nutrition(3)
                .saturationMod(0.8F)
                .build();
        return new Properties()
                .food(foodProperties)
                .tab(TabSgoldentestmod.TAB_SGOLDENTESTMOD)
                .stacksTo(16);
    }
}
