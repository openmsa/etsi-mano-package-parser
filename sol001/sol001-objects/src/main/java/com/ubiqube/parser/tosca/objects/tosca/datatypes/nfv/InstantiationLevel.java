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
package com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv;

import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.Root;

/**
 * Describes the scale level for each aspect that corresponds to a given level
 * of resources to be instantiated within a deployment flavour in term of the
 * number VNFC instances
 */
public class InstantiationLevel extends Root {
	/**
	 * Human readable description of the level
	 */
	@Valid
	@NotNull
	@JsonProperty("description")
	private String description;

	/**
	 * Represents for each aspect the scale level that corresponds to this
	 * instantiation level. scale_info shall be present if the VNF supports scaling.
	 */
	@Valid
	@JsonProperty("scale_info")
	private Map<String, ScaleInfo> scaleInfo;

	@NotNull
	public String getDescription() {
		return this.description;
	}

	public void setDescription(@NotNull final String description) {
		this.description = description;
	}

	public Map<String, ScaleInfo> getScaleInfo() {
		return this.scaleInfo;
	}

	public void setScaleInfo(final Map<String, ScaleInfo> scaleInfo) {
		this.scaleInfo = scaleInfo;
	}
}
