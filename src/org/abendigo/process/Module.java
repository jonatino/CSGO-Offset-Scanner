package org.abendigo.process;


import com.sun.jna.Pointer;

public final class Module {

	private final GameProcess process;
	private final String name;
	private final int address;
	private final int size;
	private final Pointer pointer;

	public Module(GameProcess process, String name, int address, int size) {
		this.process = process;
		this.name = name;
		this.address = address;
		this.size = size;
		this.pointer = new Pointer(address);
	}

	public GameProcess process() {
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

	public int address() {
		return address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Module)) {
			return false;
		}

		Module module = (Module) o;

		if (address != module.address) {
			return false;
		}
		if (size != module.size) {
			return false;
		}
		if (name != null ? !name.equals(module.name) : module.name != null) {
			return false;
		}
		return pointer.equals(module.pointer);
	}

}
