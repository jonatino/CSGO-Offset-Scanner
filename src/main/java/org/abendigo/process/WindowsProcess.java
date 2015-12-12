package org.abendigo.process;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinNT;
import org.abendigo.misc.Cacheable;
import org.abendigo.natives.windows.Kernel32;
import org.abendigo.natives.NativeProcess;
import org.abendigo.natives.windows.Psapi;

import java.nio.ByteBuffer;

/**
 * Created by Jonathan on 11/13/2015.
 */
public final class WindowsProcess implements NativeProcess {

	private final int id;
	private final WinNT.HANDLE handle;

	public WindowsProcess(int id, WinNT.HANDLE handle) {
		this.id = id;
		this.handle = handle;
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public Pointer pointer() {
		return handle.getPointer();
	}

	@Override
	public Module findModule(String moduleName) {
		return Psapi.getModule(this, moduleName);
	}

	@Override
	public ByteBuffer readMemory(long address, int bytesToRead) {
		return readMemory(Cacheable.pointer(address), bytesToRead);
	}

	@Override
	public ByteBuffer readMemory(Pointer address, int bytesToRead) {
		ByteBuffer buffer = Cacheable.buffer(bytesToRead);
		if (!Kernel32.ReadProcessMemory(pointer(), address, buffer, bytesToRead, 0)) {
			throw new Win32Exception(Native.getLastError());
		}
		return buffer;
	}

}
