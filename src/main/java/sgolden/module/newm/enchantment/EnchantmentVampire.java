package sgolden.module.newm.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import sgolden.module.newm.util.RegistryCollections.EnchantmentCollection;

import java.util.ArrayList;
import java.util.List;

import static sgolden.module.newm.creativetab.TabSgoldentestmod.TAB_SGOLDENTESTMOD;
import static sgolden.module.newm.util.RegistryCollections.EnchantmentCollection.RegistryEnchantments;
import static sgolden.module.newm.util.RegistryCollections.ItemCollection.TAB_ADD_MITEM;

public class EnchantmentVampire extends Enchantment
{
    private static final float healMultiplier = 0.1F;

    public EnchantmentVampire() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        this.setRegistryName("vampire");
        RegistryEnchantments.add(this);
    }

    @Override
    public int getMaxLevel() {return 2;}

    public static void onEntityDamage(LivingDamageEvent event)
    {
        Entity source = event.getSource().getEntity();
        if (source instanceof LivingEntity attackSource)
        {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentCollection.ENCHANTMENT_VAMPIRE, attackSource);
            if (level != 0)
            {
                float damageAmount = event.getAmount();
                attackSource.heal(level * healMultiplier * damageAmount);
            }
        }
    }

    public static void tabLoader()
    {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (int level = 1; level <= 2; level++)
        {
            ItemStack vampireBook = EnchantedBookItem.createForEnchantment(
                    new EnchantmentInstance(
                            EnchantmentCollection.ENCHANTMENT_VAMPIRE, level
                    )
            );
            itemStacks.add(vampireBook);
        }
        TAB_ADD_MITEM.tabReg(itemStacks, TAB_SGOLDENTESTMOD);
    }
}
