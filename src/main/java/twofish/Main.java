package twofish;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.params.KeyParameter;
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
		byte[] key = "weak-key".getBytes();
		byte[] input = "going to encrypt".getBytes();

		String encrypt = Hex.toHexString(encrypt(key, input));
		
		System.out.println(encrypt);
		
		byte[] decrypt = decrypt(key, Hex.decode(encrypt));
		
		System.out.println(new String(decrypt));
	}
	
	public static byte[] encrypt(byte[] key, byte[] input) throws Exception {
		BufferedBlockCipher cipher = new BufferedBlockCipher(new TwofishEngine());

		cipher.init(true, new KeyParameter(key));

		byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
		
		int outputLen = cipher.processBytes(input, 0, input.length, cipherText, 0);
		cipher.doFinal(cipherText, outputLen);
		
		return cipherText;
	}
	
	public static byte[] decrypt(byte[] key, byte[] input) throws Exception {
		BufferedBlockCipher cipher = new BufferedBlockCipher(new TwofishEngine());

		cipher.init(false, new KeyParameter(key));

		byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
		
		int outputLen = cipher.processBytes(input, 0, input.length, cipherText, 0);
		cipher.doFinal(cipherText, outputLen);
		
		return cipherText;
	}
}
