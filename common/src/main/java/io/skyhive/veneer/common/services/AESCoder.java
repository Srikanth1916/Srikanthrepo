package io.skyhive.veneer.common.services;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */
public class AESCoder {
    private static byte[] KEY =
            "7278386963FD423784829CFD4F8B7DB7".getBytes(StandardCharsets.UTF_8);
    private static byte[] IV =
            "F3274CA6E4384D6A".getBytes(StandardCharsets.UTF_8);

    public static String encryptString(String input) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey originalKey = new SecretKeySpec(KEY, 0, KEY.length,
                "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, originalKey, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decryptString(String cipherText) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey originalKey = new SecretKeySpec(KEY, 0, KEY.length,
                "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, originalKey, ivParameterSpec);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
