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
package com.ubiqube.etsi.mano.tosca;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
public class ZipResolver extends AbstractResolver {

	private final ZipFile zipFile;

	public ZipResolver(final String fileName) {
		try {
			zipFile = new ZipFile(fileName);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	protected boolean exist(final String path) {
		return zipFile.getEntry(path) != null;
	}

	@Override
	protected String handleContent(final String url) {
		final ZipEntry entry = zipFile.getEntry(url);
		try (InputStream is = zipFile.getInputStream(entry)) {
			return new String(is.readAllBytes(), Charset.defaultCharset());
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

}
