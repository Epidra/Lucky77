package mod.lucky77;

import mod.lucky77.custom.content.RegisterMod;
import mod.lucky77.custom.content.RegisterSeed;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Lucky77.MODID)
public class Lucky77 {
    
    public static final String MODID = "lucky77";
    public static RegisterMod MODS = new RegisterMod();
    public static RegisterSeed SEEDS = new RegisterSeed();
    
    
    
    
    
    //   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
    
    public Lucky77(IEventBus modEventBus, ModContainer modContainer){
        // modEventBus.addListener(this::setupCommon);
        // modEventBus.addListener(this::setupClient);
        
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        
        // Only needed if THIS class has a @SubscribeEvent
        // NeoForge.EVENT_BUS.register(this);
    }
    
    
    
    
    
    //   -------- -------- -------- --------     SETUP     -------- -------- -------- --------   //
    
    private void setupCommon(final FMLCommonSetupEvent event){
    
    }
    
    private void setupClient(final FMLClientSetupEvent event){
    
    }
    
    
    
}
