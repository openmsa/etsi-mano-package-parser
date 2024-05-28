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
package com.ubiqube.parser.tosca.sol006.ir;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import com.ubiqube.parser.tosca.generator.YangException;
import com.ubiqube.parser.tosca.sol006.statement.DescriptionStatement;
import com.ubiqube.parser.tosca.sol006.statement.GroupingStatement;
import com.ubiqube.parser.tosca.sol006.statement.ImportStatement;
import com.ubiqube.parser.tosca.sol006.statement.IncludeStatement;
import com.ubiqube.parser.tosca.sol006.statement.ModuleStatement;
import com.ubiqube.parser.tosca.sol006.statement.RevisionStatement;
import com.ubiqube.parser.tosca.sol006.statement.SubMouduleStatement;
import com.ubiqube.parser.tosca.sol006.statement.YangVersionStatement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YangRoot {

	List<ModuleStatement> module = new ArrayList<>();

	List<SubMouduleStatement> submodule = new ArrayList<>();

	private List<RevisionStatement> revision;

	private YangVersionStatement yangVersion;

	private List<IncludeStatement> include;

	private List<ImportStatement> imp;

	private DescriptionStatement description;

	private List<GroupingStatement> grouping;

	public static YangRoot of(final ParseTree tree) {
		return new YangRoot();
	}

	public void load(final IrStatement res) {
		if (res.getKeyword().identifier().equals("submodule")) {
			final SubMouduleStatement sub = new SubMouduleStatement();
			sub.load(res);
			submodule.add(sub);
		} else if (res.getKeyword().identifier().equals("module")) {
			final ModuleStatement sub = new ModuleStatement();
			sub.load(res);
			module.add(sub);
		}
	}

	public void rebuildNameSpaces() {
		submodule.stream()
				.filter(x -> x.getNamespace() == null)
				.forEach(x -> {
					final ModuleStatement mod = findModuleByName(x.getBelongsTo().getName());
					x.setNamespace(mod.getNamespace());
				});

	}

	private ModuleStatement findModuleByName(final String name) {
		return module.stream()
				.filter(x -> name.equals(x.getName()))
				.findFirst()
				.orElseThrow(() -> new YangException("Could not find " + name));
	}
}
