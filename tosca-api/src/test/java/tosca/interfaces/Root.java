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
package tosca.interfaces;

import java.util.Map;

import com.ubiqube.parser.tosca.NotificationDefnition;
import com.ubiqube.parser.tosca.ToscaProperties;
import com.ubiqube.parser.tosca.api.ToscaInernalBase;

import jakarta.validation.Valid;

public class Root extends ToscaInernalBase {
	@Valid
	private String type;

	@Valid
	private ToscaProperties inputs;

	@Valid
	private Map<String, NotificationDefnition> notifications;

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public ToscaProperties getInputs() {
		return this.inputs;
	}

	public void setInputs(final ToscaProperties inputs) {
		this.inputs = inputs;
	}

	public Map<String, NotificationDefnition> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(final Map<String, NotificationDefnition> notifications) {
		this.notifications = notifications;
	}

}
