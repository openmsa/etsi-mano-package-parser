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
package com.ubiqube.etsi.mano.sol001.v441.mapper;

import org.mapstruct.Mapper;

import com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.ChangeCurrentVnfPackage;
import com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.NsVnfIndicator;
import com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.Nslcm;
import com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.VnfIndicator;
import com.ubiqube.parser.tosca.objects.tosca.interfaces.nfv.Vnflcm;

@Mapper
public interface InterfaceMapper {
	Nslcm mapToNslcm(tosca.interfaces.nfv.Nslcm o);

	NsVnfIndicator mapToNsVnfIndicator(tosca.interfaces.nfv.NsVnfIndicator o);

	Vnflcm mapToVnflcm(tosca.interfaces.nfv.Vnflcm o);

	VnfIndicator mapToVnfIndicator(tosca.interfaces.nfv.VnfIndicator o);

	ChangeCurrentVnfPackage mapToChangeCurrentVnfPackage(tosca.interfaces.nfv.ChangeCurrentVnfPackage o);
}
