package View.Menu.UserArea.CostumerArea;

import Controller.CostumerAreaController;
import Controller.Controller;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;

public class Purchase extends Menu {
    private ArrayList<String> receiverInfo;

    public Purchase(Menu parentMenu) {
        super("Purchase", parentMenu);
        this.receiverInfo = new ArrayList<>();
    }

    private void getReceiverInformation() {
        while (true) {
            receiverInfo.clear();
            View.printString("enter your phone number (back to exit):");
            receiverInfo.add(scanner.nextLine().trim());
            if (receiverInfo.get(0).matches("(?i)back")) {
                break;
            }
            if (!getMatcher(receiverInfo.get(0), "\\d+").matches()) {
                View.printString("invalid");
            }
            View.printString("enter your address (back to exit):");
            receiverInfo.add(scanner.nextLine().trim());
            if (receiverInfo.get(1).matches("(?i)back")) {
                break;
            }
            getDiscountCode();
        }
    }

    private void getDiscountCode() {
        while (true) {
            View.printString("enter your discount code or type 'skip' (back to exit):");
            String discountCode = scanner.nextLine().trim();
            if (getMatcher(discountCode, "(?i)back").matches()) {
                break;
            }
            if (getMatcher(discountCode, "(?i)skip").matches()) {
                receiverInfo.add("no");
                doPayment();
                break;
            }
            if (!Controller.hasDiscountCode(discountCode)) {
                View.printString("invalid discount code");
                continue;
            } else {
                receiverInfo.add(discountCode);
                doPayment();
                break;
            }
        }
    }

    private void doPayment() {
        while (true) {
            View.printString("enter 'pay' to do payment or 'back' to exit:");
            String task = scanner.nextLine().trim();
            if (getMatcher(task, "(?i)back").matches()) {
                break;
            }
            if (getMatcher(task, "(?i)pay").matches()) {
                View.printString(CostumerAreaController.finishPayment(receiverInfo));
            }
            View.printString("invalid command");
        }
    }

    @Override
    public void run(String lastCommand) {
        getReceiverInformation();
        this.parentMenu.run(lastCommand);
    }
}
