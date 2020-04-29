package View.Menu.UserArea;

import Controller.Controller;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;

public class ViewPersonalInfo extends Menu {

    public ViewPersonalInfo(Menu parentMenu) {
        super("View Personal Info", parentMenu);
    }

    private void showPersonalInfo(ArrayList<String> info) {
        String line = "username:" + info.get(0) + "\n";
        line += "first name" + info.get(1) + "\n";
        line += "last name" + info.get(2) + "\n";
        line += "Email" + info.get(3) + "\n";
        line += "phone number" + info.get(4) + "\n";
        line += "password" + info.get(5) + "\n";
        View.printString(line);
    }

    @Override
    public void run(String lastCommand) {
        this.showPersonalInfo(Controller.getPersonalInfo());
        if (getMatcher(lastCommand, "(?i)edit (\\S+)").matches()) {
            View.printString("inter new" + lastCommand.split("\\s")[1] + ":");
            Controller.editField(lastCommand.split("\\s")[1], scanner.nextLine().trim());
            View.printString(lastCommand.split("\\s")[1] + "edited");
            this.run(scanner.nextLine().trim());
        }
        if (getMatcher(lastCommand, "(?i)logout").matches()) {
            Controller.logout();
            View.printString("logout");
            allMenus.get(0).run("");
        }
        if (lastCommand.equals("back")) {
            this.parentMenu.run("");
        }
    }
}
