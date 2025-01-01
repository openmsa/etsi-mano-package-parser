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
package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.ubiqube.parser.tosca.ParseException;

@Mojo(name = "tosca-json-generator", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class JsonSchemaGeneratorMojo extends AbstractMojo {
	@Parameter(property = "files")
	private List<FileHolder> files;

	@Parameter(defaultValue = "${project.build.directory}/generated-sources/tosca", required = true)
	private File outputDirectory;

	/**
	 * The injected Maven project.
	 */
	@Parameter(defaultValue = "${project}", readonly = true)
	private MavenProject project;

	@Parameter(defaultValue = "")
	private String packagePrefix;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		files.forEach(x -> {
			getLog().info("🪄 Starting class generation using: " + x.getFile() + " / " + x.getType());
			final ToscaJsonSchemaWalker tw = new ToscaJsonSchemaWalker();
			final ToscaJsonSchemaGenerator tl = new ToscaJsonSchemaGenerator(Paths.get(outputDirectory.getAbsolutePath()), x.getType());
			tw.generate(x.getFile().getAbsolutePath(), tl);
		});
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
			throw new ParseException("");
		}
		return objectOrNull;
	}
}
