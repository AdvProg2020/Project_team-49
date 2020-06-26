package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class purchasePageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Controller.cancelSong();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startSong("src/main/resources/Sound/PurchasePage/BackGround.mp3");
            }
        }).start();
    }
}
