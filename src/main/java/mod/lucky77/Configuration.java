package mod.lucky77;

import net.minecraftforge.common.ForgeConfigSpec;

@SuppressWarnings("unused")
public class Configuration {
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	public static final ConfigSpawn SPAWN = new ConfigSpawn(BUILDER, "spawn", 1, 2, 6);
	
	public static class ConfigSpawn {
		public final ForgeConfigSpec.BooleanValue drowned;
		public final ForgeConfigSpec.BooleanValue husk;
		public final ForgeConfigSpec.BooleanValue stray;
		public final ForgeConfigSpec.BooleanValue cave_spider;
		public final ForgeConfigSpec.BooleanValue creeper;
		public final ForgeConfigSpec.BooleanValue enderman;
		public final ForgeConfigSpec.BooleanValue giant;
		public final ForgeConfigSpec.BooleanValue skeleton;
		public final ForgeConfigSpec.BooleanValue spider;
		public final ForgeConfigSpec.BooleanValue witch;
		public final ForgeConfigSpec.BooleanValue wither;
		public final ForgeConfigSpec.BooleanValue wither_skeleton;
		public final ForgeConfigSpec.BooleanValue zombie;
		public final ForgeConfigSpec.BooleanValue zombie_villager;
		public final ForgeConfigSpec.BooleanValue evoker;
		public final ForgeConfigSpec.BooleanValue illusioner;
		public final ForgeConfigSpec.BooleanValue ravager;
		public final ForgeConfigSpec.BooleanValue vex;
		public final ForgeConfigSpec.BooleanValue vindicator;
		
		ConfigSpawn(ForgeConfigSpec.Builder builder, String id, int _min, int _max, int _weight) {
			builder.push("SPAWN RESTRICTIONS");
			builder.comment("Override default spawn rules for monsters. On TRUE, Monsters are then only allowed to spawn on a light level of 2 or below.");
			builder.comment("The Overworld at night is light level 5, restricting the monsters to only spawn in dark caves.");
			drowned         = builder.define("Drowned",        false);
			husk            = builder.define("Husk",           false);
			stray           = builder.define("Stray",          false);
			cave_spider     = builder.define("Cave Spider",    false);
			creeper         = builder.define("Creeper",        false);
			enderman        = builder.define("Enderman",       false);
			giant           = builder.define("Giant",          false);
			skeleton        = builder.define("Skeleton",       false);
			spider          = builder.define("Spider",         false);
			witch           = builder.define("Witch",          false);
			wither          = builder.define("Wither",         false);
			wither_skeleton = builder.define("WitherSkeleton", false);
			zombie          = builder.define("Zombie",         false);
			zombie_villager = builder.define("ZombieVillager", false);
			evoker          = builder.define("Evoker",         false);
			illusioner      = builder.define("Illusioner",     false);
			ravager         = builder.define("Ravager",        false);
			vex             = builder.define("Vex",            false);
			vindicator      = builder.define("Vindicator",     false);
			builder.pop();
		}
	}
	
	public static final ForgeConfigSpec spec = BUILDER.build();
	
}