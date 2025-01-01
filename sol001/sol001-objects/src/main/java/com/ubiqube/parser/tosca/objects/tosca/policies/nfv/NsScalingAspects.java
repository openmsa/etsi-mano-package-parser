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
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.NsScalingAspect;
import com.ubiqube.parser.tosca.objects.tosca.policies.Root;

/**
 * The ScalingAspects type is a policy type representing the scaling aspects
 * used for horizontal scaling as defined in ETSI GS NFV-IFA 014 [2]
 */
public class NsScalingAspects extends Root {
	/**
	 * Describe the details of a particular aspect including the corresponding NS
	 * levels.
	 */
	@Valid
	@NotNull
	@JsonProperty("aspects")
	@Size(min = 1)
	private Map<String, NsScalingAspect> aspects;

	@Valid
	private List<String> targets;

	@NotNull
	public Map<String, NsScalingAspect> getAspects() {
		return this.aspects;
	}

	public void setAspects(@NotNull final Map<String, NsScalingAspect> aspects) {
		this.aspects = aspects;
	}

	public List<String> getTargets() {
		return this.targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}
}
