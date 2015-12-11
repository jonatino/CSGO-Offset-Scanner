package org.abendigo.process;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinNT;
import org.abendigo.natives.Buffers;
import org.abendigo.natives.Kernel32;
import org.abendigo.natives.Psapi;

import java.nio.ByteBuffer;

/**
 * Created by Jonathan on 11/13/2015.
 */
public final class GameProcess {

	private final int id;
	private final WinNT.HANDLE handle;

	public GameProcess(int id, WinNT.HANDLE handle) {
		this.id = id;
		this.handle = handle;
	}

	public int id() {
		return id;
	}

	public Pointer pointer() {
		return handle.getPointer();
	}

	public Module findModule(String moduleName) {
		return Psapi.getModule(this, moduleName);
	}

	public ByteBuffer readMemory(long address, int bytesToRead) {
		return readMemory(new Pointer(address), bytesToRead);
	}

	public ByteBuffer readMemory(Pointer address, int bytesToRead) {
		ByteBuffer buffer = Buffers.nativeBuffer(bytesToRead);
		if (!Kernel32.ReadProcessMemory(pointer(), address, buffer, bytesToRead, 0)) {
			throw new Win32Exception(Native.getLastError());
		}
		buffer.rewind();
		return buffer;
	}

	public static final int PROCESS_ALL = 0x438;

	public static GameProcess findProcess(String name) {
		while (true) {
			Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();
			WinNT.HANDLE snapshot = Kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPALL, 0);
			try {
				while (Kernel32.Process32Next(snapshot, processEntry)) {
					String pName = Native.toString(processEntry.szExeFile);
					if (name.equals(pName)) {
						int pid = processEntry.th32ProcessID.intValue();
						return new GameProcess(pid, Kernel32.OpenProcess(PROCESS_ALL, true, pid));
					}
				}
			} finally {
				Kernel32.CloseHandle(snapshot);
			}
		}
	}

}
