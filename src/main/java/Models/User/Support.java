package Models.User;

import Models.Chat;

import java.util.ArrayList;

public class Support extends User {
    ArrayList<User> contacts;
    private ArrayList<Chat> chats;

    public Support(String username, String firstName, String lastName, String EMail, long phoneNumber, String password) {
        super(username, firstName, lastName, EMail, phoneNumber, password);
        contacts = new ArrayList<>();
        this.chats = new ArrayList<>();
    }

    public ArrayList<User> getContacts() {
        return contacts;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void addChat(Chat chat) {
        chats.add(chat);
    }

    @Override
    public String getType() {
        return "Support";
    }
}
