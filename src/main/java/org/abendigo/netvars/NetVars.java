package org.abendigo.netvars;

import com.beaudoin.jmm.misc.Strings;
import org.abendigo.netvars.impl.ClientClass;
import org.abendigo.netvars.impl.RecvProp;
import org.abendigo.netvars.impl.RecvTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static org.abendigo.OffsetManager.clientModule;
import static org.abendigo.misc.PatternScanner.READ;
import static org.abendigo.misc.PatternScanner.getAddressForPattern;


/**
 * Created by Jonathan on 11/16/2015.
 */
public final class NetVars {

	private static final ArrayDeque<NetVar> netVars = new ArrayDeque<>(16_500);

	private static final ClientClass clientClass = new ClientClass();
	private static final RecvTable table = new RecvTable();
	private static final RecvProp prop = new RecvProp();

	public static void load() {
		int firstclass = getAddressForPattern(clientModule(), 0, 0, 0, "DT_TEWorldDecal");
		firstclass = getAddressForPattern(clientModule(), 0x2B, 0, READ, firstclass);

		for (clientClass.setBase(firstclass); clientClass.readable(); clientClass.setBase(clientClass.next())) {
			table.setBase(clientClass.table());
			String tableName = table.tableName();
			if (tableName.length() > 0 && table.propCount() > 0) {
				scanTable(table, 0, tableName);
			}
		}
	}

	public static void dump() {
		List<String> text = new ArrayList<>();
		netVars.forEach(n -> text.add(n.toString()));
		try {
			Files.write(Paths.get("NetVars.txt"), text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void scanTable(RecvTable table, int offset, String className) {
		for (int i = 0; i < table.propCount(); i++) {
			prop.setBase(table.propForId(i));
			prop.setOffset(offset);

			String propName = prop.name();
			int propOffset = prop.offset();

			if (Character.isDigit(propName.charAt(0))) {
				continue;
			}

			if (propOffset != 0x0) {
				netVars.add(new NetVar(className, propName, propOffset));
			}

			int child = prop.table();
			if (child == 0) {
				continue;
			}
			scanTable(new RecvTable().setBase(child), propOffset, className);
		}
	}

	public static int byName(String className, String varname) {
		for (NetVar var : netVars) {
			if (var.className.equals(className) && var.varName.equals(varname)) {
				return var.offset;
			}
		}
		throw new RuntimeException("NetVar [" + className + ", " + varname + "] not found!");
	}

	private static class NetVar {

		private final String className;
		private final String varName;
		private final int offset;

		private NetVar(String className, String varName, int offset) {
			this.className = className;
			this.varName = varName;
			this.offset = offset;
		}

		@Override
		public String toString() {
			return className + " " + varName + " = " + Strings.hex(offset);
		}

	}

}
