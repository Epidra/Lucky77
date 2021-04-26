package mod.lucky77.blocks;

import mod.lucky77.tileentities.TileBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    public abstract void interact(World world, BlockPos pos, PlayerEntity player, TileBase tile);




    //----------------------------------------SUPPORT----------------------------------------//

    protected void spawnInventory(World world, BlockPos pos, TileBase tile){
        if(tile != null){
            InventoryHelper.dropContents(world, pos, tile);
            world.updateNeighbourForOutputSignal(pos, this);
        }
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
