package sgolden.module.newm.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fluids.FluidStack;
import sgolden.module.newm.block.entity.BlockExpPoolEntity;

public class BlockExpPoolEntityRenderer implements BlockEntityRenderer<BlockExpPoolEntity>
{
    BlockEntityRendererProvider.Context context;

    public BlockExpPoolEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.context = context;
    }

    @Override
    public void render(BlockExpPoolEntity blockEntity, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferSource, int combinedOverlay, int packedLight) {
        BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = new ItemStack(Items.BAMBOO);
        BakedModel bakedModel = itemRenderer.getModel(itemStack, blockEntity.getLevel(), null, 0);
        VertexConsumer buffer = bufferSource.getBuffer(RenderType.translucentNoCrumbling());

        matrixStackIn.pushPose();
        dispatcher.renderSingleBlock(Blocks.GLASS.defaultBlockState(), matrixStackIn, bufferSource, combinedOverlay, packedLight, EmptyModelData.INSTANCE);
        matrixStackIn.popPose();

        renderFluid(blockEntity, dispatcher, matrixStackIn, buffer);


//        matrixStackIn.pushPose();
//        matrixStackIn.translate(0.5f, 0.5f, 0.5f);
//        itemRenderer.render(itemStack, ItemTransforms.TransformType.FIXED, true, matrixStackIn, bufferSource, combinedOverlay, packedLight, bakedModel);
//        matrixStackIn.popPose();
    }

    public void renderFluid(BlockEntity blockEntity, BlockRenderDispatcher dispatcher, PoseStack matrixStackIn, VertexConsumer buffer)
    {

//        GlStateManager._disableCull();
        int counter = ((BlockExpPoolEntity) blockEntity).getCounter();
        FluidStack fluidStack = new FluidStack(Fluids.WATER, counter);
        TextureAtlasSprite still = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getTexture(fluidStack.getFluid().defaultFluidState().createLegacyBlock(), blockEntity.getLevel(), blockEntity.getBlockPos());
        int colorRGB = fluidStack.getFluid().getAttributes().getColor();
        float height = (float) fluidStack.getAmount() / (9 / 8f * 500);
        float u0 = still.getU0();
        float u1 = still.getU1();
        float v0 = still.getV0();
        float v1 = still.getV1();
        float vHeight = (v1 - v0) * (1 - (fluidStack.getAmount() / 500f));
        if (counter != 0)
        {
            matrixStackIn.pushPose();
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f + height, 1 / 16f, still.getU0(), still.getV0(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f + height, 15 / 16f, still.getU1(), still.getV0(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f + height, 15 / 16f, still.getU1(), still.getV1(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f + height, 1 / 16f, still.getU0(), still.getV1(), colorRGB, 1.0f);

            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f + height, 1 / 16f, still.getU0(), still.getV0() + vHeight, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f + height, 15 / 16f, still.getU1(), still.getV0() + vHeight, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f, 15 / 16f, still.getU1(), still.getV1(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f, 1 / 16f, still.getU0(), still.getV1(), colorRGB, 1.0f);

            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f, 1 / 16f, still.getU0(), still.getV0(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f, 15 / 16f, still.getU1(), still.getV0(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f + height, 15 / 16f, still.getU1(), still.getV1() - vHeight, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f + height, 1 / 16f, still.getU0(), still.getV1() - vHeight, colorRGB, 1.0f);

            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f + height, 1 / 16f, still.getU0(), still.getV0() + vHeight, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f + height, 1 / 16f, still.getU1(), still.getV0() + vHeight, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f, 1 / 16f, still.getU1(), still.getV1(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f, 1 / 16f, still.getU0(), still.getV1(), colorRGB, 1.0f);

            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f, 15 / 16f, still.getU0(), still.getV0() + vHeight, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f, 15 / 16f, still.getU1(), still.getV0() + vHeight, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f + height, 15 / 16f, still.getU1(), still.getV1(), colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f + height, 15 / 16f, still.getU0(), still.getV1(), colorRGB, 1.0f);
            matrixStackIn.popPose();

            matrixStackIn.pushPose();
            matrixStackIn.mulPose(Quaternion.fromXYZDegrees(new Vector3f(0, 0, 180)));
            matrixStackIn.translate(-1, -1 / 8f, 0);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f, 1 / 16f, u0, v0, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 1 / 16f, 1 / 16f, 15 / 16f, u1, v0, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f, 15 / 16f, u1, v1, colorRGB, 1.0f);
            addVertex(buffer, matrixStackIn, 15 / 16f, 1 / 16f, 1 / 16f, u0, v1, colorRGB, 1.0f);
            matrixStackIn.popPose();
        }
//        GlStateManager._enableCull();
    }

    public static void addVertex(VertexConsumer buffer, PoseStack matrixStackIn, float x, float y, float z, float u, float v, int RGBA, float alpha) {
        float red = ((RGBA >> 16) & 0xFF) / 255f;
        float green = ((RGBA >> 8) & 0xFF) / 255f;
        float blue = ((RGBA >> 0) & 0xFF) / 255f;
        buffer.vertex(matrixStackIn.last().pose(), x, y, z).color(red, green, blue, alpha).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880)/*.lightmap(0, 240)*/.normal(matrixStackIn.last().normal(), 0, 1.0F, 0).endVertex();
    }
}
