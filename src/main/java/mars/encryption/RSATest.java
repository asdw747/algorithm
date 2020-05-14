package mars.encryption;

import mars.utils.cryptor.RSAUtils;

import java.security.PrivateKey;

public class RSATest {

    public static void main(String [] args) {

//        KeyPair keyPair = RSAUtils.generateRSAKeyPair(2048);
//
//        if (keyPair != null) {
//            RSAUtils.printPublicKeyInfo(keyPair.getPublic());
//            RSAUtils.printPrivateKeyInfo(keyPair.getPrivate());
//        }
        try {
            PrivateKey privateKey = RSAUtils.loadPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDF7+Jfz3FrCxTb\n" +
                    "3fPvXdcFmyaP/qOtfssfs2COoDFF6TvM/FQ4BiM+g6Cctlw5N/Tfm+OB18kHRGSu\n" +
                    "tJGLxeHlYoKexrcGDi5ezbkMtXhjtycd9Q2Sz9fmhdr3Q43yJJw+ZPimIL887MDP\n" +
                    "jrtjVOeVOZ3a/r+PvqWI6SNAme5Q0tAaPHeuADBxSCC7I40gaOr1qGEchUvKzM9k\n" +
                    "9BD18TB7luRG2ccgF9iBcALDsAE8oiFukspM7y+iY6MW5uY8L6D/z4JO4t7Co4A8\n" +
                    "meQXaAvSPEl83aQCe35Zymtph0r8xavlcu6H0POSM6U8bASG1rsr6v1el+NCbI+C\n" +
                    "RvY1kec5AgMBAAECggEAS8WI4ySGRnP3iDNvLjK+HWdHW9ge0Eu5dmk0jWTFd/3Q\n" +
                    "5qexlcDDvSqmUVd99ml1VCRY81unmSCVaku1a8xUrXsIhb+MUMUc+y3FqRfO/l0e\n" +
                    "nFky2QC3pYeYSw+RRyC8ryX4CaB/lNMM/GenVF8sR/PGeg8LRfu1YrPcyt4iXM2p\n" +
                    "cauthFghQXEfvmOalXlrp5XFv3EoZoo7+OeKGo8Ft451JrWdED+rpzkW70KMu/fv\n" +
                    "cwbrbfEsCrGyeT4rkNpcMov87Y0IQhUhYSmYWS8Djn5KvTn8k8vuyH37xwBHI3kA\n" +
                    "8swPhV2MuohCxT/rKYldLtjKPI+vRGpDs12Zr0QgAQKBgQD7pLcdAsJWLFj8H6Ak\n" +
                    "8ZRl6RWtOXshxVNnXI19mdVr0ZC8dBSQhXMYXimf3Q5BSjp8lInVbhfe9LNABsMv\n" +
                    "vaSIP2dqSsWhDQQIZlhBvZW/yU8FtNCXEirIIGQys+FW5EZQxgqc09EBkwpm7z+A\n" +
                    "2bqFHRRV4oOdb0EZvkqQcaV/FQKBgQDJXSRiBAAUyKo7ogJ/w4YkDyu1oSxerXKG\n" +
                    "CqaLgGxrIKziDmpmN7z0o42Wu+w9WYp3WiIdeVMhs0NaycP9hlHSFtqMU6+heT3M\n" +
                    "1X3IMzc6bXq4uaKqL5NMOEoYqKCVsKjHvKmaA93NLfejY1m8Ecx4GLyaNKGKMPM2\n" +
                    "xPPGSX8wlQKBgHqIckHGHisn3QynuatDlycooE2BN9vx4fLqtlr6lVmy6e9Z9smI\n" +
                    "scqjz1DwqwhPxLr0+UjcGvON49rBcDnG9l8BwLGZRzJszBHrA/6++jQxKjF1Ijzt\n" +
                    "s7dPTnsB7DALZ9HdHT3QRm2l0+LbjOiJHLHEvyoy6jO3ANQwPM6pCBUhAoGBALRp\n" +
                    "jVV8PA5jE5LGPvL13dOk/AaJ11Uklf1ewYUvlPQOnJcVT2MyAUw21X799FWrShJR\n" +
                    "YUuDDmadVHWdXMpjdB+Fdl5QBIXqOOKzTTT4Hj1tJY0oVYOhiJm7vTeGrgov86ix\n" +
                    "ckuZtzpEOyHau+VzFP1tPF31Vd6YqPCjWb6IzzpZAoGBAJ93YT4boj1bufRk4JXX\n" +
                    "CkeRinNhbtGFYqBxuhkmaK6xd57L4faCqmIIR3GcZDgNTJiADUgB8Q1fF6Ass8yq\n" +
                    "BVjaktH6fT7qVVRdmjxseP3SG9VlYr7Dmg4WUEbIUDf72y9rR2tHuCQcIkRx2oca\n" +
                    "q3Jevh/8B+350KH2+di0e0A5");

            RSAUtils.printPrivateKeyInfo(privateKey);

            PrivateKey privateKey1 = RSAUtils.loadPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDF7+Jfz3FrCxTb" +
                    "3fPvXdcFmyaP/qOtfssfs2COoDFF6TvM/FQ4BiM+g6Cctlw5N/Tfm+OB18kHRGSu" +
                    "tJGLxeHlYoKexrcGDi5ezbkMtXhjtycd9Q2Sz9fmhdr3Q43yJJw+ZPimIL887MDP" +
                    "jrtjVOeVOZ3a/r+PvqWI6SNAme5Q0tAaPHeuADBxSCC7I40gaOr1qGEchUvKzM9k" +
                    "9BD18TB7luRG2ccgF9iBcALDsAE8oiFukspM7y+iY6MW5uY8L6D/z4JO4t7Co4A8" +
                    "meQXaAvSPEl83aQCe35Zymtph0r8xavlcu6H0POSM6U8bASG1rsr6v1el+NCbI+C" +
                    "RvY1kec5AgMBAAECggEAS8WI4ySGRnP3iDNvLjK+HWdHW9ge0Eu5dmk0jWTFd/3Q" +
                    "5qexlcDDvSqmUVd99ml1VCRY81unmSCVaku1a8xUrXsIhb+MUMUc+y3FqRfO/l0e" +
                    "nFky2QC3pYeYSw+RRyC8ryX4CaB/lNMM/GenVF8sR/PGeg8LRfu1YrPcyt4iXM2p" +
                    "cauthFghQXEfvmOalXlrp5XFv3EoZoo7+OeKGo8Ft451JrWdED+rpzkW70KMu/fv" +
                    "cwbrbfEsCrGyeT4rkNpcMov87Y0IQhUhYSmYWS8Djn5KvTn8k8vuyH37xwBHI3kA" +
                    "8swPhV2MuohCxT/rKYldLtjKPI+vRGpDs12Zr0QgAQKBgQD7pLcdAsJWLFj8H6Ak" +
                    "8ZRl6RWtOXshxVNnXI19mdVr0ZC8dBSQhXMYXimf3Q5BSjp8lInVbhfe9LNABsMv" +
                    "vaSIP2dqSsWhDQQIZlhBvZW/yU8FtNCXEirIIGQys+FW5EZQxgqc09EBkwpm7z+A" +
                    "2bqFHRRV4oOdb0EZvkqQcaV/FQKBgQDJXSRiBAAUyKo7ogJ/w4YkDyu1oSxerXKG" +
                    "CqaLgGxrIKziDmpmN7z0o42Wu+w9WYp3WiIdeVMhs0NaycP9hlHSFtqMU6+heT3M" +
                    "1X3IMzc6bXq4uaKqL5NMOEoYqKCVsKjHvKmaA93NLfejY1m8Ecx4GLyaNKGKMPM2" +
                    "xPPGSX8wlQKBgHqIckHGHisn3QynuatDlycooE2BN9vx4fLqtlr6lVmy6e9Z9smI" +
                    "scqjz1DwqwhPxLr0+UjcGvON49rBcDnG9l8BwLGZRzJszBHrA/6++jQxKjF1Ijzt" +
                    "s7dPTnsB7DALZ9HdHT3QRm2l0+LbjOiJHLHEvyoy6jO3ANQwPM6pCBUhAoGBALRp" +
                    "jVV8PA5jE5LGPvL13dOk/AaJ11Uklf1ewYUvlPQOnJcVT2MyAUw21X799FWrShJR" +
                    "YUuDDmadVHWdXMpjdB+Fdl5QBIXqOOKzTTT4Hj1tJY0oVYOhiJm7vTeGrgov86ix" +
                    "ckuZtzpEOyHau+VzFP1tPF31Vd6YqPCjWb6IzzpZAoGBAJ93YT4boj1bufRk4JXX" +
                    "CkeRinNhbtGFYqBxuhkmaK6xd57L4faCqmIIR3GcZDgNTJiADUgB8Q1fF6Ass8yq" +
                    "BVjaktH6fT7qVVRdmjxseP3SG9VlYr7Dmg4WUEbIUDf72y9rR2tHuCQcIkRx2oca" +
                    "q3Jevh/8B+350KH2+di0e0A5");

            RSAUtils.printPrivateKeyInfo(privateKey1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
