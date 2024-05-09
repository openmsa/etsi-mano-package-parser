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
package tosca.nodes;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ubiqube.parser.tosca.Artifact;
import com.ubiqube.parser.tosca.AttributeAssignement;
import com.ubiqube.parser.tosca.InterfaceDefinition;
import com.ubiqube.parser.tosca.RequirementDefinition;
import com.ubiqube.parser.tosca.api.ToscaInernalBase;

import jakarta.validation.Valid;

public class Root extends ToscaInernalBase {
	@Valid
	private Map<String, Artifact> artifacts;

	@Valid
	@JsonIgnore
	private Map<String, AttributeAssignement> overloadedAttributes;

	@Valid
	@JsonIgnore
	private RequirementDefinition overloadedRequirements;

	@Valid
	@JsonIgnore
	private Map<String, InterfaceDefinition> overloadedInterfaces;

	public Map<String, Artifact> getArtifacts() {
		return this.artifacts;
	}

	public void setArtifacts(final Map<String, Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public Map<String, AttributeAssignement> getOverloadedAttributes() {
		return this.overloadedAttributes;
	}

	public void setOverloadedAttributes(final Map<String, AttributeAssignement> overloadedAttributes) {
		this.overloadedAttributes = overloadedAttributes;
	}

	public RequirementDefinition getOverloadedRequirements() {
		return this.overloadedRequirements;
	}

	public void setOverloadedRequirements(final RequirementDefinition overloadedRequirements) {
		this.overloadedRequirements = overloadedRequirements;
	}

	public Map<String, InterfaceDefinition> getOverloadedInterfaces() {
		return this.overloadedInterfaces;
	}

	public void setOverloadedInterfaces(final Map<String, InterfaceDefinition> overloadedInterfaces) {
		this.overloadedInterfaces = overloadedInterfaces;
	}

}
