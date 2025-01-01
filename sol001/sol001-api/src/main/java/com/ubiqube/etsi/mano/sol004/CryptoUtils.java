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
package com.ubiqube.etsi.mano.sol004;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
public final class CryptoUtils {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private CryptoUtils() {
		// Nothing.
	}

	public static boolean verify(final InputStream stream, final byte[] cert, final byte[] sig) {
		try {
			return verifyNoEx(stream, cert, sig);
		} catch (InvalidKeyException | SignatureException | InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static boolean verifyNoEx(final InputStream stream, final byte[] cert, final byte[] sig) throws InvalidKeyException, SignatureException, IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		final X509EncodedKeySpec publicKey = new X509EncodedKeySpec(cert);
		final String algo = Optional.ofNullable(publicKey.getAlgorithm()).orElse("RSA");
		final KeyFactory keyFactory = KeyFactory.getInstance(algo);
		final PublicKey pubkey = keyFactory.generatePublic(publicKey);
		final Signature sigs = Signature.getInstance(algo);
		sigs.initVerify(pubkey);
		readAll(stream, sigs);
		return sigs.verify(sig);
	}

	private static void readAll(final InputStream stream, final Signature sigs) throws SignatureException, IOException {
		final byte[] buffer = new byte[1024];
		int len;
		while ((len = stream.read(buffer)) > -1) {
			sigs.update(buffer, 0, len);
		}
	}

	private static void readAll(final InputStream stream) throws IOException {
		final byte[] buffer = new byte[1024];
		while (stream.read(buffer) > -1) {
			// Nothing.
		}
	}

	public static boolean checkHash(final InputStream stream, final String algorithm, final String hash) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (final NoSuchAlgorithmException e) {
			throw new Sol004Exception("Unable to find algorithm: " + algorithm, e);
		}
		try (DigestInputStream dis = new DigestInputStream(stream, digest)) {
			readAll(dis);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		final byte[] bytes = digest.digest();
		final String newDigest = bytesToHex(bytes);
		return hash.equals(newDigest);
	}

	public static String bytesToHex(final byte[] hash) {
		final StringBuilder hexString = new StringBuilder();
		for (final byte element : hash) {
			final String hex = Integer.toHexString(0xff & element);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static String signData(final byte[] data, final X509Certificate signingCertificate, final PrivateKey signingKey) {
		try {
			return signDataEx(data, signingCertificate, signingKey);
		} catch (CertificateEncodingException | OperatorCreationException | CMSException | IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static String signDataEx(final byte[] data, final X509Certificate signingCertificate, final PrivateKey signingKey) throws CMSException, CertificateEncodingException, OperatorCreationException, IOException {
		final List<X509Certificate> certList = new ArrayList<>();
		final CMSTypedData cmsData = new CMSProcessableByteArray(data);
		certList.add(signingCertificate);
		final Store<?> certs = new JcaCertStore(certList);
		final CMSSignedDataGenerator cmsGenerator = new CMSSignedDataGenerator();
		final ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256withRSA").build(signingKey);
		cmsGenerator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
				new JcaDigestCalculatorProviderBuilder().setProvider("BC")
						.build()).build(contentSigner, signingCertificate));
		cmsGenerator.addCertificates(certs);
		final CMSSignedData cms = cmsGenerator.generate(cmsData, true);
		final Writer out = new StringWriter();
		try (final JcaPEMWriter writer = new JcaPEMWriter(out)) {
			writer.writeObject(cms.toASN1Structure());
		}
		return out.toString();
	}

}
