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

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VipCpScalingAspectDeltas {
	/**
	 * Represents the scaling aspect to which this policy applies
	 */
	@Valid
	@NotNull
	@JsonProperty("aspect")
	private String aspect;

	/**
	 * Describes the VipCp scaling deltas to be applied for every scaling steps of a
	 * particular aspect.
	 */
	@Valid
	@NotNull
	@JsonProperty("deltas")
	@Size(min = 1)
	private Map<String, VipCpLevel> deltas;

	@Valid
	private List<String> targets;

	@NotNull
	public String getAspect() {
		return this.aspect;
	}

	public void setAspect(@NotNull final String aspect) {
		this.aspect = aspect;
	}

	@NotNull
	public Map<String, VipCpLevel> getDeltas() {
		return this.deltas;
	}

	public void setDeltas(@NotNull final Map<String, VipCpLevel> deltas) {
		this.deltas = deltas;
	}

	public List<String> getTargets() {
		return this.targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
