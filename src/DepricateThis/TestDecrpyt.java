package DepricateThis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TestDecrpyt {
    public static void main(String[] args) {
        String storedHashedPassword = "Z6dYIFVfA+E6EGcaWVcfF+ekvsWTPwxeZ8vGgiE5Ua8="; // Retrieve from the database
        String storedSalt = "eXaosVlLUsKdEktTNxbIRQ=="; // Retrieve from the database
        String enteredPassword = "password123"; // Provided by the user during login

        try {
            // Convert the stored salt from Base64 string to byte array
            byte[] salt = Base64.getDecoder().decode(storedSalt);

            // Hash the entered password with the stored salt
            String enteredHashedPassword = hashPassword(enteredPassword, salt);

            // Compare the stored hashed password with the entered hashed password
            if (enteredHashedPassword.equals(storedHashedPassword)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid password.");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Hash the password with the given salt
    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes());

        // Convert byte array to Base64 encoded string
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
