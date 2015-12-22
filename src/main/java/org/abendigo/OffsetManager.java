package org.abendigo;

import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;
import org.abendigo.process.Module;
import org.abendigo.process.NativeProcess;

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
		NetVars.load(clientModule);
	}

	public static void loadOffsets() {
		Offsets.load(clientModule, engineModule);
	}

}
