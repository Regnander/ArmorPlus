/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.render

import net.minecraft.client.renderer.entity.RenderArrow
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.entity.entityarrow.EntityEnderDragonArrow
import net.thedragonteam.armorplus.util.Utils.setRL

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
class RenderEnderDragonArrow(rm: RenderManager) : RenderArrow<EntityEnderDragonArrow>(rm) {

    public override fun getEntityTexture(entity: EntityEnderDragonArrow): ResourceLocation? {
        return res
    }

    companion object {

        private val res = setRL("textures/entity/projectiles/ender_dragon_arrow.png")
    }

}