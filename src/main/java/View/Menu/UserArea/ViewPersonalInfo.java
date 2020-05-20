package View.Menu.UserArea;

import Controller.Controller;
import Controller.DataBase;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;

public class ViewPersonalInfo extends Menu {

    public ViewPersonalInfo(Menu parentMenu) {
        super("View Personal Info", parentMenu);
    }

    private void showPersonalInfo(ArrayList<String> info) {
        String line = "username: " + info.get(0) + "\n";
        line += "first name: " + info.get(1) + "\n";
        line += "last name: " + info.get(2) + "\n";
        line += "Email: " + info.get(3) + "\n";
        line += "phone number: " + info.get(4) + "\n";
        line += "password: " + info.get(5);
        View.printString(line);
    }

    @Override
    public void showMenu() {
        View.printString(this.getName() + " help:");
        View.printString("edit");
        View.printString("logout");
        View.printString("help");
        View.printString("back");
        View.printString("exit");
        View.printString("______________");
    }

    @Override
    public void run(String lastCommand) {
        this.showPersonalInfo(Controller.getPersonalInfo());
        while (true) {
            View.printString(this.getName().toUpperCase() + ":");
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)edit (.+)").matches()) {
                View.printString("enter new content:");
                View.printString(Controller.editField(getMatcher(command, "(?i)edit (.+)").group(1), scanner.nextLine().trim()));
                continue;
            }
            if (getMatcher(command, "(?i)logout").matches()) {
                Controller.logout();
                View.printString("logout");
                allMenus.get(0).run(lastCommand);
                break;
            }
            if (getMatcher(command, "(?i)help").matches()) {
                this.showMenu();
                continue;
            }
            if (getMatcher(command, "(?i)back").matches()) {
                this.parentMenu.run(lastCommand);
                break;
            }
            if (getMatcher(command, "(?i)exit").matches()) {
                DataBase.endProgram();
            }
            View.printString("invalid command");
        }
        this.parentMenu.run(lastCommand);
    }
}
