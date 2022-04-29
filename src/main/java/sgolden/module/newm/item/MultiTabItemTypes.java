package sgolden.module.newm.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import sgolden.module.newm.util.RegistryCollections.ItemCollection;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

public class MultiTabItemTypes
{
    public interface MItemLike extends ItemLike
    {
        Map<CreativeModeTab, Integer> getCategories();

        default void overFillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items)
        {
            if (OverAllowedIn(tabIn))
            {
                Integer index = getCategories().get(tabIn);
                if (index != null && index != -1)
                    items.add(index, new ItemStack(this));
                else
                    items.add(new ItemStack(this));
            }
        }

        default boolean OverAllowedIn(CreativeModeTab tabIn) {
            if (getCategories().keySet().stream().anyMatch(tab -> tab == tabIn)) return true;
            CreativeModeTab itemCategory = this.asItem().getItemCategory();
            return itemCategory != null && (itemCategory == CreativeModeTab.TAB_SEARCH || tabIn == itemCategory);
        }

        @Deprecated
        default MItemLike tabReg(CreativeModeTab tab, Integer index)
        {
            getCategories().put(tab, index);
            return this;
        }

        default MItemLike tabReg(CreativeModeTab tab) {return tabReg(tab, -1);}
    }

    public static class TabAddMItem extends Item
    {
        private final Map<List<Object>, Map<CreativeModeTab, Integer>> MultiCategories = new LinkedHashMap<>();

        public TabAddMItem() {
            super(new Properties());
            this.setRegistryName("multi_add_item");
            ItemCollection.RegistryItems.add(this);
        }

        public <T> TabAddMItem tabReg(List<T> Obs, CreativeModeTab tabIn, int index)
        {
            if (MultiCategories.get(Obs) != null)
                MultiCategories.get(Obs).put(tabIn, index);
            else
            {
                Map<CreativeModeTab, Integer> cate = new HashMap<>();
                cate.put(tabIn, index);
                MultiCategories.put(List.of(Obs.toArray()), cate);
            }
            return this;
        }

        public <T> TabAddMItem tabReg(List<T> Obs, CreativeModeTab tabIn) {return tabReg(Obs, tabIn, -1);}

        public Map<List<Object>, Map<CreativeModeTab, Integer>> GetMultiCategories() {return MultiCategories;}

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items)
        {
            for (List<Object> obList : MultiCategories.keySet())
            {
                if (obList != null && OverAllowedIn(tabIn, obList))
                {
                    int index = MultiCategories.get(obList).get(tabIn);
                    List<ItemStack> itemStacks = new ArrayList<>();
                    if (obList.get(0) instanceof MItemLike)
                    {
                        for (Object it : obList)
                            itemStacks.add(new ItemStack((MItemLike) it));
                    } else if (obList.get(0) instanceof ItemStack)
                    {
                        itemStacks = List.of(obList.toArray(new ItemStack[0]));
                    }
                    if (index != -1)
                        items.addAll(index, itemStacks);
                    else
                        items.addAll(itemStacks);
                }
            }
        }

        private <T> boolean OverAllowedIn(CreativeModeTab tabIn, List<T> ObjList) {
            return MultiCategories.get(ObjList).keySet().stream().anyMatch(tab -> tab == tabIn);
        }
    }

    public static class MItem extends Item implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MItem(Properties p_41383_) {
            super(p_41383_);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }
    }

    public static class MBlockItem extends BlockItem implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MBlockItem(Block block, Properties p_41383_) {
            super(block, p_41383_);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }
    }

    public static class MSwordItem extends SwordItem implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
            super(p_43269_, p_43270_, p_43271_, p_43272_);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }
    }

    public static class MPickaxeItem extends PickaxeItem implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MPickaxeItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
            super(p_43269_, p_43270_, p_43271_, p_43272_);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }

    }

    public static class MAxeItem extends AxeItem implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MAxeItem(Tier p_43269_, float p_43270_, float p_43271_, Properties p_43272_) {
            super(p_43269_, p_43270_, p_43271_, p_43272_);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }
    }

    public static class MHoeItem extends HoeItem implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MHoeItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
            super(p_43269_, p_43270_, p_43271_, p_43272_);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }
    }

    public static class MShovelItem extends ShovelItem implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MShovelItem(Tier p_43269_, float p_43270_, float p_43271_, Properties p_43272_) {
            super(p_43269_, p_43270_, p_43271_, p_43272_);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }
    }

    public static class MArmorItem extends ArmorItem implements MItemLike
    {
        private final Map<CreativeModeTab, Integer> categories = new HashMap<>();

        public MArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
            super(material, slot, properties);
            ItemCollection.RegistryItems.add(this);
        }

        @Override
        public Map<CreativeModeTab, Integer> getCategories() {
            return categories;
        }

        @Override
        public Collection<CreativeModeTab> getCreativeTabs()
        {
            return categories.keySet();
        }

        @ParametersAreNonnullByDefault
        @Override
        public void fillItemCategory(CreativeModeTab tabIn, NonNullList<ItemStack> items) {
            overFillItemCategory(tabIn, items);
        }
    }
}
