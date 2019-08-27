package site.pushy.ymall.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {

    public MD5Utils() {
        throw new UnsupportedOperationException("The util cannot be initialized");
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes("utf-8"));
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 16) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
