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
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.sol001;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VduProfile;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VirtualLinkMonitoringParameter;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfMonitoringParameter;
import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfcMonitoringParameter;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;

@Mapper
public interface MapStruct331Impl {
	@Mapping(target = "nfviMaintenanceInfo", ignore = true)
	VnfVirtualLink map(tosca.nodes.nfv.VnfVirtualLink o);

	default Map<String, VirtualLinkMonitoringParameter> mapToVirtualLinkMonitoringParameter(final List<tosca.datatypes.nfv.VirtualLinkMonitoringParameter> value) {
		if (null == value) {
			return Map.of();
		}
		return value.stream().collect(Collectors.toMap(tosca.datatypes.nfv.VirtualLinkMonitoringParameter::getName, this::map));
	}

	VirtualLinkMonitoringParameter map(tosca.datatypes.nfv.VirtualLinkMonitoringParameter value);
	//

	VNF map(tosca.nodes.nfv.VNF o);

	default Map<String, VnfMonitoringParameter> mapToVnfMonitoringParameter(final List<tosca.datatypes.nfv.VnfMonitoringParameter> value) {
		if (null == value) {
			return Map.of();
		}
		return value.stream().collect(Collectors.toMap(tosca.datatypes.nfv.VnfMonitoringParameter::getName, this::map));
	}

	VnfMonitoringParameter map(tosca.datatypes.nfv.VnfMonitoringParameter o);

	//

	Compute map(tosca.nodes.nfv.vdu.Compute o);

	@Mapping(target = "nfviMaintenanceInfo", ignore = true)
	VduProfile map(tosca.datatypes.nfv.VduProfile o);

	default Map<String, VnfcMonitoringParameter> map(final List<tosca.datatypes.nfv.VnfcMonitoringParameter> value) {
		if (null == value) {
			return Map.of();
		}
		return value.stream().collect(Collectors.toMap(tosca.datatypes.nfv.VnfcMonitoringParameter::getName, this::map));
	}

	VnfcMonitoringParameter map(tosca.datatypes.nfv.VnfcMonitoringParameter o);
}
