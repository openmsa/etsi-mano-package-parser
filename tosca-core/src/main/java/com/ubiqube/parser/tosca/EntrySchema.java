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

import java.util.List;

import com.ubiqube.parser.tosca.constraints.Constraint;

public class EntrySchema {
	private String type;
	private String description;
	private List<Constraint> constraints;

	public EntrySchema() {
		//
	}

	public EntrySchema(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(final List<Constraint> constraints) {
		this.constraints = constraints;
	}

}
