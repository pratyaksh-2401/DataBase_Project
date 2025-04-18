package AuthPage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordDecrypt {
    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes());

        // Convert byte array to Base64 encoded string
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static boolean checkPassword(String storedHashedPassword, String storedSalt, String enteredPassword) {
        try {
            System.out.println(enteredPassword);

            // Convert the stored salt from Base64 string to byte array
            byte[] salt = Base64.getDecoder().decode(storedSalt);

            // Hash the entered password with the stored salt
            String enteredHashedPassword = hashPassword(enteredPassword, salt);

            // Compare the stored hashed password with the entered hashed password
            if (enteredHashedPassword.equals(storedHashedPassword)) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid password.");
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
