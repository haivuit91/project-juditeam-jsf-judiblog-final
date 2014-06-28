/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author monkeydluffy
 */
public class MD5 {
    public static String encryptMD5(String pw){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pw.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashText = number.toString(16);
            while(hashText.length() < 32){
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException  e) {
            throw new RuntimeException();
        }
    }
}
