package org.abendigo.netvars.impl;

import static org.abendigo.Main.csgo;

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
		return csgo.readMemory(base, 4).getInt() + (id * 0x3C);
	}

	public String tableName() {
		byte[] bytes = new byte[32];
		csgo.readMemory(csgo.readMemory(base + 0xC, 4).getInt(), bytes.length).get(bytes);
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == 0) {
				bytes[i] = 32;
			}
		}
		return new String(bytes).split(" ")[0].trim();
	}

	public String classNameA() {
	/*	auto toReturn = GetTableName();
		toReturn.replace( toReturn.begin(), toReturn.begin() + 3, "C" );
		return toReturn;*/

		return "";
	}

	public int propCount() {
		return csgo.readMemory(base + 0x4, 4).getInt();
	}

	public boolean readable() {
		try {
			return csgo.readMemory(base, offset) != null;
		} catch (Exception e) {
			return false;
		}
	}


}
