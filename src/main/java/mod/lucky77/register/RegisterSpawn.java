package mod.lucky77.register;

import mod.lucky77.Configuration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static mod.lucky77.Lucky77.MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterSpawn {
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUBSCRIBER  ---------- ---------- ---------- ---------- //
	
	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event){
		event.register(EntityType.DROWNED, SpawnPlacements.Type.IN_WATER,  Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkDrownedSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.HUSK,    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkHuskSpawnRules,    SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.STRAY,   SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkStraySpawnRules,   SpawnPlacementRegisterEvent.Operation.REPLACE);
		
		event.register(EntityType.CAVE_SPIDER,     SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.CREEPER,         SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.ENDERMAN,        SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.GIANT,           SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.SKELETON,        SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.SPIDER,          SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.WITCH,           SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.WITHER,          SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.WITHER_SKELETON, SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.ZOMBIE,          SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.ZOMBIE_VILLAGER, SpawnPlacements.Type.ON_GROUND,       Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.EVOKER,          SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.ILLUSIONER,      SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.RAVAGER,         SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.VEX,             SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EntityType.VINDICATOR,      SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RegisterSpawn::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  RULES  ---------- ---------- ---------- ---------- //
	
	public static boolean checkMonsterSpawnRules(EntityType<? extends Monster> entity, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return level.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(level, pos, random, entity) && isValidSpawnPoint(entity, level, spawnType, pos, random);
	}
	
	public static boolean checkDrownedSpawnRules(EntityType<Drowned> entity, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		if (!levelAccessor.getFluidState(pos.below()).is(FluidTags.WATER)) {
			return false;
		} else {
			Holder<Biome> holder = levelAccessor.getBiome(pos);
			boolean flag = levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, random, entity) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.getFluidState(pos).is(FluidTags.WATER));
			if (holder.is(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS)) {
				return random.nextInt(15) == 0 && flag;
			} else {
				return random.nextInt(40) == 0 && isDeepEnoughToSpawn(levelAccessor, pos) && flag;
			}
		}
	}
	
	public static boolean checkHuskSpawnRules(EntityType<Husk> entity, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return checkMonsterSpawnRules(entity, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.canSeeSky(pos));
	}
	
	public static boolean checkStraySpawnRules(EntityType<Stray> entity, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		BlockPos blockpos = pos;
		
		do {
			blockpos = blockpos.above();
		} while(levelAccessor.getBlockState(blockpos).is(Blocks.POWDER_SNOW));
		
		return checkMonsterSpawnRules(entity, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.canSeeSky(blockpos.below()));
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public static boolean isValidSpawnPoint(EntityType<? extends Mob> entity, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		BlockPos blockpos = pos.below();
		return spawnType == MobSpawnType.SPAWNER || level.getBlockState(blockpos).isValidSpawn(level, blockpos, entity);
	}
	
	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor level, BlockPos pos, RandomSource random, EntityType<? extends Mob> entity){
		if(entity == EntityType.DROWNED) if(Configuration.SPAWN.drowned.get()){ isDarkEnoughToSpawnSimple(level, pos, random); } else { isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.HUSK   ) if(Configuration.SPAWN.husk.get(   )){ isDarkEnoughToSpawnSimple(level, pos, random); } else { isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.STRAY  ) if(Configuration.SPAWN.stray.get(  )){ isDarkEnoughToSpawnSimple(level, pos, random); } else { isDarkEnoughToSpawnComplex(level, pos, random); }
		
		if(entity == EntityType.CAVE_SPIDER    ) if(Configuration.SPAWN.cave_spider.get(    )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.CREEPER        ) if(Configuration.SPAWN.creeper.get(        )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.ENDERMAN       ) if(Configuration.SPAWN.enderman.get(       )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.GIANT          ) if(Configuration.SPAWN.giant.get(          )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.SKELETON       ) if(Configuration.SPAWN.skeleton.get(       )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.SPIDER         ) if(Configuration.SPAWN.spider.get(         )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.WITCH          ) if(Configuration.SPAWN.witch.get(          )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.WITHER         ) if(Configuration.SPAWN.wither.get(         )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.WITHER_SKELETON) if(Configuration.SPAWN.wither_skeleton.get()){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.ZOMBIE         ) if(Configuration.SPAWN.zombie.get(         )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.ZOMBIE_VILLAGER) if(Configuration.SPAWN.zombie_villager.get()){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.EVOKER         ) if(Configuration.SPAWN.evoker.get(         )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.ILLUSIONER     ) if(Configuration.SPAWN.illusioner.get(     )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.RAVAGER        ) if(Configuration.SPAWN.ravager.get(        )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.VEX            ) if(Configuration.SPAWN.vex.get(            )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		if(entity == EntityType.VINDICATOR     ) if(Configuration.SPAWN.vindicator.get(     )){ return isDarkEnoughToSpawnSimple(level, pos, random); } else { return isDarkEnoughToSpawnComplex(level, pos, random); }
		
		return false;
	}
	
	public static boolean isDarkEnoughToSpawnSimple(ServerLevelAccessor level, BlockPos pos, RandomSource random) {
		DimensionType dimensiontype = level.dimensionType();
		if(dimensiontype.hasSkyLight()){
			int light = level.getMaxLocalRawBrightness(pos);
			return light <= 2;
		}
		return true;
	}
	
	public static boolean isDarkEnoughToSpawnComplex(ServerLevelAccessor levelAccessor, BlockPos pos, RandomSource random) {
		if (levelAccessor.getBrightness(LightLayer.SKY, pos) > random.nextInt(32)) {
			return false;
		} else {
			DimensionType dimensiontype = levelAccessor.dimensionType();
			int i = dimensiontype.monsterSpawnBlockLightLimit();
			if (i < 15 && levelAccessor.getBrightness(LightLayer.BLOCK, pos) > i) {
				return false;
			} else {
				int j = levelAccessor.getLevel().isThundering() ? levelAccessor.getMaxLocalRawBrightness(pos, 10) : levelAccessor.getMaxLocalRawBrightness(pos);
				return j <= dimensiontype.monsterSpawnLightTest().sample(random);
			}
		}
	}
	
	private static boolean isDeepEnoughToSpawn(LevelAccessor levelAccessor, BlockPos pos) {
		return pos.getY() < levelAccessor.getSeaLevel() - 5;
	}
	
	
	
	
	
}
