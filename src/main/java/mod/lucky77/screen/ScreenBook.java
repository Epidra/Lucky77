package mod.lucky77.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.lucky77.Lucky77;
import mod.lucky77.item.ItemBook;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ScreenBook extends Screen {

    private ResourceLocation BOOK = new ResourceLocation(Lucky77.MODID, "textures/gui/book_gray.png");
    ItemBook item;
    int currentPage;

    List<String>[] text = new List[]{new ArrayList(), new ArrayList()};
    String[] header = new String[]{"", ""};
    ResourceLocation[] image = new ResourceLocation[]{new ResourceLocation(Lucky77.MODID, "textures/gui/book_gray.png"), new ResourceLocation(Lucky77.MODID, "textures/gui/book_gray.png")};
    int[] imageID = new int[]{0, 0};





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public ScreenBook(ItemBook item) {
        super(NarratorChatListener.NO_TITLE);
        this.item = item;
        loadPages();
    }





    //----------------------------------------INPUT----------------------------------------//

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
        if(mouseButton == 0){
            int x = (this.width  - 256) / 2;
            int y = (this.height - 180) / 2;
            if(mouseRect(x +  32, y + 154, 18, 10, mouseX, mouseY)){ commandPageBack();    }
            if(mouseRect(x + 206, y + 154, 18, 10, mouseX, mouseY)){ commandPageForward(); }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }





    //----------------------------------------DRAW----------------------------------------//

    public void render(PoseStack matrixStack, int mousePosX, int mousePosY, float partialTick) {
        this.renderBackground(matrixStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BOOK);
        int x = (this.width  - 256) / 2;
        int y = (this.height - 180) / 2;
        this.blit(matrixStack, x, y, 0, 0, 256, 180); // Background

        for(int i = 0; i < 2; i++){
            int offset = 120 * i;
            if(header[i].length() > 0){
                int w = this.font.width(header[i]) / 2;
                this.font.draw(matrixStack, header[i], x + 68 - w + offset, y + 16, 555555);
            }

            if(imageID[i] > -1){
                RenderSystem.setShaderTexture(0, image[i]);
                this.blit(matrixStack, x + 8 + offset, y + 26, (imageID[i] % 2) * 128, (imageID[i] / 2) * 128, 128, 128); // Background
            }

            for(int k = 0; k < text[i].size(); k++){
                this.font.draw(matrixStack, text[i].get(k),  x + 16 + offset, y + 32 + 9*k, 0);
            }
        }

        RenderSystem.setShaderTexture(0, BOOK);

        if(currentPage > 0){
            if(mouseRect(x + 32, y + 154, 18, 10, mousePosX, mousePosY)){
                this.blit(matrixStack, x + 32, y + 154, 238, 246, 18, 10); // Left Arrow Highlight
            } else {
                this.blit(matrixStack, x + 32, y + 154, 215, 246, 18, 10); // Left Arrow
            }
        }

        if(currentPage + 2 < item.getMaxPages()){
            if(mouseRect(x + 206, y + 154, 18, 10, mousePosX, mousePosY)){
                this.blit(matrixStack, x + 206, y + 154, 238, 233, 18, 10); // Right Arrow Highlight
            } else {
                this.blit(matrixStack, x + 206, y + 154, 215, 233, 18, 10); // Right Arrow
            }
        }

        super.render(matrixStack, mousePosX, mousePosY, partialTick);
    }





    //----------------------------------------COMMAND----------------------------------------//

    private void commandPageBack(){
        if(currentPage > 0){
            currentPage -= 2;
            loadPages();
        }
    }

    private void commandPageForward(){
        if(currentPage + 2 < item.getMaxPages()){
            currentPage += 2;
            loadPages();
        }
    }





    //----------------------------------------SUPPORT----------------------------------------//

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
                text[i]    = createTextField(I18n.get(item.getPage(currentPage + i).pageBody), 20);
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
            while(point_last + length < text.length()) {
                if(charlist.length > length) {
                    int point_next = 0;
                    for(int x = 0; x < length; x++) {
                        if(charlist[x + point_last] == ' ') point_next = x;
                        if(charlist[x + point_last] == '/') {
                            if(charlist[x + 1 + point_last] == 'b') {
                                charlist[x + point_last] = ' ';
                                charlist[x + 1 + point_last] = ' ';
                                point_next = x + 2;
                                break;
                            }
                        }
                    }
                    list.add(new String(charlist, point_last, point_next));
                    point_last += point_next;
                }
            }
            list.add(text.substring(point_last));
        }
        return list;
    }



}
