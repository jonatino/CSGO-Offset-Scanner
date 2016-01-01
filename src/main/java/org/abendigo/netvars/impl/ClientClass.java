package org.abendigo.netvars.impl;

import org.abendigo.misc.Strings;

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

	public String className() {
		byte[] bytes = new byte[64];
		csgo.read(csgo.read(base + 0x8, 4).getInt(), bytes.length).get(bytes);
		return Strings.transform(bytes);
	}

	public int next() {
		return csgo.read(base + 0x10, 4).getInt();
	}

	public int table() {
		return csgo.read(base + 0xC, 4).getInt();
	}

	public boolean readable() {
		return csgo.canRead(base, 0x28);
	}

}
