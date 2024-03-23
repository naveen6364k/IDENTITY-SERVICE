package com.innoclique.identity.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Testing {
    public static String generateLicenseKey(String firstName, String lastName, String dob, String locationName) {
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
                if (i == 4 || i == 10) { // Insert dashes at positions 4 and 10
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

    public static void main(String[] args) {
        String firstName = "Karthik";
        String lastName = "Seelam";
        String dob = "2001-03-31";
        String locationName = "Hyderabad";

        String licenseKey = generateLicenseKey(firstName, lastName, dob, locationName);
        System.out.println("Generated License Key: " + licenseKey);
    }
}
