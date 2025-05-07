package com.example.ejercicio.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Component
public class AesUtil {
    private static final String SECRET_KEY = "clave12345678901"; // 16 caracteres (AES-128)

    public String encriptar(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (GeneralSecurityException e) {
            // Manejo de errores de cifrado
            System.err.println("Error al encriptar: " + e.getMessage());
        } catch (Exception e) {
            // Cualquier otro error
            System.err.println("Error inesperado al encriptar: " + e.getMessage());
        }
        return null;
    }

    public String desencriptar(String encryptedData) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (IllegalArgumentException e) {
            // Base64 inválido
            System.err.println("Datos encriptados inválidos (Base64): " + e.getMessage());
        } catch (GeneralSecurityException e) {
            // Error en la clave o en el proceso de descifrado
            System.err.println("Error al desencriptar (clave inválida o datos corruptos): " + e.getMessage());
        } catch (Exception e) {
            // Cualquier otro error
            System.err.println("Error inesperado al desencriptar: " + e.getMessage());
        }
        return null;
    }
}