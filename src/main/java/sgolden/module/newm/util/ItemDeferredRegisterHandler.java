package sgolden.module.newm.util;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.http.annotation.Obsolete;
import sgolden.module.newm.sgoldentestmod;

@Obsolete
public class ItemDeferredRegisterHandler
{

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static void register(IEventBus eventBus)
    {
        sgoldentestmod.SLOGGER.info("Deferred Registry Done.");
        ITEMS.register(eventBus);
    }
}
