/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.proxy

import net.minecraft.util.datafix.DataFixesManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.client.gui.APTab
import net.thedragonteam.armorplus.commands.CommandArmorPlus
import net.thedragonteam.armorplus.compat.ICompatibility.InitializationPhase.*
import net.thedragonteam.armorplus.registry.*
import net.thedragonteam.armorplus.tileentity.*
import net.thedragonteam.armorplus.worldgen.OreGen
import net.thedragonteam.armorplus.worldgen.nbt.StructureGenNBT
import net.thedragonteam.thedragonlib.util.LogHelper

open class CommonProxy {

    open fun preInit(event: FMLPreInitializationEvent) {
        ModSounds.init()
        ModPotions.registerPotions()
        ModEntities.init()
        ModBlocks.init()
        LogHelper.debug("Blocks Successfully Registered")
        APBlocks.init()
        ModItems.init() // Initializes the items
        LogHelper.debug("Items Successfully Registered")
        APItems.init() // Initializes the helper item class
        registerWorldGenerators()
        registerTileEntities()
        registerFixes()
        ModCompatibility.registerModCompat()
        ModCompatibility.loadCompat(PRE_INIT)
        LogHelper.info("Finished PreInitialization")
    }

    open fun init(event: FMLInitializationEvent) {
        registerEvents()
        ModOreDicts.registerOreDictEntries()
        APTab.initialize()
        ModEnchantments.registerEnchantments()
        ModCompatibility.loadCompat(INIT)
        LogHelper.info("Finished Initialization")
    }

    open fun postInit(event: FMLPostInitializationEvent) {
        ModCompatibility.loadCompat(POST_INIT)
        LogHelper.info("Finished PostInitialization")
    }

    fun modMapping(@Suppress("UNUSED_PARAMETER") event: FMLModIdMappingEvent) {
        ModCompatibility.loadCompat(MAPPING)
    }

    fun serverLoad(event: FMLServerStartingEvent) {
        event.registerServerCommand(CommandArmorPlus())
    }

    fun registerEvents() {
        //Register to receive subscribed events
        MinecraftForge.EVENT_BUS.register(this)
        ModAchievements.init()
        ModRecipes.init()
        LavaInfuserRecipes.init()
    }

    open fun registerModels() {}

    fun registerTileEntities() {
        GameRegistry.registerTileEntityWithAlternatives(TileEntityWorkbench::class.java, "Workbench", "APWorkbench", "WorkbenchTier1", "WorkbenchTierOne")
        GameRegistry.registerTileEntityWithAlternatives(TileEntityHighTechBench::class.java, "HighTechBench", "APHighTechBench", "WorkbenchTier2", "WorkbenchTierTwo")
        GameRegistry.registerTileEntityWithAlternatives(TileEntityUltiTechBench::class.java, "UltiTechBench", "APUltiTechBench", "WorkbenchTier3", "WorkbenchTierThree")
        GameRegistry.registerTileEntityWithAlternatives(TileEntityChampionBench::class.java, "ChampionBench", "APChampionBench", "WorkbenchTier4", "WorkbenchTierFour")
        GameRegistry.registerTileEntityWithAlternatives(TileEntityLavaInfuser::class.java, "LavaInfuserRecipe", "APLavaInfuser")
    }

    fun registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(OreGen(), 1)
        GameRegistry.registerWorldGenerator(StructureGenNBT(), 2)
    }

    fun registerFixes() {
        TileEntityLavaInfuser.registerFixesFurnace(DataFixesManager.createFixer())
    }
}