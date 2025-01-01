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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.Root;
import com.ubiqube.parser.tosca.scalar.Size;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * supports the specification of requirements related to virtual memory of a
 * virtual compute resource
 */
public class VirtualMemory extends Root {
	/**
	 * It specifies the memory allocation to be cognisant of the relevant
	 * process/core allocation.
	 */
	@Valid
	@NotNull
	@JsonProperty("numa_enabled")
	private Boolean numaEnabled = false;

	/**
	 * Amount of virtual memory.
	 */
	@Valid
	@NotNull
	@JsonProperty("virtual_mem_size")
	private Size virtualMemSize;

	/**
	 * The requirement for huge pages resources. Each element in the list indicates
	 * a hugepage size and the total memory requested for hugepages of that size.
	 */
	@Valid
	@JsonProperty("huge_pages_requirements")
	private List<Hugepages> hugePagesRequirements;

	/**
	 * The hardware platform specific VDU memory requirements. A map of strings that
	 * contains a set of key-value pairs that describes hardware platform specific
	 * VDU memory requirements.
	 */
	@Valid
	@JsonProperty("vdu_mem_requirements")
	private Map<String, String> vduMemRequirements;

	/**
	 * The memory core oversubscription policy in terms of virtual memory to
	 * physical memory on the platform.
	 */
	@Valid
	@JsonProperty("virtual_mem_oversubscription_policy")
	private String virtualMemOversubscriptionPolicy;

	@NotNull
	public Boolean getNumaEnabled() {
		return this.numaEnabled;
	}

	public void setNumaEnabled(@NotNull final Boolean numaEnabled) {
		this.numaEnabled = numaEnabled;
	}

	@NotNull
	public Size getVirtualMemSize() {
		return this.virtualMemSize;
	}

	public void setVirtualMemSize(@NotNull final Size virtualMemSize) {
		this.virtualMemSize = virtualMemSize;
	}

	public List<Hugepages> getHugePagesRequirements() {
		return this.hugePagesRequirements;
	}

	public void setHugePagesRequirements(final List<Hugepages> hugePagesRequirements) {
		this.hugePagesRequirements = hugePagesRequirements;
	}

	public Map<String, String> getVduMemRequirements() {
		return this.vduMemRequirements;
	}

	public void setVduMemRequirements(final Map<String, String> vduMemRequirements) {
		this.vduMemRequirements = vduMemRequirements;
	}

	public String getVirtualMemOversubscriptionPolicy() {
		return this.virtualMemOversubscriptionPolicy;
	}

	public void setVirtualMemOversubscriptionPolicy(final String virtualMemOversubscriptionPolicy) {
		this.virtualMemOversubscriptionPolicy = virtualMemOversubscriptionPolicy;
	}
}
