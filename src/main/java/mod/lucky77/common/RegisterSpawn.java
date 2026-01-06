package mod.lucky77.common;

import mod.lucky77.Config;
import mod.lucky77.Lucky77;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = Lucky77.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegisterSpawn {
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     SUBSCRIBER     -------- -------- -------- --------   //
	
	/** Overrides the existing Spawn Rules for Monsters in unmodded Minecraft **/
	@SubscribeEvent
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event){
		// Special Spawns
		event.register(EntityType.DROWNED, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkDrownedSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.HUSK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkHuskSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.STRAY, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkStraySpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.GUARDIAN, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkGuardianSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.BLAZE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.GHAST, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkGhastSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.MAGMA_CUBE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMagmaCubeSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.HOGLIN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkNetherSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.PIGLIN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkNetherSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.PILLAGER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.SLIME, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkSlimeSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		// Earthbound Spawns
		event.register(EntityType.CAVE_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.CREEPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.ENDERMAN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.GIANT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.SKELETON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.WITCH, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.WITHER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.WITHER_SKELETON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.ZOMBIE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.ZOMBIE_VILLAGER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.BOGGED, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		// Unrestricted Spawns
		event.register(EntityType.EVOKER, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.ILLUSIONER, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.RAVAGER, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.VEX, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EntityType.VINDICATOR, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     RULES     -------- -------- -------- --------   //
	
	/** Check for Generic Spawn Rules (same as - Monster::checkMonsterSpawnRules) **/
	private static boolean checkMonsterSpawnRules(EntityType<? extends Monster> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
		return level.getDifficulty() != Difficulty.PEACEFUL && (MobSpawnType.ignoresLightRequirements(spawnType) || isDarkEnoughToSpawn(level, pos, random, entity)) && isValidSpawnPoint(entity, level, spawnType, pos, random);
	}
	
	/** Check for Custom Slime Spawn Rules (altered from - Slime::checkSlimeSpawnRules) **/
	public static boolean checkSlimeSpawnRules(EntityType<Slime> slime, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		if(!isDarkEnoughToSpawn(level, pos, random, slime)) return false;
		if (MobSpawnType.isSpawner(spawnType)) {
			return isValidSpawnPoint(slime, level, spawnType, pos, random);
		} else {
			if (level.getDifficulty() != Difficulty.PEACEFUL) {
				if (spawnType == MobSpawnType.SPAWNER) {
					return isValidSpawnPoint(slime, level, spawnType, pos, random);
				}
				
				if (level.getBiome(pos).is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
						&& pos.getY() > 50
						&& pos.getY() < 70
						&& random.nextFloat() < 0.5F
						&& random.nextFloat() < level.getMoonBrightness()
						&& level.getMaxLocalRawBrightness(pos) <= random.nextInt(8)) {
					return isValidSpawnPoint(slime, level, spawnType, pos, random);
				}
				
				if (!(level instanceof WorldGenLevel)) {
					return false;
				}
				
				ChunkPos chunkpos = new ChunkPos(pos);
				boolean flag = WorldgenRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, ((WorldGenLevel)level).getSeed(), 987234911L).nextInt(10) == 0;
				if (random.nextInt(10) == 0 && flag && pos.getY() < 40) {
					return isValidSpawnPoint(slime, level, spawnType, pos, random);
				}
			}
			
			return false;
		}
	}
	
	/** Check for Custom Piglin & Hoglin Spawn Rules (altered from - Piglin::checkPiglinSpawnRules) **/
	public static boolean checkNetherSpawnRules(EntityType<? extends Mob> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return !level.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK) && isDarkEnoughToSpawn(level, pos, random, entity);
	}
	
	
	/** Check for Custom Ghast Spawn Rules (altered from - Ghast::checkGhastSpawnRules) **/
	public static boolean checkGhastSpawnRules(EntityType<Ghast> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL && random.nextInt(20) == 0 && isDarkEnoughToSpawn(level, pos, random, entity) && isValidSpawnPoint(entity, level, spawnType, pos, random);
	}
	
	/** Check for Custom Guardian Spawn Rules (altered from - Guardian::checkGuardianSpawnRules) **/
	public static boolean checkGuardianSpawnRules(EntityType<? extends Guardian> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return (random.nextInt(20) == 0 || !level.canSeeSkyFromBelowWater(pos))
				&& level.getDifficulty() != Difficulty.PEACEFUL
				&& (MobSpawnType.isSpawner(spawnType) || level.getFluidState(pos).is(FluidTags.WATER))
				&& level.getFluidState(pos.below()).is(FluidTags.WATER)
				&& isDarkEnoughToSpawn(level, pos, random, entity);
	}
	
	/** Check for Custom MagmaCube Spawn Rules (altered from - MagmaCube::checkMagmaCubeSpawnRules) **/
	public static boolean checkMagmaCubeSpawnRules(EntityType<MagmaCube> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(level, pos, random, entity);
	}
	
	/** Check for Custom Drowned Spawn Rules (same as - Drowned::checkDrownedSpawnRules) **/
	private static boolean checkDrownedSpawnRules(EntityType<Drowned> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
		if (!level.getFluidState(pos.below()).is(FluidTags.WATER) && !MobSpawnType.isSpawner(spawnType)) {
			return false;
		} else {
			Holder<Biome> holder = level.getBiome(pos);
			boolean flag = level.getDifficulty() != Difficulty.PEACEFUL && (MobSpawnType.ignoresLightRequirements(spawnType) || isDarkEnoughToSpawn(level, pos, random, entity)) && (MobSpawnType.isSpawner(spawnType) || level.getFluidState(pos).is(FluidTags.WATER));
			if (flag && MobSpawnType.isSpawner(spawnType)) {
				return true;
			} else {
				return holder.is(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS) ? random.nextInt(15) == 0 && flag : random.nextInt(40) == 0 && (pos.getY() < level.getSeaLevel() - 5) && flag;
			}
		}
	}
	
	/** Check for custom Husk spawn rules (same as - Husk::checkHuskSpawnRules) **/
	private static boolean checkHuskSpawnRules(EntityType<Husk> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
		return checkMonsterSpawnRules(entity, level, spawnType, pos, random) && (MobSpawnType.isSpawner(spawnType) || level.canSeeSky((pos)));
	}
	
	/** Checks for custom Stray spawn rules (same as - Stray::checkStraySpawnRules) **/
	private static boolean checkStraySpawnRules(EntityType<Stray> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
		BlockPos blockPos = pos;
		do {
			blockPos = blockPos.above();
		} while(level.getBlockState(blockPos).is(Blocks.POWDER_SNOW));
		return checkMonsterSpawnRules(entity, level, spawnType, pos, random) && (MobSpawnType.isSpawner(spawnType) || level.canSeeSky(blockPos.below()));
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Checks if Monster is on an allowed place to spawn (same as - Mob::checkMobSpawnRules) **/
	private static boolean isValidSpawnPoint(EntityType<? extends Mob> entity, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random){
		BlockPos blockPos = pos.below();
		return spawnType == MobSpawnType.SPAWNER || level.getBlockState(blockPos).isValidSpawn(level, blockPos, entity);
	}
	
	/** Configurable handling of LightLevelSpawnRule to either use the modded or default behaviour **/
	private static boolean isDarkEnoughToSpawn(ServerLevelAccessor level, BlockPos pos, RandomSource random, EntityType<? extends Mob> entity){
		// Custom SpawnRules
		if(entity == EntityType.DROWNED) if(Config.spawnDrowned){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.HUSK) if(Config.spawnHusk){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.STRAY) if(Config.spawnStray){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		// No LightLevel Check
		if(entity == EntityType.GUARDIAN) if(Config.spawnGuardian){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return true; } // Personal SpawnRule altered with isDarkEnoughToSpawn
		if(entity == EntityType.BLAZE) if(Config.spawnBlaze){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return true; } // AnyLightMonsterSpawnRule
		if(entity == EntityType.GHAST) if(Config.spawnBlaze){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return true; } // Personal SpawnRule altered with isDarkEnoughToSpawn
		if(entity == EntityType.PILLAGER) if(Config.spawnPillager){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return level.getBrightness(LightLayer.BLOCK, pos) <= 8; } // PatrollingMonsterSpawnRule
		if(entity == EntityType.PIGLIN) if(Config.spawnPiglin){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return true; } // Personal SpawnRule altered with isDarkEnoughToSpawn
		if(entity == EntityType.HOGLIN) if(Config.spawnHoglin){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return true; } // Personal SpawnRule altered with isDarkEnoughToSpawn
		if(entity == EntityType.MAGMA_CUBE) if(Config.spawnMagmaCube){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return true; } // Personal SpawnRule altered with isDarkEnoughToSpawn
		if(entity == EntityType.SLIME) if(Config.spawnSlime){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return true; } // Personal SpawnRule altered with isDarkEnoughToSpawn
		// Default Monster SpawnRules
		if(entity == EntityType.CAVE_SPIDER) if(Config.spawnCaveSpider){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.CREEPER) if(Config.spawnCreeper){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.ENDERMAN) if(Config.spawnEnderman){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.SKELETON) if(Config.spawnSkeleton){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.SPIDER) if(Config.spawnSpider){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.WITCH) if(Config.spawnWitch){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.WITHER_SKELETON) if(Config.spawnWitherSkeleton){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.ZOMBIE) if(Config.spawnZombie){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.ZOMBIE_VILLAGER) if(Config.spawnZombieVillager){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.EVOKER) if(Config.spawnEvoker){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.RAVAGER) if(Config.spawnRavager){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.VINDICATOR) if(Config.spawnVindicator){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.BOGGED) if(Config.spawnBogged){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		
		return false;
	}
	
	/** Checks if the LightLevel is low enough (The mod Version) **/
	private static boolean isDarkEnoughToSpawnSimple(ServerLevelAccessor level, BlockPos pos, RandomSource random){
		DimensionType dimType = level.dimensionType();
		if(dimType.hasSkyLight()){
			int light = level.getMaxLocalRawBrightness(pos);
			return light <= 2;
		}
		return true;
	}
	
	/** checks if the LightLevel is low enough (The Minecraft Version) **/
	private static boolean isDarkEnoughToSpawnComplex(ServerLevelAccessor level, BlockPos pos, RandomSource random){
		if(level.getBrightness(LightLayer.SKY, pos) > random.nextInt(32)){
			return false;
		} else {
			DimensionType dimType = level.dimensionType();
			int lightLimit = dimType.monsterSpawnBlockLightLimit();
			if(lightLimit < 15 && level.getBrightness(LightLayer.BLOCK, pos) > lightLimit){
				return false;
			} else {
				int brightness = level.getLevel().isThundering() ? level.getMaxLocalRawBrightness(pos, 10) : level.getMaxLocalRawBrightness(pos);
				return brightness <= dimType.monsterSpawnLightTest().sample(random);
			}
		}
	}
	
	
	
}
