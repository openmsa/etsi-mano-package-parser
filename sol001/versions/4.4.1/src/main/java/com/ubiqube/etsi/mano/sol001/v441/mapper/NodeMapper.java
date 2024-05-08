package com.ubiqube.etsi.mano.sol001.v441.mapper;

import org.mapstruct.Mapper;

import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Forwarding;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Mciop;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NFP;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NS;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NfpPosition;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NfpPositionElement;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.NsVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.PnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Sap;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduSubCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VipCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VirtualCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.OsContainer;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.OsContainerDeployableUnit;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualBlockStorage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualFileStorage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualObjectStorage;

@Mapper
public interface NodeMapper {
	NS mapToNS(tosca.nodes.nfv.NS o);

	Sap mapToSap(tosca.nodes.nfv.Sap o);

	NsVirtualLink mapToNsVirtualLink(tosca.nodes.nfv.NsVirtualLink o);

	NfpPositionElement mapToNfpPositionElement(tosca.nodes.nfv.NfpPositionElement o);

	NfpPosition mapToNfpPosition(tosca.nodes.nfv.NfpPosition o);

	NFP mapToNFP(tosca.nodes.nfv.NFP o);

	Forwarding mapToForwarding(tosca.nodes.nfv.Forwarding o);

	PNF mapToPNF(tosca.nodes.nfv.PNF o);

	PnfExtCp mapToPnfExtCp(tosca.nodes.nfv.PnfExtCp o);

	VNF mapToVNF(tosca.nodes.nfv.VNF o);

	VnfExtCp mapToVnfExtCp(tosca.nodes.nfv.VnfExtCp o);

	Compute mapToCompute(tosca.nodes.nfv.vdu.Compute o);

	VirtualBlockStorage mapToVirtualBlockStorage(tosca.nodes.nfv.vdu.VirtualBlockStorage o);

	VirtualObjectStorage mapToVirtualObjectStorage(tosca.nodes.nfv.vdu.VirtualObjectStorage o);

	VirtualFileStorage mapToVirtualFileStorage(tosca.nodes.nfv.vdu.VirtualFileStorage o);

	VduCp mapToVduCp(tosca.nodes.nfv.VduCp o);

	VnfVirtualLink mapToVnfVirtualLink(tosca.nodes.nfv.VnfVirtualLink o);

	VipCp mapToVipCp(tosca.nodes.nfv.VipCp o);

	VduSubCp mapToVduSubCp(tosca.nodes.nfv.VduSubCp o);

	OsContainer mapToOsContainer(tosca.nodes.nfv.vdu.OsContainer o);

	OsContainerDeployableUnit mapToOsContainerDeployableUnit(
			tosca.nodes.nfv.vdu.OsContainerDeployableUnit o);

	Mciop mapToMciop(tosca.nodes.nfv.Mciop o);

	VirtualCp mapToVirtualCp(tosca.nodes.nfv.VirtualCp o);
}
