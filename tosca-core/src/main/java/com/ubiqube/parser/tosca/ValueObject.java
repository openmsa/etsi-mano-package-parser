/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.parser.tosca;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.constraints.Constraint;
import lombok.Getter;
import lombok.Setter;

@Setter
public class ValueObject {
	@Getter
    private String type;
	@Getter
    private Boolean required = Boolean.TRUE;
	private Object def;
	@Getter
    private String description;
	private EntrySchema entrySchema;
	@Getter
    private List<Constraint> constraints = new ArrayList<>();
	@Getter
    private String status;

	public ValueObject() {
		// Nothing.
	}

	public ValueObject(final String string) {
		this.type = string;
	}

	public static ValueObject listOf(final String type) {
		final ValueObject vo = new ValueObject();
		vo.setType("list");
		final EntrySchema entrySchema = new EntrySchema();
		entrySchema.setType(type);
		vo.setEntrySchema(entrySchema);
		return vo;
	}

	public static ValueObject mapOf(final String type) {
		final ValueObject vo = new ValueObject();
		vo.setType("map");
		final EntrySchema entrySchema = new EntrySchema();
		entrySchema.setType(type);
		vo.setEntrySchema(entrySchema);
		return vo;
	}

    @JsonProperty("default")
	public Object getDef() {
		return def;
	}

    @JsonProperty("entry_schema")
	public EntrySchema getEntrySchema() {
		return entrySchema;
	}

    @Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("ValueObject [type=" + type + ", ");
		if (null != required) {
			sb.append("required=" + required + ", ");
		}
		if (null != def) {
			sb.append("def=" + def + ", ");
		}
		if (null != description) {
			sb.append("description=" + description.replace("\n", " ") + ", ");
		}
		if (null != entrySchema) {
			sb.append("entrySchema=" + entrySchema + ", ");
		}
		if (null != constraints) {
			sb.append("constraints=" + constraints + ", ");
		}
		if (null != status) {
			sb.append("status=" + status);
		}
		sb.append("]\n");
		return sb.toString();
	}

}
