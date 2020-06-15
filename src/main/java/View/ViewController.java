package View;

import Controller.DataBase;
import Models.Category;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    public ImageView digikalaImage;
    public TextField searchField;
    public ImageView lightRedButton;
    public ImageView lightOrangeButton;
    public ImageView lightWhiteButton;
    public ImageView lightBlueButton;
    public ImageView lightGrayButton;
    public ImageView lightGreenButton;
    public AnchorPane innerPane;
    public AnchorPane mainPane;
    public ImageView costumerAreaButton;
    public Rectangle productsRectangle;
    public Rectangle offsRectangle;
    public ImageView secondMovingPicture;
    public ImageView thirdMovingPicture;
    public ImageView firstMovingPicture;
    public ImageView rightArrow;
    public ImageView leftArrow;
    public GridPane bulletsPane;
    public ImageView secondWhiteBullet;
    public ImageView firstWhiteBullet;
    public ImageView thirdWhiteBullet;
    public ImageView firstBlueBullet;
    public ImageView secondBlueBullet;
    public ImageView thirdBlueBullet;
    public Pane upBarPane;
    public Pane movingPicturesPane;
    private static int timeForChangeImages = 0;
    public ImageView colorWheel;
    public ImageView muteButton;
    public ImageView playMusicButton;
    public GridPane colorsPane;
    public GridPane settingPane;
    public ImageView settingButton;
    public ImageView searchButton;
    public Label offersLabel;
    public Label categoriesLabel;
    public GridPane categoryPain;
    public Label category1;
    public Label category2;
    public Label category3;
    public Label category4;
    public Label category5;
    public Label category6;
    public Label category7;
    public Label category8;
    public Label category9;
    public GridPane subCategoryPain;
    public GridPane wholeCategoryPane;
    public Label azmayesh;
    public Label subcategory00;
    public Label subcategory01;
    public Label subcategory02;
    public Label subcategory03;
    public Label subcategory04;
    public Label subcategory05;
    public Label subcategory10;
    public Label subcategory11;
    public Label subcategory12;
    public Label subcategory20;
    public Label subcategory21;
    public Label subcategory06;
    public Label subcategory15;
    public Label subcategory14;
    public Label subcategory13;
    public Label subcategory24;
    public Label subcategory22;
    public Label subcategory23;
    public Label subcategory07;
    public Label subcategory16;
    public Label subcategory08;
    public Label subcategory17;
    public Label subcategory18;
    public Label subcategory26;
    public Label subcategory25;
    public Label subcategory27;
    public Label subcategory28;
    public Label subcategory30;
    public Label subcategory31;
    public Label subcategory32;
    public Label subcategory33;
    public Label subcategory35;
    public Label subcategory34;
    public Label subcategory36;
    public Label subcategory37;
    public Label subcategory38;


    private ArrayList<Label> categoryLabels = new ArrayList<>();

    private ArrayList<Label> subCategoryLabels = new ArrayList<>();

    private ArrayList<Label> allCategoryLabels = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setAllCategories();
        setCategories();
        firstMovingPicture.setVisible(true);
        firstBlueBullet.setVisible(true);
        arrangeTooltips();
        Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.millis(3000), e -> run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setCategories() {
        int i = 0;
        for (Category category : DataBase.getAllCategories()) {
            if(category.getParentCategory() == null){
                categoryLabels.get(i).setText(category.getName());
                categoryLabels.get(i).setVisible(true);
                categoryLabels.get(i).setDisable(false);
                i++;
            }
        }

    }

    private void setAllCategories() {
        categoryLabels.add(category1);
        categoryLabels.add(category2);
        categoryLabels.add(category3);
        categoryLabels.add(category4);
        categoryLabels.add(category5);
        categoryLabels.add(category6);
        categoryLabels.add(category7);
        categoryLabels.add(category8);
        categoryLabels.add(category9);

        subCategoryLabels.add(subcategory00);subCategoryLabels.add(subcategory01);subCategoryLabels.add(subcategory02);subCategoryLabels.add(subcategory03);
        subCategoryLabels.add(subcategory04);subCategoryLabels.add(subcategory05);subCategoryLabels.add(subcategory06);subCategoryLabels.add(subcategory07);
        subCategoryLabels.add(subcategory08);subCategoryLabels.add(subcategory10);subCategoryLabels.add(subcategory11);subCategoryLabels.add(subcategory12);
        subCategoryLabels.add(subcategory13);subCategoryLabels.add(subcategory14);subCategoryLabels.add(subcategory15);subCategoryLabels.add(subcategory16);
        subCategoryLabels.add(subcategory17);subCategoryLabels.add(subcategory18);subCategoryLabels.add(subcategory20);subCategoryLabels.add(subcategory21);
        subCategoryLabels.add(subcategory22);subCategoryLabels.add(subcategory23);subCategoryLabels.add(subcategory24);subCategoryLabels.add(subcategory25);
        subCategoryLabels.add(subcategory26);subCategoryLabels.add(subcategory27);subCategoryLabels.add(subcategory28);subCategoryLabels.add(subcategory30);
        subCategoryLabels.add(subcategory31);subCategoryLabels.add(subcategory32);subCategoryLabels.add(subcategory33);subCategoryLabels.add(subcategory34);
        subCategoryLabels.add(subcategory35);subCategoryLabels.add(subcategory36);subCategoryLabels.add(subcategory37);subCategoryLabels.add(subcategory38);

        allCategoryLabels.addAll(categoryLabels);
        allCategoryLabels.addAll(subCategoryLabels);

    }



    private void arrangeTooltips() {

        Tooltip firstMovingPictureTooltip = new Tooltip("buy Call of Duty Soon");
        firstMovingPictureTooltip.setStyle("-fx-font: normal bold 10 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;");
        Tooltip.install(firstMovingPicture, firstMovingPictureTooltip);
        Tooltip secondMovingPictureTooltip = new Tooltip("buy Mortal Combat Soon");
        secondMovingPictureTooltip.setStyle("-fx-font: normal bold 10 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: blue;");
        Tooltip.install(secondMovingPicture, secondMovingPictureTooltip);
        Tooltip thirdMovingPictureTooltip = new Tooltip("buy God of War Soon");
        thirdMovingPictureTooltip.setStyle("-fx-font: normal bold 10 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: red;");
        Tooltip.install(thirdMovingPicture, thirdMovingPictureTooltip);
    }

    private void run() {
        timeForChangeImages += 3;
        if (timeForChangeImages % 6 == 0) {
            movePicturesRight();
            timeForChangeImages = 0;
        }
    }

    public void searched(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            searchField.clear();
        }
    }

    public void changeBackGroundColor(MouseEvent mouseEvent) {

        if (mouseEvent.getSource().equals(lightBlueButton)) {
            innerPane.setStyle("-fx-background-color: #cee8f0");
        } else if (mouseEvent.getSource().equals(lightGrayButton)) {
            innerPane.setStyle("-fx-background-color:  #f3f3f3");
        } else if (mouseEvent.getSource().equals(lightGreenButton)) {
            innerPane.setStyle("-fx-background-color: #cdeae0");
        } else if (mouseEvent.getSource().equals(lightRedButton)) {
            innerPane.setStyle("-fx-background-color: #ff726f");
        } else if (mouseEvent.getSource().equals(lightOrangeButton)) {
            innerPane.setStyle("-fx-background-color: #feddb6");
        } else if (mouseEvent.getSource().equals(lightWhiteButton)) {
            innerPane.setStyle("-fx-background-color: #FFFFFF");
        }
    }

    public void onOptions(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(categoriesLabel)) {
            productsRectangle.setFill(Color.RED);
        }
        if (mouseEvent.getSource().equals(offersLabel)) {
            offsRectangle.setFill(Color.RED);
        }

    }

    public void outOption(MouseEvent mouseEvent) {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        offsRectangle.setFill(Color.WHITE);
        productsRectangle.setFill(Color.WHITE);
    }


    public void clickOnMovingPictures(MouseEvent mouseEvent) {
        timeForChangeImages = 0;
        if (mouseEvent.getSource().equals(rightArrow)) {
            movePicturesRight();
        } else if (mouseEvent.getSource().equals(leftArrow)) {
            movePicturesLeft();
        }
    }

    private void movePicturesRight() {
        if (firstMovingPicture.isVisible()) {
            firstMovingPicture.setVisible(false);
            firstBlueBullet.setVisible(false);
            secondMovingPicture.setVisible(true);
            secondBlueBullet.setVisible(true);
        } else if (secondMovingPicture.isVisible()) {
            secondMovingPicture.setVisible(false);
            secondBlueBullet.setVisible(false);
            thirdBlueBullet.setVisible(true);
            thirdMovingPicture.setVisible(true);
        } else if (thirdMovingPicture.isVisible()) {
            thirdMovingPicture.setVisible(false);
            thirdBlueBullet.setVisible(false);
            firstBlueBullet.setVisible(true);
            firstMovingPicture.setVisible(true);
        }
    }

    private void movePicturesLeft() {
        if (firstMovingPicture.isVisible()) {
            firstMovingPicture.setVisible(false);
            firstBlueBullet.setVisible(false);
            thirdBlueBullet.setVisible(true);
            thirdMovingPicture.setVisible(true);
        } else if (secondMovingPicture.isVisible()) {
            secondMovingPicture.setVisible(false);
            secondBlueBullet.setVisible(false);
            firstBlueBullet.setVisible(true);
            firstMovingPicture.setVisible(true);
        } else if (thirdMovingPicture.isVisible()) {
            thirdMovingPicture.setVisible(false);
            thirdBlueBullet.setVisible(false);
            secondMovingPicture.setVisible(true);
            secondBlueBullet.setVisible(true);
        }
    }

    public void playOrMuteMusic(MouseEvent mouseEvent) {
        if (muteButton.isVisible()) {
            muteButton.setVisible(false);
            muteButton.setDisable(true);
            playMusicButton.setDisable(false);
            playMusicButton.setVisible(true);
        } else {
            muteButton.setVisible(true);
            muteButton.setDisable(false);
            playMusicButton.setDisable(true);
            playMusicButton.setVisible(false);
        }
    }

    public void openColorBar(MouseEvent mouseEvent) {
        if (colorsPane.isVisible()) {
            colorsPane.setVisible(false);
            colorsPane.setDisable(true);
        } else {
            colorsPane.setDisable(false);
            colorsPane.setVisible(true);
        }

    }

    public void settingClicked(MouseEvent mouseEvent) {
        if (settingPane.isVisible()) {
            settingPane.setVisible(false);
            settingPane.setDisable(true);
        } else {
            settingPane.setDisable(false);
            settingPane.setVisible(true);
        }

    }

    public void settingPaneExited(MouseEvent mouseEvent) {
        settingPane.setDisable(true);
        settingPane.setVisible(false);
    }

    public void searchedWithSearchImage(MouseEvent mouseEvent) {
        searchField.clear();
    }

    public void onCategory(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color: aliceblue");
    }

    public void outCategory(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setStyle("-fx-background-color:  #C0C0C0");
    }

    public void categoryAndProductsClicked(MouseEvent mouseEvent) {
        if (wholeCategoryPane.isVisible()) {
            wholeCategoryPane.setVisible(false);
            wholeCategoryPane.setDisable(true);
            categoryPain.setVisible(false);
            categoryPain.setDisable(true);
            subCategoryPain.setVisible(false);
            subCategoryPain.setDisable(true);
        } else {
            categoryPain.setVisible(true);
            categoryPain.setDisable(false);
            subCategoryPain.setVisible(true);
            subCategoryPain.setDisable(false);
            wholeCategoryPane.setVisible(true);
            wholeCategoryPane.setDisable(false);

        }
    }

    public void wholeCategoryExited(MouseEvent mouseEvent) {
        wholeCategoryPane.setVisible(false);
        wholeCategoryPane.setDisable(true);
        categoryPain.setVisible(false);
        categoryPain.setDisable(true);
        subCategoryPain.setVisible(false);
        subCategoryPain.setDisable(true);
    }


    public void clickedOnACategory(MouseEvent mouseEvent) {
    }

    public void enteredToSubCategory(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setTextFill(Color.RED);

    }

    public void exitedFromSubCategory(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.setTextFill(Color.BLACK);
    }
}

