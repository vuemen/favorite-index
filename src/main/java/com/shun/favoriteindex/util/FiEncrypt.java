package com.shun.favoriteindex.util;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 加密解密工具
 */
public class FiEncrypt {

    private static Key key;
    // 设置密钥key
    private static String KEY_STR = "favorite-index";
    private static String CHARSET = "UTF-8";
    private static String ALGORITHM = "DES";

    // 静态代码块
    static {
        try {
            // 生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            // 运用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置上密钥种子
            secureRandom.setSeed(KEY_STR.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成密钥对象
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 加密
     * @param text
     * @return
     */
    public static String encrypt(String text) {
        // 基于BASE64编码，接收byte[]并转换为String
        BASE64Encoder base64encoder = new BASE64Encoder();
        try {
            // 按UTF-8编码
            byte[] bytes = text.getBytes(CHARSET);
            // 获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] doFinal = cipher.doFinal(bytes);
            // byte[] to encode好的String并返回
            return base64encoder.encode(doFinal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     * @param text
     * @return
     * @throws IOException
     */
    public static String decrypt(String text) {
        //基于BASE64编码，接收byte[]并转换为String
        BASE64Decoder base64decoder = new BASE64Decoder();
        try {
            //将字符串decode为byte[]
            byte[] bytes = base64decoder.decodeBuffer(text);
            //获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, key);
            //解密
            byte[] doFinal = cipher.doFinal(bytes);
            //返回解密之后的信息
            return new String(doFinal, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
