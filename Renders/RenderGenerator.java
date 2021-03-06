/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2017
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ElectriCraft.Renders;

import org.lwjgl.opengl.GL11;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;

import Reika.DragonAPI.Interfaces.TileEntity.RenderFetcher;
import Reika.ElectriCraft.Base.ElectriTERenderer;
import Reika.ElectriCraft.TileEntities.TileEntityGenerator;
import Reika.RotaryCraft.Auxiliary.IORenderer;
import Reika.RotaryCraft.ModInterface.ModelGenerator;

public class RenderGenerator extends ElectriTERenderer
{

	private ModelGenerator GeneratorModel = new ModelGenerator();

	public void renderTileEntityGeneratorAt(TileEntityGenerator tile, double par2, double par4, double par6, float par8)
	{
		int var9;

		if (!tile.isInWorld())
			var9 = 0;
		else
			var9 = tile.getBlockMetadata();

		ModelGenerator var14;
		var14 = GeneratorModel;

		this.bindTextureByName("/Reika/ElectriCraft/Textures/generatortex.png");

		this.setupGL(tile, par2, par4, par6);

		int var11 = 0;
		float var13;
		switch(tile.getFacing()) {
		case EAST:
			var11 = 270;
			break;
		case WEST:
			var11 = 90;
			break;
		case NORTH:
			var11 = 180;
			break;
		case SOUTH:
			var11 = 0;
			break;
		default:
			break;
		}

		GL11.glRotatef(var11+180, 0.0F, 1.0F, 0.0F);
		if (tile.isFlipped && tile.getFacing().offsetZ != 0) {
			GL11.glRotated(180, 0, 1, 0);
		}
		var14.renderAll(tile, null);

		this.closeGL(tile);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double par2, double par4, double par6, float par8)
	{
		if (this.doRenderModel((TileEntityGenerator)tile))
			this.renderTileEntityGeneratorAt((TileEntityGenerator)tile, par2, par4, par6, par8);
		if (((TileEntityGenerator) tile).isInWorld() && MinecraftForgeClient.getRenderPass() == 1) {
			IORenderer.renderIO(tile, par2, par4, par6);
		}
	}

	@Override
	public String getImageFileName(RenderFetcher te) {
		return "generatortex.png";
	}
}
