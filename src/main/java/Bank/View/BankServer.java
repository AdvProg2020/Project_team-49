package Bank.View;

import Bank.Model.Account;
import Bank.Model.Token;
import Controller.DataBase;
import Controller.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class BankServer {
    private static HashMap<Token, Account> onlineUsers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        DataBase.dataBaseRun();
        new ServerImp().run();
    }

    static class ServerImp {
        public void run() throws IOException {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket clientSocket;
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream).start();
            }
        }
    }

    static class ClientHandler extends Thread {

        private Socket socket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;

        public ClientHandler(Socket socket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
            this.socket = socket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
        }
        private void handleClient() {



        }
    }


}
