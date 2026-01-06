package mod.lucky77.common.item;

import mod.lucky77.custom.content.ContentPage;
import mod.lucky77.custom.other.SupportPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WritableBookItem;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ItemBook extends Item {
	
	private final int colorID;
	private final List<ContentPage> content = new ArrayList<>();
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public ItemBook(int colorID){
		super(new Properties());
		this.colorID = colorID;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	/** Called to trigger the item's "innate" right click behaviour **/
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand){
		ItemStack stack = player.getItemInHand(hand);
		if(level.isClientSide()){
			SupportPlayer.openBookScreen(this);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	public void addPage(String pageHeader, List<String> pageBody, String imageSource, int imageID, String modID){
		content.add(new ContentPage(pageHeader, pageBody, ResourceLocation.fromNamespaceAndPath(modID, imageSource), imageID));
	}
	
	public void addPage(String pageHeader, String pageBody, String imageSource, int imageID, String modID){
		List<String> list = new ArrayList<>(); list.add(pageBody);
		addPage(pageHeader, list, imageSource, imageID, modID);
	}
	
	public void addPage(String pageHeader, String pageBody){
		addPage(pageHeader, pageBody, "", -1, "");
	}
	
	public void addPage(String pageHeader, String imageSource, int imageID, String modID){
		addPage(pageHeader, "", imageSource, imageID, modID);
	}
	
	public int getColorID(){
		return colorID;
	}
	
	public int getMaxPages(){
		return content.size();
	}
	
	public ContentPage getPage(int pageID){
		return content.get(pageID % getMaxPages());
	}
	
	
	
}
