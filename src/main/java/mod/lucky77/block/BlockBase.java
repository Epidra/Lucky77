package mod.lucky77.block;

import mod.lucky77.blockentity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
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

    public boolean triggerEvent(BlockState p_49226_, Level p_49227_, BlockPos p_49228_, int p_49229_, int p_49230_) {
        super.triggerEvent(p_49226_, p_49227_, p_49228_, p_49229_, p_49230_);
        BlockEntity blockentity = p_49227_.getBlockEntity(p_49228_);
        return blockentity == null ? false : blockentity.triggerEvent(p_49229_, p_49230_);
    }

    //@Nullable
    //public MenuProvider getMenuProvider(BlockState p_49234_, Level p_49235_, BlockPos p_49236_) {
    //    BlockEntity blockentity = p_49235_.getBlockEntity(p_49236_);
    //    return blockentity instanceof MenuProvider ? (MenuProvider)blockentity : null;
    //}

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }




    //----------------------------------------GETTER/SETTER----------------------------------------//

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
