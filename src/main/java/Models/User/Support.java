package Models.User;

import java.util.ArrayList;

public class Support extends User {
    ArrayList<User> contacts;

    public Support(String username, String firstName, String lastName, String EMail, long phoneNumber, String password) {
        super(username, firstName, lastName, EMail, phoneNumber, password);
        contacts = new ArrayList<>();
    }

    public ArrayList<User> getContacts() {
        return contacts;
    }

    @Override
    public String getType() {
        return "Support";
    }
}
