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
package com.ubiqube.parser.tosca.sol006.ir;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
public abstract class IrKeyword implements IrNode {
	public static final class Qualified extends IrKeyword {
		private final String prefix;

		public Qualified(final String prefix, final String localName) {
			super(localName);
			this.prefix = prefix;
		}

		@Override
		public String prefix() {
			return prefix;
		}

		@Override
		public String asStringDeclaration() {
			return prefix + ':' + identifier();
		}

		@Override
		public String toString() {
			return "Qualified [prefix=" + prefix + ", identifier()=" + identifier() + "]";
		}

	}

	public static final class Unqualified extends IrKeyword {
		public Unqualified(final String localName) {
			super(localName);
		}

		@Override
		public String prefix() {
			return null;
		}

		@Override
		public String asStringDeclaration() {
			return identifier();
		}

		@Override
		public String toString() {
			return "Unqualified [prefix()=" + prefix() + ", identifier()=" + identifier() + "]";
		}

	}

	private final String identifier;

	IrKeyword(final String localName) {
		this.identifier = localName;
	}

	/**
	 * This keyword's 'identifier' part. This corresponds to what the RFCs refer to
	 * as {@code YANG keyword} or as {@code language extension keyword}.
	 *
	 * <p>
	 * Note the returned string is guaranteed to conform to rules of
	 * {@code identifier} ABNF and therefore is directly usable as a
	 * {@code localName}.
	 *
	 * @return This keyword's identifier part.
	 */
	public final String identifier() {
		return identifier;
	}

	/**
	 * This keyword's 'prefix' part. This corresponds to {@code prefix identifier}.
	 * For {@code YANG keyword}s this is null. For language extension references
	 * this is the non-null prefix which references the YANG module defining the
	 * language extension.
	 *
	 * <p>
	 * Note the returned string, if non-null, is guaranteed to conform to rules of
	 * {@code identifier} ABNF and therefore is directly usable as a
	 * {@code localName}.
	 *
	 * @return This keyword's prefix, or null if this keyword references a YANG
	 *         keyword.
	 */
	public abstract String prefix();

	/**
	 * Helper method to re-create the string which was used to declared this
	 * keyword.
	 *
	 * @return Declaration string.
	 */
	public abstract String asStringDeclaration();

}
