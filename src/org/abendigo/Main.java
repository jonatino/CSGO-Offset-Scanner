package org.abendigo;

import org.abendigo.misc.Prefs;

public final class Main {

	public static void main(String[] args) {
		Prefs.load();

		Prefs.set("save", true);
		/*if (Prefs.get("save")) {
			System.out.println("Hi there");
		} else {
			System.out.println("Canada sucks");
		}*/

		/*GameProcess process = GameProcess.findProcess("csgo.exe");

		Module clientModule = process.findModule("client.dll");
		Module engineModule = process.findModule("engine.dll");

		Offsets.load(clientModule, engineModule);
		NetVars.load(clientModule, engineModule);*/

		/*if (Prefs.get("save")) {
			SavingMode mode = Prefs.get("saving_mode", SavingMode.TXT);
			Offsets.dump(mode);
			NetVars.dump(mode);
		}*/
	}

	public enum SavingMode {
		TXT,
		XML,
		JSON
	}

}
