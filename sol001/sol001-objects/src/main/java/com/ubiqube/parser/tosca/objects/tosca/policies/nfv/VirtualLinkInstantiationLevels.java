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
package com.ubiqube.parser.tosca.objects.tosca.policies.nfv;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VirtualLinkBitrateLevel;
import com.ubiqube.parser.tosca.objects.tosca.policies.Root;

/**
 * The VirtualLinkInstantiationLevels type is a policy type representing all the
 * instantiation levels of virtual link resources to be instantiated within a
 * deployment flavour as defined in ETSI GS NFV-IFA 011
 * [1].tosca.nodes.nfv.VnfVirtualLink
 */
public class VirtualLinkInstantiationLevels extends Root {
	/**
	 * Describes the virtual link levels of resources that can be used to
	 * instantiate the VNF using this flavour.
	 */
	@Valid
	@NotNull
	@JsonProperty("levels")
	@Size(min = 1)
	private Map<String, VirtualLinkBitrateLevel> levels;

	@Valid
	private List<String> targets;

	@NotNull
	public Map<String, VirtualLinkBitrateLevel> getLevels() {
		return this.levels;
	}

	public void setLevels(@NotNull final Map<String, VirtualLinkBitrateLevel> levels) {
		this.levels = levels;
	}

	public List<String> getTargets() {
		return this.targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}
}
