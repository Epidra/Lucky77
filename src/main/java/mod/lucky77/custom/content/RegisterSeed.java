package mod.lucky77.custom.content;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class RegisterSeed {
	
	private final Map<String, ContentCrop> map = new HashMap<>();
	private final ContentCrop ERROR = new ContentCrop("error", Blocks.WHEAT, Items.WHEAT_SEEDS, Items.WHEAT);
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public RegisterSeed(){
	
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Add new Content to the register **/
	public void addToMap(String id, Block crop, Item seed, Item fruit){
		map.put(id, new ContentCrop(id, crop, seed, fruit));
	}
	
	/** Get the dropped item of a crop by the name **/
	public Item getDropByMap(String id){
		return map.getOrDefault(id, ERROR).drop();
	}
	
	/** Get the seed of a crop by the name **/
	public Item getSeedByMap(String id){
		return map.getOrDefault(id, ERROR).seed();
	}
	
	/** Get the crop by the name **/
	public Block getCropByMap(String id){
		return map.getOrDefault(id, ERROR).crop();
	}
	
	/** Get the seed of a crop by the numerical ID (or position on the register) **/
	public Item getSeedByID(int id){
		int pos = 0;
		for(String key : map.keySet()){
			if(pos == id){
				return getSeedByMap(key);
			}
			pos++;
		}
		return getSeedByMap("");
	}
	
	/** Returns the size of the register **/
	public int size(){
		return map.size();
	}
	
	
	
}
