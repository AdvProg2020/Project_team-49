package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import Models.Product;
import Models.Score;
import Models.User.Guest;
import Models.User.User;
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
    private Product product = Controller.getSelectedProduct();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product.addScore(new Score(new Guest(), 4.6, product));
        product.addScore(new Score(new Guest(), 1.6, product));
        product.addScore(new Score(new Guest(), 2.6, product));
        product.addScore(new Score(new Guest(), .6, product));
        product = Controller.getSelectedProduct();
        product.setAverageScore(4.8);
        setScoreLabels();
        setStars();
    }

    private void setScoreLabels() {
        String averageScore = String.valueOf(product.getAverageScore());
        averageScore = averageScore.substring(0, 3);
        averageScoreNumber.setText(String.valueOf(averageScore));
        scoresNumber.setText(String.valueOf(product.getAllScores().size()));
    }

    private void setStars() {
        double n = product.getAverageScore();
        emptyStar1.toFront();
        emptyStar2.toFront();
        emptyStar3.toFront();
        emptyStar4.toFront();
        emptyStar5.toFront();
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
        if (n < 0.5) return;
        if (n >= 0.5 && n < 1.0) {
            halfStar1.toFront();
            return;
        }
        fullStar1.toFront();
        if (n >= 1 && n < 1.5) return;
        if (n >= 1.5 && n < 2.0) {
            halfStar2.toFront();
            return;
        }
        fullStar2.toFront();
        if (n >= 2 && n < 2.5) return;
        if (n >= 2.5 && n < 3.0) {
            halfStar3.toFront();
            return;
        }
        fullStar3.toFront();
        if (n >= 3 && n < 3.5) return;
        if (n >= 3.5 && n < 4.0) {
            halfStar4.toFront();
            return;
        }
        fullStar4.toFront();
        if (n >= 4 && n < 4.5) return;
        if (n >= 4.5 && n < 5.0) {
            halfStar5.toFront();
            return;
        }
        fullStar5.toFront();
    }
}
