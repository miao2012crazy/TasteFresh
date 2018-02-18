package com.xialan.tastefresh.commonutil;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class RSAUtil {

    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";

    /**
     * BASE64����
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
//		return (new BASE64Decoder()).decodeBuffer(key);
        return Base64.decode(key, Base64.DEFAULT);
    }
    /**
     * BASE64����
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
//		return (new BASE64Encoder()).encodeBuffer(key);
        return Base64.encodeToString(key, Base64.DEFAULT);
    }
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDA+QNhGxF9v+Q8pElTrBnn4BVA+AWWXexSvOn/6DJmA1PKBq3COZwy7FXgLr82ZW6ZMJ5n8nlqzcsQd+W2PLzreD2svWz7O1XrFuB9R4E5pyDOA9INGqjfevAyUAplPpN3tB/zgr4g1tUxRpxTTZLug1hlXUgdVhtZWkzQjXI8rQIDAQAB";
    public static final String KEY_ALGORTHM = "RSA";//

    /**
     * 用公钥加密
     * @param data	加密数据
     * @param key	密钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data,String key)throws Exception{
        //对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        //取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //对数据解密
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }








}