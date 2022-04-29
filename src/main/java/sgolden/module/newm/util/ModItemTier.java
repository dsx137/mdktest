package sgolden.module.newm.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum ModItemTier implements Tier
{
    COPPER(2, 30, 5.0F, 1F, 15, () -> Ingredient.of(Items.COPPER_INGOT), null);
    private final int level;
    private final int maxUses;
    private final float speed;
    private final float attackDamage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    private final TagKey<Block> tag;

    ModItemTier(int level, int maxUses, float speed, float attackDamage, int enchantmentValue, Supplier<Ingredient> repairIngredient, TagKey<Block> tag) {
        this.level = level;
        this.maxUses = maxUses;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
        this.tag = tag;
    }


    @Override
    public int getUses() {return this.maxUses;}

    @Override
    public float getSpeed() {return this.speed;}

    @Override
    public float getAttackDamageBonus() {return this.attackDamage;}

    @Override
    public int getLevel() {return this.level;}

    @Override
    public int getEnchantmentValue() {return this.enchantmentValue;}

    @NotNull
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Nullable
    @Override
    public TagKey<Block> getTag() {return this.tag;}
}
