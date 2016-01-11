package org.abendigo;

import com.beaudoin.jmm.process.Module;
import com.beaudoin.jmm.process.NativeProcess;
import com.sun.jna.Platform;
import org.abendigo.misc.PatternScanner;
import org.abendigo.netvars.NetVars;
import org.abendigo.offsets.Offsets;

import static org.abendigo.misc.PatternScanner.READ;

/**
 * Created by Jonathan on 12/22/2015.
 */
public final class OffsetManager {

	public static NativeProcess csgo;
	public static Module clientModule, engineModule;

	static {
		if (Platform.isWindows()) {
			csgo = NativeProcess.byName("csgo.exe");

			clientModule = csgo.findModule("client.dll");
			engineModule = csgo.findModule("engine.dll");
		} else if (Platform.isLinux()) {
			csgo = NativeProcess.byName("csgo_linux");

			clientModule = csgo.findModule("client_client.so");
			engineModule = csgo.findModule("engine_client.so");

            //\xE8\x00\x00\x00\x00\x8B\x78\x14\x6B\xD6\x38
            int foundGlowPointerCall = PatternScanner.getAddressForPattern(clientModule,
                    0, 0, READ, 0xE8,0x00,0x00,0x00,0x00,0x8B,0x78,0x14,0x6B,0xD6,0x34);
            System.out.println(foundGlowPointerCall);

		}
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
