package org.abendigo.tools;

import org.abendigo.netvars.impl.ClientClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.abendigo.OffsetManager.clientModule;
import static org.abendigo.misc.PatternScanner.READ;
import static org.abendigo.misc.PatternScanner.getAddressForPattern;

/**
 * Created by Jonathan on 1/1/2016.
 */
public final class ClassIdDumper {

	public static void main(String[] args) {
		int firstclass = getAddressForPattern(clientModule(), 0, 0, 0, "DT_TEWorldDecal");
		firstclass = getAddressForPattern(clientModule(), 0x2B, 0, READ, firstclass);

		List<String> text = new ArrayList<>();
		for (ClientClass clientClass = new ClientClass(firstclass); clientClass.readable(); clientClass = new ClientClass(clientClass.next())) {
			text.add(clientClass.className() + " = " + clientClass.classId());
		}
		try {
			Files.write(Paths.get("ClassIds.txt"), text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
