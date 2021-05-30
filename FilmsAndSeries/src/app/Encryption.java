package app;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


public class Encryption {

    public static final String key = "TajnyklickmojiDB"; // klič k DTB [84, 97, 106, 110, 121, 107, 108, 105, 99, 107, 109, 111, 106, 105, 68, 66] //16b

    /**
     * encrypt our psw
     *
     * @param password String password
     * @param cipher   cipher
     * @param aesKey   key
     * @return byte array of values
     * @throws InvalidKeyException       e
     * @throws BadPaddingException       e
     * @throws IllegalBlockSizeException e
     */
    public static byte[] encrypt(String password, Cipher cipher, Key aesKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(password.getBytes());
    }

    /**
     * decrypt our ciphered string pws
     *
     * @param enryptedPSWD ciphered pswd
     * @param aesKey       aeskey
     * @param cipher       cipher
     * @return take array of ciphered password and transform
     * @throws InvalidKeyException       er
     * @throws BadPaddingException       err
     * @throws IllegalBlockSizeException err
     */
    public static String decrypt(byte[] enryptedPSWD, Key aesKey, Cipher cipher) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return new String(cipher.doFinal(enryptedPSWD));
    }


    //I let here methods to show how AES encrypting works
    public static void main(String[] args) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES"); // na 128bit code // znám vždy
        System.out.println(Arrays.toString(aesKey.getEncoded()));
        Cipher cipher = Cipher.getInstance("AES"); //vytvoří instanci cipher
        byte[] secret = encrypt("admin", cipher, aesKey); //zde se zašifruje heslo
        System.out.println(Arrays.toString(secret));
/////////////////////////////////////////////////////////
        String encodedKey = Base64.getEncoder().encodeToString(secret);
        System.out.println(encodedKey);
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        System.out.println(Arrays.toString(originalKey.getEncoded()));
        System.out.println(decrypt(originalKey.getEncoded(), aesKey, cipher));
    }


}

