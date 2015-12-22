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
		return csgo.read(base + 0x14, 4).getInt();
	}

	public int next() {
		return csgo.read(base + 0x10, 4).getInt();
	}

	public int table() {
		return csgo.read(base + 0xC, 4).getInt();
	}

	public boolean readable() {
		try {
			return csgo.read(base, 0x28) != null;
		} catch (Exception e) {
			return false;
		}
	}

}
