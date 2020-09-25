package crypto;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class Main {

	/**
	 * java.crypto パッケージを使わないシンプルなサンプル。
	 * 
	 * https://www.bouncycastle.org/specifications.html
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		String key = "weak-key";
		String input = "going to encrypt";

		String encrypt = Hex.toHexString(encrypt(input, key));

		System.out.println(encrypt);

		byte[] decrypt = decrypt(Hex.decode(encrypt), key);

		System.out.println(new String(decrypt));
	}

	public static byte[] encrypt(String toEncrypt, String key) throws Exception {
		// create a binary key from the argument key (seed)
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "twofish");
		Cipher cipher = Cipher.getInstance("twofish");
		cipher.init(Cipher.ENCRYPT_MODE, sksSpec);

		// enctypt!
		byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());

		return encrypted;
	}

	public static byte[] decrypt(byte[] toDecrypt, String key) throws Exception {
		// create a binary key from the argument key (seed)
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "twofish");
		Cipher cipher = Cipher.getInstance("twofish");

		cipher.init(Cipher.DECRYPT_MODE, sksSpec);

		byte[] decrypted = cipher.doFinal(toDecrypt);

		return decrypted;
	}
}
