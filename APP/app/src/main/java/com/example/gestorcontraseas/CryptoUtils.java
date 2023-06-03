package com.example.gestorcontraseas;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

public class CryptoUtils extends AsyncTask<String, Void, String> {

    private static final int SALT_SIZE_BYTES = 8;
    private static final int ITERATIONS = 1;
    private static final int KEY_SIZE_BYTES = 16;
    private static final int IV_SIZE_BYTES = 16;

    static {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    /**
     * Encripta una cadena de texto utilizando AES con una clave determinística generada a partir de la contraseña.
     *
     * @param plaintext La cadena de texto a encriptar
     * @param password  La contraseña utilizada para generar la clave de cifrado
     * @return La cadena de texto encriptada
     */
    public static String encrypt(String plaintext, String password) throws Exception {
        // Genera una clave AES determinística utilizando PBKDF2 con la contraseña y una sal fija
        byte[] salt = new byte[SALT_SIZE_BYTES];
        KeyParameter key = generateKey(password.toCharArray(), salt);

        // Genera un IV aleatorio
        byte[] iv = new byte[IV_SIZE_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // Configura el cifrado AES con CBC
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key.getKey(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Encripta los datos
        byte[] encryptedData = cipher.doFinal(plaintext.getBytes("UTF-8"));

        // Combina la sal, el IV y los datos encriptados en un arreglo de bytes
        byte[] saltAndIvAndEncryptedData = new byte[SALT_SIZE_BYTES + IV_SIZE_BYTES + encryptedData.length];
        System.arraycopy(salt, 0, saltAndIvAndEncryptedData, 0, SALT_SIZE_BYTES);
        System.arraycopy(iv, 0, saltAndIvAndEncryptedData, SALT_SIZE_BYTES, IV_SIZE_BYTES);
        System.arraycopy(encryptedData, 0, saltAndIvAndEncryptedData, SALT_SIZE_BYTES + IV_SIZE_BYTES, encryptedData.length);

        // Codifica el arreglo de bytes resultante en Base64
        return new String(Base64.encode(saltAndIvAndEncryptedData), "UTF-8");
    }

    /**
     * Desencripta una cadena de texto encriptada con AES utilizando una clave determinística generada a partir de la contraseña.
     *
     * @param ciphertext La cadena de texto encriptada
     * @param password   La contraseña utilizada para generar la clave de cifrado
     * @return La cadena de texto desencriptada
     */
    public static String decrypt(String ciphertext, String password) throws Exception {
        // Decodifica la cadena de texto encriptada desde Base64
        byte[] saltAndIvAndEncryptedData = Base64.decode(ciphertext.getBytes("UTF-8"));

        // Extrae la sal, el IV y los datos encriptados del arreglo de bytes
        byte[] salt = new byte[SALT_SIZE_BYTES];
        byte[] iv = new byte[IV_SIZE_BYTES];
        byte[] encryptedData = new byte[saltAndIvAndEncryptedData.length - SALT_SIZE_BYTES - IV_SIZE_BYTES];
        System.arraycopy(saltAndIvAndEncryptedData, 0, salt, 0, SALT_SIZE_BYTES);
        System.arraycopy(saltAndIvAndEncryptedData, SALT_SIZE_BYTES, iv, 0, IV_SIZE_BYTES);
        System.arraycopy(saltAndIvAndEncryptedData, SALT_SIZE_BYTES + IV_SIZE_BYTES, encryptedData, 0, encryptedData.length);

        // Genera una clave AES determinística utilizando PBKDF2 con la contraseña y la sal extraída
        KeyParameter key = generateKey(password.toCharArray(), salt);

        // Configura el cifrado AES con CBC
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key.getKey(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Desencripta los datos
        byte[] decryptedData = cipher.doFinal(encryptedData);

        // Convierte los datos desencriptados a una cadena de texto
        return new String(decryptedData, "UTF-8");
    }

    /**
     * Genera una clave AES determinística utilizando PBKDF2 con la contraseña y una sal fija.
     *
     * @param password La contraseña utilizada para generar la clave de cifrado
     * @param salt     La sal utilizada en la derivación de clave
     * @return Una clave AES determinística generada a partir de la contraseña y la sal
     */
    private static KeyParameter generateKey(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, ITERATIONS, KEY_SIZE_BYTES * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] keyBytes = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        return new KeyParameter(keyBytes);
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}