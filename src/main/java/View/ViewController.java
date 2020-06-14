package View;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController {

    public ImageView digikalaImage;
    public TextField searchField;
    public Button customerAreaButton;
    public ImageView lightRedButton;
    public ImageView lightOrangeButton;
    public ImageView lightWhiteButton;
    public ImageView lightBlueButton;
    public ImageView lightGrayButton;
    public ImageView lightGreenButton;
    public AnchorPane innerPane;
    public AnchorPane mainPane;


    public void changeColorBlue() {
        innerPane.setStyle("-fx-background-color: #cee8f0");
    }

    public void changeColorRed() {
        innerPane.setStyle("-fx-background-color: #ff726f");
    }

    public void changeColorGreen() {
        innerPane.setStyle("-fx-background-color: #cdeae0");
    }

    public void changeColorWhite() {
        innerPane.setStyle("-fx-background-color: #FFFFFF");
    }

    public void changeColorGray() {
        innerPane.setStyle("-fx-background-color: #d3d3d3");
    }

    public void changeColorOrange() {
        innerPane.setStyle("-fx-background-color: #feddb6");
    }


    public void searched(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            searchField.clear();

        }
    }
}
