/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotationalInduction.Auxiliary;

import java.util.ArrayList;

import Reika.RotationalInduction.Base.NetworkTileEntity;
import Reika.RotationalInduction.TileEntities.TileEntityGenerator;
import Reika.RotationalInduction.TileEntities.TileEntityMotor;
import Reika.RotationalInduction.TileEntities.TileEntityWire;

public class WireNetwork {

	private ArrayList<TileEntityWire> wires = new ArrayList();
	private ArrayList<TileEntityMotor> sinks = new ArrayList();
	private ArrayList<TileEntityGenerator> sources = new ArrayList();

	public static final int TORQUE_PER_AMP = 8;

	public WireNetwork() {

	}

	private int getMaxInputVoltage() {
		int max = 0;
		for (int i = 0; i < sources.size(); i++) {
			TileEntityGenerator e = sources.get(i);
			max = Math.max(max, e.getGenVoltage());
		}
		return max;
	}

	public int getNetworkVoltage() {
		return this.getMaxInputVoltage();
	}

	public int getInputCurrent() {
		int max = this.getMaxInputVoltage();
		int current = 0;
		for (int i = 0; i < sources.size(); i++) {
			TileEntityGenerator e = sources.get(i);
			if (max == e.getGenVoltage()) {
				current += e.getGenCurrent();
			}
		}
		return current;
	}

	public int getNumberMotors() {
		return sinks.size();
	}

	public int getCurrentPerOutput() {
		return this.getInputCurrent()/this.getNumberMotors();
	}

	public void tick() {
		int v = this.getNetworkVoltage();
		int a = this.getInputCurrent();
		for (int i = 0; i < wires.size(); i++) {
			TileEntityWire wire = wires.get(i);
			if (a > wire.getCurrentLimit())
				wire.overCurrent();
			if (v > wire.getVoltageLimit())
				wire.overVoltage();
		}
		for (int i = 0; i < sinks.size(); i++) {
			TileEntityMotor sink = sinks.get(i);
			if (a > sink.getCurrentLimit())
				sink.overCurrent();
			if (v > sink.getVoltageLimit())
				sink.overVoltage();
		}
		for (int i = 0; i < sources.size(); i++) {
			TileEntityGenerator source = sources.get(i);
			if (a > source.getCurrentLimit())
				source.overCurrent();
			if (v > source.getVoltageLimit())
				source.overVoltage();
		}
	}

	public void merge(WireNetwork n) {
		if (n != this) {
			for (int i = 0; i < n.wires.size(); i++) {
				TileEntityWire wire = n.wires.get(i);
				wire.setNetwork(this);
			}
			for (int i = 0; i < n.sinks.size(); i++) {
				TileEntityMotor emitter = n.sinks.get(i);
				emitter.setNetwork(this);
			}
			for (int i = 0; i < n.sources.size(); i++) {
				TileEntityGenerator source = n.sources.get(i);
				source.setNetwork(this);
			}
		}
	}

	private void clear() {
		for (int i = 0; i < wires.size(); i++) {
			wires.get(i).resetNetwork();
		}
		for (int i = 0; i < sinks.size(); i++) {
			sinks.get(i).resetNetwork();
		}
		for (int i = 0; i < sources.size(); i++) {
			sources.get(i).resetNetwork();
		}

		wires.clear();
		sinks.clear();
		sources.clear();
	}

	public void addElement(NetworkTileEntity te) {
		if (te instanceof TileEntityGenerator)
			sources.add((TileEntityGenerator)te);
		else if (te instanceof TileEntityMotor)
			sinks.add((TileEntityMotor)te);
		else
			wires.add((TileEntityWire)te);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getInputCurrent()+"A @ "+this.getNetworkVoltage()+"V");
		sb.append(" ");
		sb.append(wires.size()+" wires, "+sinks.size()+" emitters, "+sources.size()+" generators");
		sb.append("["+this.hashCode()+"]");
		return sb.toString();
	}

	public void removeElement(NetworkTileEntity te) {
		if (te instanceof TileEntityGenerator)
			sources.remove(te);
		else if (te instanceof TileEntityMotor)
			sinks.remove(te);
		else
			wires.remove(te);
		this.rebuild();
	}

	private void rebuild() {
		ArrayList<NetworkTileEntity> li = new ArrayList();
		for (int i = 0; i < wires.size(); i++) {
			li.add(wires.get(i));
		}
		for (int i = 0; i < sinks.size(); i++) {
			li.add(sinks.get(i));
		}
		for (int i = 0; i < sources.size(); i++) {
			li.add(sources.get(i));
		}
		this.clear();

		for (int i = 0; i < li.size(); i++) {
			NetworkTileEntity te = li.get(i);
			te.findAndJoinNetwork(te.worldObj, te.xCoord, te.yCoord, te.zCoord);
		}
	}

}
