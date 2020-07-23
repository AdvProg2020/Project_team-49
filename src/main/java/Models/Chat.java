package Models;

import Models.User.Costumer;
import Models.User.Support;

import java.util.ArrayList;

public class Chat {
    private ArrayList<String> messages;
    private Costumer costumer;
    private Support support;

    public Chat(ArrayList<String> messages, Costumer costumer, Support support) {
        this.messages = messages;
        this.costumer = costumer;
        this.support = support;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public Support getSupport() {
        return support;
    }

    @Override
    public String toString() {
        String message = "";
        for (String s : messages) {
            message += s + "@#";
        }
        return message + "!@" +
                costumer.getUsername() + "!@" +
                support.getUsername();
    }
}
