package org.abendigo;

import org.abendigo.process.NativeProcess;
import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;
import org.abendigo.process.Module;

public final class Main {

	public static NativeProcess csgo;

	public static void main(String[] args) {
		long stamp = System.currentTimeMillis();

		csgo = NativeProcess.byName("csgo.exe");

		Module clientModule = csgo.findModule("client.dll");
		Module engineModule = csgo.findModule("engine.dll");

		Offsets.load(clientModule, engineModule);
		NetVars.load(clientModule, engineModule);

		NetVars.dump();

		System.out.println("Took: " + (System.currentTimeMillis() - stamp) + "ms");
	}

}
