package mod.lucky77.block;

import mod.lucky77.register.RegisterSeed;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.IPlantable;

@SuppressWarnings("unused")
public class BlockCropDouble extends CropBlock {
	
	private final String seed;
	private final boolean isRepeatable;
	private final int resetToAge;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public BlockCropDouble(Block block, String seedID){
		super(Properties.copy(block));
		seed = seedID;
		isRepeatable = false;
		resetToAge = 0;
	}
	
	public BlockCropDouble(Block block, String seedID, int ageOnReset){
		super(Properties.copy(block));
		seed = seedID;
		isRepeatable = true;
		resetToAge = ageOnReset;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  PLACEMENT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(isRepeatable && state.getValue(AGE) == 7 && level.getBlockState(pos.below(1)).is(state.getBlock())){
			player.spawnAtLocation(RegisterSeed.GetDropByMap(seed));
			level.setBlock(pos, this.getStateForAge(resetToAge), 2);
			return InteractionResult.SUCCESS;
		} return super.use(state, level, pos, player, hand, result);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  TICK  ---------- ---------- ---------- ---------- //
	
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
		if(!level.isAreaLoaded(pos, 1)) return;
		if(level.getRawBrightness(pos, 0) >= 9){
			int currentAge = this.getAge(state);
			if(currentAge < this.getMaxAge()){
				float growthSpeed = getGrowthSpeed(this, level, pos);
				if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((int)(25.0f / growthSpeed) + 1) == 0)){
					if(currentAge == 6){
						if(level.getBlockState(pos.above(1)).is(Blocks.AIR) && !level.getBlockState(pos.below(1)).is(state.getBlock())){
							level.setBlock(pos.above(1), this.getStateForAge(1), 2);
						}
					}
					level.setBlock(pos, this.getStateForAge(currentAge + 1), 2);
					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
				}
			}
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter getter, BlockPos pos, Direction facing, IPlantable plantable){
		return super.mayPlaceOn(state, getter, pos);
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos){
		return super.canSurvive(state, level, pos) || (level.getBlockState(pos.below(1)).is(this) && level.getBlockState(pos.below(1)).getValue(AGE) == 7);
	}
	
	@Override
	public void growCrops(Level level, BlockPos pos, BlockState state){
		int nextAge = this.getAge(state) + this.getBonemealAgeIncrease(level);
		int maxAge = this.getMaxAge();
		if(nextAge > maxAge){
			nextAge = maxAge;
		}
		if(this.getAge(state) == 7 && level.getBlockState(pos.above(1)).is(Blocks.AIR)){
			level.setBlock(pos.above(1), this.getStateForAge(nextAge), 2);
		} else {
			level.setBlock(pos, this.getStateForAge(nextAge - 1), 2);
		}
	}
	
	@Override
	protected ItemLike getBaseSeedId(){
		return RegisterSeed.GetSeedByMap(seed);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  BLOCKSTATE  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
}