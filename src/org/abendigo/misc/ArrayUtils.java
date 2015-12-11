package org.abendigo.misc;

/**
 * Created by Jonathan on 11/16/2015.
 */
public final class ArrayUtils {

	public static byte[] toByteArray(int value) {
		return new byte[]{(byte) value, (byte) (value >> 8), (byte) (value >> 16), (byte) (value >> 24)};
	}

	public static byte[] toByteArray(int... value) {
		byte[] byteVals = new byte[value.length];
		for (int i = 0; i < value.length; i++) {
			byteVals[i] = (byte) value[i];
		}
		return byteVals;
	}


}
