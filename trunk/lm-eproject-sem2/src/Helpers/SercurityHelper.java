/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.security.Key;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 *
 * @author Administrator PC
 */
public class SercurityHelper {
    private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] { 1, 0, 0, 2, 1, 9, 8,5, 0, 8, 0,8, 1, 9, 8, 5 };


public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue;
        encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue;
        decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
    
    private static SecretKey readKey(String input) 
      throws Exception {            
      KeySpec ks = null;
      SecretKey ky = null;
      SecretKeyFactory kf = null; 
      
       ks = new DESKeySpec(keyValue);
       kf = SecretKeyFactory.getInstance(ALGO);
       ky = kf.generateSecret(ks);       
      return ky;
   }
    public static String secretCipher( String mode
      , String input) throws Exception {
      String cypherResult = null;
      try {
        Cipher cf = Cipher.getInstance(ALGO);
        SecretKey ky = SercurityHelper.readKey(ALGO);
        
      if (mode.equalsIgnoreCase(SercurityMode.encrypt.toString())) 
         cf.init(Cipher.ENCRYPT_MODE,ky);
      else if (mode.equalsIgnoreCase(SercurityMode.decrypt.toString()))
         cf.init(Cipher.DECRYPT_MODE,ky);
      else
         throw new Exception("Invalid mode: "+mode);
      
          
         cypherResult = secretCipher(mode, input);
      } catch (Exception e) {
         System.out.println("Exception: "+e);
         return null;
      }
      return cypherResult;
   }
    public static enum SercurityMode{
        encrypt,
        decrypt
    }
}
