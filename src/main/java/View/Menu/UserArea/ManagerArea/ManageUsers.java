package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;
import java.util.HashMap;

public class ManageUsers extends Menu {

    public ManageUsers(Menu parentMenu) {
        super("Manage Users", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("View User", getView());
        subMenus.put("Delete User", getDeleteUser());
        subMenus.put("Change User Type", getChangeUserType());
        subMenus.put("Logout", getLogout());
        subMenus.put("Create Manager Profile", getCreateManagerProfile());
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)view (\\S+)").matches()) {
            if (!checkUsername(command.split("\\s")[1])) {
                return "invalid";
            }
            return "View User";
        } else if (getMatcher(command, "(?i)delete user (\\S+)").matches()) {
            if (!checkUsername(command.split("\\s")[2])) {
                return "invalid";
            }
            return "Delete User";
        } else if (getMatcher(command, "(?i)change user type (\\S+) (\\S+)").matches()) {
            if (!checkChangeUserCommand(command)) {
                return "invalid";
            }
            return "Change User Type";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)create manager profile").matches()) {
            return "Create Manager Profile";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        }
        else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getView() {
        return new Menu("View", this) {
            @Override
            public void run(String lastCommand) {
                String[] command = lastCommand.split("\\s");
                if (!Controller.hasUserWithUsername(command[1])) {
                    View.printString("user not exist");
                } else {
                    String info = ManagerAreaController.viewUser(command[1]);
                    View.printString("username: " + info.split(",")[0]);
                    View.printString("first name: " + info.split(",")[1]);
                    View.printString("last name: " + info.split(",")[2]);
                    View.printString("Email: " + info.split(",")[3]);
                    View.printString("phone number: " + info.split(",")[4]);
                    if (Controller.getCurrentUserType().equals("Seller")) {
                        View.printString("company name: " + info.split(",")[5]);
                    }
                    View.printString("_________________________________________");
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getDeleteUser() {
        return new Menu("Delete User", this) {
            @Override
            public void run(String lastCommand) {
                String[] command = lastCommand.split("\\s");
                if (!Controller.hasUserWithUsername(command[2])) {
                    View.printString("user not exist");
                } else {
                    View.printString(ManagerAreaController.deleteUser(command[2]));
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    //kamel nist (etelaat karbar jadid)
    private Menu getChangeUserType() {
        return new Menu("Change User Type", this) {
            @Override
            public void run(String lastCommand) {
                String[] command = lastCommand.split("\\s");
                if (!Controller.hasUserWithUsername(command[4])) {
                    View.printString("user not exist");
                } else if (Controller.getCurrentUserType().equals(command[3].toLowerCase())) {
                    View.printString("user type is the same");
                } else {
                    View.printString(ManagerAreaController.changeUserType(command[4], command[3].toLowerCase()));
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    //error be joz username check nashode
    private Menu getCreateManagerProfile() {
        return new Menu("Create Manager Profile", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> info = getManagerInformation();
                if (info.size() != 0) {
                    View.printString(Controller.createAccount(info, "manager"));
                }
                this.parentMenu.run(lastCommand);
            }
        };
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

    private void showUsers() {
        ArrayList<String> users = ManagerAreaController.showAllUsers();
        View.printString("Users:");
        for (String user : users) {
            View.printString("username: " + user.split(",")[0]);
            View.printString("user type: " + user.split(",")[1]);
            View.printString("first name: " + user.split(",")[2]);
            View.printString("last name: " + user.split(",")[3]);
            View.printString("Email: " + user.split(",")[4]);
            View.printString("phone number: " + user.split(",")[5]);
            View.printString("______________________________________");
        }
    }

    @Override
    public void run(String lastCommand) {
        this.showUsers();
        super.run(lastCommand);
    }

    private boolean checkUsername(String username) {
        if (!getMatcher(username, "\\w+").matches()) {
            View.printString("invalid username");
            return false;
        }
        return true;
    }

    private boolean checkChangeUserCommand(String command) {
        if (!getMatcher(command, "(?i)change user type (manager|seller|costumer) (\\S+)").matches()) {
            View.printString("invalid user type");
            return false;
        }
        if (!getMatcher(command, "(?i)change user type (\\S+) (\\w+)").matches()) {
            View.printString("invalid username");
            return false;
        }
        return true;
    }

    //kamel nist
    private ArrayList<String> getManagerInformation() {
        ArrayList<String> info = new ArrayList<>();
        while(true) {
            if (getMatcher(info.get(info.size() - 1), "(?i)back").matches()) {
                info.clear();
                break;
            }
            info.clear();
            View.printString("inter username:");
            info.add(scanner.nextLine().trim());
            if (Controller.hasUserWithUsername(info.get(0))) {
                View.printString("user exist with this username");
                continue;
            }
            View.printString("inter password:");
            info.add(scanner.nextLine().trim());
            if (!getMatcher(info.get(1), "\\w+").matches()) {
                View.printString("invalid password");
                continue;
            }
            View.printString("inter first name:");
            info.add(scanner.nextLine().trim());
            View.printString("inter last name");
            info.add(scanner.nextLine().trim());
            View.printString("inter Email:");
            info.add(scanner.nextLine().trim());
            View.printString("inter phone number:");
            info.add(scanner.nextLine().trim());
            if (!getMatcher(info.get(5), "\\d+").matches()) {
                View.printString("invalid phone number");
                continue;
            }
            for (String s : info) {
                if (getMatcher(s, "(?i)back").matches()) {
                    info.clear();
                    break;
                }
            }
            break;
        }
        return info;
    }
}
