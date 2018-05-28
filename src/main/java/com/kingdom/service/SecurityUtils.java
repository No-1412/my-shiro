package com.kingdom.service;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

import java.security.SecureRandom;

/**
 * @author No.1412
 * @version 2018/5/29
 */
public class SecurityUtils {

    private static final SecureRandom random = new SecureRandom();

    private static final int hashIterations = 512;

    /**
     * 密码加密
     */
    public static String encryptPassword(String password) {
        byte[] salt = generateRandom();
        Sha256Hash hash = new Sha256Hash(password, ByteSource.Util.bytes(salt), hashIterations);
        return Hex.encodeToString(salt) + hash.toHex();
    }

    /**
     * 生成8位的随机字节-> salt
     */
    public static byte[] generateRandom() {
//        return (int) ((Math.random() * 9 + 1) * 10000000);
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return bytes;
    }
}
