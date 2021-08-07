package mod.lucky77.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.lucky77.menu.MenuBase;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ScreenBase<T extends MenuBase> extends AbstractContainerScreen<T> {

    // ...




    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public ScreenBase(T container, Inventory player, Component name, int imageWidth, int imageHeight) {
        super(container, player, name);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void containerTick() {

    }

    public void render(PoseStack matrixStack, int mousePosX, int mousePosY, float partialTick) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mousePosX, mousePosY, partialTick);
        this.renderTooltip(matrixStack, mousePosX, mousePosY);
    }

    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        return super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
    }




    //----------------------------------------INPUT----------------------------------------//

    ///** Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton */
    //public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
    //    super.mouseClicked(mouseX, mouseY, mouseButton);
    //    //if (mouseButton == 0){ if(mouseRect( 58, 0, 56, 24, mouseX, mouseY)) { commandChangePage(0); } }
    //    return false;
    //}




    //----------------------------------------DRAW----------------------------------------//

    //@Override
    //public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    //    this.renderBackground(matrixStack);
    //    super.render(matrixStack, mouseX, mouseY, partialTicks);
    //    this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    //}
//
    ///** Draw the foreground layer for the GuiContainer (everything in front of the items) */
    //protected void drawGuiContainerForegroundLayer(MatrixStack matrixstack, int mouseX, int mouseY){
//
    //}
//
    ///** Draws the background layer of this container (behind the items). */
    //protected void drawGuiContainerBackgroundLayer(MatrixStack matrixstack, float partialTicks, int mouseX, int mouseY){
    //    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    //    this.minecraft.getTextureManager().bindTexture(TEXTURE);
    //    int i = (this.width  - this.xSize) / 2;
    //    int j = (this.height - this.ySize) / 2;
    //    this.blit(matrixstack, i, j, 0, 0, this.xSize, this.ySize);
    //}




    //----------------------------------------COMMAND----------------------------------------//

    // ...




    //----------------------------------------SUPPORT----------------------------------------//

    /** Checks if mouse is inside a rectangle **/
    protected boolean mouseRect(int x, int y, int width, int height, double mouseX, double mouseY){
        if(leftPos + x < mouseX && mouseX < leftPos + x + width){
            return topPos + y < mouseY && mouseY < topPos + y + height;
        }
        return false;
    }

}
