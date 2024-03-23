package com.innoclique.identity.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
@Component
public class LicenseKeyGenerator {

    public String generateLicenseKey(String firstName, String lastName, String dob, String locationName) {
        // Concatenate the provided information
        String data = firstName + lastName + dob + locationName;

        try {
            // Create a SHA-256 message digest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Compute the hash value of the concatenated data
            byte[] hashBytes = digest.digest(data.getBytes());

            // Take the first 16 bytes (128 bits) of the hash value
            byte[] truncatedHash = new byte[16];
            System.arraycopy(hashBytes, 0, truncatedHash, 0, 16);

            // Convert the truncated hash bytes to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : truncatedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Insert dashes at specific positions to match the desired format
            StringBuilder formattedKey = new StringBuilder();
            for (int i = 0; i < hexString.length(); i++) {
                if (i > 0 && i % 4 == 0) {
                    formattedKey.append('-');
                }
                formattedKey.append(hexString.charAt(i));
            }

            return formattedKey.toString().toUpperCase(); // Convert to uppercase
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
