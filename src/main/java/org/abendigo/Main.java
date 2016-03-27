package org.abendigo;

import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;

import static org.abendigo.offsets.Offsets.m_dwGlowObject;

/**
 * Created by Jonathan on 12/22/2015.
 */
public final class Main {

	public static void main(String... args) {
		OffsetManager.initAll();

		NetVars.dump();
		Offsets.dump();

		System.out.println(OffsetManager.clientModule().data().getInt(m_dwGlowObject));
	}

}
