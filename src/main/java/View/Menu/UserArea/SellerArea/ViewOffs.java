package View.Menu.UserArea.SellerArea;

import Controller.Controller;
import Controller.SellerAreaController;
import View.Menu.Menu;
import View.View;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewOffs extends Menu {
    public ViewOffs(Menu parentMenu) {
        super("View Offs", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("View Off", getView());
        subMenus.put("Add Off", getAddOff());
        subMenus.put("Edit Off", getEditOff());
        subMenus.put("Logout", getLogout());
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)view (\\S+)").matches()) {
            if (!checkId(command.split("\\s")[1])) {
                return "invalid";
            }
            return "View Off";
        } else if (getMatcher(command, "(?i)add off").matches()) {
            return "Add Off";
        } else if (getMatcher(command, "(?i)edit (\\S+)").matches()) {
            if (!checkId(command.split("\\s")[1])) {
                return "invalid";
            }
            return "Edit Off";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getView() {
        return new Menu("View Off", this) {
            @Override
            public void run(String lastCommand) {
                String answer = SellerAreaController.viewOff(Long.parseLong(lastCommand.split("\\s")[1]));
                if (answer.equals("off not exist")) {
                    View.printString(answer);
                } else {
                    View.printString("off Id: " + answer.split("\\s")[0]);
                    View.printString("off started at: " + answer.split("\\s")[1]);
                    View.printString("off ends at: " + answer.split("\\s")[2]);
                    View.printString("off amount: " + answer.split("\\s")[3]);
                    View.printString("_______________________________________");
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getEditOff() {
        return new Menu("Edit Off", this) {
            @Override
            public void run(String lastCommand) {
                while (true) {
                    View.printString("enter field:");
                    String field = scanner.nextLine().trim();
                    if (field.matches("(?i)back")) {
                        break;
                    }
                    View.printString("enter new content:");
                    String content = scanner.nextLine().trim();
                    if (content.matches("(?i)back")) {
                        break;
                    }
                    View.printString(SellerAreaController.editOff(field, content, Long.parseLong(lastCommand.split("\\s")[1])));
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    //kamel nist
    private Menu getAddOff() {
        return new Menu("Add Off", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> info = new ArrayList<>();
                View.printString("enter products Ids (separate them with space):");
                info.add(scanner.nextLine().trim());
                View.printString("enter end date (date structure):");
                info.add(scanner.nextLine().trim());
                View.printString("enter off percentage:");
                info.add(scanner.nextLine().trim());
                SellerAreaController.addOff(info);
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private void showOffs(){
        ArrayList<String> offs = SellerAreaController.showOffs();
        View.printString("Offs:");
        for (String off : offs) {
            View.printString("off Id:" + off.split("\\s")[0]);
            View.printString("off amount:" + off.split("\\s")[1]);
            View.printString("start time:" + off.split("\\s")[2]);
            View.printString("end time:" + off.split("\\s")[3]);
            View.printString("____________________________________");
        }
    }

    private Menu getLogout() {
        return new Menu("Logout", this) {
            @Override
            public void run(String lastCommand) {
                Controller.logout();
                View.printString("logout successful");
                allMenus.get(0).run("");
            }
        };
    }

    @Override
    public void run(String lastCommand) {
        this.showOffs();
        super.run(lastCommand);
    }

    //chiz jalebie!!
    public void repeatRun(String lastCommand) {
        super.run(lastCommand);
    }

    private boolean checkId(String Id) {
        if (!getMatcher(Id, "\\d+").matches()) {
            View.printString("invalid product Id");
            return false;
        }
        return true;
    }
}
