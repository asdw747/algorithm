package mars.utils.cryptor;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Mr.Zheng
 * @date 2014年8月22日 下午1:44:23
 */
public final class RSAUtils {
	private static final String RSA = "RSA";
	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	 * 随机生成RSA密钥对(默认密钥长度为1024)
	 * 
	 * @return
	 */
	public static KeyPair generateRSAKeyPair() {
		return generateRSAKeyPair(1024);
	}

	/**
	 * 随机生成RSA密钥对
	 * 
	 * @param keyLength
	 *            密钥长度，范围：512～2048<br>
	 *            一般1024
	 * @return
	 */
	public static KeyPair generateRSAKeyPair(int keyLength) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
			kpg.initialize(keyLength);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static int getKeySize(PublicKey publicKey) {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		return rsaPublicKey.getModulus().bitLength();
	}

	public static int getKeySize(PrivateKey privateKey) {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
		return rsaPrivateKey.getModulus().bitLength();
	}

	/**
	 * 用公钥加密 <br>
	 * 每次加密的字节数，不能超过密钥的长度值减去11
	 * 
	 * @param data
	 *            需加密数据的byte数据
	 * @param publicKey 公钥
	 * @return 加密后的byte型数据
	 */
	public static byte[] encryptData(byte[] data, PublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA);
			// 编码前设定编码方式及密钥
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			int keyBit = getKeySize(publicKey);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			int step = keyBit / 8 - 11;

			for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
				byte[] cache;
				if (inputLen - offSet > step) {
					cache = cipher.doFinal(data, offSet, step);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}

				out.write(cache, 0, cache.length);
				++i;
			}

			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 用私钥解密
	 * 
	 * @param encryptedData
	 *            经过encryptedData()加密返回的byte数据
	 * @param privateKey
	 *            私钥
	 * @return
	 */
	public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			int keyBit = getKeySize(privateKey);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			int step = keyBit / 8;

			for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
				byte[] cache;
				if (inputLen - offSet > step) {
					cache = cipher.doFinal(encryptedData, offSet, step);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}

				out.write(cache, 0, cache.length);
				++i;
			}

			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
	 * 
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 通过私钥byte[]将公钥还原，适用于RSA算法
	 * 
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 使用N、e值还原公钥
	 * 
	 * @param modulus
	 * @param publicExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(String modulus, String publicExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 使用N、d值还原私钥
	 * 
	 * @param modulus
	 * @param privateExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(String modulus, String privateExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从字符串中加载私钥<br>
	 * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
	 *
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(privateKeyStr);
			// X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * 从文件中输入流中加载公钥
	 * 
	 * @param in
	 *            公钥输入流
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(InputStream in) throws Exception {
		try {
			return loadPublicKey(readKey(in));
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 * 
	 * @param in 私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(InputStream in) throws Exception {
		try {
			return loadPrivateKey(readKey(in));
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}

	/**
	 * 读取密钥信息
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static String readKey(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String readLine = null;
		StringBuilder sb = new StringBuilder();
		while ((readLine = br.readLine()) != null) {
			if (readLine.charAt(0) == '-') {
				continue;
			} else {
				sb.append(readLine);
				sb.append('\r');
			}
		}

		return sb.toString();
	}

	/**
	 * 使用私钥对数据进行加密签名
	 * 
	 * @param encryptByte
	 *            数据
	 * @param privateKey
	 *            私钥
	 * @return 加密后的签名
	 */
	public static String rsaSign(byte[] encryptByte, PrivateKey privateKey) {
		try {
			Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(privateKey);
			signature.update(encryptByte);
			byte[] signed = signature.sign();
			return Base64Utils.encode(signed);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 使用公钥判断签名是否与数据匹配
	 * 
	 * @param encryptByte
	 *            数据
	 * @param bs
	 *            签名
	 * @param publicKey
	 *            公钥
	 * @return 是否篡改了数据
	 */
	public static boolean doCheck(byte[] encryptByte, byte[] bs, PublicKey publicKey) {
		try {
			Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
			signature.initVerify(publicKey);
			signature.update(encryptByte);
			boolean bverify = signature.verify(bs);
			return bverify;

		} catch (Exception e) {
		}

		return false;
	}

	/**
	 * 打印公钥信息
	 * 
	 * @param publicKey
	 */
	public static void printPublicKeyInfo(PublicKey publicKey) {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		System.out.println("----------RSAPublicKey----------");
		System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
		System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
		System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
	}

	public static void printPrivateKeyInfo(PrivateKey privateKey) {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
		System.out.println("----------RSAPrivateKey ----------");
		System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
		System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());
		System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());

	}


	public static void main(String[] args){
//		String pK = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA56GnjETvfjh3ef2pD7Me3JpX/NPkkqwCjboHo4gsSwHNwidtklQSY8D3h6KwDEVvbbQIEwn3IhMWPinlMB0mW1nZ7Vcuz0YmFn4wdLkZ6L4ihpoX9QBGW84bgtywzENLoyfNuJdEwn+J/W53ytzZL/7s4zyH4YcL5OkE+ceu4mQTA4sfelHbP1Gaq28DdDbc0sTr+Q0Iga0aSAFL1GvrXMMaiM0DJvJx1M/jqFypx8XEQJFO0U2bBiaHqzKxYCgOL7o1hMJt7EOVmSIgdrag1Mu/vDCkq5J0/Q/aI9jVoNmsOiguV+pCRXX/gG5Qiyg7lfYNBFt6SYQF02zauFB3SQIDAQAB";
//		String p = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDnoaeMRO9+OHd5/akPsx7cmlf80+SSrAKNugejiCxLAc3CJ22SVBJjwPeHorAMRW9ttAgTCfciExY+KeUwHSZbWdntVy7PRiYWfjB0uRnoviKGmhf1AEZbzhuC3LDMQ0ujJ824l0TCf4n9bnfK3Nkv/uzjPIfhhwvk6QT5x67iZBMDix96Uds/UZqrbwN0NtzSxOv5DQiBrRpIAUvUa+tcwxqIzQMm8nHUz+OoXKnHxcRAkU7RTZsGJoerMrFgKA4vujWEwm3sQ5WZIiB2tqDUy7+8MKSrknT9D9oj2NWg2aw6KC5X6kJFdf+AblCLKDuV9g0EW3pJhAXTbNq4UHdJAgMBAAECggEBANq00DapgFh1iJou4EpqnmE9eobGq7Oxplebp0rJcMkB0v3XrKXVq+d0dEQlYIS1qxiyPE19w1LxIrdfZ3+ehnEKzbJD93bOjXf/flE31TblEER3itdSgxx7544QQv8bK3fKRXxdmBbtxaT9gS6SiJ/A3HtS1BomIRzqyD/wQziN6PGsaX2U6N/qhbYBWZ3Q1kH/m/JHKiNYRYpwmehIFa5cGHO5161y8Dn93itGeK+3W++9WKkVUmO2tSvor/df7OnATmgo2wzymm015EiBmCnwfwjZKKvUWp5p8S5EQVuD60dPog/jn3T4/PxjOZPQE/aoDinNLIzzhvJIfjWSQOECgYEA/tqH2dt0/DBDxJpkmDpFi/mgj3eLcJ5k11RI7ZbURi1PK/RtY/l6xNSWPbDsW3G+G5vnn/XYUKmnMiJUhKkwarggtT7sgSpdauU3A55xmVMj6aynXrJ5G3sIZ2RCpHiZJVAguiy1ZR9iBuJ9wo7YMMwDIne6wJcDYUz6qga5TJsCgYEA6KxiC/ViFyMYqyNyNeDnRVGnkEs3JDSwcfAFZftCVNV9XouPdwjinp3e7j6CqHb+YG5Hv9eYIGQSaRVv0MXcct/2u5RX2QIMqRC4GuYUX/3MEpaZ+w4xlSLTQcxOaFzi74StGFyQDAK6s8mXxAC1Jv3+JiXqiXeXOzjFdWBnP+sCgYEA+rdPuHIeR07QDGT7G4sQCiFc5GbtpbAdcny3dgzQ7ez7GznON/Eh5utglnYsP9XI+W5J0uLPmKMeGcMONKghOovDmaq6jmN4HYY7tfK0vOq+xM6WpSGc5P0Hindm3FjMCJooaB89oKrcC/vbToCqjG7XkpwI/BhUOe+6FXSh7IsCgYBua5DEWBnLHS7R2SqUgz1+WjgJdIiguBicmeoV8d4S4JZ0zgqx1RenawinTCc5x+8E2JFYa/qYNKXAFRpRm/T4120CrG3cYOqdOYAF0/cLbyjwEWTWDnBddH+JlGoJgTt5rB9xlkwORyVPuXY64JMYBCYe+JOL1aOByXl8MNoPLQKBgQDC1x62WaBgh/Hm2oAvnD20zpdQOyMl3fbfwxyyqwGxxn96YYOLqq4fPBEfKBZ+hRR0eTUxNXYK3M/be0CuY8FqzbA6xTIc8HRu0OSxdmWD/rr33RoOaCpb2TIXSfq9S7MNOExsw23i17jGqOOsCbwUFf3kPs2Y45zD77CqUXobNA==";

		String pK = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvqveIIosaPq27t1EstO0\n" +
				"mS9h7UWU73Q4F8ryQs+oipHhp1OPqg+QelSGSmMljq/lj5Tkb/Bzdo6t+T1T9Wr0\n" +
				"/r88O4nMbHnYLlYC1SATGcfGrnQXoHBWdUmVoLeJQOE85j5UAW/FNrUTrCjCrk9V\n" +
				"/Aw+E5xY8CacHLFMDjLrB1suu1Uv3nTwT5voUfgf631fgj6oq0/suG6j5uyxUaN0\n" +
				"+w611164W8ev3Pq9tNK2su+W6QkQtSOMrhPGRLOHZo8u4sNmyyadeE/aFD6NCL3L\n" +
				"QQTaZV6SLEyQ2YSZQwLW9t/Sf+cMD15IJI4S7WVC1lw0dF9B3Qb0ggSwGmE7Bt+e\n" +
				"KwIDAQAB";
		String p = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+q94giixo+rbu\n" +
				"3USy07SZL2HtRZTvdDgXyvJCz6iKkeGnU4+qD5B6VIZKYyWOr+WPlORv8HN2jq35\n" +
				"PVP1avT+vzw7icxsedguVgLVIBMZx8audBegcFZ1SZWgt4lA4TzmPlQBb8U2tROs\n" +
				"KMKuT1X8DD4TnFjwJpwcsUwOMusHWy67VS/edPBPm+hR+B/rfV+CPqirT+y4bqPm\n" +
				"7LFRo3T7DrXXXrhbx6/c+r200ray75bpCRC1I4yuE8ZEs4dmjy7iw2bLJp14T9oU\n" +
				"Po0IvctBBNplXpIsTJDZhJlDAtb239J/5wwPXkgkjhLtZULWXDR0X0HdBvSCBLAa\n" +
				"YTsG354rAgMBAAECggEBAL5KTI9xFs1WmkddsPg/7NeMwFDKxs8L0Ad+1bT0BiL1\n" +
				"YJ1b+d8hVXGg9b07ofn7dkGLuUg2UyWZU+uQ1z/xFL1BId3RsqD4IVZtJLqLHkT2\n" +
				"akUlO4c1VjQL0jbptSN5JwwqXDBq2gm1QgLpUxIfv5KirTlpKiHShq7kCyGIk6Ns\n" +
				"FPmqWDAMYxq6oSL4rS2rNaO/YoMaUK5F/qUlsCfBZu1yBycM+Lx3S5+mfTz17Omx\n" +
				"w2jBeKeudoAe97c6WPN48s9z9ODgFp+m9aHeS/Yce2BroyRjymCRGO4F/GMX2QPR\n" +
				"KRp+FiL3vmjdS3liTpuTIug8wDyxqXpCcAngIhs/5WECgYEA7KE3XUJpSuwJGHcm\n" +
				"cikcXlrKVAeHHHKf0k1JQvIs62Rh060C8E/MTxjQHGhIgNxF/Em498LOAil/1Ndy\n" +
				"/PRI+ruZKFNmFpdFk/+dVIzRCADUK151c8VhIzRVkGlu72nqSysxUB2FKYM2ULjo\n" +
				"FcJzMsk4WQEB1eHbdRwzJJhnd+kCgYEAzkeNk4GbpVxxdQ0bZwwgEN56P2KA1fAY\n" +
				"nihflJ1wc9DFbghOXXJpXIcRfaT79bp8nqzaF5yA1lJ9CfWDrFus0/vthUzjy0oK\n" +
				"AqmQ7GDelI+T++PqaksZ07XkmqSIu8UP18XoBtrVjXEBghE9fr+AZhDC2ZLQC4CH\n" +
				"QHCiCVRu7PMCgYA12j7iX5Hc9jjfs4YxZv/IzXrMguYR6FhNIT+yF7F3ZOIKrY5E\n" +
				"qLSDr871GBqgEQFweEq7QD9oKI+qHdpSjTnqrjUeA1TxgT3Zf8wLiPytiJrAv/+U\n" +
				"+G+x9mAevI/9tqoWr17Kr901ZGJBHsPa5+UVwsurHdqQFZW/YkqVYoXxuQKBgG7P\n" +
				"ByF23Sp+N0BekxrO66bELD1CZ90ExeSn4XhO/qpoOrY55gbSwACBWgb5ipPc4rbG\n" +
				"Ob23R4pZ82tXJK2A7Z1OVrBltnRsyGXXus8P1mFNo3wLWdBRmYUdz7i2q/DaEgGi\n" +
				"AVs1UMRTMTEym6srqgip9lG0SOf02jLmjlSes5u7AoGAY7vPLxzzmd2wAzzU/pwN\n" +
				"QZKbA7v/acTOz1EoQhlZwae8xPz3wSZ1YiWYNR2zj5dJ+lVBwnPicE/vuKZJI9hF\n" +
				"1bKn/uHqROTxklpMYvxW2fg2ZWoNLMy1GP5BlVwZvoT1Q/RbXoYIaqgBUk1W0qhU\n" +
				"gZljSKBOs+Uaq42a7bZh9zU=";
		try {
			PublicKey publicKey =loadPublicKey(pK);
			System.out.println(publicKey);

			PrivateKey privateKey = loadPrivateKey(p);
			System.out.println(privateKey);

//			DecimalFormat df = new DecimalFormat("0000");
//			String str2 = df.format(99999);
//			System.out.println(str2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
