package mod.lucky77.util.content;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ContentCrop {
	
	private final String id;
	private final Block crop;
	private final Item  seed;
	private final Item  drop;
	
	public ContentCrop(String _id, Block _crop, Item _seed, Item _drop){
		id = _id;
		crop = _crop;
		seed = _seed;
		drop = _drop;
	}
	
	public Block getCrop(){
		return crop;
	}
	
	public Item getSeed(){
		return seed;
	}
	
	public Item getDrop(){
		return drop;
	}
	
}
