package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.ubiqube.parser.tosca.ParseException;

public class SchemaGeneratorMojo extends AbstractMojo {
	@Parameter(property = "files")
	private List<File> files;

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
		final ToscaJsonSchemaWalker tw = new ToscaJsonSchemaWalker();
		files.forEach(x -> {
			getLog().info("Starting class generation using: " + x);
			// tw.generate(x.getAbsolutePath());
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
