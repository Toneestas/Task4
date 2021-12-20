import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class Hmac {
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    public static byte[] getHmac(byte[] key, String currentMove) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Mac signer = Mac.getInstance(HMAC_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key, HMAC_ALGORITHM);
        signer.init(keySpec);
        byte[] digest = signer.doFinal(currentMove.getBytes("utf-8"));
        return digest;
    }
}
