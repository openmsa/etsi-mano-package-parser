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
package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
public class ToscaRoot {
	@Getter
    private Imports imports;
	private TopologyTemplate topologyTemplate = new TopologyTemplate();
	@JsonProperty("tosca_definitions_version")
	private String version;
	@Getter
    private String description;
	@Getter
    private String namespace;
	private Map<String, CapabilityTypes> capabilityTypes = new HashMap<>();
	private Map<String, ToscaClass> artifactTypes = new HashMap<>();
	private Map<String, RelationshipType> relationshipTypes = new HashMap<>();
	private Map<String, ToscaClass> nodeTypes = new HashMap<>();
	@Getter
    @JsonProperty("interface_types")
	private Map<String, InterfaceType> interfaceTypes;
	@Getter
    @JsonProperty("data_types")
	private Map<String, DataType> dataTypes;
	@Getter
    @JsonProperty("policy_types")
	private Map<String, PolicyType> policyTypes;
	// XXX Musrt be deleted.
	@Getter
    private Map<String, PolicyDefinition> policies;
	@Getter
    @JsonProperty("group_types")
	private Map<String, GroupType> groupTypes;
	// XXX: Must be deleted.
	@Getter
    private Map<String, GroupDefinition> groups;
	@Getter
    private Map<String, String> metadata;
	@Getter
    private Map<String, RepositoryDefinition> repositories;

    @JsonProperty("topology_template")
	public TopologyTemplate getTopologyTemplate() {
		return topologyTemplate;
	}

    @JsonProperty("tosca_definitions_version")
	public String getVersion() {
		return version;
	}

    @JsonProperty("capability_types")
	public Map<String, CapabilityTypes> getCapabilityTypes() {
		return capabilityTypes;
	}

    @JsonProperty("artifact_types")
	public Map<String, ToscaClass> getArtifactTypes() {
		return artifactTypes;
	}

    @JsonProperty("relationship_types")
	public Map<String, RelationshipType> getRelationshipTypes() {
		return relationshipTypes;
	}

    @JsonProperty("node_types")
	public Map<String, ToscaClass> getNodeTypes() {
		return nodeTypes;
	}

    @Override
	public String toString() {
		return "ToscaRoot [imports=" + imports + ",\n topologyTemplate=" + topologyTemplate + ",\n version=" + version + ",\n description=" + description + ",\n capabilityTypes=" + capabilityTypes + ",\n artifactTypes=" + artifactTypes + ",\n relationshipTypes=" + relationshipTypes + ",\n nodeTypes=" + nodeTypes + "]";
	}

}
