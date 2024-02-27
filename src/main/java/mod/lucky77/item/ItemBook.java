package mod.lucky77.item;

import mod.lucky77.util.content.ContentPage;
import mod.lucky77.util.system.SystemPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ItemBook extends Item {
	
	int colorID = 0;
	List<ContentPage> content = new ArrayList<>();
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	/** Default Constructor */
	public ItemBook(int colorID){
		super(new Properties());
		this.colorID = colorID;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if(level.isClientSide()){
			SystemPlayer.openBookScreen(this);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public void addPage(String pageHeader, String pageBody, String imageSource, int imageID, String modID){
		content.add(new ContentPage(pageHeader, pageBody, imageSource, imageID, modID));
	}
	
	public void addPage(String pageHeader, String pageBody){
		content.add(new ContentPage(pageHeader, pageBody, "", -1, ""));
	}
	
	public void addPage(String pageHeader, String imageSource, int imageID, String modID){
		content.add(new ContentPage(pageHeader, "", imageSource, imageID, modID));
	}
	
	public int getColorID(){
		return colorID;
	}
	
	public int getMaxPages(){
		return content.size();
	}
	
	public ContentPage getPage(int pageID){
		return content.get(pageID >= content.size() ? 0 : pageID);
	}
	
	
	
}
