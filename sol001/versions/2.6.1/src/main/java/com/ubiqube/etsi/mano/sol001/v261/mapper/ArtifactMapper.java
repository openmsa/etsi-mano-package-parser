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
package com.ubiqube.etsi.mano.sol001.v261.mapper;

import org.mapstruct.Mapper;

import com.ubiqube.parser.tosca.objects.tosca.artifacts.implementation.nfv.Mistral;
import com.ubiqube.parser.tosca.objects.tosca.artifacts.nfv.SwImage;
import org.mapstruct.Mapping;

@Mapper
public interface ArtifactMapper {
	@Mapping(target = "checksum", ignore = true)
	@Mapping(target = "containerFormat", ignore = true)
	@Mapping(target = "diskFormat", ignore = true)
	@Mapping(target = "minDisk", ignore = true)
	@Mapping(target = "minRam", ignore = true)
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "operatingSystem", ignore = true)
	@Mapping(target = "provider", ignore = true)
	@Mapping(target = "size", ignore = true)
	@Mapping(target = "supportedVirtualisationEnvironments", ignore = true)
	@Mapping(target = "version", ignore = true)
	SwImage mapToSwImage(tosca.artifacts.nfv.SwImage o);

	Mistral mapToMistral(tosca.artifacts.implementation.nfv.Mistral o);

}
