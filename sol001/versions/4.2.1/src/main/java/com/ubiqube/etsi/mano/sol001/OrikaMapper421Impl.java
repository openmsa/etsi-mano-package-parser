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
package com.ubiqube.etsi.mano.sol001;

import com.ubiqube.etsi.mano.service.pkg.tosca.Frequency2Converter;
import com.ubiqube.etsi.mano.service.pkg.tosca.Range2Converter;
import com.ubiqube.etsi.mano.service.pkg.tosca.Size2Converter;
import com.ubiqube.etsi.mano.service.pkg.tosca.Time2Converter;
import com.ubiqube.parser.tosca.api.ToscaMapper;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.Mciop;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.Compute;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.OsContainer;
import com.ubiqube.parser.tosca.objects.tosca.nodes.nfv.vdu.VirtualBlockStorage;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 *
 * @author olivier
 *
 */
public class OrikaMapper421Impl implements ToscaMapper {
	private final MapperFacade mapper;

	public OrikaMapper421Impl() {
		final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		final ConverterFactory conv = mapperFactory.getConverterFactory();
		conv.registerConverter(new Size2Converter());
		conv.registerConverter(new Frequency2Converter());
		conv.registerConverter(new Time2Converter());
		conv.registerConverter(new Range2Converter());
		configureMapper(mapperFactory);
		mapper = mapperFactory.getMapperFacade();
	}

	public void configureMapper(final MapperFactory mapper) {
		mapper.classMap(Compute.class, tosca.nodes.nfv.vdu.Compute.class)
				.customize(new ArtifactMapper())
				.byDefault()
				.register();
		mapper.classMap(VirtualBlockStorage.class, tosca.nodes.nfv.vdu.VirtualBlockStorage.class)
				.customize(new BlockStorageMapper())
				.byDefault()
				.register();
		mapper.classMap(OsContainer.class, tosca.nodes.nfv.vdu.OsContainer.class)
				.customize(new ArtifactStorageMapper())
				.byDefault()
				.register();
		mapper.classMap(Mciop.class, tosca.nodes.nfv.Mciop.class)
				.customize(new MciopArtifactMapper())
				.byDefault()
				.register();
	}

	@Override
	public <U> U map(final Object x, final Class<U> dest) {
		return mapper.map(x, dest);
	}
}
