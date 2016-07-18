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

    private static NativeProcess process;
    private static Module clientModule, engineModule;

    static {
        StringBuilder procBaseName = new StringBuilder("csgo");
        StringBuilder clientBaseName = new StringBuilder("client");
        StringBuilder engineBaseName = new StringBuilder("engine");

        if (Platform.isWindows()) {
            procBaseName.append(".exe");
            clientBaseName.append(".dll");
            engineBaseName.append(".dll");
        } else if (Platform.isLinux()) {
            procBaseName.append("_linux");
            clientBaseName.append("_client.so");
            engineBaseName.append("_client.so");

            //\xE8\x00\x00\x00\x00\x8B\x78\x14\x6B\xD6\x38
            int foundGlowPointerCall = PatternScanner.getAddressForPattern(clientModule, 0, 0, READ, 0xE8, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x78, 0x14, 0x6B, 0xD6, 0x34);
            System.out.println(foundGlowPointerCall);
        } else if (Platform.isMac()) {
            procBaseName.append("_osx");
            clientBaseName.append(".dylib");//Not sure completely
            engineBaseName.append(".dylib");
        } else {
            throw new RuntimeException("Unsupported operating system type!");
        }

        String processName = procBaseName.toString();
        String clientName = clientBaseName.toString();
        String engineName = engineBaseName.toString();

        waitUntilFound("process", () -> (process = NativeProcess.byName(processName)) != null);
        waitUntilFound("client module", () -> (clientModule = process.findModule(clientName)) != null);
        waitUntilFound("engine module", () -> (engineModule = process.findModule(engineName)) != null);
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

    public static NativeProcess process() {
        return process;
    }

    public static Module clientModule() {
        return clientModule;
    }

    public static Module engineModule() {
        return engineModule;
    }

    private static void waitUntilFound(String message, Clause clause) {
        System.out.print("Looking for " + message + ". Please wait.");
        while (!clause.get()) try {
            Thread.sleep(3000);
            System.out.print(".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nFound " + message + "!");
    }

    @FunctionalInterface
    private interface Clause {
        boolean get();
    }

}
