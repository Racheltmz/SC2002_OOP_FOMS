package authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Hash password for security purposes
public class Hashing {
    public static String genHash(String passwordToHash) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Hashing Algorithm Not Found.");
        }
        return hashedPassword;
    }
}
