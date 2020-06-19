package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import Controller.DataBase;
import Models.Product;
import Models.User.Cart;
import Models.User.Costumer;
import Models.User.Guest;
import Models.User.Seller;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Cart cart;
    private int rectangleIndex = 1;
    private int productIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productIndex = 0;
        rectangleIndex = 1;
        if (Controller.getCurrentUserType().equalsIgnoreCase("costumer")) {
            cart = ((Costumer) Controller.getCurrentUser()).getCart();
        } else if (Controller.getCurrentUser().getType().equalsIgnoreCase("guest")) {
            cart = ((Guest) Controller.getCurrentUser()).getCart();
        }
        deepShitStuff();
        setMainPains();
        if (cartIsEmpty.isVisible()) return;

        setProductsGridPane();
    }

    private void setProductsGridPane() {
        setVRectangles();
        setArrows();
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
        cart.addProduct(DataBase.getAllProducts().get(0), (Seller) DataBase.getAllUsers().get(1), 2);
        cart.addProduct(DataBase.getAllProducts().get(1), (Seller) DataBase.getAllUsers().get(6), 2);
        cart.addProduct(DataBase.getAllProducts().get(2), (Seller) DataBase.getAllUsers().get(1), 2);
        cart.addProduct(DataBase.getAllProducts().get(4), (Seller) DataBase.getAllUsers().get(6), 2);
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


        } else {
            rectangleIndex++;
            if (rectangleIndex > 3) rectangleIndex = 3;
            setVRectangles();

        }
    }


    private void setProductsInProductBar() {
        product1.setDisable(true);
        product1.setVisible(false);
        product2.setDisable(true);
        product2.setVisible(false);
        product3.setDisable(true);
        product3.setVisible(false);
        for (int i = productIndex; i < productIndex + 3 && i < cart.getProducts().size(); i++) {
            Product product = cart.getProducts().get(i);
            if (i == productIndex) {
                product1.setVisible(true);
                product1.setDisable( false);
            } else if (i == productIndex + 1) {
                product2.setVisible(true);
                product2.setDisable( false);
            } else if (i == productIndex + 2) {
                product3.setVisible(true);
                product3.setDisable( false);
            }
        }
    }

    public void increaseProductNumber(MouseEvent mouseEvent) {
    }
}
