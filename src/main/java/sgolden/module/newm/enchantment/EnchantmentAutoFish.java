package sgolden.module.newm.enchantment;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import sgolden.module.newm.util.RegistryCollections.EnchantmentCollection;

import java.util.List;

import static sgolden.module.newm.creativetab.TabSgoldentestmod.TAB_SGOLDENTESTMOD;
import static sgolden.module.newm.util.RegistryCollections.EnchantmentCollection.RegistryEnchantments;
import static sgolden.module.newm.util.RegistryCollections.ItemCollection.TAB_ADD_MITEM;

public class EnchantmentAutoFish extends Enchantment
{
    private static final int throwCD = 10;
    private static int _TThrow = 0;
    private static boolean pendingThrow = false;

    public EnchantmentAutoFish() {
        super(Rarity.RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
        this.setRegistryName("autofish");
        RegistryEnchantments.add(this);
    }

    @Override
    public int getMaxLevel() {return 1;}

    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.side != LogicalSide.CLIENT || !event.phase.equals(TickEvent.Phase.START)) return;
        Player player = event.player;
        if (!player.getUUID().equals(Minecraft.getInstance().player.getUUID())) return;
        InteractionHand hand = findHandOfRod(player);
        if (hand != null)
        {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentCollection.ENCHANTMENT_AUTO_FISH, player);
            ItemStack fishRod = player.getItemInHand(hand);
            if (level != 0)
            {
                if (player.fishing != null && isFished(player) && fishRod.getMaxDamage() - fishRod.getDamageValue() - fishRod.getUseDuration() > 1)
                {
                    Minecraft.getInstance().gameMode.useItem(player, player.level, hand);
                    pendingThrow = true;
                }
                if (pendingThrow && fishRod.getMaxDamage() - fishRod.getDamageValue() > 5)
                {
                    _TThrow++;
                    if (_TThrow >= throwCD)
                    {
                        _TThrow = 0;
                        Minecraft.getInstance().gameMode.useItem(player, player.level, hand);
                        pendingThrow = false;
                    }
                }
            }
        }
    }

    private static boolean isFished(Player player)
    {
        if (player.fishing == null) return false;
        Vec3 vec = player.fishing.getDeltaMovement();
        return player.fishing.isInWater() && vec.y < -0.075 && vec.x == 0 && vec.z == 0;
    }

    private static InteractionHand findHandOfRod(Player player) {
        if (player.getMainHandItem().getItem() instanceof FishingRodItem) return InteractionHand.MAIN_HAND;
        else if (player.getOffhandItem().getItem() instanceof FishingRodItem) return InteractionHand.OFF_HAND;
        else return null;
    }

    public static void tabLoader()
    {
        ItemStack autoFishBook = EnchantedBookItem.createForEnchantment(
                new EnchantmentInstance(
                        EnchantmentCollection.ENCHANTMENT_AUTO_FISH, 1
                )
        );
        TAB_ADD_MITEM.tabReg(List.of(autoFishBook), TAB_SGOLDENTESTMOD);
    }
}
