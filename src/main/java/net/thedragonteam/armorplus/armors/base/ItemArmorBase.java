/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.util.EnumTiers;

import java.util.List;
import java.util.Objects;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModItems.enderDragon;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.*;
import static net.thedragonteam.armorplus.util.Utils.setLocation;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.ParticlesHelper.spawnParticle;
import static net.thedragonteam.thedragonlib.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.thedragonlib.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.thedragonlib.util.PotionUtils.*;

public class ItemArmorBase extends ItemArmor {

    public static ArmorMaterial coalArmor = addArmorMaterial("COAL", setLocation("coal_armor"), 7,
            coalArmorProtectionPoints, coalArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial emeraldArmor = addArmorMaterial("EMERALD", setLocation("emerald_armor"), 35,
            emeraldArmorProtectionPoints, emeraldArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial lapisArmor = addArmorMaterial("LAPIS", setLocation("lapis_armor"), 11,
            lapisArmorProtectionPoints, lapisArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial lavaArmor = addArmorMaterial("LAVA", setLocation("lava_armor"), 45,
            lavaArmorProtectionPoints, lavaArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial obsidianArmor = addArmorMaterial("OBSIDIAN", setLocation("obsidian_armor"), 40,
            obsidianArmorProtectionPoints, obsidianArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial redstoneArmor = addArmorMaterial("REDSTONE", setLocation("redstone_armor"), 11,
            redstoneArmorProtectionPoints, redstoneArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial chickenArmor = addArmorMaterial("CHICKEN", setLocation("chicken_armor"), 3,
            chickenArmorProtectionPoints, chickenArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial slimeArmor = addArmorMaterial("SLIME", setLocation("slime_armor"), 3,
            slimeArmorProtectionPoints, slimeArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial arditeArmor = addArmorMaterial("ARDITE", setLocation("ardite_armor"), 55,
            arditeArmorProtectionPoints, arditeArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial cobaltArmor = addArmorMaterial("COBALT", setLocation("cobalt_armor"), 44,
            cobaltArmorProtectionPoints, cobaltArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial knightSlimeArmor = addArmorMaterial("KNIGHT_SLIME", setLocation("knight_slime_armor"), 33,
            knightSlimeArmorProtectionPoints, knightSlimeArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial manyullynArmor = addArmorMaterial("MANYULLYN", setLocation("manyullyn_armor"), 66,
            manyullynArmorProtectionPoints, manyullynArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial pigIronArmor = addArmorMaterial("PIG_IRON", setLocation("pig_iron_armor"), 33,
            pigIronArmorProtectionPoints, pigIronArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial enderDragonArmor = addArmorMaterial("ENDER_DRAGON", setLocation("ender_dragon_armor"), 60,
            enderDragonArmorProtectionPoints, enderDragonArmorToughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial guardianArmor = addArmorMaterial("GUARDIAN", setLocation("guardian_armor"), 50,
            guardianArmorProtectionPoints, guardianArmorToughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial superStarArmor = addArmorMaterial("SUPER_STAR", setLocation("super_star_armor"), 50,
            superStarArmorProtectionPoints, superStarArmorToughnessPoints, EnumTiers.TIER_3);
    public static EnumAction wear = addAction("WEAR");
    public EnumRarity formattingName;
    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    private APArmorMaterial material;
    private EntityEquipmentSlot slot;

    public ItemArmorBase(APArmorMaterial armorMaterial, EntityEquipmentSlot slot) {
        super(armorMaterial.getArmorMaterial(), 0, slot);
        this.itemEasy = armorMaterial.getItemEasy();
        this.itemExpert = armorMaterial.getItemExpert();
        this.formatting = armorMaterial.getFormatting();
        this.material = armorMaterial;
        this.slot = slot;
        setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = armorMaterial.getName() + "_boots";
                setRegistryName(boots);
                setUnlocalizedName(setName(boots));
                break;
            case LEGS:
                String leggings = armorMaterial.getName() + "_leggings";
                setRegistryName(leggings);
                setUnlocalizedName(setName(leggings));
                break;
            case CHEST:
                String chestplate = armorMaterial.getName() + "_chestplate";
                setRegistryName(chestplate);
                setUnlocalizedName(setName(chestplate));
                break;
            case HEAD:
                String helmet = armorMaterial.getName() + "_helmet";
                setRegistryName(helmet);
                setUnlocalizedName(setName(helmet));
                break;
        }
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplus);
        formattingName = addRarity("ARMOR_COLOR", formatting, "Armor Color");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(HEAD);
        ItemStack chest = entity.getItemStackFromSlot(CHEST);
        ItemStack legs = entity.getItemStackFromSlot(LEGS);
        ItemStack feet = entity.getItemStackFromSlot(FEET);
        switch (material) {
            case COAL:
                switch (slot) {
                    case FEET:
                        if (enableCoalBEffect && !enableFullCoalArmorEffect)
                            addPotion(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                    case LEGS:
                        if (enableCoalLEffect && !enableFullCoalArmorEffect)
                            addPotion(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                    case CHEST:
                        if (enableCoalCEffect && !enableFullCoalArmorEffect)
                            addPotion(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                    case HEAD:
                        if (enableCoalHEffect && !enableFullCoalArmorEffect)
                            addPotion(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                }
                break;
            case EMERALD:
                switch (slot) {
                    case FEET:
                        if (enableEmeraldBEffect && !enableFullEmeraldArmorEffect)
                            addPotion(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                    case LEGS:
                        if (enableEmeraldLEffect && !enableFullEmeraldArmorEffect)
                            addPotion(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                    case CHEST:
                        if (enableEmeraldCEffect && !enableFullEmeraldArmorEffect)
                            addPotion(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                    case HEAD:
                        if (enableEmeraldHEffect && !enableFullEmeraldArmorEffect)
                            addPotion(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                }
                break;
            case LAPIS:
                switch (slot) {
                    case FEET:
                        if (enableLapisBEffect && !enableFullLapisArmorEffect)
                            addPotion(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                    case LEGS:
                        if (enableLapisLEffect && !enableFullLapisArmorEffect)
                            addPotion(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                    case CHEST:
                        if (enableLapisCEffect && !enableFullLapisArmorEffect)
                            addPotion(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                    case HEAD:
                        if (enableLapisHEffect && !enableFullLapisArmorEffect)
                            addPotion(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                }
                break;
            case LAVA:
                switch (slot) {
                    case FEET:
                        if (enableLavaBEffect && !enableFullLavaArmorEffect)
                            addPotion(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                    case LEGS:
                        if (enableLavaLEffect && !enableFullLavaArmorEffect)
                            addPotion(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                    case CHEST:
                        if (enableLavaCEffect && !enableFullLavaArmorEffect)
                            addPotion(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                    case HEAD:
                        if (enableLavaHEffect && !enableFullLavaArmorEffect)
                            addPotion(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                }
                break;
            case REDSTONE:
                switch (slot) {
                    case FEET:
                        if (enableRedstoneBEffect && !enableFullRedstoneArmorEffect) {
                            addPotion(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case LEGS:
                        if (enableRedstoneLEffect && !enableFullRedstoneArmorEffect) {
                            addPotion(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case CHEST:
                        if (enableRedstoneCEffect && !enableFullRedstoneArmorEffect) {
                            addPotion(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case HEAD:
                        if (enableRedstoneHEffect && !enableFullRedstoneArmorEffect) {
                            addPotion(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                }
                break;
            case OBSIDIAN:
                switch (slot) {
                    case FEET:
                        if (enableObsidianBEffect && !enableFullObsidianArmorEffect)
                            addPotion(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
                        break;
                    case LEGS:
                        if (enableObsidianLEffect && !enableFullObsidianArmorEffect)
                            addPotion(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
                        break;
                    case CHEST:
                        if (enableObsidianCEffect && !enableFullObsidianArmorEffect)
                            addPotion(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
                        break;
                    case HEAD:
                        if (enableObsidianHEffect && !enableFullObsidianArmorEffect)
                            addPotion(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
                        break;
                }
                break;
            case ENDER_DRAGON:
                if (enableFlightAbility) {
                    if (!head.isEmpty() && head.getItem() == enderDragon[0] && !chest.isEmpty() && chest.getItem() == enderDragon[1] && !legs.isEmpty() && legs.getItem() == enderDragon[2] && !feet.isEmpty() && feet.getItem() == enderDragon[3] || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
                if (getPotion(enderDragonArmorRemovePotionEffect) != null)
                    removePotion(entity, getPotion(enderDragonArmorRemovePotionEffect));
                break;
            case SUPER_STAR:
                switch (slot) {
                    case FEET:
                        if (enableSuperStarBEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addPotion(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
                        break;
                    case LEGS:
                        if (enableSuperStarLEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addPotion(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
                        break;
                    case CHEST:
                        if (enableSuperStarCEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addPotion(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
                        break;
                    case HEAD:
                        if (enableSuperStarHEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addPotion(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removePotion(entity, getPotion(superStarArmorRemovePotionEffect));
                        break;
                }
                break;
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    public void lavaEffects(EntityPlayer entity, ItemStack itemStack) {
        if (!enableFullLavaArmorEffect) {
            entity.extinguish();
            entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
        }
        if (entity.isInWater() && !enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
            addPotion(entity, MobEffects.SLOWNESS, 1, BAD);
            itemStack.damageItem(1, entity);
            entity.attackEntityFrom(DamageSource.DROWN, 1F);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (getRD()) {
            case EASY:
                return Objects.equals(repair.getItem(), itemEasy);
            case EXPERT:
                return Objects.equals(repair.getItem(), itemExpert);
            case HELLISH:
                return false;
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        switch (material) {
            case COAL:
                if (isKeyDown()) {
                    if (!enableFullCoalArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(coalArmorAddPotionEffect), coalArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(coalArmorAddPotionEffect), coalArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case EMERALD:
                if (isKeyDown()) {
                    if (!enableFullEmeraldArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case LAPIS:
                if (isKeyDown()) {
                    if (!enableFullLapisArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case LAVA:
                if (isKeyDown()) {
                    if (!enableFullLavaArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case REDSTONE:
                if (isKeyDown()) {
                    if (!enableFullRedstoneArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case OBSIDIAN:
                if (isKeyDown()) {
                    if (!enableFullObsidianArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case ENDER_DRAGON:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, "Flight");
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case GUARDIAN:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(guardianArmorAddPotionEffect), guardianArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case SLIME:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(slimeArmorAddPotionEffect), slimeArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case CHICKEN:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(chickenArmorAddPotionEffect), chickenArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case SUPER_STAR:
                if (isKeyDown()) {
                    if (!enableFullSuperStarArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case ARDITE:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(arditeArmorAddPotionEffect), arditeArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case COBALT:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(cobaltArmorAddPotionEffect), cobaltArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case MANYULLYN:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(manyullynArmorAddPotionEffect), manyullynArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case KNIGHT_SLIME:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(knightSlimeArmorAddPotionEffect), knightSlimeArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
            case PIG_IRON:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(pigIronArmorAddPotionEffect), pigIronArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak, formatting);
                break;
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return wear;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}