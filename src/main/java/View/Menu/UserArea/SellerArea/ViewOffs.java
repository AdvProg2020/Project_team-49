package View.Menu.UserArea.SellerArea;

import Controller.Controller;
import Controller.SellerAreaController;
import View.Menu.Menu;
import View.View;

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
                SellerAreaController.editOff("", "", 1);
                this.parentMenu.run("");
            }
        };
    }

    private Menu getAddOff() {
        return new Menu("Add Off", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.addOff(new String[3]);
                this.parentMenu.run("");
            }
        };
    }

    private void showOffs(){
        SellerAreaController.showOffs();
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
