package mod.lucky77.blocks;

import mod.lucky77.tileentities.TileBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBlock extends BlockBase {

    // ...





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Contructor */
    public BlockBlock(Material material, MaterialColor materialcolor, float hardness, float resistance, SoundType sound, int light) {
        super(material, materialcolor, hardness, resistance, sound, light);
    }

    /** Contructor with predefined BlockProperty */
    public BlockBlock(Block block) {
        super(block);
    }





    //----------------------------------------PLACEMENT----------------------------------------//

    // ...





    //----------------------------------------INTERACTION----------------------------------------//

    public void interact(World world, BlockPos pos, PlayerEntity player, TileBase tile){

    }





    //----------------------------------------SUPPORT----------------------------------------//

    // ...



}
