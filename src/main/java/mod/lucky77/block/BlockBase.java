package mod.lucky77.block;

import mod.lucky77.blockentity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import javax.annotation.Nullable;

public abstract class BlockBase extends Block {

    // ...





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Contructor */
    public BlockBase(Material material, MaterialColor materialcolor, float hardness, float resistance, SoundType sound, int light) {
        super(Properties.of(material, materialcolor).strength(hardness, resistance).sound(sound));
    }

    /** Contructor with with Property extracted from Block */
    public BlockBase(Block block) {
        super(Properties.copy(block));
    }

    /** Contructor with predefined BlockProperty */
    public BlockBase(Properties properties) {
        super(properties);
    }





    //----------------------------------------PLACEMENT----------------------------------------//

    // ...





    //----------------------------------------INTERACTION----------------------------------------//

    public abstract void interact(Level world, BlockPos pos, Player player, BlockEntityBase tile);





    //----------------------------------------SUPPORT----------------------------------------//

    protected void spawnInventory(Level world, BlockPos pos, BlockEntityBase tile){
        if(tile != null){
            Containers.dropContents(world, pos, tile);
            world.updateNeighbourForOutputSignal(pos, this);
        }
    }

    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int val1, int val2) {
        super.triggerEvent(state, level, pos, val1, val2);
        BlockEntity blockentity = level.getBlockEntity(pos);
        return blockentity != null && blockentity.triggerEvent(val1, val2);
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> entityTypeA, BlockEntityType<E> entityTypeE, BlockEntityTicker<? super E> ticker) {
        return entityTypeE == entityTypeA ? (BlockEntityTicker<A>)ticker : null;
    }

    protected BlockPos getTilePosition(BlockPos pos, boolean isPrimary, Direction facing){
        if(facing == Direction.DOWN || facing == Direction.UP){
            return isPrimary ? pos : pos.below();
        } else {
            if(!isPrimary) facing = facing.getOpposite();
            BlockPos pos2 = pos;
            if(!isPrimary){
                if(facing == Direction.EAST)  pos2 = pos.north();
                if(facing == Direction.WEST)  pos2 = pos.south();
                if(facing == Direction.NORTH) pos2 = pos.west();
                if(facing == Direction.SOUTH) pos2 = pos.east();
            }
            return pos2;
        }
    }



}
