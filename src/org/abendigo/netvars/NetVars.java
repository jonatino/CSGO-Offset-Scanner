package org.abendigo.netvars;

import org.abendigo.Main;
import org.abendigo.process.Module;

import java.util.HashMap;
import java.util.Map;

import static org.abendigo.process.MemoryUtils.READ;
import static org.abendigo.process.MemoryUtils.getAddressForPattern;


/**
 * Created by Jonathan on 11/16/2015.
 */
public final class NetVars {

	private static final Map<String, NetVar> netVars = new HashMap<>();

	public static void load(Module clientModule, Module engineModule) {
		int firstclass = getAddressForPattern(clientModule, 0, 0, 0, "DT_TEWorldDecal");
		System.out.println(firstclass);
		firstclass = getAddressForPattern(clientModule, 0x2B, 0, READ, firstclass);
		System.out.println(firstclass);

		//m_iWeaponID = 12960
	}

	public static NetVar byName(String name) {
		return netVars.get(name);
	}

	public static void dump(Main.SavingMode saving_mode) {
	}

	private class NetVar {

		private final int baseAddress;
		private final String name;
		private final Map<String, Integer> childVars;

		private NetVar(int baseAddress, String name, Map<String, Integer> childVars) {
			this.baseAddress = baseAddress;
			this.name = name;
			this.childVars = childVars;
		}

		public int baseAddress() {
			return baseAddress;
		}

		public String name() {
			return name;
		}

		public int getChild(String name) {
			return childVars.getOrDefault(name, -1);
		}

		public Map<String, Integer> getChildren() {
			return childVars;
		}

	}

}
