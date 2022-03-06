package mod.lucky77.item;

import mod.lucky77.screen.ScreenBook;
import mod.lucky77.util.PageContent;
import net.minecraft.client.Minecraft;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ItemBook extends Item {

    int colorID = 0;
    List<PageContent> content = new ArrayList<>();





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Constructor */
    public ItemBook(CreativeModeTab group, int colorID){
        super(new Properties().tab(group));
        this.colorID = colorID;
    }





    //----------------------------------------INTERACTION----------------------------------------//

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(level.isClientSide()){
            Minecraft.getInstance().setScreen(new ScreenBook(this));
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }





    //----------------------------------------SUPPORT----------------------------------------//

    public void addPage(String pageHeader, String pageBody, String imageSource, int imageID, String modID){
        content.add(new PageContent(pageHeader, pageBody, imageSource, imageID, modID));
    }

    public int getColorID(){
        return colorID;
    }

    public List<PageContent> getAllPages(){
        return content;
    }

    public int getMaxPages(){
        return content.size();
    }

    public PageContent getPage(int pageID){
        return content.get(pageID >= content.size() ? 0 : pageID);
    }



}
