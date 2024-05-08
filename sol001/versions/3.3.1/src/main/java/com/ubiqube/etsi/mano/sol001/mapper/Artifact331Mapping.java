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
package com.ubiqube.etsi.mano.sol001.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ubiqube.parser.tosca.Artifact;

import tosca.datatypes.nfv.SwImageData;

@Mapper
public interface Artifact331Mapping {
	@Mapping(target = "artifactVersion", ignore = true)
	@Mapping(target = "checksumAlgorithm", source = "checksum.algorithm")
	@Mapping(target = "deployPath", ignore = true)
	@Mapping(target = "description", ignore = true)
	@Mapping(target = "file", ignore = true)
	@Mapping(target = "properties", ignore = true)
	@Mapping(target = "repository", ignore = true)
	@Mapping(target = "type", ignore = true)
	Artifact map(final SwImageData s);

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
	SwImageData map(Artifact o);
}
