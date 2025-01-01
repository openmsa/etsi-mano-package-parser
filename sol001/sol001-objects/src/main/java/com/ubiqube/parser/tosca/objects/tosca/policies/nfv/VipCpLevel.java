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

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class VipCpLevel {
	/**
	 * Number of instances of VipCp based on the referenced VipCp node template to
	 * deploy for an instantiation level or for a scaling delta.
	 */
	@Valid
	@NotNull
	@JsonProperty("number_of_instances")
	@DecimalMin(value = "0", inclusive = true)
	private Integer numberOfInstances;

	@NotNull
	public Integer getNumberOfInstances() {
		return this.numberOfInstances;
	}

	public void setNumberOfInstances(@NotNull final Integer numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}

}
