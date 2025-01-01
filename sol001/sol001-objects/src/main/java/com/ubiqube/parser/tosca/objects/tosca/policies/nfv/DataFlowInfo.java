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

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class DataFlowInfo {
	/**
	 * Identifier of the Data flow information
	 */
	@Valid
	@NotNull
	@JsonProperty("data_flow_info_id")
	private String dataFlowInfoId;

	/**
	 * Associated data mirroring job name as defined in NsVirtualLink node
	 */
	@Valid
	@NotNull
	@JsonProperty("associated_mirroring_job_name")
	private String associatedMirroringJobName;

	/**
	 * The direction of the data flow that are requested to be mirrored. The
	 * direction is specified against the associated CP. I.e., 'in' means the data
	 * flow that enters the target connection point needs to be mirrored. 'out'
	 * means the data flow that sends out the target connection point needs to be
	 * mirrored. 'both' means the data flows that both enters and sends out the
	 * target connection point all need to be mirrored.
	 */
	@Valid
	@NotNull
	@JsonProperty("direction")
	private String direction;

	@Valid
	private List<String> targets;

	@NotNull
	public String getDataFlowInfoId() {
		return this.dataFlowInfoId;
	}

	public void setDataFlowInfoId(@NotNull final String dataFlowInfoId) {
		this.dataFlowInfoId = dataFlowInfoId;
	}

	@NotNull
	public String getAssociatedMirroringJobName() {
		return this.associatedMirroringJobName;
	}

	public void setAssociatedMirroringJobName(@NotNull final String associatedMirroringJobName) {
		this.associatedMirroringJobName = associatedMirroringJobName;
	}

	@NotNull
	public String getDirection() {
		return this.direction;
	}

	public void setDirection(@NotNull final String direction) {
		this.direction = direction;
	}

	public List<String> getTargets() {
		return this.targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
