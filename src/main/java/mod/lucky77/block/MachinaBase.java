package mod.lucky77.block;

import mod.lucky77.blockentity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class MachinaBase extends BlockBase {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;




    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Contructor with predefined BlockProperty */
    public MachinaBase(Block block) {
        super(block);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }




    //----------------------------------------PLACEMENT----------------------------------------//

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(newState.getBlock() == Blocks.AIR){
            spawnInventory(worldIn, pos, (BlockEntityBase) worldIn.getBlockEntity(pos));
            worldIn.destroyBlock(pos,  true);
            worldIn.removeBlockEntity(pos);
        }
    }




    //----------------------------------------INTERACTION----------------------------------------//

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!world.isClientSide() && player instanceof ServerPlayer) {
            interact(world, pos, player, (BlockEntityBase) world.getBlockEntity(pos));
        }
        return InteractionResult.SUCCESS;
    }




    //----------------------------------------SUPPORT----------------------------------------//

    //@Override
    public boolean hasBlockEntity() {
        return true;
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }




    //----------------------------------------GETTER/SETTER----------------------------------------//

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

}
