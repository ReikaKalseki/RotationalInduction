/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2017
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
// Date: 21/09/2014 11:26:11 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package Reika.ElectriCraft.Renders;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.tileentity.TileEntity;

import Reika.DragonAPI.Instantiable.Rendering.LODModelPart;
import Reika.RotaryCraft.Base.RotaryModelBase;

public class ModelTransformer extends RotaryModelBase
{
	//fields
	LODModelPart Shape1;
	LODModelPart Shape1a;
	LODModelPart Shape2;
	LODModelPart Shape2a;
	LODModelPart Shape3;
	LODModelPart Shape3a;

	public ModelTransformer()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new LODModelPart(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 4, 16, 4);
		Shape1.setRotationPoint(2F, 8F, -2F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		this.setRotation(Shape1, 0F, 0F, 0F);
		Shape1a = new LODModelPart(this, 17, 0);
		Shape1a.addBox(0F, 0F, 0F, 4, 16, 4);
		Shape1a.setRotationPoint(-6F, 8F, -2F);
		Shape1a.setTextureSize(64, 32);
		Shape1a.mirror = true;
		this.setRotation(Shape1a, 0F, 0F, 0F);
		Shape2 = new LODModelPart(this, 17, 21);
		Shape2.addBox(0F, 0F, 0F, 4, 4, 4);
		Shape2.setRotationPoint(-2F, 20F, -2F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		this.setRotation(Shape2, 0F, 0F, 0F);
		Shape2a = new LODModelPart(this, 0, 21);
		Shape2a.addBox(0F, 0F, 0F, 4, 4, 4);
		Shape2a.setRotationPoint(-2F, 8F, -2F);
		Shape2a.setTextureSize(64, 32);
		Shape2a.mirror = true;
		this.setRotation(Shape2a, 0F, 0F, 0F);
		Shape3 = new LODModelPart(this, 34, 8);
		Shape3.addBox(0F, 0F, 0F, 6, 1, 6);
		Shape3.setRotationPoint(1F, 12.5F, -3F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		this.setRotation(Shape3, 0F, 0F, 0F);
		Shape3a = new LODModelPart(this, 34, 0);
		Shape3a.addBox(0F, 0F, 0F, 6, 1, 6);
		Shape3a.setRotationPoint(-7F, 12.5F, -3F);
		Shape3a.setTextureSize(64, 32);
		Shape3a.mirror = true;
		this.setRotation(Shape3a, 0F, 0F, 0F);
	}

	@Override
	public void renderAll(TileEntity te, ArrayList li, float phi, float theta)
	{
		Shape1.render(te, f5);
		Shape1a.render(te, f5);
		Shape2.render(te, f5);
		Shape2a.render(te, f5);

		this.renderCoils(te, li);
	}

	private void renderCoils(TileEntity te, ArrayList li) {

		LODModelPart[] s = {Shape3, Shape3a};
		for (int c = 0; c <= 1; c++) {
			int n1 = (Integer)li.get(c);
			GL11.glPushMatrix();
			int n;
			double ty;
			double dy;
			double sc;
			if (n1 == 1) {
				n = 0;
				dy = sc = ty = 0;
				for (int i = 0; i < 4; i++) {
					GL11.glPushMatrix();
					GL11.glTranslated(0, i/8D, 0);
					s[c].render(te, f5);
					GL11.glPopMatrix();
				}
			}
			else if (n1 < 16) {
				n = 6;
				sc = 0.75;
				dy = 0.25;
				ty = 12;
			}
			else if (n1 < 128) {
				n = 8;
				sc = 0.5;
				dy = 0.75;
				ty = 16;
			}
			else {
				n = 16;
				sc = 0.25;
				dy = 2.25;
				ty = 32;
			}

			for (int i = 0; i < n; i++) {
				GL11.glPushMatrix();
				GL11.glTranslated(0, i/ty, 0);
				GL11.glScaled(1, sc, 1);
				GL11.glTranslated(0, dy, 0);
				s[c].render(te, f5);
				GL11.glPopMatrix();
			}

			GL11.glPopMatrix();
		}
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

}
