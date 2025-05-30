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
package com.ubiqube.etsi.mano.sol001.v281.mapper;

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
