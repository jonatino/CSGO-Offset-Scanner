package org.abendigo;

import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;

/**
 * Created by Jonathan on 12/22/2015.
 */
public final class Main {

	public static void main(String... args) {
		OffsetManager.initAll();

		NetVars.dump();
		Offsets.dump();
	}

}
