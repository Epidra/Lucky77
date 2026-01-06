package mod.lucky77.common.block.base;

import mod.lucky77.common.block.entity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public abstract class MachinaCube extends Block {
	
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty OFFSET = BlockStateProperties.ATTACHED;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public MachinaCube(MapColor color, float hardness, float resistance, SoundType sound){
		super(Properties.of().mapColor(color).destroyTime(hardness).explosionResistance(resistance).sound(sound));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OFFSET, true));
	}
	
	/** Constructor with Property extracted from Block **/
	public MachinaCube(Block block){
		super(Properties.ofFullCopy(block));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OFFSET, true));
	}
	
	/** Constructor with predefined Property **/
	public MachinaCube(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OFFSET, true));
	}
	
	
	
	
	
	//   -------- -------- -------- --------     PLACEMENT     -------- -------- -------- --------   //
	
	/** ??? **/
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context){
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}
	
	/** ??? **/
	@Override
	public void onRemove(BlockState stateOLD, Level level, BlockPos pos, BlockState stateNEW, boolean isMoving){
		if(stateNEW.getBlock() == Blocks.AIR){
			spawnInventory(level, pos, (BlockEntityBase) level.getBlockEntity(pos));
			level.destroyBlock(pos, true);
			level.removeBlockEntity(pos);
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	/** ??? **/
	protected abstract void interact(Level level, BlockPos pos, Player player, BlockEntityBase blockEntity);
	
	/** ??? **/
	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult){
		if(!level.isClientSide() && player instanceof ServerPlayer){
			interact(level, pos, player, (BlockEntityBase) level.getBlockEntity(pos));
		}
		return InteractionResult.SUCCESS;
	}
	
	/** ??? **/
	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult){
		if(!level.isClientSide() && player instanceof ServerPlayer){
			interact(level, pos, player, (BlockEntityBase) level.getBlockEntity(pos));
		}
		return ItemInteractionResult.SUCCESS;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** ??? **/
	@Override
	public RenderShape getRenderShape(BlockState state){
		return RenderShape.MODEL;
	}
	
	/** ??? **/
	@Override
	protected boolean triggerEvent(BlockState state, Level level, BlockPos pos, int val1, int val2){
		super.triggerEvent(state, level, pos, val1, val2);
		BlockEntity blockEntity = level.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(val1, val2);
	}
	
	/** ??? **/
	protected void spawnInventory(Level level, BlockPos pos, BlockEntityBase blockEntity){
		if(blockEntity != null){
			Containers.dropContents(level, pos, blockEntity);
			level.updateNeighbourForOutputSignal(pos, this);
		}
	}
	
	// protected BlockPos getTilePosition(BlockPos pos, boolean isPrimary, Direction facing){
	// 	if(!isPrimary){
	// 		if(facing == Direction.UP   ) return pos.below();
	// 		if(facing == Direction.DOWN ) return pos.below();
	// 		if(facing == Direction.EAST ) return pos.east();
	// 		if(facing == Direction.WEST ) return pos.west();
	// 		if(facing == Direction.NORTH) return pos.north();
	// 		if(facing == Direction.SOUTH) return pos.south();
	// 	} return pos;
	// }
	
	// /** Get a valid BE if the correct Block is found at the pos **/
	// protected BlockEntityBase getBlockEntityFromPos(BlockState state, BlockGetter level, BlockPos pos, BlockEntityBase compareBE){
	// 	BlockEntity blockentity = level.getBlockEntity(pos);
	// 	return blockentity.getType() == compareBE.getType() ? (BlockEntityBase) blockentity : null;
	// }
	
	
	
	// not class dependent
	@Nullable
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> typeServer, BlockEntityType<E> typeClient, BlockEntityTicker<? super E> ticker){
		return typeServer == typeClient ? (BlockEntityTicker<A>) ticker : null;
	}
	
	// @Nullable
	// protected static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(Level level, BlockEntityType<T> serverType, BlockEntityType<? extends AbstractFurnaceBlockEntity> clientType) {
	// 	return level.isClientSide ? null : createTickerHelper(serverType, clientType, AbstractFurnaceBlockEntity::serverTick);
	// }
	
	
	
	
	
	//   -------- -------- -------- --------     BLOCKSTATE     -------- -------- -------- --------   //
	
	/** ??? **/
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
		builder.add(FACING, OFFSET);
	}
	
	/** ??? **/
	@Override
	public BlockState rotate(BlockState state, Rotation rot){
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	/** ??? **/
	@Override
	public BlockState mirror(BlockState state, Mirror mirror){
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}
	
	
	
}
