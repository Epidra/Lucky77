package mod.lucky77.common.block.base;

import mod.lucky77.common.block.entity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

public abstract class MachinaHigh extends MachinaBase {
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public MachinaHigh(MapColor color, float hardness, float resistance, SoundType sound){
		super(Properties.of().mapColor(color).destroyTime(hardness).explosionResistance(resistance).sound(sound));
	}
	
	/** Constructor with Property extracted from Block **/
	public MachinaHigh(Block block){
		super(block);
	}
	
	/** Constructor with predefined Property **/
	public MachinaHigh(Properties properties) {
		super(properties);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     PLACEMENT     -------- -------- -------- --------   //
	
	/** Called by ItemBlocks after a block is set in the world, to allow post-place logic **/
	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack){
		level.setBlockAndUpdate(pos, state.setValue(FACING, entity.getMotionDirection().getOpposite()).setValue(OFFSET, true));
		if(level.isEmptyBlock(pos.above())){
			level.setBlockAndUpdate(pos.above(), state.setValue(FACING, entity.getMotionDirection().getOpposite()).setValue(OFFSET, false));
		} else {
			level.destroyBlock(pos, true);
		}
	}
	
	@Override
	public void onRemove(BlockState stateOLD, Level level, BlockPos pos, BlockState stateNEW, boolean isMoving){
		if(stateNEW.getBlock() == Blocks.AIR){
			boolean isPrimary = stateOLD.getValue(OFFSET);
			spawnInventory(level, getPositionBE(pos, isPrimary, Direction.DOWN), (BlockEntityBase) level.getBlockEntity(pos));
			if(isPrimary){
				level.destroyBlock(pos.above(), false);
			} else {
				level.destroyBlock(pos.below(), false);
			}
			level.removeBlockEntity(pos);
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	// ...
	
	// /** Get a valid BE if the correct Block is found at the pos **/
	// @Override
	// protected BlockEntityBase getBlockEntityFromPos(BlockState state, BlockGetter level, BlockPos pos, BlockEntityBase compareBE){
	// 	BlockPos offset = getTilePosition(pos, state.getValue(OFFSET), Direction.DOWN);
	// 	BlockEntity blockentity = level.getBlockEntity(offset);
	// 	return blockentity.getType() == compareBE.getType() ? (BlockEntityBase) blockentity : null;
	// }
	
	@Override
	protected BlockPos getPositionBE(BlockPos pos, boolean isPrimary, Direction facing){
		return isPrimary ? pos : pos.below();
	}
	
	
	
}
