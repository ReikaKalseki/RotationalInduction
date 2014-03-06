package Reika.RotationalInduction.Items;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import Reika.DragonAPI.Libraries.World.ReikaWorldHelper;
import Reika.RotationalInduction.Induction;
import Reika.RotationalInduction.Registry.InductionItems;
import Reika.RotationalInduction.Registry.InductionTiles;
import Reika.RotationalInduction.Registry.WireType;
import Reika.RotationalInduction.TileEntities.TileEntityWire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWirePlacer extends Item {

	public ItemWirePlacer(int par1, int tex) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		maxStackSize = 64;
		this.setCreativeTab(Induction.tabInduction);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer ep, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
		if (!ReikaWorldHelper.softBlocks(world, x, y, z) && world.getBlockMaterial(x, y, z) != Material.water && world.getBlockMaterial(x, y, z) != Material.lava) {
			if (side == 0)
				--y;
			if (side == 1)
				++y;
			if (side == 2)
				--z;
			if (side == 3)
				++z;
			if (side == 4)
				--x;
			if (side == 5)
				++x;
			if (!ReikaWorldHelper.softBlocks(world, x, y, z) && world.getBlockMaterial(x, y, z) != Material.water && world.getBlockMaterial(x, y, z) != Material.lava)
				return false;
		}
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1);
		List inblock = world.getEntitiesWithinAABB(EntityLivingBase.class, box);
		if (inblock.size() > 0)
			return false;
		if (!ep.canPlayerEdit(x, y, z, 0, is))
			return false;
		else
		{
			if (!ep.capabilities.isCreativeMode)
				--is.stackSize;
			int meta = is.getItemDamage()%WireType.INS_OFFSET;
			world.setBlock(x, y, z, InductionTiles.WIRE.getBlockID(), meta, 3);
		}
		world.playSoundEffect(x+0.5, y+0.5, z+0.5, InductionTiles.WIRE.getPlaceSound(), 1F, 1.5F);
		TileEntityWire te = (TileEntityWire)world.getBlockTileEntity(x, y, z);
		te.insulated = is.getItemDamage() >= WireType.INS_OFFSET;
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 32; i++) {
			ItemStack item = new ItemStack(par1, 1, i);
			if (InductionItems.WIRE.isAvailableInCreative(item))
				par3List.add(item);
		}
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public final String getUnlocalizedName(ItemStack is)
	{
		int d = is.getItemDamage();
		return super.getUnlocalizedName() + "." + String.valueOf(d);
	}

	@Override
	public final void registerIcons(IconRegister ico) {}

}