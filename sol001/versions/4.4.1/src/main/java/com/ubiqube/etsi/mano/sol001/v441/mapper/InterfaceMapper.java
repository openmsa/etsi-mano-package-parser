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
