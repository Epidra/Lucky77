package mod.lucky77;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml
@Mod("lucky77")
public class Lucky77 {

    // The Mod ID
    public static final String MODID = "lucky77";





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public Lucky77() {
        MinecraftForge.EVENT_BUS.register(this);
    }



}
