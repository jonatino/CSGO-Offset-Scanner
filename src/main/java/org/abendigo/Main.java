package org.abendigo;

import org.abendigo.natives.NativeProcess;
import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;
import org.abendigo.process.WindowsProcess;
import org.abendigo.process.Module;

public final class Main {

	public static void main(String[] args) {
		long stamp = System.currentTimeMillis();

		NativeProcess process = NativeProcess.findProcess("csgo.exe");
		
		Module clientModule = process.findModule("client.dll");
		Module engineModule = process.findModule("engine.dll");

		Offsets.load(clientModule, engineModule);
		NetVars.load(clientModule, engineModule);

		System.out.println("Took: " + (System.currentTimeMillis() - stamp) + "ms");
	}

}
