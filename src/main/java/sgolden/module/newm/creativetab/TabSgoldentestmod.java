package sgolden.module.newm.creativetab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import sgolden.module.newm.util.Reference;
import sgolden.module.newm.util.RegistryCollections.ItemCollection;

public class TabSgoldentestmod extends CreativeModeTab
{
    public static final TabSgoldentestmod TAB_SGOLDENTESTMOD = new TabSgoldentestmod(Reference.MOD_NAME);

    public TabSgoldentestmod(String label) {
        super(label);
    }

    @NotNull
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemCollection.DIRT_BALL);
    }
}
