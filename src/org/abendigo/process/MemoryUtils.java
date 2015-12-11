package org.abendigo.process;

import org.abendigo.misc.ArrayUtils;

import java.nio.ByteBuffer;

/**
 * Created by Jonathan on 11/15/2015.
 */
public final class MemoryUtils {

	public static final int READ = 1, SUBTRACT = 2;

	private static Module lastModule;
	private static ByteBuffer moduleData;

	public static int getAddressForPattern(Module module, int pattern_offset, int address_offset, int flags, String className) {
		return getAddressForPattern(module, pattern_offset, address_offset, flags, className.getBytes());
	}

	public static int getAddressForPattern(Module module, int pattern_offset, int address_offset, int flags, int value) {
		return getAddressForPattern(module, pattern_offset, address_offset, flags, ArrayUtils.toByteArray(value));
	}

	public static int getAddressForPattern(Module module, int pattern_offset, int address_offset, int flags, int... values) {
		return getAddressForPattern(module, pattern_offset, address_offset, flags, ArrayUtils.toByteArray(values));
	}

	public static int getAddressForPattern(Module module, int pattern_offset, int address_offset, int flags, byte... values) {
		if (lastModule == null || !lastModule.equals(module)) {
			moduleData = module.process().readMemory(module.pointer(), module.size());
			lastModule = module;
		}

		long off = module.size() - values.length;
		for (int i = 0; i < off; i++) {
			if (checkMask(moduleData, i, values)) {
				i += module.address() + pattern_offset;
				if ((flags & READ) == READ) {
					i = module.process().readMemory(i, 4).getInt();
				}
				if ((flags & SUBTRACT) == SUBTRACT) {
					i -= module.address();
				}
				return i + address_offset;
			}
		}

		return -1;
	}

	private static boolean checkMask(ByteBuffer data, int offset, byte[] pMask) {
		for (int i = 0; i < pMask.length; i++) {
			if (pMask[i] != 0x00 && (pMask[i] != data.get(offset + i))) {
				return false;
			}
		}
		return true;
	}

}
