package com.example.gestorcontraseas;
import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryption {
    private static final String TAG = "PasswordEncryption";
    private static final String ALGORITHM = "AES";
    private static final String KEY = "Kekers230115nuat"; // Clave de encriptación (16, 24 o 32 bytes)

    public static String encrypt(String password) {
        try {
            SecretKey secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, "Error during encryption: " + e.getMessage());
            return null;
        }
    }

    public static String decrypt(String encryptedPassword) {
        try {
            SecretKey secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.decode(encryptedPassword, Base64.DEFAULT);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            Log.e(TAG, "Error during decryption: " + e.getMessage());
            return null;
        }
    }
}
