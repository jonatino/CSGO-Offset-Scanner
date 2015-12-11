package org.abendigo.natives;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import org.abendigo.process.GameProcess;
import org.abendigo.process.Module;

import java.util.Arrays;
import java.util.List;

public final class Psapi {

	public static Module getModule(GameProcess process, String name) {
		WinDef.HMODULE[] lphModules = new WinDef.HMODULE[1024];
		IntByReference lpcbNeededs = new IntByReference();
		PsapiStdCall.LPMODULEINFO moduleInfo = new PsapiStdCall.LPMODULEINFO();

		EnumProcessModulesEx(process.pointer(), lphModules, lphModules.length, lpcbNeededs, 0x03);
		for (int i = 0; i < lpcbNeededs.getValue() / 4; i++) {
			WinDef.HMODULE hModule = lphModules[i];
			if (GetModuleInformation(process.pointer(), hModule, moduleInfo, moduleInfo.size())) {
				if (moduleInfo.lpBaseOfDll != null) {
					String moduleName = GetModuleBaseNameA(process.pointer(), hModule);
					if (name.equals(moduleName)) {
						return new Module(process, name, Pointer.nativeValue(hModule.getPointer()), moduleInfo.SizeOfImage);
					}
				}
			}
		}
		throw new NullPointerException("Could not find module " + name);
	}

	public static native boolean GetModuleInformation(Pointer hProcess, WinDef.HMODULE hModule, PsapiStdCall.LPMODULEINFO lpmodinfo, int cb);

	public static native int GetModuleBaseNameA(Pointer hProcess, WinDef.HMODULE hModule, byte[] lpImageFileName, int nSize);

	private static String GetModuleBaseNameA(Pointer hProcess, WinDef.HMODULE hModule) {
		byte[] lpImageFileName = new byte[128];
		GetModuleBaseNameA(hProcess, hModule, lpImageFileName, lpImageFileName.length);
		return Native.toString(lpImageFileName);
	}

	public static boolean EnumProcessModulesEx(Pointer hProcess, WinDef.HMODULE[] lphModule, int cb, IntByReference lpcbNeededs, int flags) {
		return psapi.EnumProcessModulesEx(hProcess, lphModule, cb, lpcbNeededs, flags);
	}

	static {
		Native.register(NativeLibrary.getInstance("Psapi"));
	}

	private static PsapiStdCall psapi = (PsapiStdCall) Native.loadLibrary("Psapi", PsapiStdCall.class);

	private interface PsapiStdCall extends StdCallLibrary {

		boolean EnumProcessModulesEx(Pointer hProcess, WinDef.HMODULE[] lphModule, int cb, IntByReference lpcbNeededs, int flags);

		class LPMODULEINFO extends Structure {

			public WinNT.HANDLE lpBaseOfDll;
			public int SizeOfImage;
			public WinNT.HANDLE EntryPoint;

			@Override
			protected List getFieldOrder() {
				return Arrays.asList("lpBaseOfDll", "SizeOfImage", "EntryPoint");
			}
		}

	}
}
