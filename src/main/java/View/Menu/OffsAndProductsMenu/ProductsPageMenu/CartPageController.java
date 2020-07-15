package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import Controller.DataBase;
import Models.DiscountCode;
import Models.Log.BuyLog;
import Models.Log.SellLog;
import Models.Off;
import Models.Product;
import Models.User.Cart;
import Models.User.Costumer;
import Models.User.Guest;
import Models.User.Seller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;

public class CartPageController implements Initializable {
    public ImageView digikalaLogo;
    public Pane cartMainPane;
    public Pane finalPricePane;
    public Pane cartProductsStatusPane;
    public Pane cartIsEmpty;
    public Pane isNotEmptyPane;
    public Pane product1;
    public ImageView productImage1;
    public Label productName1;
    public ImageView plus1;
    public Label numberOfProduct1;
    public ImageView minus1;
    public Label companyName1;
    public Label finalPriceForProduct1;
    public Label discountPriceForProduct1;
    public Pane product2;
    public ImageView productImage2;
    public Label productName2;
    public ImageView plus2;
    public Label numberOfProduct2;
    public ImageView minus2;
    public Label companyName2;
    public Label finalPriceForProduct2;
    public Label discountPriceForProduct2;
    public Pane product3;
    public ImageView productImage3;
    public Label productName3;
    public ImageView plus3;
    public Label numberOfProduct3;
    public ImageView minus3;
    public Label companyName3;
    public Label finalPriceForProduct3;
    public Label discountPriceForProduct3;
    public ImageView upArrow;
    public ImageView downArrow;
    public Rectangle firstProductBar;
    public Rectangle secondProductBar;
    public Label totalDiscount;
    public Label totalPrice;
    public Label moneyMinusDiscount;
    public Label finalPayNumber;
    public Rectangle firstRec;
    public Rectangle thirdRec;
    public Rectangle secondRec;
    public ImageView trash2;
    public ImageView trash1;
    public ImageView trash3;

    public Pane informationPane;
    public Rectangle addressRec;
    public TextField addressTextField;
    public Label addressLabel;
    public Rectangle phoneNumberRec;
    public TextField phoneNumberTextField;
    public Rectangle explanationsRec;
    public TextField explanationsTextField;
    public Label explanationsLabel;
    public Pane discountCodeLabel;
    public Pane finalPayment;
    public Circle purchaseCircle;
    public Rectangle inProgressRec;
    public Label phoneNumberLabel;
    public Rectangle discountRec;
    public TextField discountTextField;
    public Label discountLabel;
    public Label priceToPayLabel;
    public Rectangle payRec;
    public Label payLabel;
    public Pane paymentPane;
    public Label backToCartLabel;
    public Rectangle backToCartRectangle;
    public Rectangle confirmPurchaseRectangle;
    public Label confirmPurchaseLabel;
    public Label purchaseLabel;
    public Button checkInformationButton;
    private Cart cart;
    private int rectangleIndex = 1;
    private int productIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productIndex = 0;
        rectangleIndex = 1;
        backToCartLabel.setDisable(true);
        backToCartLabel.setVisible(false);
        backToCartRectangle.setDisable(true);
        backToCartRectangle.setVisible(false);
        confirmPurchaseLabel.setDisable(false);
        confirmPurchaseLabel.setVisible(true);
        confirmPurchaseRectangle.setDisable(false);
        confirmPurchaseRectangle.setVisible(true);

        if (Controller.getCurrentUserType().equalsIgnoreCase("costumer")) {
            cart = ((Costumer) Controller.getCurrentUser()).getCart();
        } else if (Controller.getCurrentUser().getType().equalsIgnoreCase("guest")) {
            cart = ((Guest) Controller.getCurrentUser()).getCart();
        }
//        System.out.println(cart + " this is cart");
        //deepShitStuff();
        setMainPains();
        if (cartIsEmpty.isVisible()) return;
        setProductsGridPane();

        Controller.cancelSong();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startSong("src/main/resources/Sound/CartAndPurchase/BackGround.mp3");
            }
        }).start();

    }

    private void setProductsGridPane() {
        setVRectangles();
        setArrows();
        setProductsInProductBar();
    }

    private void setArrows() {
        upArrow.setDisable(true);
        upArrow.setVisible(false);
        downArrow.setVisible(false);
        downArrow.setDisable(true);
        if (cart.getProducts().size() > 3) {
            upArrow.setDisable(false);
            upArrow.setVisible(true);
            downArrow.setVisible(true);
            downArrow.setDisable(false);
        }
    }



    private void setVRectangles() {
        firstRec.setVisible(false);
        secondRec.setVisible(false);
        thirdRec.setVisible(false);

        if (rectangleIndex == 1) {
            firstRec.setVisible(true);
        } else if (rectangleIndex == 2) {
            secondRec.setVisible(true);
        } else if (rectangleIndex == 3) {
            thirdRec.setVisible(true);
        }
    }

    private void deepShitStuff() {
        Seller seller = (Seller) DataBase.getAllUsers().get(1);
        Product product = DataBase.getAllProducts().get(0);
        cart.addProduct(DataBase.getAllProducts().get(0), (Seller) DataBase.getAllUsers().get(1), 1);
        cart.addProduct(DataBase.getAllProducts().get(1), (Seller) DataBase.getAllUsers().get(1), 2);
        cart.addProduct(DataBase.getAllProducts().get(2), (Seller) DataBase.getAllUsers().get(1), 2);
        cart.addProduct(DataBase.getAllProducts().get(4), (Seller) DataBase.getAllUsers().get(1), 3);
        DataBase.getAllProducts().get(0).setDiscountPercentage(0.36);
        product.setOffPercentage(0.36);
        DataBase.getAllProducts().get(0).setDoesItHaveOff(true);
        DataBase.getAllProducts().get(0).setImageAddress("./photos/cart/run.png");
        DataBase.getAllProducts().get(1).setImageAddress("photos/cart/plus.png");
        DataBase.getAllProducts().get(2).setImageAddress("photos/cart/digikalaLogo.png");
        DataBase.getAllProducts().get(4).setImageAddress("photos/cart/digikalaLogo.png");
    }

    private void setMainPains() {
        isNotEmptyPane.setDisable(true);
        cartIsEmpty.setDisable(true);
        isNotEmptyPane.setVisible(false);
        cartIsEmpty.setVisible(false);
        if (cart.getProducts().size() == 0) {
            cartIsEmpty.setDisable(false);
            cartIsEmpty.setVisible(true);
        } else {
            isNotEmptyPane.setDisable(false);
            isNotEmptyPane.setVisible(true);
            cartProductsStatusPane.setDisable(false);
            cartProductsStatusPane.setVisible(true);
        }
    }

    public void gotoMainMenu(MouseEvent mouseEvent) {

    }

    public void seeMoreProducts(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        if (imageView.equals(upArrow)) {
            rectangleIndex--;
            if (rectangleIndex <= 0) rectangleIndex = 1;
            setVRectangles();
            if (rectangleIndex == 1) {
                productIndex--;
                if (productIndex < 0) {
                    productIndex = 0;
                    return;
                }
                setProductsGridPane();
            }

        } else {
            rectangleIndex++;
            if (rectangleIndex > 3) rectangleIndex = 3;
            setVRectangles();
            if (productIndex + 2 == cart.getProducts().size() - 1) {
                return;
            }
            if (rectangleIndex == 3) {
                productIndex++;
                if (productIndex == cart.getProducts().size()) {
                    productIndex = cart.getProducts().size() - 1;
                    return;
                }
                setProductsGridPane();
            }
        }
    }

    private void setProductsInProductBar() {
        resetMinus();
        discountPriceForProduct1.setVisible(false);
        discountPriceForProduct2.setVisible(false);
        discountPriceForProduct3.setVisible(false);
        secondProductBar.setVisible(false);
        firstProductBar.setVisible(false);
        product1.setDisable(true);
        product1.setVisible(false);
        product2.setDisable(true);
        product2.setVisible(false);
        product3.setDisable(true);
        product3.setVisible(false);
        for (int i = productIndex; i < productIndex + 3 && i < cart.getProducts().size(); i++) {
            Product product = cart.getProducts().get(i);
            Seller seller = cart.getSellers().get(i);
            double priceInit = 0;
            int items = cart.getItemsByProductId(product.getProductId(), seller);

            if (i == productIndex) {
                product1.setVisible(true);
                product1.setDisable(false);
                firstProductBar.setVisible(true);
                setProductsImage(productImage1, product.getImageAddress());
                productName1.setText(product.getName());
                companyName1.setText(seller.getCompanyName());
                finalPriceForProduct1.setText(String.valueOf(product.getPrice(seller)));
                numberOfProduct1.setText(String.valueOf(items));
                if (product.getDoesItHaveOff()) {
                    discountPriceForProduct1.setVisible(true);
                    double discount = product.getPrice(seller) * Integer.parseInt(numberOfProduct1.getText()) * (product.getOffPercentage() / (1.0 + product.getOffPercentage()));
                    int n = Math.round((float) discount);
                    discountPriceForProduct1.setText(String.valueOf(n));
                }
                if (items == 1) {
                    trash1.setVisible(true);
                } else if (items > 1) {
                    minus1.setVisible(true);
                }
                priceInit = product.getPrice(seller) * Integer.parseInt(numberOfProduct1.getText());
                finalPriceForProduct1.setText(String.valueOf(priceInit));

            } else if (i == productIndex + 1) {
                product2.setVisible(true);
                product2.setDisable(false);
                secondProductBar.setVisible(true);
                setProductsImage(productImage2, product.getImageAddress());
                productName2.setText(product.getName());
                companyName2.setText(seller.getCompanyName());
                numberOfProduct2.setText(String.valueOf(items));
                finalPriceForProduct2.setText(String.valueOf(product.getPrice(seller)));
                if (product.getDoesItHaveOff()) {
                    discountPriceForProduct2.setVisible(true);
                    double discount = product.getPrice(seller) * (product.getOffPercentage() / (1.0 + product.getOffPercentage()));
                    int n = Math.round((float) discount);
                    discountPriceForProduct2.setText(String.valueOf(n));
                }
                if (items == 1) {
                    trash2.setVisible(true);
                } else if (items > 1) {
                    minus2.setVisible(true);
                }
                priceInit = product.getPrice(seller) * Integer.parseInt(numberOfProduct2.getText());
                finalPriceForProduct2.setText(String.valueOf(priceInit));
            } else if (i == productIndex + 2) {
                product3.setVisible(true);
                product3.setDisable(false);
                setProductsImage(productImage3, product.getImageAddress());
                productName3.setText(product.getName());
                companyName3.setText(seller.getCompanyName());
                numberOfProduct3.setText(String.valueOf(items));
                finalPriceForProduct3.setText(String.valueOf(product.getPrice(seller)));
                if (product.getDoesItHaveOff()) {
                    discountPriceForProduct3.setVisible(true);
                    double discount = product.getPrice(seller) * (product.getOffPercentage() / (1.0 + product.getOffPercentage()));
                    int n = Math.round((float) discount);
                    discountPriceForProduct3.setText(String.valueOf(n));
                }
                if (items == 1) {
                    trash3.setVisible(true);
                } else if (items > 1) {
                    minus3.setVisible(true);
                }
                priceInit = product.getPrice(seller) * Integer.parseInt(numberOfProduct3.getText());
                finalPriceForProduct3.setText(String.valueOf(priceInit));
            }
        }
        totalDiscount.setText(String.valueOf(cart.calculateOffPrice()));
        totalPrice.setText(String.valueOf(cart.calculatePrice()));
        moneyMinusDiscount.setText(String.valueOf(cart.calculateFinalPrice()));
        finalPayNumber.setText(String.valueOf(cart.calculateFinalPrice()));
    }

    private void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);
        }
    }

    private void resetMinus() {
        minus1.setVisible(false);
        minus2.setVisible(false);
        minus3.setVisible(false);
        trash1.setVisible(false);
        trash2.setVisible(false);
        trash3.setVisible(false);
    }

    private void setProductsImage(ImageView image, String address) {
        try {
            image.setImage(new Image(address));
            centerImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void increaseProductNumber(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        if (imageView.equals(minus1) || imageView.equals(trash1)) {
            doIncAndDecCalculation(0, -1);
        } else if (imageView.equals(minus2) || imageView.equals(trash2)) {
            doIncAndDecCalculation(1, -1);
        } else if (imageView.equals(minus3) || imageView.equals(trash3)) {
            doIncAndDecCalculation(2, -1);
        } else if (imageView.equals(plus1)) {
            doIncAndDecCalculation(0, 1);
        } else if (imageView.equals(plus2)) {
            doIncAndDecCalculation(1, 1);
        } else if (imageView.equals(plus3)) {
            doIncAndDecCalculation(2, 1);
        }
    }

    private void doIncAndDecCalculation(int delta, int items) {
        Product product = cart.getProducts().get(productIndex + delta);
        Seller seller = cart.getSellers().get(cart.getProducts().indexOf(product));
        cart.addItem(items, product, seller);
        if (cart.getItemsByProductId(product.getProductId(), seller) == 0) {
            int index = cart.getProducts().indexOf(product);
            cart.removeItem(product, seller);
            rectangleIndex = 1;
            productIndex = 0;
        }
        setProductsGridPane();
        setMainPains();
    }

    public void gotoBuyMenu(MouseEvent mouseEvent) {
        cartProductsStatusPane.setVisible(false);
        cartProductsStatusPane.setDisable(true);
        paymentPane.setVisible(false);
        paymentPane.setDisable(true);
        restartButtonsForChangingPane();

        if (mouseEvent.getSource().equals(confirmPurchaseRectangle) || mouseEvent.getSource().equals(confirmPurchaseLabel)) {
            restartPaymentTextFields();
            paymentPane.setVisible(true);
            paymentPane.setDisable(false);

            backToCartLabel.setDisable(false);
            backToCartLabel.setVisible(true);
            backToCartRectangle.setDisable(false);
            backToCartRectangle.setVisible(true);
            inProgressRec.setStyle("-fx-fill: #00bfd6");
            purchaseCircle.setStyle("-fx-fill: #00bfd6");
            purchaseLabel.setStyle("-fx-text-fill: #00bfd6");

        } else if (mouseEvent.getSource().equals(backToCartLabel) || mouseEvent.getSource().equals(backToCartRectangle)) {
            cartProductsStatusPane.setVisible(true);
            cartProductsStatusPane.setDisable(false);

            confirmPurchaseLabel.setDisable(false);
            confirmPurchaseLabel.setVisible(true);
            confirmPurchaseRectangle.setDisable(false);
            confirmPurchaseRectangle.setVisible(true);

            inProgressRec.setStyle("-fx-fill: #959595");
            purchaseCircle.setStyle("-fx-fill: #959595");
            purchaseLabel.setStyle("-fx-text-fill: #959595");
        }

    }

    private void restartButtonsForChangingPane() {
        backToCartLabel.setDisable(true);
        backToCartLabel.setVisible(false);
        backToCartRectangle.setDisable(true);
        backToCartRectangle.setVisible(false);

        confirmPurchaseLabel.setDisable(true);
        confirmPurchaseLabel.setVisible(false);
        confirmPurchaseRectangle.setDisable(true);
        confirmPurchaseRectangle.setVisible(false);
    }


    private void restartPaymentTextFields() {
        phoneNumberTextField.clear();
        addressTextField.clear();
        explanationsTextField.clear();
        discountTextField.clear();
        priceToPayLabel.setText(String.valueOf(cart.calculateFinalPrice()));
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        restartTextFieldsWithRecs();

        if ((mouseEvent.getSource()).equals(addressTextField)) {
            addressLabel.setVisible(true);
            addressLabel.setTextFill(Color.valueOf("#1a73e8"));
            addressRec.setStrokeWidth(2.5);
            addressRec.setStroke(Color.valueOf("#1a73e8"));
        }
        if ((mouseEvent.getSource()).equals(phoneNumberTextField)) {
            phoneNumberLabel.setVisible(true);
            phoneNumberLabel.setTextFill(Color.valueOf("#1a73e8"));
            phoneNumberRec.setStrokeWidth(2.5);
            phoneNumberRec.setStroke(Color.valueOf("#1a73e8"));
        }
        if ((mouseEvent.getSource()).equals(explanationsTextField)) {
            explanationsLabel.setVisible(true);
            explanationsLabel.setTextFill(Color.valueOf("#1a73e8"));
            explanationsRec.setStrokeWidth(2.5);
            explanationsRec.setStroke(Color.valueOf("#1a73e8"));
        }
        if ((mouseEvent.getSource()).equals(discountTextField)) {
            discountLabel.setVisible(true);
            discountLabel.setTextFill(Color.valueOf("#1a73e8"));
            discountRec.setStrokeWidth(2.5);
            discountRec.setStroke(Color.valueOf("#1a73e8"));
        }

    }

    private void restartTextFieldsWithRecs() {
        Color gray = Color.valueOf("#7a7a7a");
        if (!addressTextField.getText().equals("")) {
            addressLabel.setVisible(true);
            addressLabel.setTextFill(gray);
        } else if (addressTextField.getText().equals("")) {
            addressLabel.setVisible(false);
        }
        if (!phoneNumberTextField.getText().equals("")) {
            phoneNumberLabel.setVisible(true);
            phoneNumberLabel.setTextFill(gray);
        } else if (phoneNumberTextField.getText().equals("")) {
            phoneNumberLabel.setVisible(false);
        }
        if (!explanationsTextField.getText().equals("")) {
            explanationsLabel.setVisible(true);
            explanationsLabel.setTextFill(gray);
        } else if (explanationsTextField.getText().equals("")) {
            explanationsLabel.setVisible(false);
        }
        if (!discountTextField.getText().equals("")) {
            discountLabel.setVisible(true);
            discountLabel.setTextFill(gray);
        } else if (discountTextField.getText().equals("")) {
            discountLabel.setVisible(false);
        }
        addressRec.setStrokeWidth(1.5);
        addressRec.setStroke(Color.valueOf("#7a7a7a"));
        addressTextField.selectEnd();
        addressTextField.deselect();

        phoneNumberRec.setStrokeWidth(1.5);
        phoneNumberRec.setStroke(Color.valueOf("#7a7a7a"));
        phoneNumberTextField.selectEnd();
        phoneNumberTextField.deselect();

        explanationsRec.setStrokeWidth(1.5);
        explanationsRec.setStroke(Color.valueOf("#7a7a7a"));
        explanationsTextField.selectEnd();
        explanationsTextField.deselect();

        discountRec.setStrokeWidth(1.5);
        discountRec.setStroke(Color.valueOf("#7a7a7a"));
        discountTextField.selectEnd();
        discountTextField.deselect();
    }

    private boolean validateData() {
        boolean answer = true;
        if (!phoneNumberTextField.getText().matches("\\d+")) {
            phoneNumberRec.setStrokeWidth(2.5);
            phoneNumberRec.setStroke(Color.valueOf("#fb3449"));
            phoneNumberTextField.selectEnd();
            phoneNumberTextField.deselect();

            phoneNumberLabel.setTextFill(Color.valueOf("#fb3449"));
            answer = false;
        }
        if (!addressTextField.getText().matches("(\\w|-)+")) {
            addressRec.setStrokeWidth(2.5);
            addressRec.setStroke(Color.valueOf("#fb3449"));
            addressTextField.selectEnd();
            addressTextField.deselect();

            addressLabel.setTextFill(Color.valueOf("#fb3449"));
            answer = false;
        }
        if (!explanationsTextField.getText().matches("(\\w|\\s)+")) {
            explanationsRec.setStrokeWidth(2.5);
            explanationsRec.setStroke(Color.valueOf("#fb3449"));
            explanationsTextField.selectEnd();
            explanationsTextField.deselect();

            explanationsLabel.setTextFill(Color.valueOf("#fb3449"));
            answer = false;
        }
        if (!isDiscountAvailable(discountTextField.getText())) {
            discountRec.setStrokeWidth(2.5);
            discountRec.setStroke(Color.valueOf("#fb3449"));
            discountTextField.selectEnd();
            discountTextField.deselect();

            discountLabel.setTextFill(Color.valueOf("#fb3449"));
            answer = false;
        }
        return answer;
    }

    private boolean checkDataForPay() {
        if (!validateData()) {
            payRec.setDisable(true);
            payRec.setOpacity(0.3);
            payRec.setStyle("-fx-cursor: default");
            payLabel.setStyle("-fx-cursor: default");
            return false;
        } else {
            payRec.setDisable(false);
            payRec.setOpacity(1);
            payRec.setStyle("-fx-cursor: hand");
            payLabel.setStyle("-fx-cursor: hand");
            return true;
        }
    }

    public boolean isDiscountAvailable(String discountCode) {
        if (discountCode.equals("")) {
            return true;
        }
        Costumer costumer = (Costumer) Controller.currentUser;
        if (((Costumer) Controller.currentUser).getDiscountCodeById(discountCode) == null) {
            return false;
        }
        if (costumer.getDiscountCodeById(discountCode).getStartDate().getTime() > new Date().getTime()) {
            return false;
        }
        if (costumer.getDiscountCodeById(discountCode).getEndDate().getTime() < new Date().getTime()) {
            costumer.removeDiscountCode(costumer.getDiscountCodeById(discountCode));
            return false;
        }
        return true;
    }

    //handle errors!!!
    public void Pay(MouseEvent mouseEvent) {
        String discountCode;
        if (discountTextField.getText().equals("")) {
            discountCode = "no";
        } else {
            discountCode = discountTextField.getText();
        }
        String payment = finishPayment(discountCode);
        if (payment.equals("your credit is not enough")) {

        }
        if (payment.contains("not available for count")) {

        }
        if (payment.equals("payment done")) {

        } else {

        }
    }

    public static String finishPayment(String discountCode) {
        Costumer costumer = (Costumer) Controller.currentUser;
        Cart cart = costumer.getCart();
        Set<Seller> sellers = new HashSet<>(cart.getSellers());
        int discount = 0;
        if (!discountCode.equals("no")) {
            discount = costumer.getDiscountCodeById(discountCode).getDiscountPercent();
        }
        if ((getTotalPrice() * (1 - discount / 100)) > costumer.getCredit()) {
            return "your credit is not enough";
        }
        for (Seller seller : sellers) {
            double paidAmount = 0;
            double reducedAmountForOff = 0;
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 0; i < cart.getSellers().size(); i++) {
                if (cart.getSellers().get(i).equals(seller)) {
                    paidAmount += (cart.getProducts().get(i).getPrice(seller) * cart.getItemsByProductId(cart.getProducts().get(i).getProductId(), seller));
                    products.add(cart.getProducts().get(i));
                }
            }
            for (Product product : products) {
                if (product.remainingProductForSeller(seller) < cart.getItemsByProductId(product.getProductId(), seller)) {
                    return product.getName() + " for seller " + seller.getUsername()
                            + " not available for count " + cart.getItemsByProductId(product.getProductId(), seller);
                }
                product.addAvailableItemsForSeller(seller, cart.getItemsByProductId(product.getProductId(), seller) * -1);
                int offPercent = 0;
                for (Off off : seller.getOffs()) {
                    if (off.getProducts().contains(product)) {
                        offPercent = off.getOffAmount();
                    }
                }
                reducedAmountForOff += (product.getPrice(seller) / (1 - offPercent / 100)) - (product.getPrice(seller));
            }
            costumer.addBuyLog(new BuyLog(paidAmount * (1 - discount / 100), discount, products
                    , seller.getUsername(), DataBase.getLogId(), new Date()));
            seller.addSellLog(new SellLog(paidAmount, reducedAmountForOff, products, costumer.getUsername(), DataBase.getLogId(), new Date()));
            seller.addCredit(getTotalPrice());
        }
        String answer = "payment done";
        if (getTotalPrice() > 1000000) {
            answer += "\ngift discount code activated for your account";
            ArrayList<Costumer> allowed = new ArrayList<>();
            allowed.add(costumer);
            costumer.addDiscountCode(new DiscountCode("monthly gift"
                    , new Date(), new Date(2592000000L + new Date().getTime()), 10
                    , 50000, 2, allowed));
        }
        costumer.addCredit((getTotalPrice() * -1) * (1 - (discount / 100)));
        costumer.setCart(new Cart());
        return answer;
    }

    public static double getTotalPrice() {
        Cart cart = ((Costumer) Controller.currentUser).getCart();
        double totalPrice = 0;
        for (int i = 0; i < cart.getProducts().size(); i++) {
            totalPrice += (cart.getProducts().get(i).getPrice(cart.getSellers().get(i)) * cart.getItemsByProductId(cart.getProducts().get(i).getProductId(), cart.getSellers().get(i)));
        }
        return totalPrice;
    }

    public void checkInformation(ActionEvent event) {
        restartTextFieldsWithRecs();
        checkDataForPay();
    }

    public void goBackToLastPane(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((ImageView) mouseEvent.getSource()).getScene();
        Controller.setCurrentPane(Controller.getLastPane());
        scene.setRoot(Controller.getLastPane());
        stage.setScene(scene);
        stage.show();
    }
}
