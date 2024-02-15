package mod.lucky77.block;

import mod.lucky77.register.RegisterSeed;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("unused")
public class BlockCropSingle extends CropBlock {
	
	private final String seed;
	private final boolean isRepeatable;
	private final int resetToAge;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public BlockCropSingle(Block block, String seedID){
		super(Properties.copy(block));
		seed = seedID;
		isRepeatable = false;
		resetToAge = 0;
	}
	
	public BlockCropSingle(Block block, String seedID, int ageOnReset){
		super(Properties.copy(block));
		seed = seedID;
		isRepeatable = true;
		resetToAge = ageOnReset;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  PLACEMENT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(isRepeatable && state.getValue(AGE) == 7){
			player.spawnAtLocation(RegisterSeed.GetDropByMap(seed));
			level.setBlock(pos, this.getStateForAge(resetToAge), 2);
			return InteractionResult.SUCCESS;
		} return super.use(state, level, pos, player, hand, result);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  TICK  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	protected ItemLike getBaseSeedId() {
		return RegisterSeed.GetSeedByMap(seed);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  BLOCKSTATE  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
}
