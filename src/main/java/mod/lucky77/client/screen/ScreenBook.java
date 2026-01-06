package mod.lucky77.client.screen;

import com.mojang.blaze3d.platform.InputConstants;
import mod.lucky77.Lucky77;
import mod.lucky77.client.screen.base.ScreenEmpty;
import mod.lucky77.common.item.ItemBook;
import mod.lucky77.custom.button.ButtonSet;
import mod.lucky77.custom.content.ContentPage;
import mod.lucky77.custom.vector.Vector2;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ScreenBook extends Screen {
	
	// needs to be moved closer to the center
	
	
	
	private ResourceLocation BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_gray.png");
	private final List<ContentPage> content = new ArrayList<>();
	private int currentPage = 0;
	
	protected final ButtonSet buttonSet = new ButtonSet();
	
	protected int leftPos = 0;
	protected int topPos = 0;
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public ScreenBook(ItemBook item){
		super(Component.empty());
		loadPages(item);
		createButtons();
	}
	
	//   -------- -------- -------- --------     INITIALIZATION     -------- -------- -------- --------   //
	
	@Override
	public void init(){
		super.init();
		this.leftPos = (this.width - 256) / 2;
		this.topPos  = (this.height - 256) / 2;
	}
	
	// protected abstract void createButtons();
	
	protected void createButtons(){
		buttonSet.addButton(0, new Vector2(32-2, 154+2), new Vector2(215, 246), new Vector2(215, 246), new Vector2(238, 246), new Vector2(18, 10), -1, () -> this.currentPage > 0, () -> this.commandPageBack());
		buttonSet.addButton(0, new Vector2(32-2, 154+2), new Vector2(215, 246), new Vector2(215, 246), new Vector2(238, 246), new Vector2(18, 10), -1, () -> this.currentPage + 2 < content.size(), () -> this.commandPageForward());
		buttonSet.addTexture(BOOK);
	}
	
	//   -------- -------- -------- --------     TICK     -------- -------- -------- --------   //
	
	// ...
	
	//   -------- -------- -------- --------     INPUT     -------- -------- -------- --------   //
	
	public boolean keyPressed(int keyCode, int scanCode, int modifiers){
		
		// superfy
		if(super.keyPressed(keyCode, scanCode, modifiers)){
			return true;
		} else if(this.minecraft.options.keyInventory.isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))){
			this.onClose();
			return true;
		} else {
			
			switch(keyCode){
				case 263:
				case 266:
					commandPageBack();
					return true;
				case 262:
				case 267:
					commandPageForward();
					return true;
				default:
					return false;
			}
			
		}
		
		
	}
	
	//   -------- -------- -------- --------     RENDER     -------- -------- -------- --------   //
	
	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
		
		buttonSet.update(leftPos, topPos, mouseX, mouseY);
		renderBackGround(guiGraphics, mouseX, mouseY, partialTick);
		buttonSet.render(guiGraphics, leftPos, topPos);
		renderForeGround(guiGraphics, mouseX, mouseY, partialTick);
		
		// for (Renderable renderable : this.renderables) {
		// 	renderable.render(guiGraphics, mouseX, mouseY, partialTick);
		// }
	}
	
	// @Override
	// public void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY){
	// 	this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
	// 	buttonSet.update(leftPos, topPos, mouseX, mouseY);
	// 	renderBackGround(guiGraphics, mouseX, mouseY, partialTick);
	// 	buttonSet.render(guiGraphics, leftPos, topPos);
	// 	renderForeGround(guiGraphics, mouseX, mouseY, partialTick);
	// 	super.render(guiGraphics, mouseX, mouseY, partialTick); // wrong
	// }
	
	// protected abstract void renderBackGround(GuiGraphics guiGraphics, int mousePosX, int mousePosY, float partialTick);
	// protected abstract void renderForeGround(GuiGraphics guiGraphics, int mousePosX, int mousePosY, float partialTick);
	
	protected void renderBackGround(GuiGraphics guiGraphics, int mousePosX, int mousePosY, float partialTick){
		// Background
		guiGraphics.blit(BOOK, leftPos, topPos, 0, 0, 256, 180);
		for(int i = 0; i < 2; i++){
			int offset = 118 * i;
			// Page Image
			if(content.get(currentPage + i).imageID() > -1){
				guiGraphics.blit(content.get(currentPage + i).imageSource(), leftPos + 8+22-8+4 + offset, leftPos + 26, (content.get(currentPage + 1).imageID() % 3) * 84, (content.get(currentPage + i).imageID() / 3) * 128, 84, 128);
			}
		}
	}
	
	protected void renderForeGround(GuiGraphics guiGraphics, int mousePosX, int mousePosY, float partialTick){
		for(int i = 0; i < 2; i++){
			int offset = 118 * i;
			// Page Header
			if(content.get(currentPage + i).pageHeader().length() > 0){
				int w = this.font.width(content.get(currentPage + i).pageHeader()) / 2;
				guiGraphics.drawString(font, content.get(currentPage + i).pageHeader(), leftPos + 68 - w + offset, topPos + 16, 10000, false);
			}
			// Page Text
			for(int k = 0; k < content.get(currentPage + i).pageBody().size(); k++){
				guiGraphics.drawString(font, content.get(currentPage + i).pageBody().get(k), leftPos + 16 + offset, topPos + 32 + 9*k, 0, false);
			}
		}
	}
	
	//   -------- -------- -------- --------     COMMAND     -------- -------- -------- --------   //
	
	private void commandPageBack(){
		currentPage -= 2;
	}
	private void commandPageForward(){
		currentPage += 2;
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
	
	private void loadPages(ItemBook item){
		for(int i = 0; i < item.getMaxPages(); i++){
			// Basic Page Info
			String header = I18n.get(item.getPage(i).pageHeader());
			List<String> text = createTextField(I18n.get(item.getPage(i).pageBody().get(0)));
			ResourceLocation imageSource = item.getPage(i).imageSource();
			int imageID = item.getPage(i).imageID();
			// Set up text body (over multiple pages if necessary)
			if(text.size() > 13){
				content.add(new ContentPage(header, text.subList(0, 13), imageSource, imageID));
				for(int line = 13; line < text.size(); line += 13){
					content.add(new ContentPage("", text.subList(line, line + 13 > text.size() ? text.size(): line + 13), imageSource, -1));
				}
			} else {
				content.add(new ContentPage(header, text, imageSource, imageID));
			}
		}
		// create empty page to not break the double-sided layout
		if(content.size() % 2 == 1){
			content.add(new ContentPage("", new ArrayList<>(), content.get(0).imageSource(), -1));
		}
		// colorize the background
		switch(item.getColorID()){
			case 0 -> BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_gray.png");
			case 1 -> BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_red.png");
			case 2 -> BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_blue.png");
			case 3 -> BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_yellow.png");
			case 4 -> BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_green.png");
			case 5 -> BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_orange.png");
			case 6 -> BOOK = ResourceLocation.fromNamespaceAndPath(Lucky77.MODID, "textures/gui/book_violet.png");
		}
	}
	
	private List<String> createTextField(String text){
		int length = 20; // <-- make this configurable
		List<String> list = new ArrayList<>();
		char[] charlist = text.toCharArray();
		if(charlist.length <= length){
			list.add(text);
		} else {
			int point_last = 0;
			while(point_last + 1 < text.length()){
				int point_next = 0;
				for(int x = 0; x < length; x++){
					if(point_last + x < charlist.length){
						// EoL breaker
						if(x + point_last + 1 == charlist.length) point_next = x+1;
						// find the next space
						if(charlist[x + point_last] == ' ') point_next = x+1;
						// listen to commands
						if(charlist[x + point_last] == '/'){
							// line break
							if(charlist[x + 1 + point_last] == 'b'){
								charlist[x + point_last] = ' ';
								charlist[x + 1 + point_last] = ' ';
								point_next = x + 2;
								break;
							}
						}
					}
				}
				list.add(new String(charlist, point_last, point_next));
				point_last += point_next;
			}
		}
		return list;
	}
	
}
