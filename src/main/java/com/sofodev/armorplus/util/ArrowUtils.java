/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.showInfo;
import static com.sofodev.armorplus.util.TextUtils.translatedText;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ArrowUtils {

    @SideOnly(value = Side.CLIENT)
    public static void addArrowInformation(List<String> tooltip, String effect, double damage, TextFormatting formatting) {
        KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(translatedText("item.armorplus.arrow.ability_desc", effect));
            tooltip.add(translatedText("item.armorplus.arrow.ability", damage));
        } else {
            showInfo(tooltip, keyBindSneak, formatting);
        }
    }
}