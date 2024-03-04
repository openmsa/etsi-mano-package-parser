/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.ZipUtil.Entry;
import com.ubiqube.parser.tosca.api.ToscaApi;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduScalingAspectDeltas;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VnfIndicator;

import ma.glasnost.orika.MapperFactory;

class UbiVnfToscaTest {

	private static final Logger LOG = LoggerFactory.getLogger(UbiVnfToscaTest.class);

	private final Map<String, String> parameters = new HashMap<>();

	private final ToscaApi toscaApi;

	public UbiVnfToscaTest() {
		final MapperFactory mapperFactory = Utils.createMapperFactory();
		toscaApi = new ToscaApi(this.getClass().getClassLoader(), mapperFactory.getMapperFacade());
	}

	@Test
	void testUbiCsar() throws Exception {
		StaticTestTools.createVnfPackage();
		final ToscaParser toscaParser = new ToscaParser(new File("/tmp/ubi-tosca.csar"));
		final ToscaContext root = toscaParser.getContext();

		final List<VnfVirtualLink> list = toscaApi.getObjects(root, parameters, VnfVirtualLink.class);
		assertEquals(3, list.size());
		final VnfVirtualLink elem = list.get(0);
		assertEquals("leftVl01", elem.getInternalName());
		assertEquals("192.168.0.100", elem.getVlProfile().getVirtualLinkProtocolData().get(0).getL3ProtocolData().getIpAllocationPools().get(0).getStartIpAddress());

		final List<VnfIndicator> l2 = toscaApi.getObjects(root, parameters, VnfIndicator.class);
		assertEquals(2, l2.size());
	}

	@Test
	void testUbiCsarCompute() throws Exception {
		ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar", Entry.of("ubi-tosca/Definitions/tosca_ubi.yaml", "Definitions/tosca_ubi.yaml"),
				Entry.of("etsi_nfv_sol001_vnfd_types.yaml", "Definitions/etsi_nfv_sol001_vnfd_types.yaml"),
				Entry.of("etsi_nfv_sol001_common_types.yaml", "Definitions/etsi_nfv_sol001_common_types.yaml"),
				Entry.of("ubi-tosca/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		final ToscaParser toscaParser = new ToscaParser(new File("/tmp/ubi-tosca.csar"));
		final ToscaContext root = toscaParser.getContext();

		final List<Compute> list = toscaApi.getObjects(root, parameters, Compute.class);
		LOG.debug("{}", list);
		final List<VnfExtCp> extCp = toscaApi.getObjects(root, parameters, VnfExtCp.class);
		LOG.debug("{}", extCp);
		final List<VduScalingAspectDeltas> vsad = toscaApi.getObjects(root, parameters, VduScalingAspectDeltas.class);
		LOG.debug("vsad {}", vsad);
		assertNotNull(vsad);
	}
}
