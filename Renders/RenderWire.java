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

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import Reika.DragonAPI.Interfaces.TileEntity.RenderFetcher;
import Reika.DragonAPI.Libraries.IO.ReikaTextureHelper;
import Reika.ElectriCraft.Base.ElectriTERenderer;
import Reika.ElectriCraft.TileEntities.TileEntityWire;

public class RenderWire extends ElectriTERenderer {

	@Override
	public String getImageFileName(RenderFetcher te) {
		return "";
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double par2, double par4, double par6, float f) {
		TileEntityWire te = (TileEntityWire)tile;
		if (tile.hasWorldObj()) {

		}
		else {
			ReikaTextureHelper.bindTerrainTexture();
			this.renderBlock(te, par2, par4-0.3, par6, te.insulated ? te.getInsulatedEndIcon() : te.getEndIcon());
			this.renderBlock(te, par2, par4+0.1, par6, te.insulated ? te.getInsulatedCenterIcon() : te.getCenterIcon());
			this.renderBlock(te, par2, par4+0.5, par6, te.insulated ? te.getInsulatedEndIcon() : te.getEndIcon());
		}
	}

	private void renderBlock(TileEntityWire te, double par2, double par4, double par6, IIcon ico) {
		float u = ico.getMinU();
		float v = ico.getMinV();
		float du = ico.getMaxU();
		float dv = ico.getMaxV();
		GL11.glPushMatrix();
		GL11.glTranslated(par2, par4, par6);
		Tessellator v5 = Tessellator.instance;

		float f = 0.5F;
		double s = 0.4;
		GL11.glColor4f(f, f, f, 1);
		GL11.glScaled(s, s, s);
		GL11.glTranslated(0.5, 0.375, 0.5);
		v5.startDrawingQuads();
		v5.setNormal(0, 1, 0);
		v5.addVertexWithUV(0, 0, 1, u, v);
		v5.addVertexWithUV(1, 0, 1, du, v);
		v5.addVertexWithUV(1, 1, 1, du, dv);
		v5.addVertexWithUV(0, 1, 1, u, dv);
		v5.draw();

		v5.startDrawingQuads();
		v5.setNormal(0, 1, 0);
		v5.addVertexWithUV(0, 1, 0, u, dv);
		v5.addVertexWithUV(1, 1, 0, du, dv);
		v5.addVertexWithUV(1, 0, 0, du, v);
		v5.addVertexWithUV(0, 0, 0, u, v);
		v5.draw();

		f = 0.33F;
		GL11.glColor4f(f, f, f, 1);
		v5.startDrawingQuads();
		v5.setNormal(0, 1, 0);
		v5.addVertexWithUV(1, 1, 0, u, dv);
		v5.addVertexWithUV(1, 1, 1, du, dv);
		v5.addVertexWithUV(1, 0, 1, du, v);
		v5.addVertexWithUV(1, 0, 0, u, v);
		v5.draw();

		v5.startDrawingQuads();
		v5.setNormal(0, 1, 0);
		v5.addVertexWithUV(0, 0, 0, u, v);
		v5.addVertexWithUV(0, 0, 1, du, v);
		v5.addVertexWithUV(0, 1, 1, du, dv);
		v5.addVertexWithUV(0, 1, 0, u, dv);
		v5.draw();

		f = 1F;
		GL11.glColor4f(f, f, f, 1);
		v5.startDrawingQuads();
		v5.setNormal(0, 1, 0);
		v5.addVertexWithUV(0, 1, 1, u, dv);
		v5.addVertexWithUV(1, 1, 1, du, dv);
		v5.addVertexWithUV(1, 1, 0, du, v);
		v5.addVertexWithUV(0, 1, 0, u, v);
		v5.draw();

		v5.startDrawingQuads();
		v5.setNormal(0, 1, 0);
		v5.addVertexWithUV(0, 0, 0, u, v);
		v5.addVertexWithUV(1, 0, 0, du, v);
		v5.addVertexWithUV(1, 0, 1, du, dv);
		v5.addVertexWithUV(0, 0, 1, u, dv);
		v5.draw();
		GL11.glPopMatrix();
		v5.setColorOpaque(255, 255, 255);
	}

}
