package org.abendigo.netvars.impl;

import static org.abendigo.Main.csgo;

/**
 * Created by Jonathan on 11/16/2015.
 */
public final class ClientClass {

	private int base;
	private int offset;

	public ClientClass(int base) {
		this.base = base;
		this.offset = 0x28;
	}

	public int classId() {
		return csgo.readMemory(base + 0x14, 4).getInt();
	}

	public int next() {
		return csgo.readMemory(base + 0x10, 4).getInt();
	}

	public int table() {
		return csgo.readMemory(base + 0xC, 4).getInt();
	}

	public boolean readable() {
		try {
			return csgo.readMemory(base, offset) != null;
		} catch (Exception e) {
			return false;
		}
	}

}
