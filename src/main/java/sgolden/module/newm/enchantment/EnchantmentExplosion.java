package sgolden.module.newm.enchantment;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import sgolden.module.newm.util.RegistryCollections.EnchantmentCollection;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

import static sgolden.module.newm.creativetab.TabSgoldentestmod.TAB_SGOLDENTESTMOD;
import static sgolden.module.newm.util.RegistryCollections.EnchantmentCollection.RegistryEnchantments;
import static sgolden.module.newm.util.RegistryCollections.ItemCollection.TAB_ADD_MITEM;

public class EnchantmentExplosion extends Enchantment
{
    private static final float radiusMultiplier = 0.1F;
    private static final String nbtName = "EnchantmentExplosionLevel";

    public EnchantmentExplosion() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        this.setRegistryName("explosion");
        RegistryEnchantments.add(this);
    }

    @Override
    public int getMaxLevel() {return 3;}

    @ParametersAreNonnullByDefault
    @Override
    public boolean checkCompatibility(Enchantment enchantment) {
        return (this != enchantment && enchantment != Enchantments.INFINITY_ARROWS);
    }

    public static void onImpact(ProjectileImpactEvent event)
    {
        Entity projectile = event.getProjectile();
        if (projectile instanceof Arrow arrow)
        {
            float movementSpeed = (float) arrow.getDeltaMovement().length();
            HitResult res = event.getRayTraceResult();
            Entity arrowOwner = arrow.getOwner();
            if (arrowOwner instanceof LivingEntity LivingOwner)
            {
                CompoundTag tag = projectile.getPersistentData();
                int level = tag.getInt(nbtName);
                if (level != 0)
                {
                    projectile.level.explode(null, DamageSource.explosion(LivingOwner), null, res.getLocation().x, res.getLocation().y, res.getLocation().z, movementSpeed * radiusMultiplier * level, level > 2, Explosion.BlockInteraction.BREAK);
                    projectile.kill();
                }
            }
        }
    }

    public static void onJoinWorld(EntityJoinWorldEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof Arrow)
        {
            Entity arrowOwner = ((Arrow) entity).getOwner();
            if (arrowOwner instanceof LivingEntity LivingOwner)
            {
                int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentCollection.ENCHANTMENT_EXPLOSION, LivingOwner);
                if (level != 0)
                {
                    CompoundTag tag = entity.getPersistentData();
                    tag.putInt(nbtName, level);
                }
            }
        }
    }

    public static void tabLoader()
    {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (int level = 1; level <= 3; level++)
        {
            ItemStack explosionBook = EnchantedBookItem.createForEnchantment(
                    new EnchantmentInstance(
                            EnchantmentCollection.ENCHANTMENT_EXPLOSION, level
                    )
            );
            itemStacks.add(explosionBook);
        }
        TAB_ADD_MITEM.tabReg(itemStacks, TAB_SGOLDENTESTMOD);
    }
}