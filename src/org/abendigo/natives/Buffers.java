package org.abendigo.natives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Buffers {

	private static final int ALLOCATION_SIZE = 8_192_000;

	private static ByteBuffer currentBuffer = null;

	private static ByteBuffer allocate(int size) {
		if (size >= ALLOCATION_SIZE) {
			return ByteBuffer.allocateDirect(size);
		}
		if (currentBuffer == null || size > currentBuffer.remaining()) {
			currentBuffer = ByteBuffer.allocateDirect(ALLOCATION_SIZE);
		}
		currentBuffer.limit(currentBuffer.position() + size);
		ByteBuffer result = currentBuffer.slice();
		currentBuffer.position(currentBuffer.limit());
		currentBuffer.limit(currentBuffer.capacity());
		return result;
	}

	public static ByteBuffer nativeBuffer(int size) {
		return allocate(size).order(ByteOrder.nativeOrder());
	}

}
