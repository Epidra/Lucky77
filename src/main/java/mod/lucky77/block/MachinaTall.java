package mod.lucky77.block;

import mod.lucky77.blockentity.BlockEntityBase;
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

public abstract class MachinaTall extends BlockBase {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OFFSET = BlockStateProperties.ATTACHED;




    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Contructor with predefined BlockProperty */
    public MachinaTall(Block block) {
        super(block);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OFFSET, true));
    }




    //----------------------------------------PLACEMENT----------------------------------------//

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    /** Called by ItemBlocks after a block is set in the world, to allow post-place logic */
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        worldIn.setBlockAndUpdate(pos, state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, true));
        if(worldIn.isEmptyBlock(pos.above())) {
            worldIn.setBlockAndUpdate(pos.above(), state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, false));
        } else {
            worldIn.destroyBlock(pos, true);
        }
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(newState.getBlock() == Blocks.AIR){
            boolean isPrimary = state.getValue(OFFSET);
            final BlockPos pos2 = getTilePosition(pos, isPrimary, Direction.DOWN);
            spawnInventory(worldIn, pos2, (BlockEntityBase) worldIn.getBlockEntity(pos));
            if(isPrimary) {
                worldIn.destroyBlock(pos.above(),  false);
            } else {
                worldIn.destroyBlock(pos.below(),  false);
            }
            worldIn.removeBlockEntity(pos);
        }
    }




    //----------------------------------------INTERACTION----------------------------------------//

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!world.isClientSide() && player instanceof ServerPlayer){
            interact(world, getTilePosition(pos, state.getValue(OFFSET), Direction.DOWN), player, (BlockEntityBase) world.getBlockEntity(getTilePosition(pos, state.getValue(OFFSET), Direction.DOWN)));
        }
        return InteractionResult.SUCCESS;
    }




    //----------------------------------------SUPPORT----------------------------------------//

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OFFSET);
    }

    @Deprecated
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        boolean isPrimary = state.getValue(OFFSET);
        if(isPrimary) {
            Block block = worldIn.getBlockState(pos.above()).getBlock();
            return block == Blocks.AIR || block == Blocks.CAVE_AIR || block == Blocks.VOID_AIR;
        }
        return true;
    }




    //----------------------------------------GETTER/SETTER----------------------------------------//

    //@Override
    //public boolean hasTileEntity(BlockState state) {
    //    return state.getValue(OFFSET);
    //}

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

}
