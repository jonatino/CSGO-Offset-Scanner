package org.abendigo;

import com.beaudoin.jmm.process.Module;
import com.beaudoin.jmm.process.NativeProcess;
import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;

/**
 * Created by Jonathan on 12/22/2015.
 */
public final class OffsetManager {

	public static NativeProcess csgo;
	public static Module clientModule, engineModule;

	static {
		csgo = NativeProcess.byName("csgo.exe");

		clientModule = csgo.findModule("client.dll");
		engineModule = csgo.findModule("engine.dll");
	}

	public static void initAll() {
		loadNetVars();
		loadOffsets();
	}

	public static void loadNetVars() {
		NetVars.load();
	}

	public static void loadOffsets() {
		Offsets.load();
	}

}
