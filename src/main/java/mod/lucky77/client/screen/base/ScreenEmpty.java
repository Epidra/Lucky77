package mod.lucky77.client.screen.base;

import mod.lucky77.client.menu.MenuBase;
import mod.lucky77.custom.button.ButtonSet;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ScreenEmpty extends Screen {
	
	// Unneeded
	
	
	
	protected final ButtonSet buttonSet = new ButtonSet();
	
	protected int leftPos = 0;
	protected int topPos = 0;
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public ScreenEmpty(Component name){
		super(name);
		createButtons();
	}
	
	//   -------- -------- -------- --------     INITIALIZATION     -------- -------- -------- --------   //
	
	@Override
	public void init(){
		super.init();
		this.leftPos = (this.width - 256) / 2;
		this.topPos  = (this.height - 256) / 2;
	}
	
	protected abstract void createButtons();
	
	//   -------- -------- -------- --------     TICK     -------- -------- -------- --------   //
	
	// ...
	
	//   -------- -------- -------- --------     INPUT     -------- -------- -------- --------   //
	
	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers){
		return super.keyPressed(keyCode, scanCode, modifiers);
	}
	
	//   -------- -------- -------- --------     RENDER     -------- -------- -------- --------   //
	
	@Override
	public void render(GuiGraphics guiGraphics, int mousePosX, int mousePosY, float partialTick){
		this.renderBackground(guiGraphics, mousePosX, mousePosY, partialTick);
		buttonSet.update(leftPos, topPos, mousePosX, mousePosY);
		renderBackGround(guiGraphics, mousePosX, mousePosY, partialTick);
		buttonSet.render(guiGraphics, leftPos, topPos);
		renderForeGround(guiGraphics, mousePosX, mousePosY, partialTick);
		super.render(guiGraphics, mousePosX, mousePosY, partialTick);
	}
	
	protected abstract void renderBackGround(GuiGraphics guiGraphics, int mousePosX, int mousePosY, float partialTick);
	protected abstract void renderForeGround(GuiGraphics guiGraphics, int mousePosX, int mousePosY, float partialTick);
	
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
