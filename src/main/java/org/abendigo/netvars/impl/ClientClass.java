package org.abendigo.netvars.impl;

import static org.abendigo.OffsetManager.csgo;

/**
 * Created by Jonathan on 11/16/2015.
 */
public final class ClientClass {

	private int base;

	public ClientClass(int base) {
		this.base = base;
	}

	public int classId() {
		return csgo.readInt(base + 0x14);
	}

	public String className() {
		return csgo.readString(csgo.readInt(base + 0x8), 64);
	}

	public int next() {
		return csgo.readInt(base + 0x10);
	}

	public int table() {
		return csgo.readInt(base + 0xC);
	}

	public boolean readable() {
		return csgo.canRead(base, 0x28);
	}

}
