package com.ubiqube.parser.tosca.schema.generator.sol001.json;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Field {

	private String description;
	private String type;
	private Integer exclusiveMinimum;
	private Map<String, Object> properties;
	private Boolean required;
}
