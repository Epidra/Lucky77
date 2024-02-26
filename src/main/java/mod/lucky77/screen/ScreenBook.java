package mod.lucky77.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.lucky77.Lucky77;
import mod.lucky77.item.ItemBook;
import mod.lucky77.util.Vector2;
import mod.lucky77.util.button.ButtonSet;
import mod.lucky77.util.content.ContentPage;
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
    
    private final List<ContentPage> content   = new ArrayList<>();
    private final      ButtonSet    buttonSet = new ButtonSet();
    
    private int currentPage;
    
    
    
    
    
    // ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
    
    public ScreenBook(ItemBook item) {
        super(Component.empty());
        loadPages(item);
        createButtons();
    }
    
    
    
    
    
    // ---------- ---------- ---------- ----------  CREATE  ---------- ---------- ---------- ---------- //
    
    private void createButtons(){
        buttonSet.addButton(0, new Vector2( 32-2, 154+2), new Vector2(215, 246), new Vector2(215, 246), new Vector2(238, 246), new Vector2(18, 10), -1, () -> this.currentPage     >              0, () -> this.commandPageBack());
        buttonSet.addButton(1, new Vector2(206+2, 154+2), new Vector2(215, 233), new Vector2(215, 233), new Vector2(238, 233), new Vector2(18, 10), -1, () -> this.currentPage + 2 < content.size(), () -> this.commandPageForward());
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
    
    public void render(PoseStack matrix, int mousePosX, int mousePosY, float partialTick) {
        this.renderBackground(matrix);
        int x = (this.width  - 256) / 2;
        int y = (this.height - 180) / 2;
        RenderSystem.setShaderTexture(0, BOOK);
        blit(matrix, x, y, 0, 0, 256, 180); // Background
        buttonSet.update(x, y, mousePosX, mousePosY);
        for(int i = 0; i < 2; i++){
            int offset = 118 * i;
            if(content.get(currentPage + i).pageHeader.length() > 0){
                int w = this.font.width(content.get(currentPage + i).pageHeader) / 2;
                font.draw(matrix, content.get(currentPage + i).pageHeader, x + 68 - w + offset, y + 16, /*16777215*/ 100000);
            }
            if(content.get(currentPage + i).imageID > -1){
                RenderSystem.setShaderTexture(0, content.get(currentPage + i).imageSource);
                blit(matrix, x + 8+22-8+4 + offset, y + 26, (content.get(currentPage + i).imageID % 3) * 84, (content.get(currentPage + i).imageID / 3) * 128, 84, 128); // Background
            }
            for(int k = 0; k < content.get(currentPage + i).pageBody.size(); k++){
                font.draw(matrix, content.get(currentPage + i).pageBody.get(k),  x + 16 + offset, y + 32 + 9*k, 0);
            }
        }
        RenderSystem.setShaderTexture(0, BOOK);
        while (buttonSet.next()){
            if(buttonSet.isVisible()    ){ blit(matrix, x + buttonSet.pos().X, y + buttonSet.pos().Y, buttonSet.map().X,       buttonSet.map().Y,       buttonSet.sizeX(), buttonSet.sizeY()); }
            if(buttonSet.isHighlighted()){ blit(matrix, x + buttonSet.pos().X, y + buttonSet.pos().Y, buttonSet.highlight().X, buttonSet.highlight().Y, buttonSet.sizeX(), buttonSet.sizeY()); }
        }
        super.render(matrix, mousePosX, mousePosY, partialTick);
    }
    
    
    
    
    
    // ---------- ---------- ---------- ----------  COMMAND  ---------- ---------- ---------- ---------- //
    
    private void commandPageBack(   ){ currentPage -= 2; }
    private void commandPageForward(){ currentPage += 2; }
    
    
    
    
    
    // ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
    
    public boolean isPauseScreen() {
        return false;
    }
    
    public void init() {
        super.init();
    }
    
    /** Checks if mouse is inside a rectangle **/
    protected boolean mouseRect(int x, int y, int width, int height, double mouseX, double mouseY){
        if(x < mouseX && mouseX < x + width){
            return y < mouseY && mouseY < y + height;
        }
        return false;
    }
    
    private void loadPages(ItemBook item){
        for(int i = 0; i < item.getMaxPages(); i++){
            String header = I18n.get(item.getPage(i).pageHeader);
            List<String> text = createTextField(I18n.get(item.getPage(i).pageBody.get(0)));
            ResourceLocation imageSource = item.getPage(i).imageSource;
            int imageID = item.getPage(i).imageID;
            if(text.size() > 13){
                content.add(new ContentPage(header, text.subList(0, 13), imageSource, imageID));
                for(int line = 13; line < text.size(); line += 13){
                    content.add(new ContentPage("", text.subList(line, line + 13 > text.size() ? text.size() : line + 13), imageSource, -1));
                }
            } else {
                content.add(new ContentPage(header, text, imageSource, imageID));
            }
        }
        if(content.size() % 2 == 1){
            content.add(new ContentPage("", "", "", -1, ""));
        }
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
    
    private List<String> createTextField(String text) {
        int length = 20;
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
