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
package com.ubiqube.etsi.mano.sol001.v251.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfMonitoringParameter;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.AffinityRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.AntiAffinityRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.InstantiationLevels;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.ScalingAspects;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SecurityGroupRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SupportedVnfInterface;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInitialDelta;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInstantiationLevels;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduScalingAspectDeltas;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkBitrateInitialDelta;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkBitrateScalingAspectDeltas;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkInstantiationLevels;

@Mapper
public interface PolicyMapper {

	default Map<String, VnfMonitoringParameter> mapToVnfMonitoringParameter(final List<tosca.datatypes.nfv.VnfMonitoringParameter> value) {
		if ((null == value) || value.isEmpty()) {
			return Map.of();
		}
		return value.stream()
				.map(this::mapToNVnfMonitoringParameter)
				.collect(Collectors.toMap(VnfMonitoringParameter::getName, x -> x));
	}

	VnfMonitoringParameter mapToNVnfMonitoringParameter(tosca.datatypes.nfv.VnfMonitoringParameter o);

	InstantiationLevels mapToInstantiationLevels(tosca.policies.nfv.InstantiationLevels o);

	VduInstantiationLevels mapToVduInstantiationLevels(tosca.policies.nfv.VduInstantiationLevels o);

	VirtualLinkInstantiationLevels mapToVirtualLinkInstantiationLevels(
			tosca.policies.nfv.VirtualLinkInstantiationLevels o);

	ScalingAspects mapToScalingAspects(tosca.policies.nfv.ScalingAspects o);

	VduScalingAspectDeltas mapToVduScalingAspectDeltas(tosca.policies.nfv.VduScalingAspectDeltas o);

	VirtualLinkBitrateScalingAspectDeltas mapToVirtualLinkBitrateScalingAspectDeltas(
			tosca.policies.nfv.VirtualLinkBitrateScalingAspectDeltas o);

	VduInitialDelta mapToVduInitialDelta(tosca.policies.nfv.VduInitialDelta o);

	VirtualLinkBitrateInitialDelta mapToVirtualLinkBitrateInitialDelta(
			tosca.policies.nfv.VirtualLinkBitrateInitialDelta o);

	@Mapping(target = "nfviMaintenanceGroupInfo", ignore = true)
	AffinityRule mapToAffinityRule(tosca.policies.nfv.AffinityRule o);

	@Mapping(target = "nfviMaintenanceGroupInfo", ignore = true)
	AntiAffinityRule mapToAntiAffinityRule(tosca.policies.nfv.AntiAffinityRule o);

	SupportedVnfInterface mapToSupportedVnfInterface(tosca.policies.nfv.SupportedVnfInterface o);

	SecurityGroupRule mapToSecurityGroupRule(tosca.policies.nfv.SecurityGroupRule o);

}
