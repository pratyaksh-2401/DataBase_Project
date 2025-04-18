package DepricateThis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class TestEncrypt {
    public static void main(String[] args) {
        String password = "password123"; // Replace with the user's password

        try {
            // Generate a random salt
            byte[] salt = generateSalt();

            // Hash the password with the salt
            String hashedPassword = hashPassword(password, salt);

            System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
            System.out.println("Hashed Password: " + hashedPassword);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Generate a random salt
    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    // Hash the password with the salt
    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes());

        // Convert byte array to Base64 encoded string
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
