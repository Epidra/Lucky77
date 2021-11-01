package mod.lucky77.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.lucky77.container.ContainerBase;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ScreenBook<T extends ContainerBase> extends ContainerScreen<T>  {

    // ...





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public ScreenBook(T container, PlayerInventory player, ITextComponent name, int imageWidth, int imageHeight) {
        super(container, player, name);
        this.imageWidth  = imageWidth;
        this.imageHeight = imageHeight;
    }





    //----------------------------------------INPUT----------------------------------------//

    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        return super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
    }





    //----------------------------------------DRAW----------------------------------------//

    public void render(MatrixStack matrixStack, int mousePosX, int mousePosY, float partialTick) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mousePosX, mousePosY, partialTick);
        this.renderTooltip(matrixStack, mousePosX, mousePosY);
    }





    //----------------------------------------COMMAND----------------------------------------//

    // ...





    //----------------------------------------SUPPORT----------------------------------------//

    public void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void tick() {
        super.tick();
    }

    /** Checks if mouse is inside a rectangle **/
    protected boolean mouseRect(int x, int y, int width, int height, double mouseX, double mouseY){
        if(leftPos + x < mouseX && mouseX < leftPos + x + width){
            return topPos + y < mouseY && mouseY < topPos + y + height;
        }
        return false;
    }



}
