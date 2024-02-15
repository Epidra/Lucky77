package mod.lucky77;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("lucky77")
public class Lucky77 {
	
	// Define mod id in a common place for everything to reference
	public static final String MODID = "lucky77";
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public Lucky77() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.spec);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SETUP  ---------- ---------- ---------- ---------- //
	
	private void setupCommon(final FMLCommonSetupEvent event) {
	
	}
	
	private void setupClient(final FMLClientSetupEvent event) {
	
	}
	
	
	
}