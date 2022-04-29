package sgolden.module.newm.util.RegistryCollections;

import net.minecraft.world.item.enchantment.Enchantment;
import sgolden.module.newm.enchantment.EnchantmentAutoFish;
import sgolden.module.newm.enchantment.EnchantmentExplosion;
import sgolden.module.newm.enchantment.EnchantmentVampire;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentCollection
{
    public static final List<Enchantment> RegistryEnchantments = new ArrayList<>();
    public static final EnchantmentVampire ENCHANTMENT_VAMPIRE = new EnchantmentVampire();
    public static final EnchantmentExplosion ENCHANTMENT_EXPLOSION = new EnchantmentExplosion();
    public static final EnchantmentAutoFish ENCHANTMENT_AUTO_FISH = new EnchantmentAutoFish();

}
