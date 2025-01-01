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
package com.ubiqube.etsi.mano.sol001.v431.mapper;

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
