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
package com.ubiqube.etsi.mano.sol001;

import org.mapstruct.factory.Mappers;

import com.ubiqube.etsi.mano.sol001.v251.mapper.ArtifactMapper;
import com.ubiqube.etsi.mano.sol001.v251.mapper.CapabilityMapper;
import com.ubiqube.etsi.mano.sol001.v251.mapper.GroupMapper;
import com.ubiqube.etsi.mano.sol001.v251.mapper.InterfaceMapper;
import com.ubiqube.etsi.mano.sol001.v251.mapper.NodeMapper;
import com.ubiqube.etsi.mano.sol001.v251.mapper.PolicyMapper;
import com.ubiqube.parser.tosca.api.ToscaMapper;

public class MapstructMapper251Impl implements ToscaMapper {
	private final ArtifactMapper artifactMapper = Mappers.getMapper(ArtifactMapper.class);
	private final CapabilityMapper capabilityMApper = Mappers.getMapper(CapabilityMapper.class);
	private final GroupMapper groupMapper = Mappers.getMapper(GroupMapper.class);
	private final InterfaceMapper interfaceMapper = Mappers.getMapper(InterfaceMapper.class);
	private final NodeMapper nodeMapper = Mappers.getMapper(NodeMapper.class);
	private final PolicyMapper policyMapper = Mappers.getMapper(PolicyMapper.class);

	@Override
	public <U> U map(final Object arg, final Class<U> dest) {
		final String destClass = dest.getCanonicalName();
		final Object r = switch (destClass) {
		case "com.ubiqube.parser.tosca.objects.tosca.artifacts.nfv.SwImage" -> artifactMapper.mapToSwImage((tosca.artifacts.nfv.SwImage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.artifacts.implementation.nfv.Mistral" -> artifactMapper.mapToMistral((tosca.artifacts.implementation.nfv.Mistral) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualBindable" -> capabilityMApper.mapToVirtualBindable((tosca.capabilities.nfv.VirtualBindable) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualCompute" -> capabilityMApper.mapToVirtualCompute((tosca.capabilities.nfv.VirtualCompute) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualStorage" -> capabilityMApper.mapToVirtualStorage((tosca.capabilities.nfv.VirtualStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.groups.nfv.PlacementGroup" -> groupMapper.mapToPlacementGroup((tosca.groups.nfv.PlacementGroup) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.Nslcm" -> interfaceMapper.mapToNslcm((tosca.interfaces.nfv.Nslcm) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.Vnflcm" -> interfaceMapper.mapToVnflcm((tosca.interfaces.nfv.Vnflcm) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NS" -> nodeMapper.mapToNS((tosca.nodes.nfv.NS) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduCp" -> nodeMapper.mapToVduCp((tosca.nodes.nfv.VduCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF" -> nodeMapper.mapToVNF((tosca.nodes.nfv.VNF) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PNF" -> nodeMapper.mapToPNF((tosca.nodes.nfv.PNF) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Sap" -> nodeMapper.mapToSap((tosca.nodes.nfv.Sap) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NsVirtualLink" -> nodeMapper.mapToNsVirtualLink((tosca.nodes.nfv.NsVirtualLink) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PnfExtCp" -> nodeMapper.mapToPnfExtCp((tosca.nodes.nfv.PnfExtCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfExtCp" -> nodeMapper.mapToVnfExtCp((tosca.nodes.nfv.VnfExtCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute" -> nodeMapper.mapToCompute((tosca.nodes.nfv.vdu.Compute) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualBlockStorage" -> nodeMapper.mapToVirtualBlockStorage((tosca.nodes.nfv.vdu.VirtualBlockStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualObjectStorage" -> nodeMapper.mapToVirtualObjectStorage((tosca.nodes.nfv.vdu.VirtualObjectStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualFileStorage" -> nodeMapper.mapToVirtualFileStorage((tosca.nodes.nfv.vdu.VirtualFileStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink" -> nodeMapper.mapToVnfVirtualLink((tosca.nodes.nfv.VnfVirtualLink) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.InstantiationLevels" -> policyMapper.mapToInstantiationLevels((tosca.policies.nfv.InstantiationLevels) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInstantiationLevels" -> policyMapper.mapToVduInstantiationLevels((tosca.policies.nfv.VduInstantiationLevels) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkInstantiationLevels" -> policyMapper.mapToVirtualLinkInstantiationLevels((tosca.policies.nfv.VirtualLinkInstantiationLevels) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.ScalingAspects" -> policyMapper.mapToScalingAspects((tosca.policies.nfv.ScalingAspects) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduScalingAspectDeltas" -> policyMapper.mapToVduScalingAspectDeltas((tosca.policies.nfv.VduScalingAspectDeltas) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkBitrateScalingAspectDeltas" -> policyMapper.mapToVirtualLinkBitrateScalingAspectDeltas((tosca.policies.nfv.VirtualLinkBitrateScalingAspectDeltas) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInitialDelta" -> policyMapper.mapToVduInitialDelta((tosca.policies.nfv.VduInitialDelta) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkBitrateInitialDelta" -> policyMapper.mapToVirtualLinkBitrateInitialDelta((tosca.policies.nfv.VirtualLinkBitrateInitialDelta) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.AffinityRule" -> policyMapper.mapToAffinityRule((tosca.policies.nfv.AffinityRule) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.AntiAffinityRule" -> policyMapper.mapToAntiAffinityRule((tosca.policies.nfv.AntiAffinityRule) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SupportedVnfInterface" -> policyMapper.mapToSupportedVnfInterface((tosca.policies.nfv.SupportedVnfInterface) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SecurityGroupRule" -> policyMapper.mapToSecurityGroupRule((tosca.policies.nfv.SecurityGroupRule) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.Compute" -> nodeMapper.mapToCompute((tosca.nodes.Compute) arg);
		default -> throw new IllegalArgumentException("Unexpected value: " + destClass);
		};
		return (U) r;
	}

}
