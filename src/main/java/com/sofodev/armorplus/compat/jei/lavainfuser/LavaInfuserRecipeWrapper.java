/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.jei.lavainfuser;

import com.sofodev.armorplus.compat.crafttweaker.lavainfuser.LavaInfuserRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class LavaInfuserRecipeWrapper implements IRecipeWrapper {

    public final LavaInfuserRecipe recipe;

    public LavaInfuserRecipeWrapper(LavaInfuserRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ItemStack recipeOutput = recipe.output;

        ingredients.setInput(VanillaTypes.ITEM, recipe.input);
        if (!recipeOutput.isEmpty()) {
            ingredients.setOutput(VanillaTypes.ITEM, recipeOutput);
        }
    }

}