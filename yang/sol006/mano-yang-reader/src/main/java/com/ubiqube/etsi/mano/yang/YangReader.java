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
package com.ubiqube.etsi.mano.yang;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import urn.etsi.nfv.yang.etsi.nfv.descriptors.v20220308.Nfv;

public class YangReader {

	public Nfv read(final File file) {
		Unmarshaller marshaller;
		try {
			marshaller = JAXBContext.newInstance(Nfv.class).createUnmarshaller();
			return (Nfv) marshaller.unmarshal(file);
		} catch (final JAXBException e) {
			throw new YangReaderException(e);
		}
	}
}
