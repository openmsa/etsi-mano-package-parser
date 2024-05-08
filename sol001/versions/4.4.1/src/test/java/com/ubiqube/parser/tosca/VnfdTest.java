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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.ubiqube.parser.tosca.objects.tosca.groups.nfv.PlacementGroup;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VNF;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VduCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfExtCp;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.VnfVirtualLink;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualBlockStorage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualObjectStorage;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.AffinityRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.ScalingAspects;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SecurityGroupRule;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.SupportedVnfInterface;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInitialDelta;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduInstantiationLevels;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VduScalingAspectDeltas;
import com.ubiqube.parser.tosca.objects.tosca.policies.nfv.VirtualLinkBitrateInitialDelta;

class VnfdTest extends AbstractToscaApiTest {

	@Override
	protected void prepareArchive() {
		StaticTestTools.createVnfPackage();
	}

	@ParameterizedTest
	@MethodSource("providerClass")
	void testGeneric(final int num, final Class<?> clazz) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException, IntrospectionException {
		runTest(num, clazz);
	}

	private static Stream<Arguments> providerClass() {
		return Stream.of(
				Arguments.of(1, VirtualBlockStorage.class),
				Arguments.of(1, VirtualObjectStorage.class),
				Arguments.of(3, VnfVirtualLink.class),
				Arguments.of(4, VduCp.class),
				Arguments.of(1, PlacementGroup.class),
				Arguments.of(1, VduInstantiationLevels.class),
				Arguments.of(1, VnfExtCp.class),
				Arguments.of(1, VNF.class),
				Arguments.of(2, ScalingAspects.class),
				Arguments.of(2, VduInitialDelta.class),
				Arguments.of(2, VduScalingAspectDeltas.class),
				Arguments.of(1, SecurityGroupRule.class),
				Arguments.of(1, SupportedVnfInterface.class),
				Arguments.of(1, AffinityRule.class),
				Arguments.of(1, VirtualLinkBitrateInitialDelta.class),
				Arguments.of(2, Compute.class));
	}

	@Test
	void testVduCp() throws IllegalArgumentException, InvocationTargetException, IllegalAccessException, IntrospectionException {
		final List<VduCp> cps = runTest(4, VduCp.class);
		assertNotNull(cps.get(0).getVirtualBindingReq());
		assertNotNull(cps.get(0).getVirtualLinkReq());
	}

	@Override
	protected List<String> getIgnoreList() {
		final List<String> ignore = new ArrayList<>();
		ignore.add("getInternalDescription");
		ignore.add("getInternalName");
		ignore.add("getArtifacts");
		ignore.add("getTriggers");
		ignore.add("getTargets");
		ignore.add("getVirtualLinkable");
		// Vnflcm
		ignore.add("getScaleToLevelStart");
		ignore.add("getChangeExternalConnectivityEnd");
		ignore.add("getChangeFlavourEnd");
		ignore.add("getInstantiateEnd");
		ignore.add("getChangeExternalConnectivity");
		ignore.add("getScaleEnd");
		ignore.add("getChangeCurrentPackageStart");
		ignore.add("getScaleToLevel");
		ignore.add("getScaleToLevelEnd");
		ignore.add("getInstantiateStart");
		ignore.add("getHealEnd");
		ignore.add("getCreateSnapshotStart");
		ignore.add("getChangeExternalConnectivityStart");
		ignore.add("getModifyInformation");
		ignore.add("getHealStart");
		ignore.add("getModifyInformationStart");
		ignore.add("getInstantiate");
		ignore.add("getOperate");
		ignore.add("getRevertToSnapshotEnd");
		ignore.add("getOperateEnd");
		ignore.add("getChangeCurrentPackage");
		ignore.add("getTerminateEnd");
		ignore.add("getCreateSnapshot");
		ignore.add("getModifyInformationEnd");
		ignore.add("getHeal");
		ignore.add("getCreateSnapshotEnd");
		ignore.add("getTerminate");
		ignore.add("getChangeCurrentPackageEnd");
		ignore.add("getTerminateStart");
		ignore.add("getScaleStart");
		ignore.add("getChangeFlavourStart");
		ignore.add("getChangeFlavour");
		ignore.add("getOperateStart");
		ignore.add("getRevertToSnapshotStart");
		ignore.add("getRevertToSnapshot");
		//
		ignore.add("getFixedIpAddress");
		ignore.add("getIpAddressAssignmentSubtype");
		// VNF
		ignore.add("getServiceAvailabilityLevel");
		//
		ignore.add("getType");
		// 4.3.1
		// VduCp -> getSupportMandatory -> [0] -> getVirtualNetworkInterfaceRequirements
		ignore.add("getSupportMandatory");
		return ignore;
	}

}
