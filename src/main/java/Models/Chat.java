package Models;

import Models.User.Costumer;
import Models.User.Support;

import java.util.ArrayList;

public class Chat {
    private ArrayList<String> messages;
    private Costumer costumer;
    private Support support;

    public Chat(Costumer costumer, Support support) {
        this.messages = new ArrayList<>();
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

    public void addMessage(String message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        String message = "";
        for (String s : messages) {
            message += s + "!@";
        }
        return message;
    }
}
