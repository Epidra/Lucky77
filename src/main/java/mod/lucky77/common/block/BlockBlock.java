package mod.lucky77.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class BlockBlock extends Block {
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public BlockBlock(MapColor color, float hardness, float resistance, SoundType sound){
		super(Properties.of().mapColor(color).destroyTime(hardness).explosionResistance(resistance).sound(sound));
	}
	
	/** Constructor with Property extracted from Block **/
	public BlockBlock(Block block){
		super(Properties.ofFullCopy(block));
	}
	
	/** Constructor with predefined Property **/
	public BlockBlock(Properties properties) {
		super(properties);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     PLACEMENT     -------- -------- -------- --------   //
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	// ...
	
	
	
}
