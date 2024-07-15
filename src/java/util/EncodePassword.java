/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import org.apache.tomcat.util.codec.binary.Base64;
import java.security.MessageDigest;
/**
 *
 * @author hailt
 */
public class EncodePassword {
    public static String toSHA1(String password){
        String salt = "hdkashdkh;@.jds";
        String result = null;
        password = password + salt;
        try{
            byte[] dataBytes = password.getBytes("UTF-8");
          MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
   
}
