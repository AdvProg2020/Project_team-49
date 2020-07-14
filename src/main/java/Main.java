import Controller.DataBase;
import View.View;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Base64;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import static javax.xml.crypto.dsig.Transform.BASE64;

public class
Main {

    public static void main(String[] args) throws Exception {
        ED ed = new ED();
        String command = "deep shit i'm alireza heidari";
        String encrypt = ed.encrypt(command);
        System.out.println(encrypt);
        System.out.println(ed.decrypt(encrypt));
//        DataBase.startProgram();
//        try {
////            DataBase.startProgram();
//        } catch (Exception e) {
//            DataBase.endProgram();
//        }
    }

    static class ED {
        Cipher ecipher;
        Cipher dcipher;
        public ED() {
            try {
                SecretKey key = KeyGenerator.getInstance("DES").generateKey();
                System.out.println(key);
                ecipher = Cipher.getInstance("DES");
                dcipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                dcipher.init(Cipher.DECRYPT_MODE, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String encrypt(String str) {
            try {
                byte[] utf8 = str.getBytes("UTF8");
                byte[] enc = ecipher.doFinal(utf8);
                enc = BASE64EncoderStream.encode(enc);
                return new String(enc);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public  String decrypt(String str) {
            try {
                byte[] dec = BASE64DecoderStream.decode(str.getBytes());
                byte[] utf8 = dcipher.doFinal(dec);
                return new String(utf8, "UTF8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

