package org.abendigo;

import org.abendigo.offsets.Offsets;

/**
 * Created by Jonathan on 12/22/2015.
 */
public final class Main {

	public static void main(String... args) {
		OffsetManager.initAll();

		//0xc554
		//0x0000C550
		System.out.println(Offsets.m_iCrossHairID);
	}

}
