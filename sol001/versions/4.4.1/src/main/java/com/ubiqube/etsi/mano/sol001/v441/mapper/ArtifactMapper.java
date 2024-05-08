package com.ubiqube.etsi.mano.sol001.v441.mapper;

import org.mapstruct.Mapper;

import com.ubiqube.parser.tosca.objects.tosca.artifacts.implementation.nfv.Mistral;
import com.ubiqube.parser.tosca.objects.tosca.artifacts.nfv.HelmChart;
import com.ubiqube.parser.tosca.objects.tosca.artifacts.nfv.SwImage;

@Mapper
public interface ArtifactMapper {
	SwImage mapToSwImage(tosca.artifacts.nfv.SwImage o);

	Mistral mapToMistral(tosca.artifacts.implementation.nfv.Mistral o);

	HelmChart mapToHelmChart(tosca.artifacts.nfv.HelmChart o);
}
