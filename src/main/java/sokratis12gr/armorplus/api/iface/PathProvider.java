package sokratis12gr.armorplus.api.iface;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PathProvider {

    String pathFor(ItemStack stack);

    String pathFor(World world, BlockPos pos);
}