package com.lph.util.sha256;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * HMACSHA256 工具类
 */
public class Sha256Utils {


    /**
     *  对字符串进行 HMACSHA256 加密。并输出Base64
     * @param strText
     * @param strKey
     * @return
     */
    public static String HMACSHA256(final String strText, final String strKey) {
        String strResult = null;
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey   = new SecretKeySpec(strKey.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKey);
            byte[] hash = sha256_HMAC.doFinal(strText.getBytes());
            strResult = toBase64(hash);
            return strResult;
        } catch (NoSuchAlgorithmException e) {
            return strResult;
        } catch (InvalidKeyException e) {
            return strResult;
        }
    }


    private static String toBase64(byte[] b){
        return new String(Base64.encodeBase64(b)) ;
    }

}
