package org.abendigo.natives;

import com.sun.jna.Native;
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

public interface Psapi extends StdCallLibrary {

	static Module getModule(GameProcess process, String name) {
		WinDef.HMODULE[] lphModules = new WinDef.HMODULE[1024];
		IntByReference lpcbNeededs = new IntByReference();
		INSTANCE.EnumProcessModulesEx(process.pointer(), lphModules, lphModules.length, lpcbNeededs, 1);
		for (int i = 0; i < lpcbNeededs.getValue() / 4; i++) {
			WinDef.HMODULE hModule = lphModules[i];
			LPMODULEINFO moduleInfo = new LPMODULEINFO();
			if (INSTANCE.GetModuleInformation(process.pointer(), hModule, moduleInfo, moduleInfo.size())) {
				if (moduleInfo.lpBaseOfDll != null) {
					String moduleName = Psapi.GetModuleBaseNameA(process.pointer(), hModule);
					if (name.equals(moduleName)) {
						int baseAddress = (int) Pointer.nativeValue(moduleInfo.lpBaseOfDll.getPointer());
						return new Module(process, name, baseAddress, moduleInfo.SizeOfImage);
					}
				}
			}
		}
		throw new NullPointerException("Could not find module" + name);
	}

	Psapi INSTANCE = (Psapi) Native.loadLibrary("Psapi", Psapi.class);

	boolean EnumProcessModulesEx(Pointer hProcess, WinDef.HMODULE[] lphModule, int cb, IntByReference lpcbNeededs, int flags);

	boolean GetModuleInformation(Pointer hProcess, WinDef.HMODULE hModule, LPMODULEINFO lpmodinfo, int cb);

	int GetModuleBaseNameA(Pointer hProcess, WinDef.HMODULE hModule, byte[] lpImageFileName, int nSize);

	static String GetModuleBaseNameA(Pointer hProcess, WinDef.HMODULE hModule) {
		byte[] lpImageFileName = new byte[256];
		INSTANCE.GetModuleBaseNameA(hProcess, hModule, lpImageFileName, 256);
		return Native.toString(lpImageFileName);
	}

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
