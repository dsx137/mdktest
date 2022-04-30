package sgolden.module.newm.event;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import sgolden.module.newm.client.renderer.blockentity.BlockExpPoolEntityRenderer;
import sgolden.module.newm.enchantment.EnchantmentAutoFish;
import sgolden.module.newm.enchantment.EnchantmentExplosion;
import sgolden.module.newm.enchantment.EnchantmentVampire;
import sgolden.module.newm.util.RegistryCollections.BlockCollection;

import static sgolden.module.newm.sgoldentestmod.SLOGGER;
import static sgolden.module.newm.util.RegistryCollections.BlockEntityTypeCollection.BLOCK_EXP_POOL_ENTITY_TYPE;


public class EventHandler
{
    @Mod.EventBusSubscriber
    public class ForgeEvent
    {
        @SubscribeEvent
        public static void onPlayerJoin(EntityJoinWorldEvent event)
        {
            Entity entity = event.getEntity();
            if (entity instanceof Player)
            {
                String message = "Hello World.";
                var text = new TextComponent(message);
                entity.sendMessage(text, entity.getUUID());
            }
        }

        @SubscribeEvent
        public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event)
        {
            EventHayBlock.onFurnaceFuelBurnTime(event);
        }

        @SubscribeEvent
        public static void onEntityDamage(LivingDamageEvent event)
        {
            EnchantmentVampire.onEntityDamage(event);
        }

        @SubscribeEvent
        public static void onProjectileImpact(ProjectileImpactEvent event)
        {
            EnchantmentExplosion.onImpact(event);
        }

        @SubscribeEvent
        public static void onEntityJoinWorld(EntityJoinWorldEvent event)
        {
            EnchantmentExplosion.onJoinWorld(event);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event)
        {
            EnchantmentAutoFish.onPlayerTick(event);
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public class ModEvent
    {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event)
        {
            SLOGGER.info("Set666");
            event.enqueueWork(() -> ItemBlockRenderTypes.setRenderLayer(BlockCollection.BLOCK_EXP_POOL, RenderType.translucent())
            );
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerBlockEntityRenderer(BLOCK_EXP_POOL_ENTITY_TYPE, BlockExpPoolEntityRenderer::new);
        }
    }
}
