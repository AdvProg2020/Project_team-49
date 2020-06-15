package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.OffAndProductMenuController;
import Controller.ShowProductDetail;
import Controller.DataBase;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
import View.View;


// bad az alireza bayad in ke chan bar baz shode har kala ro to controller ezafe konam.
public class ShowProduct extends Menu {

    private long productId;
    public ShowProduct(Menu parentMenu) {
        super("Show product", parentMenu);
    }
    @Override
    public void run(String lastCommand) {
        View.printString("\"Show Product Menu:\"");
        if (!lastCommand.equalsIgnoreCase("back")){
            productId = Long.parseLong(lastCommand.split("\\s")[2]);
        }
        OffAndProductMenuController.increaseView(productId);
        while (true) {

            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("digest")) {
                View.printProductSummery(productId, ShowProductDetail.getName(productId),
                        ShowProductDetail.getOffPercentage(productId), ShowProductDetail.getExplanation(productId),
                        ShowProductDetail.getPrice(productId), ShowProductDetail.getCategory(productId),
                        ShowProductDetail.getAverageScore(productId));
                if (lastCommand.equalsIgnoreCase("back")){
                    lastCommand="show product "+productId;
                }
                new Digest(this).run(lastCommand);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("attributes")) {
                View.printAttributes(productId, ShowProductDetail.getName(productId),
                        ShowProductDetail.getOffPercentage(productId), ShowProductDetail.getExplanation(productId),
                        ShowProductDetail.getPrice(productId), ShowProductDetail.getCategory(productId),
                        ShowProductDetail.getAverageScore(productId), ShowProductDetail.getAllSeller(productId),
                        ShowProductDetail.getAllSellerPrice(productId),ShowProductDetail.getRemainedNumber(productId));
//                new Digest(this).run(lastCommand);
                this.run(lastCommand);
            }

            if (command.toLowerCase().startsWith("compare")) {
                String productIdString = command.split("\\s")[1];
                if (!productIdString.matches("\\d+")){
                    View.printString("Please Enter Number For Product Id.");
                    this.run(lastCommand);
                }
                if (!OffAndProductMenuController.isProductWithId(Long.parseLong(productIdString))){
                    View.printString("There Is No Product With This Id.");
                    this.run(lastCommand);
                }

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
                new Comments(this, productId).run(lastCommand);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log in")) {
                new UserArea(this).run(lastCommand);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log Out")) {
                Controller.logout();
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Help")){
                View.printShowProductMenu();
            }

            if (command.equalsIgnoreCase("back")) {
                this.parentMenu.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Exit")){
                DataBase.endProgram();
            }

            View.printString("Please enter valid Instruction\n" +
                    "You Can Instruction By Typing Help");
        }
    }
    private Menu digest(long productId){
        return new Menu("Pre Digest",this) {
            @Override
            public void run(String lastCommand){
                View.printProductSummery(productId, ShowProductDetail.getName(productId),
                        ShowProductDetail.getOffPercentage(productId), ShowProductDetail.getExplanation(productId),
                        ShowProductDetail.getPrice(productId), ShowProductDetail.getCategory(productId),
                        ShowProductDetail.getAverageScore(productId));
                new Digest(this).run(lastCommand);
                this.run(lastCommand);
            }
        };
    }
}
