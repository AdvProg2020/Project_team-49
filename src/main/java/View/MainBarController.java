package View;

import Controller.Controller;
import Controller.DataBase;
import Controller.Filter;
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

public class MainBarController implements Initializable {
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
    public Label subcategory00;
    public Label subcategory01;
    public Label subcategory02;
    public Label subcategory03;
    public Label subcategory04;
    public Label subcategory05;
    public Label subcategory11;
    public Label subcategory12;
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
    public Label subcategory31;
    public Label subcategory32;
    public Label subcategory33;
    public Label subcategory35;
    public Label subcategory34;
    public Label subcategory36;
    public Label subcategory37;
    public Label subcategory38;
    public Pane upBarPane;
    public ImageView digikalaImage;
    public TextField searchField;
    public ImageView lightRedButton;
    public ImageView lightOrangeButton;
    public ImageView lightWhiteButton;
    public ImageView lightBlueButton;
    public ImageView lightGrayButton;
    public ImageView lightGreenButton;
    public ImageView costumerAreaButton;
    public Rectangle productsRectangle;
    public Rectangle offsRectangle;

    private static int added = 0;

    private ArrayList<Label> categoryLabels = new ArrayList<>();

    private ArrayList<Label> subCategoryLabels = new ArrayList<>();

    private ArrayList<Label> allCategoryLabels = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllCategories();
        setCategories();
    }

    private void setCategories() {
        int i = 0;
        for (Category category : DataBase.getAllCategories()) {
            if (category.getParentCategory() == null) {
                categoryLabels.get(i).setText(category.getName());
                categoryLabels.get(i).setVisible(true);
                categoryLabels.get(i).setDisable(false);
                i++;
            }
        }
    }

    private void setSubCategoriesContent(String categoryName) {
        Category category = DataBase.getCategoryByName(categoryName);
        subcategory00.setDisable(false);
        int n = 9660;
        char c = (char) n;
        subcategory00.setText("All categories in " + categoryName + " " + c);
        subcategory00.setVisible(true);
        added = 1;
        setSubCategories(category, 0);
    }

    private void clearSubCategoryLabels() {
        for (Label label : subCategoryLabels) {
            label.setText("");
            label.setVisible(false);
            label.setDisable(true);
        }
    }

    private void setSubCategories(Category category, int howMuchInside) {
        int n = 9679;
        char c = (char) n;
        for (Category subCategory : category.getSubCategories()) {
            if (howMuchInside == 0) {
                int m = 12297;
                char M = (char) m;
                subCategoryLabels.get(added).setText(subCategory.getName() + " " + M);
                subCategoryLabels.get(added).setStyle("-fx-font-size: 17");
            } else if (howMuchInside == 1) {

                subCategoryLabels.get(added).setText(" " + c + " " + subCategory.getName());
            } else if (howMuchInside == 2) {
                subCategoryLabels.get(added).setText("  " + c + c + " " + subCategory.getName());
            } else {
                subCategoryLabels.get(added).setText("   " + c + c + c + " " + subCategory.getName());
            }
            subCategoryLabels.get(added).setVisible(true);
            subCategoryLabels.get(added).setDisable(false);
            added++;
            setSubCategories(subCategory, howMuchInside + 1);
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

        subCategoryLabels.add(subcategory00);
        subCategoryLabels.add(subcategory01);
        subCategoryLabels.add(subcategory02);
        subCategoryLabels.add(subcategory03);
        subCategoryLabels.add(subcategory04);
        subCategoryLabels.add(subcategory05);
        subCategoryLabels.add(subcategory06);
        subCategoryLabels.add(subcategory07);
        subCategoryLabels.add(subcategory08);


        subCategoryLabels.add(subcategory11);
        subCategoryLabels.add(subcategory12);
        subCategoryLabels.add(subcategory13);
        subCategoryLabels.add(subcategory14);
        subCategoryLabels.add(subcategory15);
        subCategoryLabels.add(subcategory16);
        subCategoryLabels.add(subcategory17);
        subCategoryLabels.add(subcategory18);

        subCategoryLabels.add(subcategory21);
        subCategoryLabels.add(subcategory22);
        subCategoryLabels.add(subcategory23);
        subCategoryLabels.add(subcategory24);
        subCategoryLabels.add(subcategory25);
        subCategoryLabels.add(subcategory26);
        subCategoryLabels.add(subcategory27);
        subCategoryLabels.add(subcategory28);

        subCategoryLabels.add(subcategory31);
        subCategoryLabels.add(subcategory32);
        subCategoryLabels.add(subcategory33);
        subCategoryLabels.add(subcategory34);
        subCategoryLabels.add(subcategory35);
        subCategoryLabels.add(subcategory36);
        subCategoryLabels.add(subcategory37);
        subCategoryLabels.add(subcategory38);

        allCategoryLabels.addAll(categoryLabels);
        allCategoryLabels.addAll(subCategoryLabels);

    }

    public void searched(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            searchField.clear();
        }
    }

    public void changeBackGroundColor(MouseEvent mouseEvent) {

        if (mouseEvent.getSource().equals(lightBlueButton)) {
            Controller.setColor("cee8f0");
//            innerPane.setStyle("-fx-background-color: #");
        } else if (mouseEvent.getSource().equals(lightGrayButton)) {
            Controller.setColor("f3f3f3");
//            innerPane.setStyle("-fx-background-color:  #f3f3f3");
        } else if (mouseEvent.getSource().equals(lightGreenButton)) {
            Controller.setColor("cdeae0");
//            innerPane.setStyle("-fx-background-color: #cdeae0");
        } else if (mouseEvent.getSource().equals(lightRedButton)) {
            Controller.setColor("ff726f");
//            innerPane.setStyle("-fx-background-color: #ff726f");
        } else if (mouseEvent.getSource().equals(lightOrangeButton)) {
            Controller.setColor("feddb6");
//            innerPane.setStyle("-fx-background-color: #feddb6");
        } else if (mouseEvent.getSource().equals(lightWhiteButton)) {
            Controller.setColor("FFFFFF");
//            innerPane.setStyle("-fx-background-color: #FFFFFF");
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
        clearSubCategoryLabels();
        Label label = (Label) mouseEvent.getSource();
        label.toFront();
        label.setStyle("-fx-background-color: #ffffff");
        label.setTextFill(Color.RED);
        setSubCategoriesContent(label.getText());
    }

    public void outCategory(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        label.toBack();
        label.setStyle("-fx-background-color:  #C0C0C0");
        label.setTextFill(Color.BLACK);
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
        Label label=(Label) mouseEvent.getSource();
        String category=label.getText();
        if (category.endsWith("〉")){
            category=category.split("\\s")[0];
        }else if (category.startsWith("●")){
            int i=0;
            while (category.split("\\s")[i].equals("●")){
                i++;
            }
            category=category.split("\\s")[i];
        }
        Filter.restartFilters();
        Filter.filterByCategory(category);



    }

    public void enteredToSubCategory(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        if (!label.equals(subcategory00)) {
            label.setTextFill(Color.RED);
        }
    }

    public void exitedFromSubCategory(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        if (!label.equals(subcategory00)) {
            label.setTextFill(Color.BLACK);
        }
    }

}
