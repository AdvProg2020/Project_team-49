package View;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Base64;

public class Client {

    private String token;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ED ed;
    private String type;

    public Client() {
        this.token = "";
        this.type = "guest";
        this.socket = null;
        this.dataInputStream = null;
        this.dataOutputStream = null;
        System.out.println("yea");
        View.setClient(this);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public String getToken() {
        return token;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public String getType() {
        return type;
    }


    public void run() {
        try {
            this.socket = new Socket("127.0.0.1", 8888);
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            String key = dataInputStream.readUTF();
            System.out.println(this.type);
            ed = new ED(key);
            View.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String setCategoriesInMainBar() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("setCategoriesInMainBar"));
            dataOutputStream.flush();
            return ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clickedOnACategoryOnMainBar(String category) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("clickedOnACategoryOnMainBar"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(category));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToOffsAndDiscountsPageFromMainBar() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("goToOffsAndDiscountsPageFromMainBar"));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class ED {
        private Cipher ecipher;
        private Cipher dcipher;
        private SecretKey key;

        public ED(String keyString) {
            try {
                byte[] decodedKey = Base64.getDecoder().decode(keyString);
                for (int i = 0; i < decodedKey.length; i++) {
                    if (i % 2 == 0) {
                        decodedKey[i] = (byte) (decodedKey[i] + 1);
                    } else {
                        decodedKey[i] = (byte) (decodedKey[i] - 1);
                    }
                }
                this.key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
                ecipher = Cipher.getInstance("DES");
                dcipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                dcipher.init(Cipher.DECRYPT_MODE, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Cipher getEcipher() {
            return ecipher;
        }

        public Cipher getDcipher() {
            return dcipher;
        }

        public SecretKey getKey() {
            return key;
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

        public String decrypt(String str) {
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

    // !@
    // #$   BETWEEN OBJECTS
}
