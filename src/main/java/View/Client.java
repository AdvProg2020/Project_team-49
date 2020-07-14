package View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private String token;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public void Client() {
        this.token = "";
        this.socket = null;
        this.dataInputStream = null;
        this.dataOutputStream = null;
    }

    public void run() {
        try {
            //mishe kari kard ke ta start shodan server sabr kone!
            this.socket = new Socket("127.0.0.1", 8888);
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            //View.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
