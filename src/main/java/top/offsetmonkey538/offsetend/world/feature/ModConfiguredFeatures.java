package top.offsetmonkey538.offsetend.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import top.offsetmonkey538.offsetend.block.ModBlocks;

import static top.offsetmonkey538.offsetend.OffsetEnd.id;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> END_WATER_LAKE_KEY = register("end_water_lake");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_MUSHROOM_KEY = register("end_mushroom");


    @SuppressWarnings("deprecation")
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, END_WATER_LAKE_KEY, new ConfiguredFeature<>(
                Feature.LAKE,
                new LakeFeature.Config(
                        BlockStateProvider.of(ModBlocks.END_WATER.getDefaultState()),
                        BlockStateProvider.of(Blocks.END_STONE.getDefaultState())
                )
        ));

        register(context, END_MUSHROOM_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.END_MUSHROOM))
                )
        ));
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> register(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, ConfiguredFeature<FC, F> configuredFeature) {
        context.register(key, configuredFeature);
    }
}
