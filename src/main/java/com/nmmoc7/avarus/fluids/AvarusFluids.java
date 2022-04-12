package com.nmmoc7.avarus.fluids;

import com.nmmoc7.avarus.Avarus;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class AvarusFluids {
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Avarus.MOD_ID);

    public static final RegistryObject<FlowingFluid> WATER_STEAM = FLUIDS.register("water_steam", WaterSteamFluid::source);
    public static final RegistryObject<FlowingFluid> WATER_STEAM_FLOWING = FLUIDS.register("water_steam_flowing", WaterSteamFluid::flowing);

    public static void register(IEventBus bus) {
        FLUIDS.register(bus);
    }
}
