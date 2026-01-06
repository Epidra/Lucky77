package mod.lucky77.common.block;

import mod.lucky77.Register;
import mod.lucky77.common.block.entity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import static mod.lucky77.Lucky77.SEEDS;

public class BlockCrop extends CropBlock {
	
	private final String seed;
	private final boolean isRepeatable;
	private final int resetToAge;
	private final int height;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public BlockCrop(Block block, String seedID, int ageOnReset, int height){
		super(Properties.ofFullCopy(block));
		this.seed = seedID;
		this.isRepeatable = ageOnReset > 0;
		this.resetToAge = ageOnReset;
		this.height = height;
	}
	
	public BlockCrop(Block block, String seedID){
		this(block, seedID, 0, 1);
		
	}
	
	
	
	
	
	//   -------- -------- -------- --------     PLACEMENT     -------- -------- -------- --------   //
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	/** ??? **/
	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult){
		if(isRepeatable && state.getValue(AGE) == 7 && level.getBlockState(pos.below(1)).is(state.getBlock())){
			player.spawnAtLocation(SEEDS.getDropByMap(seed));
			level.setBlock(pos, this.getStateForAge(resetToAge), 2);
			return InteractionResult.SUCCESS;
		}
		return super.useWithoutItem(state, level, pos, player, hitResult);
	}
	
	/** ??? **/
	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult){
		if(isRepeatable && state.getValue(AGE) == 7){
			player.spawnAtLocation(SEEDS.getDropByMap(seed));
			level.setBlock(pos, this.getStateForAge(resetToAge), 2);
			return ItemInteractionResult.SUCCESS;
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     TICK     -------- -------- -------- --------   //
	
	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
		
		if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (level.getRawBrightness(pos, 0) >= 9) {
			int currentAge = this.getAge(state);
			if (currentAge < this.getMaxAge()) {
				float growthSpeed = getGrowthSpeed(state, level, pos);
				if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
					if(height > 1 && currentAge == 6){
						if(level.getBlockState(pos.above(1)).is(Blocks.AIR) && !level.getBlockState(pos.below(1)).is(state.getBlock())){
							level.setBlock(pos.above(1), this.getStateForAge(1), 2); // create new block above
						}
					}
					level.setBlock(pos, this.getStateForAge(currentAge + 1), 2);
					net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
				}
			}
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos){
		return super.canSurvive(state, level, pos) || (level.getBlockState(pos.below(1)).is(this) && level.getBlockState(pos.below(1)).getValue(AGE) == 7);
	}
	
	@Override
	public void growCrops(Level level, BlockPos pos, BlockState state){
		if(height == 1){
			int i = this.getAge(state) + this.getBonemealAgeIncrease(level);
			int j = this.getMaxAge();
			if (i > j) {
				i = j;
			}
			
			level.setBlock(pos, this.getStateForAge(i), 2);
		} else {
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
	}
	
	@Override
	protected ItemLike getBaseSeedId(){
		return SEEDS.getSeedByMap(seed);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     BLOCKSTATE     -------- -------- -------- --------   //
	
	// ...
	
	
	
}
