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
 *     along with this program.  If not, see https://www.gnu.org/licenses/.
 */
package com.ubiqube.parser.yang.generator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.generator.YangException;
import com.ubiqube.parser.tosca.sol006.statement.ModuleStatement;
import com.ubiqube.parser.tosca.sol006.statement.NamedStatement;
import com.ubiqube.parser.tosca.walker.ClassCollectorListener;
import com.ubiqube.yang.Recurse;

public class YangUniqClass {
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(YangUniqClass.class);

	private YangUniqClass() {
		//
	}

	public static void makeClassUniq(final ModuleStatement root) {
		final List<Entry<String, List<NamedStatement>>> res = getDuplicated(root);
		res.forEach(YangUniqClass::print2);
		LOG.info("==================================");
		final List<Entry<String, List<NamedStatement>>> res2 = getDuplicated(root);
		res2.forEach(YangUniqClass::print);
	}

	private static List<Entry<String, List<NamedStatement>>> getDuplicated(final ModuleStatement root) {
		final ClassCollectorListener list = new ClassCollectorListener();
		Recurse.doIt(root, list);
		final Map<String, List<NamedStatement>> all = list.getMap().getMap();
		return all.entrySet().stream().filter(x -> x.getValue().size() > 1).toList();
	}

	private static Object print(final Entry<String, List<NamedStatement>> x) {
		x.getValue().stream().forEach(y -> {
			if (!(y.getParent() instanceof final NamedStatement ns)) {
				throw new YangException(y.getName() + ", have a parent of type " + y.getName() + "/" + x.getClass().getSimpleName());
			}
			if (y.getName().equals(ns.getName())) {
				LOG.warn("Parent = child name {}", y.getName());
			} else {
				final String className = y.getName() + "-" + ns.getName();
				y.setClassName(className);
				LOG.info("{} {}", y.getName(), ns.getName());
			}
		});
		return null;
	}

	private static Object print2(final Entry<String, List<NamedStatement>> x) {
		final int max = x.getValue().size();
		if (max > 20) {
			LOG.info(x.getKey());
		}
		for (int i = 0; i < max; i++) {
			final NamedStatement elem = x.getValue().get(i);
			elem.setClassName(elem.getClassName() + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(i));
		}
		return null;
	}

}
