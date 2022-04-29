package sgolden.module.newm.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

public class EventHayBlock
{
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event)
    {
        ResourceLocation resourceLocation = event.getItemStack().getItem().getRegistryName();
        assert resourceLocation != null;
        String registryNameResourceNamespace = resourceLocation.getNamespace();
        String registryNameResourcePath = resourceLocation.getPath();
        if ("minecraft".equals(registryNameResourceNamespace) && "hay_block".equals(registryNameResourcePath))
            event.setBurnTime(4000);
    }
}
