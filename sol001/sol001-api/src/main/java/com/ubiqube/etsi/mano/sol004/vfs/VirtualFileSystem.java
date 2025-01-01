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
package com.ubiqube.etsi.mano.sol004.vfs;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.ubiqube.etsi.mano.tosca.IResolver;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
public interface VirtualFileSystem {

	byte[] getFileContent(String filename);

	boolean exist(String filename);

	List<String> getFileMatching(String filenameWildcard);

	InputStream getInputStream(String fileName);

	IResolver getResolver();

	Map<String, String> getMetaInfo(String path);

}
