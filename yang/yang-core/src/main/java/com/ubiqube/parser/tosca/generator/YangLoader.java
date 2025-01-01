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
package com.ubiqube.parser.tosca.generator;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.ubiqube.parser.tosca.sol006.ir.YangRoot;

public class YangLoader {

	private final YangFileLoader yfl = new YangFileLoader();

	public List<YangRoot> loadDirectory(final Path path) {
		final String[] res = path.toFile().list((x, y) -> y.endsWith(".yang"));
		return Stream.of(res).map(x -> loafFile(new File(path.toString(), x))).toList();
	}

	private YangRoot loafFile(final File x) {
		return yfl.loadFile(x);
	}
}
