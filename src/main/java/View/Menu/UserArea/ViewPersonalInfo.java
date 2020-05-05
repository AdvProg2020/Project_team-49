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
        while (true) {
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)edit (\\S+)").matches()) {
                View.printString("enter new" + lastCommand.split("\\s")[1] + ":");
                Controller.editField(lastCommand.split("\\s")[1], scanner.nextLine().trim());
                View.printString(lastCommand.split("\\s")[1] + "edited");
                continue;
            }
            if (getMatcher(command, "(?i)logout").matches()) {
                Controller.logout();
                View.printString("logout");
                allMenus.get(0).run(lastCommand);
                break;
            }
            if (command.equals("back")) {
                break;
            }
            View.printString("invalid command");
        }
        this.parentMenu.run(lastCommand);
    }
}
