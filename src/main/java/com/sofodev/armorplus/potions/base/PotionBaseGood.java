/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.potions.base;

/**
 * @author Sokratis Fotkatzikis
 **/
public class PotionBaseGood extends PotionBase {

    public PotionBaseGood(int liquidColor, String name) {
        super(false, liquidColor, name);
        this.setBeneficial();
    }
}
