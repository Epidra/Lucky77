package mod.lucky77.screen;

import com.mojang.blaze3d.platform.InputConstants;
import mod.lucky77.Lucky77;
import mod.lucky77.item.ItemBook;
import mod.lucky77.util.button.ButtonSet;
import mod.lucky77.util.Vector2;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class ScreenBook extends Screen {
	
	private ResourceLocation BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_gray.png");
	ItemBook item;
	int currentPage;
	
	List<String>[] text = new List[]{new ArrayList(), new ArrayList()};
	String[] header = new String[]{"", ""};
	ResourceLocation[] image = new ResourceLocation[]{new ResourceLocation(Lucky77.MODID, "textures/gui/book_gray.png"), new ResourceLocation(Lucky77.MODID, "textures/gui/book_gray.png")};
	int[] imageID = new int[]{0, 0};
	
	protected ButtonSet buttonSet = new ButtonSet();
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public ScreenBook(ItemBook item) {
		super(Component.empty());
		this.item = item;
		loadPages();
		createButtons();
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CREATE  ---------- ---------- ---------- ---------- //
	
	private void createButtons(){
		buttonSet.addButton(0, new Vector2( 32-2, 154+2), new Vector2(215, 246), new Vector2(215, 246), new Vector2(238, 246), new Vector2(18, 10), -1, () -> this.currentPage     >                  0, () -> this.commandPageBack());
		buttonSet.addButton(1, new Vector2(206+2, 154+2), new Vector2(215, 233), new Vector2(215, 233), new Vector2(238, 233), new Vector2(18, 10), -1, () -> this.currentPage + 2 < item.getMaxPages(), () -> this.commandPageForward());
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INPUT  ---------- ---------- ---------- ---------- //
	
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (super.keyPressed(keyCode, scanCode, modifiers)) {
			return true;
		} else if (this.minecraft.options.keyInventory.isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
			this.onClose();
			return true;
		} else {
			switch(keyCode) {
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
	
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton){
		int x = (this.width  - 256) / 2;
		int y = (this.height - 180) / 2;
		buttonSet.interact(x, y, mouseX, mouseY);
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  RENDER  ---------- ---------- ---------- ---------- //
	
	public void render(GuiGraphics matrix, int mousePosX, int mousePosY, float partialTick) {
		this.renderBackground(matrix);
		int x = (this.width  - 256) / 2;
		int y = (this.height - 180) / 2;
		matrix.blit(BOOK, x, y, 0, 0, 256, 180); // Background
		buttonSet.update(x, y, mousePosX, mousePosY);
		for(int i = 0; i < 2; i++){
			int offset = 118 * i;
			if(header[i].length() > 0){
				int w = this.font.width(header[i]) / 2;
				matrix.drawString(font, header[i], x + 68 - w + offset, y + 16, /*16777215*/ 100000, false);
			}
			if(imageID[i] > -1){
				matrix.blit(image[i], x + 8+22-8+4 + offset, y + 26, (imageID[i] % 3) * 84, (imageID[i] / 2) * 128, 84, 128); // Background
			}
			for(int k = 0; k < text[i].size(); k++){
				matrix.drawString(font, text[i].get(k),  x + 16 + offset, y + 32 + 9*k, 0, false);
			}
		}
		while (buttonSet.next()){
			if(buttonSet.isVisible()    ){ matrix.blit(BOOK, x + buttonSet.pos().X, y + buttonSet.pos().Y, buttonSet.map().X,       buttonSet.map().Y,       buttonSet.sizeX(), buttonSet.sizeY()); }
			if(buttonSet.isHighlighted()){ matrix.blit(BOOK, x + buttonSet.pos().X, y + buttonSet.pos().Y, buttonSet.highlight().X, buttonSet.highlight().Y, buttonSet.sizeX(), buttonSet.sizeY()); }
		}
		super.render(matrix, mousePosX, mousePosY, partialTick);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  COMMAND  ---------- ---------- ---------- ---------- //
	
	private void commandPageBack(){
		currentPage -= 2;
		loadPages();
	}
	
	private void commandPageForward(){
		currentPage += 2;
		loadPages();
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public boolean isPauseScreen() {
		return false;
	}
	
	public void init() {
		super.init();
		switch (item.getColorID()) {
			case 0 -> BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_gray.png");
			case 1 -> BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_red.png");
			case 2 -> BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_blue.png");
			case 3 -> BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_yellow.png");
			case 4 -> BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_green.png");
			case 5 -> BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_orange.png");
			case 6 -> BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_violet.png");
		}
	}
	
	/** Checks if mouse is inside a rectangle **/
	protected boolean mouseRect(int x, int y, int width, int height, double mouseX, double mouseY){
		if(x < mouseX && mouseX < x + width){
			return y < mouseY && mouseY < y + height;
		}
		return false;
	}
	
	private void loadPages(){
		for(int i = 0; i < 2; i++) {
			if (currentPage + i < item.getMaxPages()) {
				header[i]  = "" + I18n.get(item.getPage(currentPage + i).pageHeader);
				text[i]    = createTextField(I18n.get(item.getPage(currentPage + i).pageBody), 21);
				image[i]   = item.getPage(currentPage + i).imageSource;
				imageID[i] = item.getPage(currentPage + i).imageID;
			} else {
				header[i] = "";
				text[i]   = new ArrayList<>();
				text[i].add("");
				imageID[i] = -1;
			}
		}
	}
	
	private List<String> createTextField(String text, int length) {
		List<String> list = new ArrayList<>();
		char[] charlist = text.toCharArray();
		if(charlist.length <= length) {
			list.add(text);
		} else {
			int point_last = 0;
			while(point_last + 1 /*+ length*/ < text.length()) {
				int point_next = 0;
				for(int x = 0; x < length; x++) {
					if(point_last + x < charlist.length){
						if(x + point_last + 1 == charlist.length) point_next = x+1;
						if(charlist[x + point_last] == ' ') point_next = x+1;
						if(charlist[x + point_last] == '/') {
							if(charlist[x + 1 + point_last] == 'b') {
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
