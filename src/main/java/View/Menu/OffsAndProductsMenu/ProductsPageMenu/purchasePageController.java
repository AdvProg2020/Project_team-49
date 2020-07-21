package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import View.View;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class purchasePageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        View.client.cancelSong();
        View.client.startSong("src/main/resources/Sound/PurchasePage/BackGround.mp3");
    }
}
