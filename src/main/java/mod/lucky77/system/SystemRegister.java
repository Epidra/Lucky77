package mod.lucky77.system;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class SystemRegister {

    // ...





    //----------------------------------------REGISTER----------------------------------------//

    public static RegistryObject<Block> register(DeferredRegister<Block> deferredBLOCK, String name, Block block){
        return register(deferredBLOCK, null, name, block, null);
    }

    public static RegistryObject<Block> register(DeferredRegister<Block> deferredBLOCK, DeferredRegister<Item> deferredITEM, String name, Block block, CreativeModeTab CreativeModeTab){
        if(CreativeModeTab != null){ deferredITEM.register(name, () -> new BlockItem(block, (new Item.Properties()).tab(CreativeModeTab))); }
        return deferredBLOCK.register(name, () -> block);
    }

    public static RegistryObject<Item> register(DeferredRegister<Item> deferred, String name, Item item){
        return deferred.register(name, () -> item);
    }

    public static RegistryObject<SoundEvent> register(DeferredRegister<SoundEvent> deferred, String name, SoundEvent sound){
        return deferred.register(name, () -> sound);
    }

    public static RegistryObject<EntityType<?>> register(DeferredRegister<EntityType<?>> deferred, String name, EntityType entity){
        return deferred.register(name, () -> entity);
    }

    public static RegistryObject<StructureFeature<JigsawConfiguration>> register(DeferredRegister<StructureFeature<?>> deferred, String name, StructureFeature<JigsawConfiguration> structure){
        return deferred.register(name, () -> (structure));
    }

    public static RegistryObject<?> register(DeferredRegister<RecipeSerializer<?>> deferred, String name, RecipeSerializer<?> recipe){
        return deferred.register(name, () -> recipe);
    }

    public static <T extends Recipe<?>> RecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new RecipeType<T>()
        {
            @Override
            public String toString()
            {
                return key;
            }
        });
    }





    //----------------------------------------BUILD----------------------------------------//

    public static Holder<PlacedFeature> buildOreSpawn(String name, BlockState state, int veinSize, int minHeight, int maxHeight, int spawnRate, RuleTest replacables, boolean isRare) {
        List<OreConfiguration.TargetBlockState> TARGETLIST = List.of(OreConfiguration.target(replacables, state));
        Holder<ConfiguredFeature<OreConfiguration, ?>> FEATURE = FeatureUtils.register(name, Feature.ORE, new OreConfiguration(TARGETLIST, veinSize));
        return PlacementUtils.register(name, FEATURE,
                isRare ?  commonOrePlacement(spawnRate, HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)))
                        : rareOrePlacement(  spawnRate, HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)))

        );
    }

    //public static ConfiguredStructureFeature<?, ?> buildConfiguredStructureFeature(){
    //    return null;
    //}

    //@SubscribeEvent(priority = EventPriority.HIGH)
    //public static void buildSpawn(BiomeLoadingEvent event) {
    //    if (event.getName() != null) {
    //        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
    //        if (biome != null) {
    //            ResourceKey<Biome> resourceKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());
    //            List<BiomeDictionary.Type> includeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(Config.ALPACA.include.get()));
    //            List<BiomeDictionary.Type> excludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(Config.ALPACA.exclude.get()));
    //            if (!includeList.isEmpty()) {
    //                Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(resourceKey);
    //                if (biomeTypes.stream().noneMatch(excludeList::contains) && biomeTypes.stream().anyMatch(includeList::contains)) {
    //                    event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ShopKeeper.ENTITY_ALPACA.get(), Config.ALPACA.weight.get(), Config.ALPACA.min.get(), Config.ALPACA.max.get()));
    //                }
    //            } else {
    //                throw new IllegalArgumentException("Do not leave the BiomeDictionary type inclusion list empty. If you wish to disable spawning of an entity, set the weight to 0 instead.");
    //            }
    //        }
    //    }
    //}

    //public static void registerEntity(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types) {
    //    event.getSpawns().getSpawner(CREATURE).add(new MobSpawnSettings.SpawnerData(ENTITY_ALPACA.get(), Config.ALPACA.weight.get(), Config.ALPACA.min.get(), Config.ALPACA.max.get()));
    //}





    //----------------------------------------SUPPORT----------------------------------------//

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), modifier);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier placement, PlacementModifier modifier) {
        return List.of(placement, InSquarePlacement.spread(), modifier, BiomeFilter.biome());
    }



}
