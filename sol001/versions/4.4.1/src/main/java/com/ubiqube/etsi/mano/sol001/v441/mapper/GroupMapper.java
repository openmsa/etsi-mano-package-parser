package com.ubiqube.etsi.mano.sol001.v441.mapper;

import org.mapstruct.Mapper;

import com.ubiqube.parser.tosca.objects.tosca.groups.nfv.NsPlacementGroup;
import com.ubiqube.parser.tosca.objects.tosca.groups.nfv.PlacementGroup;
import com.ubiqube.parser.tosca.objects.tosca.groups.nfv.VNFFG;

@Mapper
public interface GroupMapper {
	NsPlacementGroup mapToNsPlacementGroup(tosca.groups.nfv.NsPlacementGroup o);

	VNFFG mapToVNFFG(tosca.groups.nfv.VNFFG o);

	PlacementGroup mapToPlacementGroup(tosca.groups.nfv.PlacementGroup o);
}
