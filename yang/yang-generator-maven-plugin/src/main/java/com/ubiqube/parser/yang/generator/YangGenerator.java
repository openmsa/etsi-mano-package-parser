/**
 *     Copyright (C) 2019-2024 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.parser.yang.generator;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.parser.tosca.sol006.ir.YangRoot;
import com.ubiqube.parser.tosca.sol006.statement.ImportStatement;
import com.ubiqube.parser.tosca.sol006.statement.IncludeStatement;
import com.ubiqube.parser.tosca.sol006.statement.ModuleStatement;

public class YangGenerator {
	private YangGenerator() {
		//
	}

	public static YangRoot mergeRoot(final List<YangRoot> res) {
		final YangRoot yr = new YangRoot();
		res.forEach(x -> {
			yr.getModule().addAll(x.getModule());
			yr.getSubmodule().addAll(x.getSubmodule());
		});
		return yr;
	}

	public static List<ModuleStatement> findRoots(final YangRoot yr) {
		final List<ModuleStatement> mods = yr.getModule();
		final List<ModuleStatement> res = new ArrayList<>(mods);
		final List<ModuleStatement> no = mods.stream().filter(YangGenerator::isRoot).toList();
		no.forEach(x -> {
			final List<IncludeStatement> incs = x.getInclude();
			incs.stream().forEach(y -> removeInclude(y, res));
			final List<ImportStatement> imps = x.getImp();
			imps.stream().forEach(y -> removeImport(y, res));
		});
		return mods.stream().filter(x -> x.getName().equals("etsi-nfv-descriptors")).toList();
	}

	private static void removeInclude(final IncludeStatement y, final List<ModuleStatement> res) {
		final List<ModuleStatement> toRemove = res.stream().filter(x -> isMatching(x, y)).toList();
		toRemove.forEach(x -> res.remove(x));
	}

	private static boolean isMatching(final ModuleStatement x, final IncludeStatement y) {
		return x.getName().equals(y.getName());
	}

	private static boolean isRoot(final ModuleStatement x) {
		if (null != x.getBelongsTo()) {
			return false;
		}
		return true;
	}

	private static void removeImport(final ImportStatement y, final List<ModuleStatement> res) {
		final List<ModuleStatement> toRemove = res.stream().filter(x -> isMatching(x, y)).toList();
		toRemove.forEach(x -> res.remove(x));
	}

	private static boolean isMatching(final ModuleStatement x, final ImportStatement y) {
		return x.getName().equals(y.getName());
	}

}
