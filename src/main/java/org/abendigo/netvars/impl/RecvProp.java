package org.abendigo.netvars.impl;

import static org.abendigo.OffsetManager.csgo;


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
		return csgo.readInt(base + 0x28);
	}

	public String name() {
		return csgo.readString(csgo.readInt(base), 64);
	}

	public int offset() {
		return offset + csgo.readInt(base + 0x2C);
	}

	public int type() {
		return csgo.readInt(base + 0x4);
	}

	public int elements() {
		return csgo.readInt(base + 0x34);
	}

	public int stringBufferCount() {
		return csgo.readInt(base + 0xC);
	}

}
