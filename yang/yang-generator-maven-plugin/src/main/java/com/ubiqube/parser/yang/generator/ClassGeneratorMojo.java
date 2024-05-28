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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.generator.YangLoader;
import com.ubiqube.parser.tosca.sol006.ir.YangRoot;
import com.ubiqube.parser.tosca.sol006.statement.ModuleStatement;
import com.ubiqube.parser.tosca.walker.JavaPoetListener;
import com.ubiqube.yang.Recurse;

@Mojo(name = "yang-class-generator", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ClassGeneratorMojo extends AbstractMojo {
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(ClassGeneratorMojo.class);

	@Parameter(property = "directory")
	private Path directory;

	@Parameter(defaultValue = "${project.build.directory}/generated-sources/yang", required = true)
	private File outputDirectory;
	/**
	 * The injected Maven project.
	 */
	@Parameter(defaultValue = "${project}", readonly = true)
	private MavenProject project;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		final YangLoader yl = new YangLoader();
		final List<YangRoot> res = yl.loadDirectory(directory);
		final YangRoot yr = YangGenerator.mergeRoot(res);
		yr.rebuildNameSpaces();
		final List<ModuleStatement> r = YangGenerator.findRoots(yr);
		LOG.info("Found {} roots.", r.size());
		final ModuleStatement root = r.get(0);
		YangInclude.resolvInclude(yr, root);
		YangUniqClass.makeClassUniq(root);
		Recurse.doIt(root, new JavaPoetListener(outputDirectory.toString()));
		try {
			getProject().addCompileSourceRoot(outputDirectory.getCanonicalPath());
		} catch (final IOException e) {
			throw new MojoFailureException("Unable to add resources.", e);
		}
	}

	/**
	 * @return The active MavenProject.
	 */
	private final MavenProject getProject() {
		return getInjectedObject(project, "project");
	}

	private <T> T getInjectedObject(final T objectOrNull, final String objectName) {
		if (objectOrNull == null) {
			getLog().error(
					"Found null '" + objectName + "', implying that Maven @Component injection was not done properly.");
			throw new YangGeneratorException("");
		}
		return objectOrNull;
	}

}
