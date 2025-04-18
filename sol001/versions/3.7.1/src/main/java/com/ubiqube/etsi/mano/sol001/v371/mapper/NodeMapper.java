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
package com.ubiqube.etsi.mano.sol001.v371.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.L3AddressData;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.NsProfile;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.ServiceData;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VirtualMemory;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Forwarding;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NFP;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NS;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NfpPosition;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NfpPositionElement;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NsVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Sap;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduSubCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VipCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualBlockStorage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualFileStorage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualObjectStorage;

@Mapper
public interface NodeMapper {
	NS mapToNS(tosca.nodes.nfv.NS o);

	@Mapping(target = "flavourId", ignore = true)
	NsProfile mapToNsProfile(tosca.datatypes.nfv.NsProfile o);

	Sap mapToSap(tosca.nodes.nfv.Sap o);

	@Mapping(target = "ipAddressAssignmentSubtype", ignore = true)
	L3AddressData mapToL3AddressData(tosca.datatypes.nfv.L3AddressData o);

	NsVirtualLink mapToNsVirtualLink(tosca.nodes.nfv.NsVirtualLink o);

	NfpPositionElement mapToNfpPositionElement(tosca.nodes.nfv.NfpPositionElement o);

	NfpPosition mapToNfpPosition(tosca.nodes.nfv.NfpPosition o);

	NFP mapToNFP(tosca.nodes.nfv.NFP o);

	Forwarding mapToForwarding(tosca.nodes.nfv.Forwarding o);

	@Mapping(target = "artifacts", ignore = true)
	@Mapping(target = "overloadedAttributes", ignore = true)
	@Mapping(target = "overloadedInterfaces", ignore = true)
	@Mapping(target = "overloadedRequirements", ignore = true)
	@Mapping(target = "forwarding", ignore = true)
	@Mapping(target = "virtualLinkReq", ignore = true)
	@Mapping(target = "virtualLinkable", ignore = true)
	com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Forwarding mapTo(tosca.capabilities.nfv.Forwarding o);

	PNF mapToPNF(tosca.nodes.nfv.PNF o);

	PnfExtCp mapToPnfExtCp(tosca.nodes.nfv.PnfExtCp o);

	VNF mapToVNF(tosca.nodes.nfv.VNF o);

	VnfExtCp mapToVnfExtCp(tosca.nodes.nfv.VnfExtCp o);

	@Mapping(target = "supportMandatory", ignore = true)
	VirtualNetworkInterfaceRequirements mapToVirtualNetworkInterfaceRequirements(tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements o);

	Compute mapToCompute(tosca.nodes.nfv.vdu.Compute o);

	@Mapping(target = "hugePagesRequirements", ignore = true)
	VirtualMemory mapToVirtualMemory(tosca.datatypes.nfv.VirtualMemory o);

	@Mapping(target = "perVnfcInstance", ignore = true)
	VirtualBlockStorage mapToVirtualBlockStorage(tosca.nodes.nfv.vdu.VirtualBlockStorage o);

	@Mapping(target = "perVnfcInstance", ignore = true)
	VirtualObjectStorage mapToVirtualObjectStorage(tosca.nodes.nfv.vdu.VirtualObjectStorage o);

	@Mapping(target = "perVnfcInstance", ignore = true)
	VirtualFileStorage mapToVirtualFileStorage(tosca.nodes.nfv.vdu.VirtualFileStorage o);

	VduCp mapToVduCp(tosca.nodes.nfv.VduCp o);

	VnfVirtualLink mapToVnfVirtualLink(tosca.nodes.nfv.VnfVirtualLink o);

	VipCp mapToVipCp(tosca.nodes.nfv.VipCp o);

	VduSubCp mapToVduSubCp(tosca.nodes.nfv.VduSubCp o);

	default ServiceData toServiceData(final String string) {
		if (string == null) {
			return null;
		}
		final ServiceData sd = new ServiceData();
		sd.setHost(string);
		return sd;
	}

	com.ubiqube.parser.tosca.objects.tosca.nodes.Compute mapToCompute(tosca.nodes.Compute arg);
}
