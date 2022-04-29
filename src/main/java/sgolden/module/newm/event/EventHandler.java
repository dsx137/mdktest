package sgolden.module.newm.event;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sgolden.module.newm.enchantment.EnchantmentAutoFish;
import sgolden.module.newm.enchantment.EnchantmentExplosion;
import sgolden.module.newm.enchantment.EnchantmentVampire;

@Mod.EventBusSubscriber
public class EventHandler
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
