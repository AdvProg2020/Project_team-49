package View;

import Controller.Controller;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public AnchorPane innerPane;
    public AnchorPane mainPane;
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
    public Pane movingPicturesPane;
    private static int timeForChangeImages = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstMovingPicture.setVisible(true);
        firstBlueBullet.setVisible(true);
        arrangeTooltips();
        Timeline timeline;
        timeline = new Timeline(new KeyFrame(Duration.millis(3000), e -> run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Controller.cancelSong();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startSong("src/main/resources/Sound/MainPage/BackGround.mp3");
            }
        }).start();
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

}
