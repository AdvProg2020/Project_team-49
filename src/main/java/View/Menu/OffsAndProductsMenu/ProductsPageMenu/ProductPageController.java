package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import Controller.CostumerAreaController;
import Controller.OffAndProductMenuController;
import Controller.DataBase;
import Models.Category;
import Models.Comment;
import Models.Product;
import Models.Score;
import Models.User.Costumer;
import Models.User.Guest;
import Models.User.Seller;
import Models.User.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
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
    public ScrollPane mainScrollPane;
    public GridPane starsPane;
    public ImageView offersBanner;
    public ImageView productImage;
    public Label productName;
    public Label discussionNumber;
    public Label productExplanation;
    public Label brandLabel;
    public Label CategoryLabel;
    public Label categoryLabel;
    public Label remainingItems;
    public ImageView rightArrow;
    public ImageView submitCommentButton;
    public ImageView nextComments;
    public Pane seeMoreCommentsPane;
    public TextField userComment;
    public Label usernameFirstCharLabel0;
    public Circle firstCharacterCircle0;
    public Pane yourComment;
    public Rectangle neverBoughtBar2;
    public Label userNameLabel2;
    public Label usernameFirstCharLabel2;
    public Circle firstCharacterCircle2;
    public Label commentContent2;
    public Pane comment2;
    public Rectangle neverBoughtBar3;
    public Label userNameLabel3;
    public Label usernameFirstCharLabel3;
    public Circle firstCharacterCircle3;
    public Label commentContent3;
    public Rectangle neverBoughtBar4;
    public Pane comment3;
    public Label userNameLabel4;
    public Label usernameFirstCharLabel4;
    public Circle firstCharacterCircle4;
    public Label commentContent4;
    public Pane comment4;
    public Rectangle neverBoughtBar1;
    public Label userNameLabel1;
    public Label usernameFirstCharLabel1;
    public Circle firstCharacterCircle1;
    public Label commentContent1;
    public Pane comment1;
    public Label thanksForYourComment;
    public Label loginFirstForComment;
    public Rectangle commentRectangle;
    public Pane yourRatingPane;
    private int messageTime = 0;
    private Random random = new Random();
    public Label discountPercentage11;
    public Pane unavailablePane;
    public ImageView isAvailableImage;
    public ImageView notAvailableImage;

    public Label oldPrice;
    public Pane offerPane;
    public Label finalPrice;
    public Label discountPercentage;
    public Pane availablePane;
    public Pane buyPane;
    public Label sellerNameLabel;
    public Rectangle unavailableButton;
    public Label unavailableLabel;
    public Pane totalUnavailablePane;
    public Rectangle totalUnavailableButton;
    public Label discountPercentage111;
    public Label totalUnavailableLabel;
    public Rectangle addToCartRectangle;
    public Label addToCartLabel;
    public GridPane rateStarPane;
    public ImageView halfLeft1;
    public ImageView halfLeft5;
    public ImageView halfLeft4;
    public ImageView halfLeft3;
    public ImageView halfLeft2;
    public ImageView halfRight1;
    public ImageView halfRight5;
    public ImageView halfRight4;
    public ImageView halfRight3;
    public ImageView halfRight2;
    public Label buyProductFirstLabel;
    public ImageView submitImage;
    public Label submitDoneLabel;
    public ImageView leftGreen1;
    public ImageView leftGreen5;
    public ImageView leftGreen4;
    public ImageView leftGreen3;
    public ImageView leftGreen2;
    public ImageView rightGreen1;
    public ImageView rightGreen5;
    public ImageView rightGreen4;
    public ImageView rightGreen3;
    public ImageView rightGreen2;
    private int commentsIndex = 0;
    private ArrayList<ImageView> stars = new ArrayList<>();
    private ArrayList<ImageView> rateStars = new ArrayList<>();
    private ArrayList<ImageView> rateBar = new ArrayList<>();

    private Product product;
    private double score;
    private int t;
    private static int sellerIndex;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        product = Controller.getSelectedProduct();
        sellerIndex = 0;
        commentsIndex = 0;
        t = 0;
        score = 0;
//        doSomeDeepShit();
        Controller.setCurrentUser(DataBase.getAllUsers().get(0));
//        product.setDoesItHaveOff(true);
        System.out.println(product.getImageAddress());
        setYourRate();
        setRatingStuff();
        restartRateBar();
        setUnavailableLabels();
        setProductAttributes();
        setStars();
        setScoreLabels();
        //reverse Order Comments
        setCommentPane();
        refreshScoreBar();

        Controller.cancelSong();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startSong("src/main/resources/Sound/ProductsPage/BackGround.mp3");
            }
        }).start();

    }

    private void setYourRate() {
        boolean flag = false;
        double n = 0;
        for (Score score : product.getAllScores()) {
            if (score.getBuyer().equals(Controller.getCurrentUser())) {
                rateStarPane.setDisable(true);
                rateStarPane.setOpacity(0.6);
                flag = true;
                n = score.getScore();
                break;
            }
        }
        if (flag) {
            if (n >= 0.5 && n < 1.0) {
                leftGreen1.toFront();
                return;
            }
            rightGreen1.toFront();
            if (n >= 1 && n < 1.5) return;
            if (n >= 1.5 && n < 2.0) {
                leftGreen2.toFront();
                return;
            }
            rightGreen2.toFront();
            if (n >= 2 && n < 2.5) return;
            if (n >= 2.5 && n < 3.0) {
                leftGreen3.toFront();
                return;
            }
            rightGreen3.toFront();
            if (n >= 3 && n < 3.5) return;
            if (n >= 3.5 && n < 4.0) {
                leftGreen4.toFront();
                return;
            }
            rightGreen4.toFront();
            if (n >= 4 && n < 4.5) return;
            if (n >= 4.5 && n < 5.0) {
                leftGreen5.toFront();
                return;
            }
            rightGreen5.toFront();
        }
    }

    private void doSomeDeepShit() {
        product.addScore(new Score(new Guest(), 0, product));
        product.addScore(new Score(new Guest(), 1.6, product));
        product.addScore(new Score(new Guest(), 0.6, product));
        product.addScore(new Score(new Guest(), 2.6, product));
        product.addScore(new Score(new Guest(), 4.6, product));
        product.addScore(new Score(new Guest(), 5, product));
        product.addScore(new Score(new Guest(), 3.6, product));
        product.addScore(new Score(new Guest(), 4.6, product));
        product.addScore(new Score(new Guest(), 4.6, product));
        product.addScore(new Score(new Guest(), 4.6, product));
        product.addScore(new Score(new Guest(), 2.6, product));
        product.addScore(new Score(new Guest(), 4.6, product));

        product.addAComment(new Comment(DataBase.getAllUsers().get(2), product, "", "it was shit"));
        product.addAComment(new Comment(DataBase.getAllUsers().get(3), product, "", "it was deep "));
        product.addAComment(new Comment(DataBase.getAllUsers().get(1), product, "", "it was fuck shit"));
        product.addAComment(new Comment(DataBase.getAllUsers().get(4), product, "", "deep shit"));

        product.addAComment(new Comment(DataBase.getAllUsers().get(5), product, "", "it was shit"));
        product.addAComment(new Comment(DataBase.getAllUsers().get(3), product, "", "it was deep "));
        product.addAComment(new Comment(DataBase.getAllUsers().get(5), product, "", "it was shit"));
        product.addAComment(new Comment(DataBase.getAllUsers().get(3), product, "", "it was deep "));
        product.addAComment(new Comment(DataBase.getAllUsers().get(6), product, "", "it was fuck shit"));
        product.addAComment(new Comment(DataBase.getAllUsers().get(5), product, "", "deep shit"));
        product.addAComment(new Comment(DataBase.getAllUsers().get(1), product, "", "it shit"));

    }

    private void setRatingStuff() {
        rateStars.add(leftGreen1);
        rateStars.add(leftGreen2);
        rateStars.add(leftGreen3);
        rateStars.add(leftGreen4);
        rateStars.add(leftGreen5);
        rateStars.add(rightGreen1);
        rateStars.add(rightGreen2);
        rateStars.add(rightGreen3);
        rateStars.add(rightGreen4);
        rateStars.add(rightGreen5);

        rateBar.add(halfLeft1);
        rateBar.add(halfLeft2);
        rateBar.add(halfLeft3);
        rateBar.add(halfLeft4);
        rateBar.add(halfLeft5);
        rateBar.add(halfRight1);
        rateBar.add(halfRight2);
        rateBar.add(halfRight3);
        rateBar.add(halfRight4);
        rateBar.add(halfRight5);
    }

    private void restartRateBar() {
        for (ImageView imageView : rateStars) {
            imageView.toBack();
        }
    }

    private void setUnavailableLabels() {
        unavailableLabel.setStyle("-fx-text-alignment: center");
        unavailableLabel.setText("Unfortunately, this product is" + '\n' + '\n' + "not currently available.");
        if (product.getDoesItHaveDiscount() || product.getDoesItHaveOff()) {
            offersBanner.setVisible(true);
        }
        sellerIndex = 0;
        if (product.remainingItems() == 0) {
            totalUnavailablePane.setDisable(false);
            totalUnavailablePane.setVisible(true);
            totalUnavailableLabel.setStyle("-fx-text-alignment: center");
            totalUnavailableLabel.setText("Unfortunately, this product is" + '\n' + '\n' + "not currently available.");
        } else {
            setBuyPane();
        }
    }

    private void setArrow() {
        if (product.getAllSellers().size() > 1) {
            rightArrow.setDisable(false);
            rightArrow.setVisible(true);
            rightArrow.toFront();
        }
    }

    public void setBuyPane() {
        restartBuyPane();
        setArrow();
        if (sellerIndex == product.getAllSellers().size()) sellerIndex = 0;
        Seller seller = product.getAllSellers().get(sellerIndex++);
        sellerNameLabel.setText(seller.getCompanyName());
        if (product.remainingProductForSeller(seller) == 0) {
            isAvailableImage.setDisable(true);
            isAvailableImage.setVisible(false);
            notAvailableImage.setDisable(false);
            notAvailableImage.setVisible(true);
            unavailablePane.setDisable(false);
            unavailablePane.setVisible(true);
        } else {
            notAvailableImage.setVisible(false);
            notAvailableImage.setDisable(true);
            isAvailableImage.setDisable(false);
            isAvailableImage.setVisible(true);
            availablePane.setDisable(false);
            availablePane.setVisible(true);
            finalPrice.setText(String.valueOf(product.getPrice(seller)));
            if (product.getDoesItHaveOff()) {
                offerPane.setDisable(false);
                offerPane.setVisible(true);
                oldPrice.setText(String.valueOf(product.getPrice(seller)));
                String discountPercent = String.valueOf(product.getDiscountPercentage());
                discountPercent = discountPercent.substring(0, 2);
                if (discountPercent.charAt(discountPercent.length() - 1) == '.') {
                    discountPercent = discountPercent.substring(0, discountPercent.length() - 1);
                }
                discountPercent = discountPercent.concat("%");
                discountPercentage.setText(discountPercent);
            }
        }
        buyPane.setDisable(false);
        buyPane.setVisible(true);
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

    private void restartBuyPane() {
        rightArrow.setVisible(false);
        rightArrow.setDisable(true);
        isAvailableImage.setVisible(false);
        isAvailableImage.setDisable(true);
        notAvailableImage.setVisible(false);
        notAvailableImage.setDisable(true);
        offerPane.setVisible(false);
        unavailablePane.setDisable(true);
        unavailablePane.setVisible(false);
        availablePane.setDisable(true);
        availablePane.setVisible(false);
    }

    private void setProductAttributes() {
        int m = 9679;
        char M = (char) m;
        productExplanation.setText(M + " " + product.getExplanation());
        productName.setText(product.getName());
        brandLabel.setText(product.getBrand());
        try {
            categoryLabel.setText(product.getParentCategory().getName());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        discussionNumber.setText(String.valueOf(product.getAllComments().size()));
        setProductsImage();
        setAddressOfProduct();
    }

    private void setProductsImage() {
        Image image=new Image(product.getImageAddress());
        productImage.setImage(image);

        remainingItems.setText(String.valueOf(product.remainingItems()));
    }

    private void setAddressOfProduct() {
        ArrayList<String> address = new ArrayList<>();
        address.add(product.getName());
        Category category = product.getParentCategory();
        while (category != null) {
            address.add(category.getName());
            category = category.getParentCategory();
        }
        String finalAddress = "";
        for (int i = 0; i < address.size(); i++) {
            finalAddress = finalAddress + address.get(address.size() - 1 - i);
            if (i != address.size() - 1) {
                finalAddress = finalAddress + "\\";
            }
        }
        finalAddress = finalAddress.concat("\\" + product.getName());
        addressOfProduct.setText(finalAddress);
        address.clear();
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

    public void refreshScoreBar() {
        filledBar1.toBack();
        filledBar2.toBack();
        filledBar3.toBack();
        filledBar4.toBack();
        filledBar5.toBack();
        emptyBar1.toFront();
        emptyBar2.toFront();
        emptyBar3.toFront();
        emptyBar4.toFront();
        emptyBar5.toFront();
        filledBar1.setWidth(0);
        filledBar2.setWidth(0);
        filledBar3.setWidth(0);
        filledBar4.setWidth(0);
        filledBar5.setWidth(0);
        int total = product.getAllScores().size();
        int one = 0, two = 0, three = 0, four = 0, five = 0;
        for (Score score : product.getAllScores()) {
            double n = score.getScore();
            if (n >= 0 && n < 1) {
                one++;
            } else if (n >= 1 && n < 2) {
                two++;
            } else if (n >= 2 && n < 3) {
                three++;
            } else if (n >= 3 && n < 4) {
                four++;
            } else if (n >= 4 && n <= 5) {
                five++;
            }
        }
        double fraction1 = (1.0 * one) / (1.0 * total);
        double fraction2 = (1.0 * two) / (1.0 * total);
        double fraction3 = (1.0 * three) / (1.0 * total);
        double fraction4 = (1.0 * four) / (1.0 * total);
        double fraction5 = (1.0 * five) / (1.0 * total);

        double width1 = emptyBar1.getWidth() * fraction1;
        double width2 = emptyBar1.getWidth() * fraction2;
        double width3 = emptyBar1.getWidth() * fraction3;
        double width4 = emptyBar1.getWidth() * fraction4;
        double width5 = emptyBar1.getWidth() * fraction5;

        filledBar1.toFront();
        filledBar2.toFront();
        filledBar3.toFront();
        filledBar4.toFront();
        filledBar5.toFront();
        double a1 = fraction1 + fraction2 + fraction3 + fraction4 + fraction5;
        t = 0;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> runBars(width1 / 80, width2 / 80, width3 / 80, width4 / 80, width5 / 80)));
        timeline.setCycleCount(80);
        timeline.play();
    }

    private void runBars(double width1, double width2, double width3, double width4, double width5) {
        if (t == 81) return;
        filledBar1.setWidth(width1 * t);
        filledBar2.setWidth(width2 * t);
        filledBar3.setWidth(width3 * t);
        filledBar4.setWidth(width4 * t);
        filledBar5.setWidth(width5 * t);
        t += 1;
    }

    public void changeUnAvailableButtonColor(MouseEvent mouseEvent) {

        Rectangle rectangle = (Rectangle) mouseEvent.getSource();
        rectangle.setFill(Color.valueOf("#959595"));
    }

    public void exitUnavailableButton(MouseEvent mouseEvent) {
        Rectangle rectangle = (Rectangle) mouseEvent.getSource();
        rectangle.setFill(Color.valueOf("#7a7a7a"));
    }

    public void addProductToCart(MouseEvent mouseEvent) {

    }

    public void rate(MouseEvent mouseEvent) {
        restartRateBar();
        ImageView imageView = (ImageView) mouseEvent.getSource();

        leftGreen1.toFront();
        score = 0.5;
        if (imageView.equals(halfLeft1) || imageView.equals(leftGreen1)) return;

        rightGreen1.toFront();
        score = 1.0;
        if (imageView.equals(halfRight1) || imageView.equals(rightGreen1)) return;

        leftGreen2.toFront();
        score = 1.5;
        if (imageView.equals(halfLeft2) || imageView.equals(leftGreen2)) return;

        rightGreen2.toFront();
        score = 2.0;
        if (imageView.equals(halfRight2) || imageView.equals(rightGreen2)) return;

        leftGreen3.toFront();
        score = 2.5;
        if (imageView.equals(halfLeft3) || imageView.equals(leftGreen3)) return;

        rightGreen3.toFront();
        score = 3.0;
        if (imageView.equals(halfRight3) || imageView.equals(rightGreen3)) return;

        leftGreen4.toFront();
        score = 3.5;
        if (imageView.equals(halfLeft4) || imageView.equals(leftGreen4)) return;

        rightGreen4.toFront();
        score = 4.0;
        if (imageView.equals(halfRight4) || imageView.equals(rightGreen4)) return;

        leftGreen5.toFront();
        score = 4.5;
        if (imageView.equals(halfLeft5) || imageView.equals(leftGreen5)) return;

        rightGreen5.toFront();
        score = 5.0;
        if (imageView.equals(halfRight5) || imageView.equals(rightGreen5)) return;
    }

    public void submitRate(MouseEvent mouseEvent) {
        if (rateStarPane.isDisable()) return;
        rateStarPane.setOpacity(0.6);
        messageTime = 0;
        rateStarPane.setDisable(true);
        if (!canRate(product.getProductId())) {
            buyProductFirstLabel.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> showBuyProductMessage()));
            timeline.setCycleCount(3);
            timeline.play();

        } else {
            CostumerAreaController.rate(product.getProductId(), score);
            submitDoneLabel.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> showDoneMessage(submitDoneLabel)));
            timeline.setCycleCount(3);
            timeline.play();
        }

    }

    private boolean canRate(long productId) {
        if (Controller.getCurrentUserType().equals("Guest") || Controller.getCurrentUserType().equals("Manager")) {
            return false;
        } else {
            if (!CostumerAreaController.canRate(productId)) {
                return false;
            } else {
                return true;
            }
        }
    }

    private void showDoneMessage(Label label) {
        messageTime++;
        if (messageTime == 3) {
            label.setVisible(false);
        }
    }

    private void showBuyProductMessage() {
        messageTime++;
        if (messageTime == 3) {
            buyProductFirstLabel.setVisible(false);
        }
    }

    public void seeMoreComments(MouseEvent mouseEvent) {
        commentsIndex += 4;
        if (commentsIndex >= product.getAllComments().size()) {
            commentsIndex = 0;
        }
        setAllComments();
    }

    public void submitComment(MouseEvent mouseEvent) {
        String message = userComment.getText();
        if (message.equalsIgnoreCase("")) return;
        userComment.clear();
        if (Controller.getCurrentUserType().equalsIgnoreCase("guest")) {
            loginFirstForComment.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> showDoneMessage(loginFirstForComment)));
            timeline.setCycleCount(3);
            timeline.play();

        } else {
            OffAndProductMenuController.addCommentsById(product.getProductId(), "title", userComment.getText());
            yourComment.setDisable(true);
            yourComment.setVisible(false);
            submitCommentButton.setDisable(true);
            submitCommentButton.setVisible(false);
            thanksForYourComment.setVisible(true);
            setAllComments();
        }
    }

    private void setCommentPane() {
        disableAllCommentPanes();
        setAllComments();
    }

    private void disableAllCommentPanes() {
        loginFirstForComment.setVisible(false);
        comment1.setVisible(false);
        comment2.setVisible(false);
        comment3.setVisible(false);
        comment4.setVisible(false);
        comment1.setDisable(true);
        comment2.setDisable(true);
        comment3.setDisable(true);
        comment4.setDisable(true);
        yourComment.setVisible(false);
        yourComment.setDisable(true);
        thanksForYourComment.setVisible(false);
        submitCommentButton.setDisable(true);
        submitCommentButton.setVisible(false);
        seeMoreCommentsPane.setDisable(true);
        seeMoreCommentsPane.setVisible(false);
        commentRectangle.setVisible(false);
    }

    private void setAllComments() {
        disableAllCommentPanes();
        thanksForYourComment.setVisible(false);
        yourComment.setVisible(true);
        yourComment.setDisable(false);
        submitCommentButton.setVisible(true);
        submitCommentButton.setDisable(false);
        setCommentRectangle();
        usernameFirstCharLabel0.setText(String.valueOf(Controller.getCurrentUser().getUsername().charAt(0)));
        for (Comment comment : product.getAllComments()) {
            if (comment.getUserWhoComment().equals(Controller.getCurrentUser())) {
                yourComment.setDisable(true);
                yourComment.setVisible(false);
                thanksForYourComment.setVisible(true);
                submitCommentButton.setVisible(false);
                submitCommentButton.setDisable(true);
            }
        }
        for (int i = commentsIndex; i < commentsIndex + 4 && i < product.getAllComments().size(); i++) {
            Comment comment = product.getAllComments().get(i);
            if (i == commentsIndex) {
                comment1.setVisible(true);
                comment1.setDisable(false);
                userNameLabel1.setText(comment.getUserWhoComment().getUsername());
                firstCharacterCircle1.setFill(generateRandomColor());
                usernameFirstCharLabel1.setText(String.valueOf(comment.getUserWhoComment().getUsername().charAt(0)));
                commentContent1.setText(comment.getNote());
                neverBoughtBar1.setVisible(true);
                if (comment.isUserBuyThisProduct()) {
                    neverBoughtBar1.setVisible(false);
                }
            } else if (i == commentsIndex + 1) {
                comment2.setVisible(true);
                comment2.setDisable(false);
                userNameLabel2.setText(comment.getUserWhoComment().getUsername());
                firstCharacterCircle2.setFill(generateRandomColor());
                usernameFirstCharLabel2.setText(String.valueOf(comment.getUserWhoComment().getUsername().charAt(0)));
                commentContent2.setText(comment.getNote());
                neverBoughtBar2.setVisible(true);
                if (comment.isUserBuyThisProduct()) {
                    neverBoughtBar2.setVisible(false);
                }

            } else if (i == commentsIndex + 2) {
                comment3.setVisible(true);
                comment3.setDisable(false);

                userNameLabel3.setText(comment.getUserWhoComment().getUsername());

                firstCharacterCircle3.setFill(generateRandomColor());
                usernameFirstCharLabel3.setText(String.valueOf(comment.getUserWhoComment().getUsername().charAt(0)));
                commentContent3.setText(comment.getNote());
                neverBoughtBar3.setVisible(true);
                if (comment.isUserBuyThisProduct()) {
                    neverBoughtBar3.setVisible(false);
                }

            } else if (i == commentsIndex + 3) {
                comment4.setVisible(true);
                comment4.setDisable(false);
                userNameLabel4.setText(comment.getUserWhoComment().getUsername());
                firstCharacterCircle4.setFill(generateRandomColor());
                usernameFirstCharLabel4.setText(String.valueOf(comment.getUserWhoComment().getUsername().charAt(0)));
                commentContent4.setText(comment.getNote());
                neverBoughtBar4.setVisible(true);
                if (comment.isUserBuyThisProduct()) {
                    neverBoughtBar4.setVisible(false);
                }
            }
        }
        if (product.getAllComments().size() > 4) {
            seeMoreCommentsPane.setDisable(false);
            seeMoreCommentsPane.setVisible(true);
        }
    }

    private Color generateRandomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    private void setCommentRectangle() {
        commentRectangle.setVisible(true);
        int n = product.getAllComments().size();
        if (n > commentsIndex + 3) {
            commentRectangle.setHeight(400);
        } else if (n > commentsIndex + 2) {
            commentRectangle.setHeight(290);
        } else if (n > commentsIndex + 1) {
            commentRectangle.setHeight(180);
        } else {
            commentRectangle.setHeight(80);
        }
    }

    public void goBackToLastPaneFromProductPage(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((ImageView) mouseEvent.getSource()).getScene();
        Controller.setCurrentPane(Controller.getLastPane());
        scene.setRoot(Controller.getCurrentPane());
        stage.setScene(scene);
        stage.show();
    }
}
