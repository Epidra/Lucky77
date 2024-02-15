package mod.lucky77.screen;

import mod.lucky77.menu.MenuBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public abstract class ScreenBase<T extends MenuBase> extends AbstractContainerScreen<T> {
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public ScreenBase(T container, Inventory player, Component name, int imageWidth, int imageHeight) {
		super(container, player, name);
		this.imageWidth  = imageWidth;
		this.imageHeight = imageHeight;
	}
	
	public ScreenBase(T container, Inventory player, Component name, int imageWidth, int imageHeight, String title) {
		super(container, player, name);
		this.imageWidth  = imageWidth;
		this.imageHeight = imageHeight;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INITIALIZATION  ---------- ---------- ---------- ---------- //
	
	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  TICK  ---------- ---------- ---------- ---------- //
	
	public void containerTick() {
	
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INPUT  ---------- ---------- ---------- ---------- //
	
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		return super.keyPressed(keyCode, scanCode, modifiers);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  RENDER  ---------- ---------- ---------- ---------- //
	
	public void render(GuiGraphics matrixStack, int mousePosX, int mousePosY, float partialTick) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mousePosX, mousePosY, partialTick);
		this.renderTooltip(matrixStack, mousePosX, mousePosY);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	/** Checks if mouse is inside a rectangle **/
	protected boolean mouseRect(int x, int y, int width, int height, double mouseX, double mouseY){
		if(leftPos + x < mouseX && mouseX < leftPos + x + width){
			return topPos + y < mouseY && mouseY < topPos + y + height;
		}
		return false;
	}
	
	
	
}
