package mod.lucky77.system;

public class SystemStructures {

//    /**
//     * Adds the provided structure to the registry, and adds the separation settings.
//     * The rarity of the structure is determined based on the values passed into
//     * this method in the StructureFeatureConfiguration argument.
//     * This method is called by setupStructures above.
//     */
//    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(F structure, int spacing, int separation, int salt, boolean transformSurroundingLand){
//        setupMapSpacingAndLand(structure, new ConfiguredStructureFeature(spacing, separation, salt), transformSurroundingLand);
//        // structure /* The instance of the structure */
//        // spacing /* average distance apart in chunks between spawn attempts */,
//        // separation /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
//        // salt /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
//        // transformSorroundingLands /* ??? */
//    }
//
//    /**
//     * Adds the provided structure to the registry, and adds the separation settings.
//     * The rarity of the structure is determined based on the values passed into
//     * this method in the StructureFeatureConfiguration argument.
//     * This method is called by setupStructures above.
//     */
//    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(
//            F structure,
//            ConfiguredStructureFeature structureFeatureConfiguration,
//            boolean transformSurroundingLand)
//    {
//        /*
//         * We need to add our structures into the map in StructureFeature class
//         * alongside vanilla structures or else it will cause errors.
//         *
//         * If the registration is setup properly for the structure,
//         * getRegistryName() should never return null.
//         */
//        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
//
//        /*
//         * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
//         * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
//         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
//         *
//         * Note: The air space this method will create will be filled with water if the structure is below sealevel.
//         * This means this is best for structure above sealevel so keep that in mind.
//         *
//         * NOISE_AFFECTING_FEATURES requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
//         */
//        if(transformSurroundingLand){
//            StructureFeature.NOISE_AFFECTING_FEATURES =
//                    ImmutableList.<StructureFeature<?>>builder()
//                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
//                            .add(structure)
//                            .build();
//        }
//
//        /*
//         * This is the map that holds the default spacing of all structures.
//         * Always add your structure to here so that other mods can utilize it if needed.
//         *
//         * However, while it does propagate the spacing to some correct dimensions from this map,
//         * it seems it doesn't always work for code made dimensions as they read from this list beforehand.
//         *
//         * Instead, we will use the WorldEvent.Load event in StructureTutorialMain to add the structure
//         * spacing from this list into that dimension or to do dimension blacklisting properly.
//         *
//         * DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
//         */
//        StructureSettings.DEFAULTS =
//                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
//                        .putAll(StructureSettings.DEFAULTS)
//                        .put(structure, structureFeatureConfiguration)
//                        .build();
//
//
//        /*
//         * There are very few mods that relies on seeing your structure in the noise settings registry before the world is made.
//         *
//         * You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead of the NOISE_GENERATOR_SETTINGS loop below but
//         * that field only applies for the default overworld and won't add to other worldtypes or dimensions (like amplified or Nether).
//         * So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop below instead if you must.
//         */
//        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
//            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();
//
//            /*
//             * Pre-caution in case a mod makes the structure map immutable like datapacks do.
//             * I take no chances myself. You never know what another mods does...
//             *
//             * structureConfig requires AccessTransformer (See resources/META-INF/accesstransformer.cfg)
//             */
//            if(structureMap instanceof ImmutableMap){
//                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
//                tempMap.put(structure, structureFeatureConfiguration);
//                settings.getValue().structureSettings().structureConfig = tempMap;
//            }
//            else{
//                structureMap.put(structure, structureFeatureConfiguration);
//            }
//        });
//    }
//
//
//
//    /*
//     * Tells the chunkgenerator which biomes our structure can spawn in.
//     * Will go into the world's chunkgenerator and manually add our structure spacing.
//     * If the spacing is not added, the structure doesn't spawn.
//     */
//    private static Method GETCODEC_METHOD;
//    public static void addDimensionalSpacing(final WorldEvent.Load event, StructureFeature<JigsawConfiguration> structure, ConfiguredStructureFeature<?, ?> feature) {
//        if(event.getWorld() instanceof ServerLevel serverLevel){
//            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
//
//            // Skip superflat worlds to prevent issues with it. Plus, users don't want structures clogging up their superflat worlds.
//            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD)) {
//                return;
//            }
//
//            StructureSettings worldStructureConfig = chunkGenerator.getSettings();
//
//            //////////// BIOME BASED STRUCTURE SPAWNING ////////////
//            /*
//             * NOTE: BiomeLoadingEvent from Forge API does not work with structures anymore.
//             * Instead, we will use the below to add our structure to overworld biomes.
//             * Remember, this is temporary until Forge API finds a better solution for adding structures to biomes.
//             */
//
//            // Create a mutable map we will use for easier adding to biomes
//            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap = new HashMap<>();
//
//            // Add the resourcekey of all biomes that this Configured Structure can spawn in.
//            for(Map.Entry<ResourceKey<Biome>, Biome> biomeEntry : serverLevel.registryAccess().ownedRegistryOrThrow(Registry.BIOME_REGISTRY).entrySet()) {
//                // Skip all ocean, end, nether, and none category biomes.
//                // You can do checks for other traits that the biome has.
//                Biome.BiomeCategory biomeCategory = biomeEntry.getValue().getBiomeCategory();
//                if(biomeCategory != Biome.BiomeCategory.OCEAN && biomeCategory != Biome.BiomeCategory.THEEND && biomeCategory != Biome.BiomeCategory.NETHER && biomeCategory != Biome.BiomeCategory.NONE) {
//                    associateBiomeToConfiguredStructure(STStructureToMultiMap, feature, biomeEntry.getKey());
//                }
//            }
//
//            // Alternative way to add our structures to a fixed set of biomes by creating a set of biome resource keys.
//            // To create a custom resource key that points to your own biome, do this:
//            // ResourceKey.of(Registry.BIOME_REGISTRY, new ResourceLocation("modid", "custom_biome"))
////            ImmutableSet<ResourceKey<Biome>> overworldBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
////                    .add(Biomes.FOREST)
////                    .add(Biomes.MEADOW)
////                    .add(Biomes.PLAINS)
////                    .add(Biomes.SAVANNA)
////                    .add(Biomes.SNOWY_PLAINS)
////                    .add(Biomes.SWAMP)
////                    .add(Biomes.SUNFLOWER_PLAINS)
////                    .add(Biomes.TAIGA)
////                    .build();
////            overworldBiomes.forEach(biomeKey -> associateBiomeToConfiguredStructure(STStructureToMultiMap, STConfiguredStructures.CONFIGURED_RUN_DOWN_HOUSE, biomeKey));
//
//            // Grab the map that holds what ConfigureStructures a structure has and what biomes it can spawn in.
//            // Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
//            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
//            worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !STStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);
//
//            // Add our structures to the structure map/multimap and set the world to use this combined map/multimap.
//            STStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));
//
//            // Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
//            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();
//
//
//            //////////// DIMENSION BASED STRUCTURE SPAWNING ////////////
//
//            /*
//             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
//             * They will handle your structure spacing for your if you add to Registry.NOISE_GENERATOR_SETTINGS_REGISTRY in your structure's registration.
//             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
//             * If you are using mixins, you can call the codec method with an invoker mixin instead of using reflection.
//             */
//            try {
//                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
//                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(chunkGenerator));
//                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
//            }
//            catch(Exception e){
//                //StructureTutorialMain.LOGGER.error("Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
//            }
//
//            /*
//             * Makes sure this chunkgenerator and datapack dimensions can spawn our structure.
//             *
//             * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
//             * Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
//             */
//            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(worldStructureConfig.structureConfig());
//            tempMap.putIfAbsent(structure, StructureSettings.DEFAULTS.get(structure));
//
//            worldStructureConfig.structureConfig = tempMap;
//
//            /*
//             * The above three lines can be changed to do dimension blacklists/whitelist for your structure.
//             * (Don't forget to attempt to remove your structure too from the map if you are blacklisting that dimension in case it already has the structure)
//             *
//             * If you do start whitelisting/blacklisting by dimensions instead of the default adding the spacing to all dimensions,
//             * you may want to deep copy the structureConfig field first due to the fact that two dimensions using minecraft:overworld noise setting shares the same instance.
//             * The deep copying would let you only add to one dimension and not the other instead of your changes applying to both dimensions together at same time.
//             * https://github.com/TelepathicGrunt/RepurposedStructures/blob/latest-released/src/main/java/com/telepathicgrunt/repurposedstructures/misc/NoiseSettingsDeepCopier.java
//             */
//        }
//    }
//
//    /**
//     * Helper method that handles setting up the map to multimap relationship to help prevent issues.
//     */
//    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
//        STStructureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
//        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = STStructureToMultiMap.get(configuredStructureFeature.feature);
//        if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
//            //StructureTutorialMain.LOGGER.error("""
//            //        Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
//            //        This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
//            //        The two conflicting ConfiguredStructures are: {}, {}
//            //        The biome that is attempting to be shared: {}
//            //    """,
//            //        BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
//            //        BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
//            //        biomeRegistryKey
//            //);
//        }
//        else{
//            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
//        }
//    }
    
}
