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

import com.ubiqube.etsi.mano.sol001.v441.mapper.ArtifactMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.CapabilityMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.GroupMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.InterfaceMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.NodeMapper;
import com.ubiqube.etsi.mano.sol001.v441.mapper.PolicyMapper;
import com.ubiqube.parser.tosca.api.ToscaMapper;

public class MapstructMapper441Impl implements ToscaMapper {
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
		case "com.ubiqube.parser.tosca.objects.tosca.artifacts.nfv.HelmChart" -> artifactMapper.mapToHelmChart((tosca.artifacts.nfv.HelmChart) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.Forwarding" -> capabilityMApper.mapToForwarding((tosca.capabilities.nfv.Forwarding) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualBindable" -> capabilityMApper.mapToVirtualBindable((tosca.capabilities.nfv.VirtualBindable) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualCompute" -> capabilityMApper.mapToVirtualCompute((tosca.capabilities.nfv.VirtualCompute) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualStorage" -> capabilityMApper.mapToVirtualStorage((tosca.capabilities.nfv.VirtualStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.TrunkBindable" -> capabilityMApper.mapToTrunkBindable((tosca.capabilities.nfv.TrunkBindable) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.ContainerDeployable" -> capabilityMApper.mapToContainerDeployable((tosca.capabilities.nfv.ContainerDeployable) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.AssociableVdu" -> capabilityMApper.mapToAssociableVdu((tosca.capabilities.nfv.AssociableVdu) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.groups.nfv.NsPlacementGroup" -> groupMapper.mapToNsPlacementGroup((tosca.groups.nfv.NsPlacementGroup) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.groups.nfv.VNFFG" -> groupMapper.mapToVNFFG((tosca.groups.nfv.VNFFG) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.groups.nfv.PlacementGroup" -> groupMapper.mapToPlacementGroup((tosca.groups.nfv.PlacementGroup) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.ChangeCurrentVnfPackage" -> interfaceMapper.mapToChangeCurrentVnfPackage((tosca.interfaces.nfv.ChangeCurrentVnfPackage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.Nslcm" -> interfaceMapper.mapToNslcm((tosca.interfaces.nfv.Nslcm) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.NsVnfIndicator" -> interfaceMapper.mapToNsVnfIndicator((tosca.interfaces.nfv.NsVnfIndicator) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.Vnflcm" -> interfaceMapper.mapToVnflcm((tosca.interfaces.nfv.Vnflcm) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.VnfIndicator" -> interfaceMapper.mapToVnfIndicator((tosca.interfaces.nfv.VnfIndicator) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NS" -> nodeMapper.mapToNS((tosca.nodes.nfv.NS) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NFP" -> nodeMapper.mapToNFP((tosca.nodes.nfv.NFP) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Mciop" -> nodeMapper.mapToMciop((tosca.nodes.nfv.Mciop) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduCp" -> nodeMapper.mapToVduCp((tosca.nodes.nfv.VduCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VipCp" -> nodeMapper.mapToVipCp((tosca.nodes.nfv.VipCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF" -> nodeMapper.mapToVNF((tosca.nodes.nfv.VNF) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PNF" -> nodeMapper.mapToPNF((tosca.nodes.nfv.PNF) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Sap" -> nodeMapper.mapToSap((tosca.nodes.nfv.Sap) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NsVirtualLink" -> nodeMapper.mapToNsVirtualLink((tosca.nodes.nfv.NsVirtualLink) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Forwarding" -> nodeMapper.mapToForwarding((tosca.nodes.nfv.Forwarding) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.OsContainerDeployableUnit" -> nodeMapper.mapToOsContainerDeployableUnit((tosca.nodes.nfv.vdu.OsContainerDeployableUnit) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NfpPositionElement" -> nodeMapper.mapToNfpPositionElement((tosca.nodes.nfv.NfpPositionElement) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NfpPosition" -> nodeMapper.mapToNfpPosition((tosca.nodes.nfv.NfpPosition) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PnfExtCp" -> nodeMapper.mapToPnfExtCp((tosca.nodes.nfv.PnfExtCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfExtCp" -> nodeMapper.mapToVnfExtCp((tosca.nodes.nfv.VnfExtCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute" -> nodeMapper.mapToCompute((tosca.nodes.nfv.vdu.Compute) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualBlockStorage" -> nodeMapper.mapToVirtualBlockStorage((tosca.nodes.nfv.vdu.VirtualBlockStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualObjectStorage" -> nodeMapper.mapToVirtualObjectStorage((tosca.nodes.nfv.vdu.VirtualObjectStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualFileStorage" -> nodeMapper.mapToVirtualFileStorage((tosca.nodes.nfv.vdu.VirtualFileStorage) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduSubCp" -> nodeMapper.mapToVduSubCp((tosca.nodes.nfv.VduSubCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink" -> nodeMapper.mapToVnfVirtualLink((tosca.nodes.nfv.VnfVirtualLink) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.OsContainer" -> nodeMapper.mapToOsContainer((tosca.nodes.nfv.vdu.OsContainer) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VirtualCp" -> nodeMapper.mapToVirtualCp((tosca.nodes.nfv.VirtualCp) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfIndicator" -> policyMapper.mapToVnfIndicator((tosca.policies.nfv.VnfIndicator) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkToLevelMapping" -> policyMapper.mapToVirtualLinkToLevelMapping((tosca.policies.nfv.VirtualLinkToLevelMapping) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsAffinityRule" -> policyMapper.mapToNsAffinityRule((tosca.policies.nfv.NsAffinityRule) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsAntiAffinityRule" -> policyMapper.mapToNsAntiAffinityRule((tosca.policies.nfv.NsAntiAffinityRule) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsSecurityGroupRule" -> policyMapper.mapToNsSecurityGroupRule((tosca.policies.nfv.NsSecurityGroupRule) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NfpRule" -> policyMapper.mapToNfpRule((tosca.policies.nfv.NfpRule) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsMonitoring" -> policyMapper.mapToNsMonitoring((tosca.policies.nfv.NsMonitoring) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfMonitoring" -> policyMapper.mapToVnfMonitoring((tosca.policies.nfv.VnfMonitoring) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsScalingAspects" -> policyMapper.mapToNsScalingAspects((tosca.policies.nfv.NsScalingAspects) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfToLevelMapping" -> policyMapper.mapToVnfToLevelMapping((tosca.policies.nfv.VnfToLevelMapping) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsToLevelMapping" -> policyMapper.mapToNsToLevelMapping((tosca.policies.nfv.NsToLevelMapping) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsInstantiationLevels" -> policyMapper.mapToNsInstantiationLevels((tosca.policies.nfv.NsInstantiationLevels) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfToInstantiationLevelMapping" -> policyMapper.mapToVnfToInstantiationLevelMapping((tosca.policies.nfv.VnfToInstantiationLevelMapping) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsToInstantiationLevelMapping" -> policyMapper.mapToNsToInstantiationLevelMapping((tosca.policies.nfv.NsToInstantiationLevelMapping) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkToInstantiationLevelMapping" -> policyMapper.mapToVirtualLinkToInstantiationLevelMapping((tosca.policies.nfv.VirtualLinkToInstantiationLevelMapping) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsAutoScale" -> policyMapper.mapToNsAutoScale((tosca.policies.nfv.NsAutoScale) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.DataFlowInfo" -> policyMapper.mapToDataFlowInfo((tosca.policies.nfv.DataFlowInfo) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.PnfSecurityGroupRule" -> policyMapper.mapToPnfSecurityGroupRule((tosca.policies.nfv.PnfSecurityGroupRule) arg);
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
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfPackageChange" -> policyMapper.mapToVnfPackageChange((tosca.policies.nfv.VnfPackageChange) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.LcmCoordinationAction" -> policyMapper.mapToLcmCoordinationAction((tosca.policies.nfv.LcmCoordinationAction) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.LcmCoordinationsForLcmOperation" -> policyMapper.mapToLcmCoordinationsForLcmOperation((tosca.policies.nfv.LcmCoordinationsForLcmOperation) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VipCpScalingAspectDeltas" -> policyMapper.mapToVipCpScalingAspectDeltas((tosca.policies.nfv.VipCpScalingAspectDeltas) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VipCpInitialDelta" -> policyMapper.mapToVipCpInitialDelta((tosca.policies.nfv.VipCpInitialDelta) arg);
		case "com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VipCpInstantiationLevels" -> policyMapper.mapToVipCpInstantiationLevels((tosca.policies.nfv.VipCpInstantiationLevels) arg);
		default -> throw new IllegalArgumentException("Unexpected value: " + destClass);
		};
		return (U) r;
	}

}
