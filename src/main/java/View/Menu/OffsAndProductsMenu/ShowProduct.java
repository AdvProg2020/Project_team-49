package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.ShowProductDetail;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
import View.View;


// bad az alireza bayad in ke chan bar baz shode har kala ro to controller ezafe konam.
public class ShowProduct extends Menu {

    public ShowProduct(Menu parentMenu) {
        super("Show product", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        View.printString("Show Product Menu:");
        long productId = Long.parseLong(lastCommand.split("\\s")[2]);

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("digest")) {
                View.printProductSummery(productId, ShowProductDetail.getName(productId),
                        ShowProductDetail.getOffPercentage(productId), ShowProductDetail.getExplanation(productId),
                        ShowProductDetail.getPrice(productId), ShowProductDetail.getCategory(productId),
                        ShowProductDetail.getAverageScore(productId));

                new Digest(this);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("attributes")) {
                View.printAttributes(productId, ShowProductDetail.getName(productId),
                        ShowProductDetail.getOffPercentage(productId), ShowProductDetail.getExplanation(productId),
                        ShowProductDetail.getPrice(productId), ShowProductDetail.getCategory(productId),
                        ShowProductDetail.getAverageScore(productId), ShowProductDetail.getAllSeller(productId),
                        ShowProductDetail.getRemainedNumber(productId));

                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("compare [productID]")) {
                long secondProductId = Long.parseLong(command.split("\\s")[1]);
                View.printCompareProduct(ShowProductDetail.getName(productId), ShowProductDetail.getOffPercentage(productId),
                        ShowProductDetail.getExplanation(productId), ShowProductDetail.getPrice(productId),
                        ShowProductDetail.getAverageScore(productId),

                        ShowProductDetail.getName(secondProductId), ShowProductDetail.getOffPercentage(secondProductId),
                        ShowProductDetail.getExplanation(secondProductId), ShowProductDetail.getPrice(secondProductId),
                        ShowProductDetail.getAverageScore(secondProductId));
                //bayad havasm bashe ke controller bayad ye product jadid bi in pas bede

                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Comments")) {
                new Comments(this, productId);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log in")) {
                new UserArea(this);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log Out")) {
                Controller.logout();
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Help")){
                View.printShowProductMenu();
            }

            if (command.equals("back")) {
                this.parentMenu.run(lastCommand);
            }

            View.printString("Please enter valid Instruction\n" +
                    "You Can Instruction By Typing Help");
        }
    }
}
