/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotationalInduction.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Reika.RotationalInduction.Base.InductionBlock;
import Reika.RotationalInduction.Registry.InductionTiles;

public class BlockConverter extends InductionBlock {

	public BlockConverter(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return InductionTiles.createTEFromIDAndMetadata(blockID, meta);
	}

}
