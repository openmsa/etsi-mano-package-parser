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
package com.ubiqube.parser.tosca.objects.tosca.groups;

import java.util.List;

import jakarta.validation.Valid;

/**
 * The TOSCA Group Type all other TOSCA Group Types derive from.
 */
public class Root extends com.ubiqube.parser.tosca.objects.tosca.entity.Root {
	@Valid
	private List<String> members;

	public List<String> getMembers() {
		return this.members;
	}

	public void setMembers(final List<String> members) {
		this.members = members;
	}
}
