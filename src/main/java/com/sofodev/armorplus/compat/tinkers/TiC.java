/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.tinkers;

import com.sofodev.armorplus.compat.tinkers.modifiers.TiCModifiers;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.tools.AbstractToolPulse;
import slimeknights.tconstruct.tools.TinkerMaterials;

import static slimeknights.tconstruct.library.TinkerRegistry.getMaterial;

/**
 * @author Sokratis Fotkatzikis
 */
public class TiC extends AbstractToolPulse {

    private static final TiC INSTANCE = new TiC();
    public static final TiCMaterials tinkersMaterials = new TiCMaterials();
    public static final TiCModifiers tiCModifiers = new TiCModifiers();
    public static final MaterialIntegration lavacrystalInt = new MaterialIntegration(TiCMaterials.INFUSED_LAVA_CRYSTAL);
    public static final MaterialIntegration steelInt = new MaterialIntegration(TinkerMaterials.steel, TinkerFluids.steel);
    public static final MaterialIntegration obsidianInt = new MaterialIntegration(TiCMaterials.COMPRESSED_OBSIDIAN);
    public static final MaterialIntegration infusedObsidianInt = new MaterialIntegration(TiCMaterials.LAVA_INFUSED_OBSIDIAN);

    public static TiC instance() {
        return INSTANCE;
    }

    public static void initRepresentativeItem(MaterialIntegration material, String oreDictItem) {
        material.setRepresentativeItem(oreDictItem);
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.initMaterials(lavacrystalInt);
        this.initMaterials(infusedObsidianInt);
        this.initMaterials(obsidianInt);
        if (getMaterial("steel") == null) this.initMaterials(steelInt);
        tinkersMaterials.setupMaterialStats(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        tiCModifiers.init(event);
        tinkersMaterials.setupMaterials(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        initRepresentativeItem(lavacrystalInt, "blockInfusedLavaCrystal");
        initRepresentativeItem(obsidianInt, "blockCompressedObsidian");
        initRepresentativeItem(infusedObsidianInt, "blockLavaInfusedObsidian");
        tinkersMaterials.postInit(event);
    }

    public void initMaterials(MaterialIntegration material) {
        TinkerRegistry.addMaterial(material.material);
        TinkerRegistry.integrate(material);
    }
}