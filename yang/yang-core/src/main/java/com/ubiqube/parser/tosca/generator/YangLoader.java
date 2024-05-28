/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
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
