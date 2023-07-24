/**
 *     Copyright (C) 2019-2023 Ubiqube.
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
package com.ubiqube.etsi.mano.service.pkg.tosca;

import com.ubiqube.parser.tosca.scalar.Size;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class SizeConverter extends BidirectionalConverter<Size, Long> {

	@Override
	public Long convertTo(final Size source, final Type<Long> destinationType, final MappingContext mappingContext) {
		return Long.valueOf(source.getValue().longValue());
	}

	@Override
	public Size convertFrom(final Long source, final Type<Size> destinationType, final MappingContext mappingContext) {
		return new Size(source);
	}
}
