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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Getter
@Setter
public class RepositoryDefinition {
	private String description;
	private String url;
	private Credential credential;

	@Setter
	@Getter
	public class Credential {
		private String protocol;
		/**
		 * password, bearer(oauth2), basic_auth(http), identifier(ssh),
		 * X-Auth-Token(xauth) for example.
		 */
		@JsonProperty("token_type")
		private String tokenType = "password";
		private String token;
		private Map<String, String> keys;
		private String user;
	}
}
