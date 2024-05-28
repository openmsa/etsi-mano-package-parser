/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.parser.tosca.generator;

/**
 *
 * @author Olivier Vignaud
 *
 */
public class YangException extends RuntimeException {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public YangException(final Throwable e) {
		super(e);
	}

	public YangException(final String string) {
		super(string);
	}

	public YangException(final String string, final Throwable e) {
		super(string, e);
	}

}
