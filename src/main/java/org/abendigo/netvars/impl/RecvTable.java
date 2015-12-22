package org.abendigo.netvars.impl;

import org.abendigo.misc.Strings;

import static org.abendigo.OffsetManager.csgo;

/**
 * Created by Jonathan on 11/16/2015.
 */
public final class RecvTable {

	private int base;
	private int offset;

	public RecvTable(int base) {
		this.base = base;
		this.offset = 0x10;
	}

	public int propForId(int id) {
		return csgo.read(base, 4).getInt() + (id * 0x3C);
	}

	public String tableName() {
		byte[] bytes = new byte[32];
		csgo.read(csgo.read(base + 0xC, 4).getInt(), bytes.length).get(bytes);
		return Strings.transform(bytes);
	}

	public int propCount() {
		return csgo.read(base + 0x4, 4).getInt();
	}

	public boolean readable() {
		try {
			return csgo.read(base, offset) != null;
		} catch (Exception e) {
			return false;
		}
	}


}
