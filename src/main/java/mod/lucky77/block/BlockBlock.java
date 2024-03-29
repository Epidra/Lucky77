package mod.lucky77.block;

import mod.lucky77.block.base.BlockBase;
import mod.lucky77.block.entity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("unused")
public class BlockBlock extends BlockBase {
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	/** Default Contructor */
	public BlockBlock(MapColor color, float hardness, float resistance, SoundType sound) {
		super(Properties.of().mapColor(color).strength(hardness, resistance).sound(sound));
	}
	
	/** Contructor with Property extracted from Block */
	public BlockBlock(Block block) {
		super(Properties.copy(block));
	}
	
	/** Contructor with predefined BlockProperty */
	public BlockBlock(Properties properties) {
		super(properties);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  PLACEMENT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	@Override
	public void interact(Level world, BlockPos pos, Player player, BlockEntityBase tile) {
	
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
}
