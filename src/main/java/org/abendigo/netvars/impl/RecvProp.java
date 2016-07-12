package org.abendigo.netvars.impl;

import static org.abendigo.OffsetManager.process;


/**
 * Created by Jonathan on 11/16/2015.
 */
public final class RecvProp {

	private int base;

	public RecvProp setBase(int base) {
		this.base = base;
		return this;
	}

	private int offset;

	public RecvProp setOffset(int offset) {
		this.offset = offset;
		return this;
	}

	public int table() {
		return process().readInt(base + 0x28);
	}

	public String name() {
		return process().readString(process().readInt(base), 64);
	}

	public int offset() {
		return offset + process().readInt(base + 0x2C);
	}

	public int type() {
		return process().readInt(base + 0x4);
	}

	public int elements() {
		return process().readInt(base + 0x34);
	}

	public int stringBufferCount() {
		return process().readInt(base + 0xC);
	}

}
