package mod.lucky77.blocks;

import mod.lucky77.tileentities.TileBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

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

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    /** Called by ItemBlocks after a block is set in the world, to allow post-place logic */
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        worldIn.setBlockAndUpdate(pos, state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, true));
        if(worldIn.isEmptyBlock(pos.above())) {
            worldIn.setBlockAndUpdate(pos.above(), state.setValue(FACING, placer.getMotionDirection().getOpposite()).setValue(OFFSET, false));
        } else {
            worldIn.destroyBlock(pos, true);
        }
    }

    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(newState.getBlock() == Blocks.AIR){
            boolean isPrimary = state.getValue(OFFSET);
            final BlockPos pos2 = getTilePosition(pos, isPrimary, Direction.DOWN);
            spawnInventory(worldIn, pos2, (TileBase) worldIn.getBlockEntity(pos));
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
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!world.isClientSide() && player instanceof ServerPlayerEntity){
            interact(world, getTilePosition(pos, state.getValue(OFFSET), Direction.DOWN), player, (TileBase) world.getBlockEntity(getTilePosition(pos, state.getValue(OFFSET), Direction.DOWN)));
        }
        return ActionResultType.SUCCESS;
    }





    //----------------------------------------SUPPORT----------------------------------------//

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, OFFSET);
    }

    @Deprecated
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        boolean isPrimary = state.getValue(OFFSET);
        if(isPrimary) {
            Block block = worldIn.getBlockState(pos.above()).getBlock();
            return block == Blocks.AIR || block == Blocks.CAVE_AIR || block == Blocks.VOID_AIR;
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.getValue(OFFSET);
    }

    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }



}
