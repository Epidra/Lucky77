package mod.lucky77.register;

import mod.lucky77.util.content.ContentCrop;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class RegisterSeed {
	
	private static final Map<String, ContentCrop> map = new HashMap<>();
	private static final ContentCrop error = new ContentCrop("error", Blocks.WHEAT, Items.WHEAT_SEEDS, Items.WHEAT);
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public static void AddToMap(String id, Block crop, Item seed, Item fruit){
		map.put(id, new ContentCrop(id, crop, seed, fruit));
	}
	
	public static Item GetDropByMap(String id){
		return map.getOrDefault(id, error).getDrop();
	}
	
	public static Item GetSeedByMap(String id){
		return map.getOrDefault(id, error).getSeed();
	}
	
	public static Block GetCropByMap(String id){
		return map.getOrDefault(id, error).getCrop();
	}
	
	public static Item GetSeedByID(int id){
		int pos = 0;
		for(String key : map.keySet()){
			if(pos == id){
				return GetSeedByMap(key);
			} pos++;
		} return GetSeedByMap("");
	}
	
	public static int size(){
		return map.size();
	}
	
	
	
}