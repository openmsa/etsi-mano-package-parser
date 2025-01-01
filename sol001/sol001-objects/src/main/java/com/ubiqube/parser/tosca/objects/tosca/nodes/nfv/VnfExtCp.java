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
package com.ubiqube.parser.tosca.objects.tosca.nodes.nfv;

import java.util.List;

import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.annotations.Capability;
import com.ubiqube.parser.tosca.annotations.Occurence;
import com.ubiqube.parser.tosca.annotations.Relationship;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements;

/**
 * Describes a logical external connection point, exposed by the VNF enabling
 * connection with an external Virtual Link
 */
public class VnfExtCp extends Cp {
	/**
	 * The actual virtual NIC requirements that is been assigned when instantiating
	 * the connection point
	 */
	@Valid
	@JsonProperty("virtual_network_interface_requirements")
	private List<VirtualNetworkInterfaceRequirements> virtualNetworkInterfaceRequirements;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("external_virtual_link")
	private String externalVirtualLinkReq;

	@Occurence({ "1", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("internal_virtual_link")
	private String internalVirtualLinkReq;

	public List<VirtualNetworkInterfaceRequirements> getVirtualNetworkInterfaceRequirements() {
		return this.virtualNetworkInterfaceRequirements;
	}

	public void setVirtualNetworkInterfaceRequirements(
			final List<VirtualNetworkInterfaceRequirements> virtualNetworkInterfaceRequirements) {
		this.virtualNetworkInterfaceRequirements = virtualNetworkInterfaceRequirements;
	}

	public String getExternalVirtualLinkReq() {
		return this.externalVirtualLinkReq;
	}

	public void setExternalVirtualLinkReq(final String externalVirtualLinkReq) {
		this.externalVirtualLinkReq = externalVirtualLinkReq;
	}

	public String getInternalVirtualLinkReq() {
		return this.internalVirtualLinkReq;
	}

	public void setInternalVirtualLinkReq(final String internalVirtualLinkReq) {
		this.internalVirtualLinkReq = internalVirtualLinkReq;
	}
}
