package com.ubiqube.etsi.mano.sol001.v441.mapper;

import org.mapstruct.Mapper;

import com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.AssociableVdu;
import com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.ContainerDeployable;
import com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.Forwarding;
import com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.TrunkBindable;
import com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualBindable;
import com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualCompute;
import com.ubiqube.parser.tosca.objects.tosca.capabilities.nfv.VirtualStorage;

@Mapper
public interface CapabilityMapper {
	Forwarding mapToForwarding(tosca.capabilities.nfv.Forwarding o);

	VirtualBindable mapToVirtualBindable(tosca.capabilities.nfv.VirtualBindable o);

	VirtualCompute mapToVirtualCompute(tosca.capabilities.nfv.VirtualCompute o);

	VirtualStorage mapToVirtualStorage(tosca.capabilities.nfv.VirtualStorage o);

	TrunkBindable mapToTrunkBindable(tosca.capabilities.nfv.TrunkBindable o);

	ContainerDeployable mapToContainerDeployable(tosca.capabilities.nfv.ContainerDeployable o);

	AssociableVdu mapToAssociableVdu(tosca.capabilities.nfv.AssociableVdu o);
}
