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

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Setter
@Getter
public class Sol004Entry {
	private File file;
	private String zipName;
	private HashAlgorithm hashAlgorithm;
	private String hash;
	private String signaturePath;

	public Sol004Entry(final File file, final String zipName, final HashAlgorithm hashAlgorithm) {
		super();
		this.file = file;
		this.zipName = zipName;
		this.hashAlgorithm = hashAlgorithm;
	}

}
