package mod.lucky77;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Lucky77.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    
    // --- The Config Builder --- //
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    
    // --- The Config Values --- //
    private static final ModConfigSpec.BooleanValue SPAWN_BOGGED = BUILDER
            .comment(" Set these flags to TRUE to restrict the Spawn Behaviour of those Mobs to LightLevel 0")
            .comment(" -- Overworld Monsters --")            .define("Bogged", false); // Undead
    private static final ModConfigSpec.BooleanValue SPAWN_BREEZE = BUILDER.define("Breeze", false); // Monster (exclusive to Spawners)
    private static final ModConfigSpec.BooleanValue SPAWN_DROWNED = BUILDER.define("Drowned", false); // Undead
    private static final ModConfigSpec.BooleanValue SPAWN_CAVESPIDER = BUILDER.define("CaveSpider", false); // Monster (exclusive to Spawners)
    private static final ModConfigSpec.BooleanValue SPAWN_CREEPER = BUILDER.define("Creeper", false); // Monster
    private static final ModConfigSpec.BooleanValue SPAWN_ENDERMAN = BUILDER.define("Enderman", false); // Monster (passive when wearing pumpkin)
    private static final ModConfigSpec.BooleanValue SPAWN_GUARDIAN = BUILDER.define("Guardian", false); // Underwater Monster
    private static final ModConfigSpec.BooleanValue SPAWN_HUSK = BUILDER.define("Husk", false); // Undead
    private static final ModConfigSpec.BooleanValue SPAWN_SKELETON = BUILDER.define("Skeleton", false); // Undead
    private static final ModConfigSpec.BooleanValue SPAWN_SLIME = BUILDER.define("Slime", false); // Monster
    private static final ModConfigSpec.BooleanValue SPAWN_SPIDER = BUILDER.define("Spider", false); // Monster
    private static final ModConfigSpec.BooleanValue SPAWN_STRAY = BUILDER.define("Stray", false); // Undead
    private static final ModConfigSpec.BooleanValue SPAWN_ZOMBIE = BUILDER.define("Zombie", false); // Undead
    private static final ModConfigSpec.BooleanValue SPAWN_ZOMBIEVILLAGER = BUILDER.define("ZombieVillager", false); // Undead
    
    private static final ModConfigSpec.BooleanValue SPAWN_EVOKER = BUILDER
            .comment(" -- Illagers --")       .define("Evoker", false); // Illager
    private static final ModConfigSpec.BooleanValue SPAWN_PILLAGER = BUILDER.define("Pillager", false); // Illager
    private static final ModConfigSpec.BooleanValue SPAWN_RAVAGER = BUILDER.define("Ravager", false); // Illager Mount
    private static final ModConfigSpec.BooleanValue SPAWN_VINDICATOR = BUILDER.define("Vindicator", false); // Illager
    private static final ModConfigSpec.BooleanValue SPAWN_WITCH = BUILDER.define("Witch", false); // Illager
    
    private static final ModConfigSpec.BooleanValue SPAWN_BLAZE = BUILDER
            .comment(" -- Nether Monsters --")       .define("Blaze", false); // Nether Monster
    private static final ModConfigSpec.BooleanValue SPAWN_GHAST = BUILDER.define("Ghast", false); // Nether Monster
    private static final ModConfigSpec.BooleanValue SPAWN_HOGLIN = BUILDER.define("Hoglin", false); // Nether Monster
    private static final ModConfigSpec.BooleanValue SPAWN_MAGMACUBE = BUILDER.define("MagmaCube", false); // Nether Monster
    private static final ModConfigSpec.BooleanValue SPAWN_PIGLIN = BUILDER.define("Piglin", false); // Nether Monster (passive when wearing gold)
    private static final ModConfigSpec.BooleanValue SPAWN_WITHERSKELETON = BUILDER.define("WitherSkeleton", false); // Nether Monster
    // upcoming - Creaking (1.21.4)
    
    // --- Ignored Monsters --- //
    // Ender Dragon - Boss Monster
    // Wither - Boss monster
    // Elder Guardian - Boss Monster
    // Warden - Boss Monster
    // Vex - Custom Spawn (as attack of an evoker)
    // Skeleton Horse - Passive behaviour
    // Zoglin - Custom Spawn (when dragging a hoglin unto the overworld)
    // Zombie Piglin - Custom Spawn (when dragging a piglin unto the overworld)
    // Zombie Horse - Passive behaviour
    // Endermite - Custom Spawn (when throwing an EnderPearl)
    // Shulker - Custom Spawn (only spawned at world generation)
    // Silverfish - Custom Spawn (when destroying infested stone)
    // Giant - Unused
    // Illusioner - Unused
    // Phantom - Custom Spawn (when player hasn't slept in a while)
    // Piglin Brutes - Custom Spawn (only spawned at world generation)
    
    // --- Building the Builder --- //
    static final ModConfigSpec SPEC = BUILDER.build();
    
    // --- The mod-readable Values --- //
    public static boolean spawnDrowned;
    public static boolean spawnHusk;
    public static boolean spawnStray;
    public static boolean spawnCaveSpider;
    public static boolean spawnCreeper;
    public static boolean spawnEnderman;
    public static boolean spawnSkeleton;
    public static boolean spawnSpider;
    public static boolean spawnWitch;
    public static boolean spawnWitherSkeleton;
    public static boolean spawnZombie;
    public static boolean spawnZombieVillager;
    public static boolean spawnEvoker;
    public static boolean spawnRavager;
    public static boolean spawnVindicator;
    public static boolean spawnBogged;
    public static boolean spawnBreeze;
    public static boolean spawnGuardian;
    public static boolean spawnSlime;
    public static boolean spawnPillager;
    public static boolean spawnBlaze;
    public static boolean spawnGhast;
    public static boolean spawnHoglin;
    public static boolean spawnMagmaCube;
    public static boolean spawnPiglin;
    
    //   -------- -------- -------- --------     LOADING     -------- -------- -------- --------   //
    
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event){
        spawnDrowned = SPAWN_DROWNED.get();
        spawnHusk = SPAWN_HUSK.get();
        spawnStray = SPAWN_STRAY.get();
        spawnCaveSpider = SPAWN_CAVESPIDER.get();
        spawnCreeper = SPAWN_CREEPER.get();
        spawnEnderman = SPAWN_ENDERMAN.get();
        spawnSkeleton = SPAWN_SKELETON.get();
        spawnSpider = SPAWN_SPIDER.get();
        spawnWitch = SPAWN_WITCH.get();
        spawnWitherSkeleton = SPAWN_WITHERSKELETON.get();
        spawnZombie = SPAWN_ZOMBIE.get();
        spawnZombieVillager = SPAWN_ZOMBIEVILLAGER.get();
        spawnEvoker = SPAWN_EVOKER.get();
        spawnRavager = SPAWN_RAVAGER.get();
        spawnVindicator = SPAWN_VINDICATOR.get();
        spawnBogged = SPAWN_BOGGED.get();
        spawnBreeze = SPAWN_BREEZE.get();
        spawnGuardian = SPAWN_GUARDIAN.get();
        spawnSlime = SPAWN_SLIME.get();
        spawnPillager = SPAWN_PILLAGER.get();
        spawnBlaze = SPAWN_BLAZE.get();
        spawnGhast = SPAWN_GHAST.get();
        spawnHoglin = SPAWN_HOGLIN.get();
        spawnMagmaCube = SPAWN_MAGMACUBE.get();
        spawnPiglin = SPAWN_PIGLIN.get();
    }
}
