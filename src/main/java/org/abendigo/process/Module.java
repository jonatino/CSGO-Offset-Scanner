package org.abendigo.process;


import com.sun.jna.Pointer;

import java.nio.ByteBuffer;

public final class Module {

	private final NativeProcess process;
	private final String name;
	private final long address;
	private final int size;
	private final Pointer pointer;
	private ByteBuffer data;

	public Module(NativeProcess process, String name, long address, int size) {
		this.process = process;
		this.name = name;
		this.address = address;
		this.size = size;
		this.pointer = new Pointer(address);
	}

	public NativeProcess process() {
		return process;
	}

	public Pointer pointer() {
		return pointer;
	}

	public String name() {
		return name;
	}

	public int size() {
		return size;
	}

	public long address() {
		return address;
	}

	public ByteBuffer data() {
		return data(false);
	}

	public ByteBuffer data(boolean forceNew) {
		if (forceNew || data == null) {
			data = process().read(pointer(), size());
		}
		return data;
	}

}
