package org.abendigo;

import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;
import org.abendigo.process.GameProcess;
import org.abendigo.process.Module;

public final class Main {

	public static void main(String[] args) {
		long stamp = System.currentTimeMillis();

		GameProcess process = GameProcess.findProcess("csgo.exe");

		Module clientModule = process.findModule("client.dll");
		Module engineModule = process.findModule("engine.dll");


		Offsets.load(clientModule, engineModule);
		NetVars.load(clientModule, engineModule);

		System.out.println("Took: " + (System.currentTimeMillis() - stamp) + "ms");


	}

}
