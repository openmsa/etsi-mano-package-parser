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

import java.io.IOException;

import com.ubiqube.parser.tosca.ZipUtil.Entry;

public class StaticTestTools {

	public static void createVnfPackage() {
		try {
			ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar", Entry.of("ubi-tosca/Definitions/tosca_ubi.yaml", "Definitions/tosca_ubi.yaml"),
					Entry.of("etsi_nfv_sol001_vnfd_types.yaml", "Definitions/etsi_nfv_sol001_vnfd_types.yaml"),
					Entry.of("etsi_nfv_sol001_common_types.yaml", "Definitions/etsi_nfv_sol001_common_types.yaml"),
					Entry.of("ubi-tosca/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void createNsdPackage() {
		try {
			ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar",
					Entry.of("ubi-nsd/Definitions/nsd_ubi.yaml", "Definitions/nsd_ubi.yaml"),
					Entry.of("etsi_nfv_sol001_nsd_types.yaml", "Definitions/etsi_nfv_sol001_nsd_types.yaml"),
					Entry.of("etsi_nfv_sol001_vnfd_types.yaml", "Definitions/etsi_nfv_sol001_vnfd_types.yaml"),
					Entry.of("etsi_nfv_sol001_pnfd_types.yaml", "Definitions/etsi_nfv_sol001_pnfd_types.yaml"),
					Entry.of("etsi_nfv_sol001_common_types.yaml", "Definitions/etsi_nfv_sol001_common_types.yaml"),
					Entry.of("ubi-nsd/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
