package sgolden.module.newm;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import sgolden.module.newm.util.ItemDeferredRegisterHandler;
import sgolden.module.newm.util.Reference;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MOD_ID)
public class sgoldentestmod
{
    // Directly reference a slf4j logger
    public static final Logger SLOGGER = LogUtils.getLogger();

    public sgoldentestmod()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemDeferredRegisterHandler.register(eventBus);
    }
}
