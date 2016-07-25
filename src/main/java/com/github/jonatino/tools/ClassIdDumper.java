/*
 *    Copyright 2016 Jonathan Beaudoin
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.jonatino.tools;

import com.github.jonatino.OffsetManager;
import com.github.jonatino.misc.PatternScanner;
import com.github.jonatino.netvars.impl.ClientClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 1/1/2016.
 */
public final class ClassIdDumper {

	public static void main(String[] args) {
		int firstclass = PatternScanner.getAddressForPattern(OffsetManager.clientModule(), 0, 0, 0, "DT_TEWorldDecal");
		firstclass = PatternScanner.getAddressForPattern(OffsetManager.clientModule(), 0x2B, 0, PatternScanner.READ, firstclass);


		List<String> text = new ArrayList<>();
		ClientClass clientClass = new ClientClass();
		for (clientClass.setBase(firstclass); clientClass.readable(); clientClass.setBase(clientClass.next())) {
			text.add(clientClass.className() + " = " + clientClass.classId());
		}
		try {
			Files.write(Paths.get("ClassIds.txt"), text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
