package mod.lucky77.client.screen.base;

import mod.lucky77.client.menu.MenuBase;
import mod.lucky77.custom.button.ButtonSet;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ScreenBase<T extends MenuBase> extends AbstractContainerScreen<T> {
	
	protected final ButtonSet buttonSet = new ButtonSet();
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public ScreenBase(T container, Inventory playerInventory, Component name, int imageWidth, int imageHeight){
		super(container, playerInventory, name);
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		createButtons();
	}
	
	//   -------- -------- -------- --------     INITIALIZATION     -------- -------- -------- --------   //
	
	@Override
	public void init(){
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}
	
	protected abstract void createButtons();
	
	//   -------- -------- -------- --------     TICK     -------- -------- -------- --------   //
	
	@Override
	public void containerTick(){
	
	}
	
	//   -------- -------- -------- --------     INPUT     -------- -------- -------- --------   //
	
	// @Override
	// public boolean keyPressed(int keyCode, int scanCode, int modifiers){
	// 	return super.keyPressed(keyCode, scanCode, modifiers);
	// }
	
	//   -------- -------- -------- --------     RENDER     -------- -------- -------- --------   //
	
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		// guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
		// guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
	}
	
	@Override
	public void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY){
		// this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		buttonSet.update(leftPos, topPos, mouseX, mouseY);
		renderSpriteLayer(guiGraphics, mouseX, mouseY);
		buttonSet.render(guiGraphics, leftPos, topPos);
		renderOverlay(guiGraphics);
		// this.renderTooltip(guiGraphics, mouseX, mouseY);
	}
	
	protected abstract void renderOverlay(GuiGraphics guiGraphics);
	protected abstract void renderSpriteLayer(GuiGraphics guiGraphics, int mousePosX, int mousePosY);
	
	// Texture Draw with hidden gui position
	protected void blit(GuiGraphics guiGraphics, ResourceLocation texture, int posX, int posY, int texX, int texY, int width, int height){
		guiGraphics.blit(texture, this.leftPos + posX, this.topPos + posY, texX, texY, width, height);
	}
	
	// Texture Draw with hidden gui position (uses full texture size)
	protected void blit(GuiGraphics guiGraphics, ResourceLocation texture, int posX, int posY, int texX, int texY){
		guiGraphics.blit(texture, this.leftPos + posX, this.topPos + posY, texX, texY, 256, 256);
	}
	
	// Texture Draw with hidden gui position (uses complete texture)
	protected void blit(GuiGraphics guiGraphics, ResourceLocation texture){
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, 256, 256);
	}
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	@Override
	public boolean isPauseScreen(){
		return false;
	}
	
	protected boolean mouseRect(int x, int y, int width, int height, double mouseX, double mouseY){
		if(leftPos + x < mouseX && mouseX < leftPos + x + width){
			return topPos + y < mouseY && mouseY < topPos + y + height;
		}
		return false;
	}
	
}
