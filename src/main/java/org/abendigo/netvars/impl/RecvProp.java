package org.abendigo.netvars.impl;

import static org.abendigo.Main.csgo;


/**
 * Created by Jonathan on 11/16/2015.
 */
public final class RecvProp {

	private int base;
	private int offset;

	public RecvProp(int base, int offset) {
		this.base = base;
		this.offset = offset;
	}

	public int table() {
		return csgo.readMemory(base + 0x28, 4).getInt();
	}

	public String propName() {
		byte[] bytes = new byte[64];
		csgo.readMemory(csgo.readMemory(base, 4).getInt(), bytes.length).get(bytes);
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == 0) {
				bytes[i] = 0x20;
			}
		}
		return new String(bytes).split(" ")[0].trim();
	}

	public int offset() {
		return offset + csgo.readMemory(base + 0x2C, 4).getInt();
	}

	public int type() {
		return csgo.readMemory(base + 0x4, 4).getInt();
	}

	public int elements() {
		return csgo.readMemory(base + 0x34, 4).getInt();
	}

	public int stringBufferCount() {
		return csgo.readMemory(base + 0xC, 4).getInt();
	}

}
