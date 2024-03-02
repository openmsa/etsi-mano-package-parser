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
package com.ubiqube.etsi.mano.sol001;

import java.util.HashMap;
import java.util.Map;

import com.ubiqube.parser.tosca.Artifact;
import com.ubiqube.parser.tosca.objects.tosca.artifacts.nfv.SwImage;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

/**
 *
 * @author olivier
 *
 */
public class ArtifactMapper extends CustomMapper<Compute, tosca.nodes.nfv.vdu.Compute> {

	@Override
	public void mapBtoA(final tosca.nodes.nfv.vdu.Compute b, final Compute a, final MappingContext context) {
		if (null == a.getArtifacts()) {
			a.setArtifacts(new HashMap<>());
		}
		if (null == b.getArtifacts()) {
			b.setArtifacts(new HashMap<>());
		}
		final Map<String, Artifact> tgt = a.getArtifacts();
		map(tgt, b.getArtifacts());
		super.mapBtoA(b, a, context);
	}

	private void map(final Map<String, Artifact> tgt, final Map<String, Artifact> artifacts) {
		artifacts.entrySet().forEach(x -> {
			final SwImage obj = mapperFacade.map(x.getValue(), SwImage.class);
			tgt.put(x.getKey(), obj);
		});
	}

}
