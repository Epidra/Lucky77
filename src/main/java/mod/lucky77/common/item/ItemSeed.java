package mod.lucky77.common.item;

import mod.lucky77.Register;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

import static mod.lucky77.Lucky77.SEEDS;

public class ItemSeed extends ItemNameBlockItem {
	
	private final String crop;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public ItemSeed(Block block, String cropID){
		super(block, new Properties());
		crop = cropID;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Returns the Block this Item is attached to **/
	@Override
	public Block getBlock(){
		return this.getBlockRaw() == null ? null : this.getBlockRaw();
	}
	
	/** Returns the correct Block from the SeedMap with the given ID **/
	private Block getBlockRaw(){
		return SEEDS.getCropByMap(crop);
	}
	
	
	
}
