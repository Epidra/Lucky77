package mod.lucky77.block;

import mod.lucky77.blockentity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BlockBlock extends BlockBase {

    // ...





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Contructor */
    // public BlockBlock(Material material, MaterialColor materialcolor, float hardness, float resistance, SoundType sound, int light) {
    //     super(material, materialcolor, hardness, resistance, sound, light);
    // }

    /** Contructor with predefined BlockProperty */
    public BlockBlock(Block block) {
        super(block);
    }





    //----------------------------------------PLACEMENT----------------------------------------//

    // ...





    //----------------------------------------INTERACTION----------------------------------------//

    public void interact(Level world, BlockPos pos, Player player, BlockEntityBase tile){

    }





    //----------------------------------------SUPPORT----------------------------------------//

    // ...



}
