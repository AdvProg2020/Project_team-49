package View.Menu.UserArea.CostumerArea;

import Controller.CostumerAreaController;
import Controller.Controller;
import View.Menu.Menu;

import java.util.ArrayList;

public class Purchase extends Menu {
    private ArrayList<String> receiverInfo;

    public Purchase(Menu parentMenu) {
        super("Purchase", parentMenu);
        this.receiverInfo = new ArrayList<>();
    }

    private void getReceiverInformation() {

    }

    private void getDiscountCode() {
        Controller.hasDiscountCode("");
    }

    private void doPayment() {
        CostumerAreaController.finishPayment(receiverInfo);
    }

    @Override
    public void run(String lastCommand) {
        this.getReceiverInformation();
        this.getDiscountCode();
        this.doPayment();
        this.parentMenu.run("");
    }
}
