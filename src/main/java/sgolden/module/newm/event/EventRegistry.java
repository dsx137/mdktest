package sgolden.module.newm.event;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sgolden.module.newm.creativetab.TabSgoldentestmod;
import sgolden.module.newm.enchantment.EnchantmentAutoFish;
import sgolden.module.newm.enchantment.EnchantmentExplosion;
import sgolden.module.newm.enchantment.EnchantmentVampire;
import sgolden.module.newm.item.ItemCookedEgg;
import sgolden.module.newm.item.ItemCopperArmors;
import sgolden.module.newm.item.ItemCopperTools;
import sgolden.module.newm.sgoldentestmod;

import java.util.List;

import static sgolden.module.newm.util.RegistryCollections.BlockCollection.RegistryBlocks;
import static sgolden.module.newm.util.RegistryCollections.BlockEntityTypeCollection.RegistryBlockEntities;
import static sgolden.module.newm.util.RegistryCollections.EnchantmentCollection.RegistryEnchantments;
import static sgolden.module.newm.util.RegistryCollections.ItemCollection.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventRegistry
{

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(RegistryItems.toArray(new Item[0]));
        ItemCopperArmors.tabLoader();
        ItemCopperTools.tabLoader();
        //TestCode
        TAB_ADD_MITEM.tabReg(List.of(new ItemStack(ITEM_COOKED_EGG)), TabSgoldentestmod.TAB_SGOLDENTESTMOD);
        sgoldentestmod.SLOGGER.info("Item Event Registry Done.");
    }

    @SubscribeEvent
    public static void onEnchantmentRegistry(final RegistryEvent.Register<Enchantment> event)
    {
        event.getRegistry().registerAll(RegistryEnchantments.toArray(new Enchantment[0]));
        EnchantmentExplosion.tabLoader();
        EnchantmentAutoFish.tabLoader();
        EnchantmentVampire.tabLoader();
        sgoldentestmod.SLOGGER.info("Enchantment Registry Done.");
    }

    @SubscribeEvent
    public static void onBlockRegistry(final RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(RegistryBlocks.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onBlockEntityTypeRegistry(final RegistryEvent.Register<BlockEntityType<?>> event)
    {
        event.getRegistry().registerAll(RegistryBlockEntities.toArray(new BlockEntityType[0]));
    }
}
