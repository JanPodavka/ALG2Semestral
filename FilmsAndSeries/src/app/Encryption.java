package app;



import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


public class Encryption {

    public static final String key = "TajnyklickmojiDB"; // klič k DTB [84, 97, 106, 110, 121, 107, 108, 105, 99, 107, 109, 111, 106, 105, 68, 66] //16b

    /**
     * @param password String password
     * @param cipher cipher
     * @param aesKey key
     * @return byte array of values 
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     */
    public static byte[] encrypt(String password,Cipher cipher,Key aesKey) throws InvalidKeyException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {

        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(password.getBytes());
    }

    /**
     * @param enryptedPSWD ciphered pswd
     * @param aesKey aeskey
     * @param cipher cipher
     * @return take array of ciphered password and transform
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decrypt(byte[] enryptedPSWD,Key aesKey,Cipher cipher) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(enryptedPSWD));
        return decrypted;
   }

    public static void main(String[] args) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES"); // na 128bit code
        Cipher cipher = Cipher.getInstance("AES"); //vytvoří instanci cipher
        byte[] secret = encrypt("Honza test",cipher,aesKey);
        System.out.println(Arrays.toString(secret));
        System.out.println(new String(secret));
        String normal = decrypt(secret,aesKey,cipher);
        System.out.println(normal);




    }



}

