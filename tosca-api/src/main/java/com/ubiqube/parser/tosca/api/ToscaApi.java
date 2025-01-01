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
package com.ubiqube.parser.tosca.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.GroupDefinition;
import com.ubiqube.parser.tosca.NodeTemplate;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.PolicyDefinition;
import com.ubiqube.parser.tosca.ToscaContext;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Main front API around tosca files.
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
public class ToscaApi {
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(ToscaApi.class);

	private final ClassLoader loader;
	private final ToscaMapper toscaMapper;

	public ToscaApi(final ClassLoader loader, final ToscaMapper toscaMapper) {
		this.loader = loader;
		this.toscaMapper = toscaMapper;
	}

	/**
	 * Return a list of populated 'destination' objects.
	 *
	 * @param <T>        The type of returned objects.
	 * @param root       Tosca context.
	 * @param parameters Destination class.
	 * @param toscaClass Tosca class to map and return.
	 * @return A List of populated object.
	 */
	public <T> List<T> getObjects(final ToscaContext root, final Map<String, String> parameters, final Class<T> toscaClass) {
		final Class<T> destination;
		try {
			destination = getVersionizedClass(loader, toscaClass);
		} catch (final ClassNotFoundException e) {
			LOG.trace("", e);
			return List.of();
		}
		final ContextResolver contextResolver = new ContextResolver(root, parameters);
		final List<NodeTemplate> nodes = getNodeMatching(root, destination);
		if (!nodes.isEmpty()) {
			return getAndValidate(toscaClass, () -> contextResolver.mapToscaToClass(nodes, destination));
		}
		final List<GroupDefinition> groups = getGroupsMatching(root, destination);
		if (!groups.isEmpty()) {
			return getAndValidate(toscaClass, () -> contextResolver.mapGroupsToClass(groups, destination));
		}
		final List<PolicyDefinition> policies = getPoliciesMatching(root, destination);
		if (!policies.isEmpty()) {
			return getAndValidate(toscaClass, () -> contextResolver.mapPoliciesToClass(policies, destination));
		}
		return new ArrayList<>();
	}

	private <T> List<T> getAndValidate(final Class<T> manoClass, final Supplier<List<T>> sup) {
		final List<T> tmp = sup.get();
		final Set<ConstraintViolation<List<T>>> res = validate(tmp);
		if (res.isEmpty()) {
			return tmp.stream().map(x -> toscaMapper.map(x, manoClass)).toList();
		}
		throw new ParseException("SOL001 file contain the following errors: " + res);
	}

	public static <U> Set<ConstraintViolation<U>> validate(final U object) {
		try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			final Validator validator = factory.getValidator();
			return validator.validate(object);
		}
	}

	private static <T> List<PolicyDefinition> getPoliciesMatching(final ToscaContext root, final Class<T> destination) {
		final Map<String, PolicyDefinition> policies = new HashMap<>();
		final List<Map<String, PolicyDefinition>> p2 = root.getTopologies().getPolicies();
		final Map<String, PolicyDefinition> p1 = root.getPolicies();
		if (null != p1) {
			policies.putAll(p1);
		}
		if (null != p2) {
			p2.forEach(policies::putAll);
		}
		final String clazzname = destination.getName();
		return policies.entrySet().stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(x -> {
					final PolicyDefinition val = x.getValue();
					val.setName(x.getKey());
					return val;
				})
				.toList();
	}

	private static <T> List<GroupDefinition> getGroupsMatching(final ToscaContext root, final Class<T> destination) {
		final Map<String, GroupDefinition> groups = new HashMap<>();
		final Map<String, GroupDefinition> p1 = root.getTopologies().getGroups();
		if (null != p1) {
			groups.putAll(p1);
		}
		final Map<String, GroupDefinition> p2 = root.getGroupDefinition();
		if (null != p2) {
			groups.putAll(p2);
		}
		final String clazzname = destination.getName();
		return groups.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(x -> {
					final GroupDefinition val = x.getValue();
					val.setName(x.getKey());
					return val;
				})
				.toList();
	}

	/**
	 * Return node matching wanted class.
	 *
	 * @param <T>         The wanted class.
	 * @param root        Tosca context.
	 * @param destination Target class.
	 * @return A list of {@link NodeTemplate}.
	 */
	private static <T> List<NodeTemplate> getNodeMatching(final ToscaContext root, final Class<T> destination) {
		final String clazzname = destination.getName();
		return root.getTopologies()
				.getNodeTemplate()
				.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(x -> {
					final NodeTemplate val = x.getValue();
					val.setName(x.getKey());
					return val;
				})
				.toList();
	}

	@SuppressWarnings("unchecked")
	private static <T> Class<T> getVersionizedClass(final ClassLoader loader, final Class<T> manoClass) throws ClassNotFoundException {
		if (!manoClass.getName().startsWith("com.ubiqube.parser.tosca.objects.")) {
			throw new ParseException("Class: " + manoClass.getName() + " is not a Mano class.");
		}
		final String tName = manoClass.getName().substring("com.ubiqube.parser.tosca.objects.".length());
		return (Class<T>) loader.loadClass(tName);
	}

}
