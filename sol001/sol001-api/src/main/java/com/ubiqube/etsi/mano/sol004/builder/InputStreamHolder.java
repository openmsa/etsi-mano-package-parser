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
package com.ubiqube.etsi.mano.sol004.builder;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.sol004.crypto.SignatureInputStream;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Setter
@Getter
public class InputStreamHolder implements AutoCloseable {
	private InputStream root;

	private InputStream last;

	private DigestInputStream hash;

	private SignatureInputStream signature;

    @Override
	public void close() {
		try {
			if (null != hash) {
				hash.close();
			}
			if (null != signature) {
				signature.close();
			}
			root.close();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

}
