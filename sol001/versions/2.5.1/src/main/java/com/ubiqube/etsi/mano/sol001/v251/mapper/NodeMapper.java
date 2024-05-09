/**
 *     Copyright (C) 2019-2024 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see https://www.gnu.org/licenses/.
 */
package com.ubiqube.etsi.mano.sol001.v251.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.BootData;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.ContentOrFileData;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.L2ProtocolData;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.L3AddressData;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.LocationInfo;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.NsVlProfile;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.ServiceData;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VduProfile;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VirtualLinkMonitoringParameter;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfLcmOperationsConfiguration;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfMonitoringParameter;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfProfile;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfcMonitoringParameter;
import com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.Vnflcm;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NS;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NsVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Sap;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualBlockStorage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualFileStorage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualObjectStorage;

@Mapper
public interface NodeMapper {
	@Mapping(target = "flavourId", ignore = true)
	@Mapping(target = "nsProfile", ignore = true)
	@Mapping(target = "serviceAvailabilityLevel", ignore = true)
	@Mapping(target = "priority", ignore = true)
	@Mapping(target = "scaleStatus", ignore = true)
	NS mapToNS(tosca.nodes.nfv.NS o);

	Sap mapToSap(tosca.nodes.nfv.Sap o);

	@Mapping(target = "fixedIpAddress", ignore = true)
	@Mapping(target = "ipAddressAssignmentSubtype", ignore = true)
	L3AddressData mapToL3AddressData(tosca.datatypes.nfv.L3AddressData o);

	NsVirtualLink mapToNsVirtualLink(tosca.nodes.nfv.NsVirtualLink o);

	@Mapping(target = "serviceAvailabilityLevel", ignore = true)
	@Mapping(target = "virtualLinkProtocolData", ignore = true)
	NsVlProfile maptToNsVlProfile(tosca.datatypes.nfv.NsVlProfile o);

	PNF mapToPNF(tosca.nodes.nfv.PNF o);

	@Mapping(target = "geographicCoordinates", ignore = true)
	LocationInfo mapToLocationInfo(tosca.datatypes.nfv.LocationInfo o);

	PnfExtCp mapToPnfExtCp(tosca.nodes.nfv.PnfExtCp o);

	@Mapping(target = "vnfProfile", ignore = true)
	@Mapping(target = "scaleStatus", ignore = true)
	VNF mapToVNF(tosca.nodes.nfv.VNF o);

	@Mapping(target = "changeCurrentPackage", ignore = true)
	@Mapping(target = "createSnapshot", ignore = true)
	@Mapping(target = "revertToSnapshot", ignore = true)
	VnfLcmOperationsConfiguration mapToVnfLcmOperationsConfiguration(tosca.datatypes.nfv.VnfLcmOperationsConfiguration o);

	@Mapping(target = "changeCurrentPackage", ignore = true)
	@Mapping(target = "changeCurrentPackageEnd", ignore = true)
	@Mapping(target = "changeCurrentPackageStart", ignore = true)
	@Mapping(target = "createSnapshot", ignore = true)
	@Mapping(target = "createSnapshotEnd", ignore = true)
	@Mapping(target = "createSnapshotStart", ignore = true)
	@Mapping(target = "revertToSnapshot", ignore = true)
	@Mapping(target = "revertToSnapshotEnd", ignore = true)
	@Mapping(target = "revertToSnapshotStart", ignore = true)
	Vnflcm mapToVnflcm(tosca.interfaces.nfv.Vnflcm o);

	@Mapping(target = "serviceAvailabilityLevel", ignore = true)
	VnfProfile mapToVnfProfile(tosca.datatypes.nfv.VnfProfile o);

	default Map<String, VnfMonitoringParameter> map(final List<tosca.datatypes.nfv.VnfMonitoringParameter> value) {
		if ((null == value) || value.isEmpty()) {
			return Map.of();
		}
		return value.stream()
				.map(this::mapToVnfMonitoringParameter)
				.collect(Collectors.toMap(VnfMonitoringParameter::getName, x -> x));
	}

	VnfMonitoringParameter mapToVnfMonitoringParameter(tosca.datatypes.nfv.VnfMonitoringParameter o);

	VnfExtCp mapToVnfExtCp(tosca.nodes.nfv.VnfExtCp o);

	@Mapping(target = "supportMandatory", ignore = true)
	VirtualNetworkInterfaceRequirements mapToVirtualNetworkInterfaceRequirements(tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements o);

	@Mapping(target = "bootOrder", ignore = true)
	@Mapping(target = "nfviConstraints", ignore = true)
	Compute mapToCompute(tosca.nodes.nfv.vdu.Compute o);

	default BootData toBootData(final String string) {
		if (null == string) {
			return null;
		}
		final BootData bd = new BootData();
		final ContentOrFileData cod = new ContentOrFileData();
		cod.setContent(string);
		bd.setContentOrFileData(cod);
		return bd;
	}

	default Map<String, VnfcMonitoringParameter> mapTpoVnfcMonitoringParameter(final List<tosca.datatypes.nfv.VnfcMonitoringParameter> value) {
		if ((null == value) || value.isEmpty()) {
			return Map.of();
		}
		return value.stream()
				.map(this::mapToVnfcMonitoringParameter)
				.collect(Collectors.toMap(VnfcMonitoringParameter::getName, x -> x));
	}

	VnfcMonitoringParameter mapToVnfcMonitoringParameter(tosca.datatypes.nfv.VnfcMonitoringParameter o);

	@Mapping(target = "nfviMaintenanceInfo", ignore = true)
	VduProfile mapToVduProfile(tosca.datatypes.nfv.VduProfile o);

	@Mapping(target = "nfviMaintenanceInfo", ignore = true)
	@Mapping(target = "perVnfcInstance", ignore = true)
	VirtualBlockStorage mapToVirtualBlockStorage(tosca.nodes.nfv.vdu.VirtualBlockStorage o);

	@Mapping(target = "nfviMaintenanceInfo", ignore = true)
	@Mapping(target = "perVnfcInstance", ignore = true)
	VirtualObjectStorage mapToVirtualObjectStorage(tosca.nodes.nfv.vdu.VirtualObjectStorage o);

	@Mapping(target = "nfviMaintenanceInfo", ignore = true)
	@Mapping(target = "perVnfcInstance", ignore = true)
	VirtualFileStorage mapToVirtualFileStorage(tosca.nodes.nfv.vdu.VirtualFileStorage o);

	@Mapping(target = "trunkBinding", ignore = true)
	VduCp mapToVduCp(tosca.nodes.nfv.VduCp o);

	@Mapping(target = "nfviMaintenanceInfo", ignore = true)
	VnfVirtualLink mapToVnfVirtualLink(tosca.nodes.nfv.VnfVirtualLink o);

	@Mapping(target = "segmentationId", ignore = true)
	L2ProtocolData mapToL2ProtocolData(tosca.datatypes.nfv.L2ProtocolData o);

	default Map<String, VirtualLinkMonitoringParameter> mapToVirtualLinkMonitoringParameter(final List<tosca.datatypes.nfv.VirtualLinkMonitoringParameter> value) {
		if ((null == value) || value.isEmpty()) {
			return Map.of();
		}
		return value.stream()
				.map(this::mapToVirtualLinkMonitoringParameter)
				.collect(Collectors.toMap(VirtualLinkMonitoringParameter::getName, x -> x));
	}

	VirtualLinkMonitoringParameter mapToVirtualLinkMonitoringParameter(tosca.datatypes.nfv.VirtualLinkMonitoringParameter o);

	default ServiceData toServiceData(final String string) {
		if (string == null) {
			return null;
		}
		final ServiceData sd = new ServiceData();
		sd.setHost(string);
		return sd;
	}
}
