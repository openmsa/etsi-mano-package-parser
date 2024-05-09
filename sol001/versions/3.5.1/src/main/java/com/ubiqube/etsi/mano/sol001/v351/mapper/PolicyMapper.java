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
package com.ubiqube.etsi.mano.sol001.v351.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ubiqube.parser.tosca.objects.tosca.datatypes.nfv.VnfPackageChangeSelector;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.AffinityRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.AntiAffinityRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.InstantiationLevels;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.LcmCoordinationAction;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.LcmCoordinationsForLcmOperation;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NfpRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsAffinityRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsAntiAffinityRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsAutoScale;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsInstantiationLevels;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsMonitoring;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsScalingAspects;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsSecurityGroupRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsToInstantiationLevelMapping;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.NsToLevelMapping;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.PnfSecurityGroupRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.ScalingAspects;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SecurityGroupRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SupportedVnfInterface;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInitialDelta;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInstantiationLevels;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduScalingAspectDeltas;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkBitrateInitialDelta;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkBitrateScalingAspectDeltas;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkInstantiationLevels;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkToInstantiationLevelMapping;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkToLevelMapping;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfIndicator;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfMonitoring;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfPackageChange;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfToInstantiationLevelMapping;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfToLevelMapping;

@Mapper
public interface PolicyMapper {
	NsAffinityRule mapToNsAffinityRule(tosca.policies.nfv.NsAffinityRule o);

	NsAntiAffinityRule mapToNsAntiAffinityRule(tosca.policies.nfv.NsAntiAffinityRule o);

	NsSecurityGroupRule mapToNsSecurityGroupRule(tosca.policies.nfv.NsSecurityGroupRule o);

	NfpRule mapToNfpRule(tosca.policies.nfv.NfpRule o);

	NsMonitoring mapToNsMonitoring(tosca.policies.nfv.NsMonitoring o);

	VnfMonitoring mapToVnfMonitoring(tosca.policies.nfv.VnfMonitoring o);

	NsScalingAspects mapToNsScalingAspects(tosca.policies.nfv.NsScalingAspects o);

	VnfToLevelMapping mapToVnfToLevelMapping(tosca.policies.nfv.VnfToLevelMapping o);

	NsToLevelMapping mapToNsToLevelMapping(tosca.policies.nfv.NsToLevelMapping o);

	VirtualLinkToLevelMapping mapToVirtualLinkToLevelMapping(
			tosca.policies.nfv.VirtualLinkToLevelMapping o);

	NsInstantiationLevels mapToNsInstantiationLevels(tosca.policies.nfv.NsInstantiationLevels o);

	VnfToInstantiationLevelMapping mapToVnfToInstantiationLevelMapping(
			tosca.policies.nfv.VnfToInstantiationLevelMapping o);

	NsToInstantiationLevelMapping mapToNsToInstantiationLevelMapping(
			tosca.policies.nfv.NsToInstantiationLevelMapping o);

	VirtualLinkToInstantiationLevelMapping mapToVirtualLinkToInstantiationLevelMapping(
			tosca.policies.nfv.VirtualLinkToInstantiationLevelMapping o);

	NsAutoScale mapToNsAutoScale(tosca.policies.nfv.NsAutoScale o);

	PnfSecurityGroupRule mapToPnfSecurityGroupRule(tosca.policies.nfv.PnfSecurityGroupRule o);

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

	@Mapping(target = "name", ignore = true)
	VnfIndicator mapToVnfIndicator(tosca.policies.nfv.VnfIndicator o);

	VnfPackageChange mapToVnfPackageChange(tosca.policies.nfv.VnfPackageChange o);

	default List<VnfPackageChangeSelector> map(final tosca.datatypes.nfv.VnfPackageChangeSelector value) {
		if (null == value) {
			return List.of();
		}
		return List.of(mapToVnfPackageChangeSelector(value));
	}

	VnfPackageChangeSelector mapToVnfPackageChangeSelector(tosca.datatypes.nfv.VnfPackageChangeSelector value);

	LcmCoordinationAction mapToLcmCoordinationAction(tosca.policies.nfv.LcmCoordinationAction o);

	LcmCoordinationsForLcmOperation mapToLcmCoordinationsForLcmOperation(
			tosca.policies.nfv.LcmCoordinationsForLcmOperation o);

}
