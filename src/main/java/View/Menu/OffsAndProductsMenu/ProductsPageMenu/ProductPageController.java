package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {


    public Label averageScoreNumber;
    public Label addressOfProduct;
    public ImageView emptyStar1;
    public ImageView halfStar1;
    public ImageView fullStar1;
    public ImageView emptyStar2;
    public ImageView halfStar2;
    public ImageView fullStar2;
    public ImageView emptyStar3;
    public ImageView halfStar3;
    public ImageView fullStar3;
    public ImageView emptyStar4;
    public ImageView halfStar4;
    public ImageView fullStar4;
    public ImageView emptyStar5;
    public ImageView halfStar5;
    public ImageView fullStar5;
    public Label scoresNumber;
    public Rectangle filledBar5;
    public Rectangle emptyBar5;
    public Rectangle filledBar4;
    public Rectangle filledBar3;
    public Rectangle filledBar2;
    public Rectangle filledBar1;
    public Rectangle emptyBar4;
    public Rectangle emptyBar3;
    public Rectangle emptyBar2;
    public Rectangle emptyBar1;
    private ArrayList<ImageView> stars = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stars.add(emptyStar1);
        stars.add(emptyStar2);
        stars.add(emptyStar3);
        stars.add(emptyStar4);
        stars.add(emptyStar5);
        stars.add(halfStar1);
        stars.add(halfStar2);
        stars.add(halfStar3);
        stars.add(halfStar4);
        stars.add(halfStar5);
        stars.add(fullStar1);
        stars.add(fullStar2);
        stars.add(fullStar3);
        stars.add(fullStar4);
        stars.add(fullStar5);
       
    }
}
