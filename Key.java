import java.security.SecureRandom;

public class Key {
    public static byte[] getCryptographicKey(){
        SecureRandom random = new SecureRandom();
        byte cryptographicKey[] = new byte[32];
        random.nextBytes(cryptographicKey);
        return cryptographicKey;
    }
}
