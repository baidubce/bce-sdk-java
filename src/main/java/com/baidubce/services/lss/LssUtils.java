package com.baidubce.services.lss;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * Created by shidaiting01 on 2016/3/24.
 */
public class LssUtils {
    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * Encodes the input String using the UTF8 charset and calls hmacSha256;
     *
     * @param input data to calculate mac
     * @param secretKey secret key
     * @return String, sha256 mac
     */
    public static String hmacSha256(String input, String secretKey) {
        if (input == null) {
            throw new NullPointerException("input");
        } else if (secretKey == null) {
            throw new NullPointerException("secretKey");
        }
        return hmacSha256(input.getBytes(CHARSET_UTF8), secretKey.getBytes(CHARSET_UTF8));
    }

    public static String hmacSha256(byte[] input, byte[] secretKey) {
        if (input == null) {
            throw new NullPointerException("input");
        } else if (secretKey == null) {
            throw new NullPointerException("secretKey");
        }
        return hmacSha256(input, 0, input.length, secretKey);
    }

    public static String hmacSha256(byte[] input, int offset, int length, byte[] secretKey) {
        if (input == null) {
            throw new NullPointerException("input");
        } else if (secretKey == null) {
            throw new NullPointerException("secretKey");
        }
        return new String(Hex.encodeHex(mac(input, offset, length, new SecretKeySpec(secretKey, HMAC_SHA256))));
    }

    public static byte[] mac(byte[] input, SecretKey secretKey) {
        if (input == null) {
            throw new NullPointerException("input");
        } else if (secretKey == null) {
            throw new NullPointerException("secretKey");
        }
        return mac(input, 0, input.length, secretKey);
    }

    public static byte[] mac(byte[] input, int offset, int length, SecretKey secretKey) {
        if (input == null) {
            throw new NullPointerException("input");
        } else if (secretKey == null) {
            throw new NullPointerException("secretKey");
        }
        try {
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            mac.update(input, offset, length);
            return mac.doFinal();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        } catch (InvalidKeyException ex) {
            throw new RuntimeException(ex);
        }
    }
}
