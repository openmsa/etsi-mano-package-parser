/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.parser.yang.generator;

import java.util.List;

import com.ubiqube.parser.tosca.generator.YangException;
import com.ubiqube.parser.tosca.sol006.ir.YangRoot;
import com.ubiqube.parser.tosca.sol006.statement.ModuleStatement;
import com.ubiqube.parser.tosca.sol006.statement.NamedStatement;
import com.ubiqube.parser.tosca.sol006.statement.SubMouduleStatement;

public class YangInclude {
	private YangInclude() {
		//
	}

	public static void resolvInclude(final YangRoot res, final ModuleStatement root) {
		root.getInclude().forEach(x -> {
			final SubMouduleStatement r = findSubModuleByName(res, x.getName());
			merge(root, r);
		});
	}

	private static void merge(final ModuleStatement root, final SubMouduleStatement r) {
		addAll(root.getGrouping(), r.getGrouping());
		addAll(root.getTypeDef(), r.getTypedef());
	}

	private static <U extends NamedStatement> void addAll(final List<U> target, final List<U> toAdd) {
		toAdd.forEach(x -> {
			checkUniqueness(target, x.getName());
			target.add(x);
		});
	}

	private static void checkUniqueness(final List<? extends NamedStatement> target, final String name) {
		if (target.stream().anyMatch(x -> x.getName().equals(name))) {
			throw new YangException("");
		}
	}

	private static SubMouduleStatement findSubModuleByName(final YangRoot res, final String name) {
		return res.getSubmodule().stream()
				.filter(x -> x.getName().equals(name))
				.findFirst()
				.orElseThrow();
	}

}
