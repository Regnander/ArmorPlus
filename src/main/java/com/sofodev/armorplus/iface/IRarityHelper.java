/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.iface;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.util.text.TextFormatting.fromColorIndex;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.minecraftforge.common.util.EnumHelper.addRarity;

/**
 * @author Sokratis Fotkatzikis
 **/
public interface IRarityHelper {

    default EnumRarity getRarity(String enumName, int formatting, String displayName) {
        return addRarity(enumName, fromColorIndex(formatting), displayName);
    }

    default EnumRarity getRarity(String enumName, TextFormatting formatting, String displayName) {
        return addRarity(enumName, formatting, displayName);
    }

    default EnumRarity getRarity(String enumName, String formatting, String displayName) {
        return addRarity(enumName, getValueByName(formatting), displayName);
    }

    /**
     * @param stack the ItemStack that is getting the rarity
     * @return the color of the ItemStack (the `rarity`)
     */
    EnumRarity getRarity(ItemStack stack);

}
