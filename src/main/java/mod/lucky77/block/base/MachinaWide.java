package mod.lucky77.block.base;

import mod.lucky77.block.entity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public abstract class MachinaWide extends BlockBase {
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty   OFFSET = BlockStateProperties.ATTACHED;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	/** Contructor with predefined BlockProperty */
	public MachinaWide(Block block) {
		super(block);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OFFSET, true));
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  PLACEMENT  ---------- ---------- ---------- ---------- //
	
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	
	/** Called by ItemBlocks after a block is set in the world, to allow post-place logic */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		worldIn.setBlock(pos, state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, true), 2);
		if(placer.getMotionDirection().getOpposite() == Direction.NORTH) {
			if(worldIn.isEmptyBlock(pos.west())) {
				worldIn.setBlock(pos.west(), state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, false), 2);
			} else {
				worldIn.destroyBlock(pos, true);
			}
		}
		if(placer.getMotionDirection().getOpposite() == Direction.SOUTH) {
			if(worldIn.isEmptyBlock(pos.east())) {
				worldIn.setBlock(pos.east(), state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, false), 2);
			} else {
				worldIn.destroyBlock(pos, true);
			}
		}
		if(placer.getMotionDirection().getOpposite() == Direction.WEST ) {
			if(worldIn.isEmptyBlock(pos.south())) {
				worldIn.setBlock(pos.south(),  state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, false), 2);
			} else {
				worldIn.destroyBlock(pos, true);
			}
		}
		if(placer.getMotionDirection().getOpposite() == Direction.EAST ) {
			if(worldIn.isEmptyBlock(pos.north())) {
				worldIn.setBlock(pos.north(),  state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, false), 2);
			} else {
				worldIn.destroyBlock(pos, true);
			}
		}
	}
	
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if(newState.getBlock() == Blocks.AIR){
			boolean isPrimary = state.getValue(OFFSET);
			Direction enumfacing = state.getValue(FACING);
			final BlockPos pos2 = getTilePosition(pos, isPrimary, Direction.DOWN);
			spawnInventory(worldIn, pos2, (BlockEntityBase) worldIn.getBlockEntity(pos));
			if(!isPrimary) enumfacing = enumfacing.getOpposite();
			if(enumfacing == Direction.NORTH) worldIn.destroyBlock(pos.west(),  false);
			if(enumfacing == Direction.SOUTH) worldIn.destroyBlock(pos.east(),  false);
			if(enumfacing == Direction.EAST ) worldIn.destroyBlock(pos.north(), false);
			if(enumfacing == Direction.WEST ) worldIn.destroyBlock(pos.south(), false);
			worldIn.removeBlockEntity(pos);
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!world.isClientSide() && player instanceof ServerPlayer) {
			interact(world, getTilePosition(pos, state.getValue(OFFSET), state.getValue(FACING)), player, (BlockEntityBase) world.getBlockEntity(getTilePosition(pos, state.getValue(OFFSET), state.getValue(FACING))));
		}
		return InteractionResult.SUCCESS;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	@Deprecated
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		boolean isPrimary = state.getValue(OFFSET);
		Direction enumfacing = state.getValue(FACING);
		if(!isPrimary) return true;
		Block block = world.getBlockState(pos).getBlock();
		if(enumfacing == Direction.NORTH){ block = world.getBlockState(pos.west() ).getBlock(); }
		if(enumfacing == Direction.SOUTH){ block = world.getBlockState(pos.east() ).getBlock(); }
		if(enumfacing == Direction.EAST ){ block = world.getBlockState(pos.north()).getBlock(); }
		if(enumfacing == Direction.WEST ){ block = world.getBlockState(pos.south()).getBlock(); }
		return block == Blocks.AIR || block == Blocks.CAVE_AIR || block == Blocks.VOID_AIR;
	}
	
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  BLOCKSTATE  ---------- ---------- ---------- ---------- //
	
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, OFFSET);
	}
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
	
	
	
}
