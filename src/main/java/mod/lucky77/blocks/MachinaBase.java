package mod.lucky77.blocks;

import mod.lucky77.tileentities.TileBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public abstract class MachinaBase extends BlockBase {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Contructor with predefined BlockProperty */
    public MachinaBase(Block block) {
        super(block);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }





    //----------------------------------------PLACEMENT----------------------------------------//

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(newState.getBlock() == Blocks.AIR){
            spawnInventory(worldIn, pos, (TileBase) worldIn.getBlockEntity(pos));
            worldIn.destroyBlock(pos,  true);
            worldIn.removeBlockEntity(pos);
        }
    }





    //----------------------------------------INTERACTION----------------------------------------//

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!world.isClientSide() && player instanceof ServerPlayerEntity) {
            interact(world, pos, player, (TileBase) world.getBlockEntity(pos));
        }
        return ActionResultType.SUCCESS;
    }





    //----------------------------------------SUPPORT----------------------------------------//

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }



}
