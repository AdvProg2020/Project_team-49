package View.Menu.UserArea;

import View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static Controller.CostumerAreaController.*;

public class CostumerAreaGraphicController implements Initializable {

    public Pane goToEditInformationPane;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField emailTextField;
    public TextField creditTextField;
    public Button checkInformationButton;
    public ImageView correctFormatEditImage;
    public ImageView wrongFormatEditImage;
    public Pane submitPersonalInformationPane;
    public Label personalInfoLabel;
    public Pane editPersonalInfoLabel;
    public ImageView goBackToPersonalInfoArrow;
    public Label userNameLabel;
    public Label creditLabel;
    public Label phoneNumberLabel;
    public Label emailLabel;
    public Label lastNameLabel;
    public Label firstNameLabel;
    public Pane userInfoPane;
    public Pane editPersonalInfoPane;
    public Rectangle creditRec;
    public Rectangle firstNameRec;
    public Rectangle lastNameRec;
    public Rectangle emailRec;
    public Label receiveStatusLog2;
    public Label logIdLabelLog2;
    public Label dateLabelLog2;
    public Label totalPriceLog2;
    public ImageView image28;
    public ImageView image26;
    public ImageView image27;
    public ImageView image25;
    public ImageView image24;
    public ImageView image23;
    public ImageView image22;
    public ImageView image21;
    public ImageView upArrowLogs;
    public ImageView downArrowLogs;
    public Label receiveStatusLog1;
    public Label logIdLabelLog11;
    public Label dateLabelLog1;
    public Label totalPriceLog1;
    public ImageView image18;
    public ImageView image17;
    public ImageView image16;
    public ImageView image15;
    public ImageView image14;
    public ImageView image13;
    public ImageView image12;
    public ImageView image11;
    public Pane firstLogPane;
    public Pane secondLogPane;
    public Pane buyHistoryPane;
    public Label buyHistoryLabel;
    public ImageView seeMoreProductsImageLog1;
    public ImageView seeMoreProductsImageLog2;
    public ImageView seeLessProductsImageLog2;
    public ImageView seeLessProductsImageLog1;
    public Pane buyHistoryIsEmptyPain;
    public Label discountCodesLabel;
    public Pane firstDiscountCode;
    public Pane discountCodesPain;
    public Label discountPercent1;
    public Label maximumAmount1;
    public Label countDiscount1;
    public Label usageDiscount1;
    public Label startDiscount1;
    public Label endDiscount1;
    public Pane secondDiscountCode;
    public Label discountPercent2;
    public Label maximumAmount2;
    public Label countDiscount2;
    public Label usageDiscount2;
    public Label startDiscount2;
    public Label endDiscount2;
    public Pane arrowAndRecForDiscounts;
    public ImageView downArrowDiscounts;
    public ImageView upArrowDiscounts;
    public Pane noDiscountsYet;
    public Label discountId2;
    public Label discountId1;
    public Pane supportPain;
    public ListView supportsStatus;
    public Label supportLabel;
    public Button startChatButton;
    public TextField chooseSupport;
    public ImageView sendMessageImage;
    public TextField chatType;
    public ListView chatMain;
    private ArrayList<String> costumer = new ArrayList<>();
    private int discountCodesIndex = 0;
    private int imagesLog1Index = 0;
    private int imagesLog2Index = 0;
    private int logIndex = 0;
    ArrayList<String> logHistory = new ArrayList<>();
    private ArrayList<String> chat = new ArrayList<>();
    private String supportUsernameForChat = "";
    private SimpleDateFormat formatter;
    private ArrayList<String> discountCodes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeALlPanes();
//        discountCodes = viewCostumerDiscountCodes();
        setCostumer();
        setLogHistory();
        userInfoPane.setDisable(false);
        userInfoPane.setVisible(true);
        setPersonalInfoLabels();
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        View.client.cancelSong();
//        View.client.startSong("src/main/resources/Sound/CustomerArea/BackGround.mp3");
    }

    private void setCostumer() {
        costumer = View.client.getCurrentUser();
    }

    private void setLogHistory() {
        logHistory = View.client.getBuyLogs();
    }

    private void setChat() {
        chat = View.client.getChatForCostumer(supportUsernameForChat);
    }

    private ArrayList<String> getBoughtProduct(String buyLog) {
        return new ArrayList<>(Arrays.asList(buyLog.split("!@")[4].split("@#")));
    }

    private void setPersonalInfoLabels() {
        userNameLabel.setText(costumer.get(0));
        firstNameLabel.setText(costumer.get(2));
        lastNameLabel.setText(costumer.get(3));
        emailLabel.setText(costumer.get(4));
        creditLabel.setText(costumer.get(6));
        phoneNumberLabel.setText(costumer.get(5));
    }

    public void goToEditPersonalInfo(MouseEvent mouseEvent) {
        personalInfoLabel.setVisible(false);
        userInfoPane.setDisable(true);
        userInfoPane.setVisible(false);
        editPersonalInfoLabel.setVisible(true);
        editPersonalInfoLabel.setDisable(false);
        editPersonalInfoPane.setDisable(false);
        editPersonalInfoPane.setVisible(true);
        restartInsideEditPersonalPane();
    }

    private void restartInsideEditPersonalPane() {
        creditTextField.setText("");
        creditTextField.setPromptText(costumer.get(6));
        firstNameTextField.setText("");
        firstNameTextField.setPromptText(costumer.get(2));
        lastNameTextField.setText("");
        lastNameTextField.setPromptText(costumer.get(3));
        emailTextField.setText("");
        emailTextField.setPromptText(costumer.get(4));
        restartTextFieldRecsInEditPane();
        correctFormatEditImage.setVisible(false);
        wrongFormatEditImage.setVisible(false);
        submitPersonalInformationPane.setDisable(true);
        submitPersonalInformationPane.setOpacity(0.5);
    }

    public void goBackToPersonalInfo(MouseEvent mouseEvent) {
        closeALlPanes();
        personalInfoLabel.setVisible(true);
        editPersonalInfoLabel.setDisable(true);
        editPersonalInfoLabel.setVisible(false);
        editPersonalInfoPane.setDisable(true);
        editPersonalInfoPane.setVisible(false);
        userInfoPane.setVisible(true);
        userInfoPane.setDisable(false);
        setPersonalInfoLabels();
    }

    public void submitPersonalInformation(MouseEvent mouseEvent) {
        if (!creditTextField.getText().matches("(\\s+)?"))
            View.client.setUserInfo("Credit", String.valueOf(Double.parseDouble(creditTextField.getText())));

        if (!emailTextField.getText().matches("(\\s+)?")) {
            View.client.setUserInfo("Email", emailTextField.getText());
        }
        if (!firstNameTextField.getText().matches("(\\s+)?")) {
            View.client.setUserInfo("FirstName", firstNameTextField.getText());
        }
        if (!lastNameTextField.getText().matches("(\\s+)?")) {
            View.client.setUserInfo("LastName", lastNameTextField.getText());
        }
        personalInfoLabel.setVisible(true);
        editPersonalInfoLabel.setDisable(true);
        editPersonalInfoLabel.setVisible(false);
        editPersonalInfoPane.setDisable(true);
        editPersonalInfoPane.setVisible(false);
        userInfoPane.setVisible(true);
        userInfoPane.setDisable(false);
        setPersonalInfoLabels();
    }

    public void checkInformation(ActionEvent event) {
        boolean flag = false;
        restartTextFieldRecsInEditPane();
        if (!creditTextField.getText().matches("(\\d+)(.?)((\\d+)?)") && !creditTextField.getText().equals("")) {
            creditRec.setStroke(Color.valueOf("#fb3449"));
            flag = true;
        }
        if (!emailTextField.getText().matches("(\\S+)@(\\S+)") && !emailTextField.getText().equals("")) {
            emailRec.setStroke(Color.valueOf("#fb3449"));
            flag = true;
        }
        if (!firstNameTextField.getText().matches("\\w+") && !firstNameTextField.getText().equals("")) {
            firstNameRec.setStroke(Color.valueOf("#fb3449"));
            flag = true;
        }
        if (!lastNameTextField.getText().matches("\\w+") && !lastNameTextField.getText().equals("")) {
            lastNameRec.setStroke(Color.valueOf("#fb3449"));
            flag = true;
        }
        if (flag) {
            disableSomeStuffInEditPane();
            return;
        }

        wrongFormatEditImage.setVisible(false);
        correctFormatEditImage.setVisible(true);
        submitPersonalInformationPane.setDisable(false);
        submitPersonalInformationPane.setOpacity(1);

    }

    private void restartTextFieldRecsInEditPane() {
        creditRec.setStroke(Color.valueOf("#d3d3d3"));
        firstNameRec.setStroke(Color.valueOf("#d3d3d3"));
        lastNameRec.setStroke(Color.valueOf("#d3d3d3"));
        emailRec.setStroke(Color.valueOf("#d3d3d3"));
    }

    private void disableSomeStuffInEditPane() {
        wrongFormatEditImage.setVisible(true);
        correctFormatEditImage.setVisible(false);
        submitPersonalInformationPane.setDisable(true);
        submitPersonalInformationPane.setOpacity(0.5);
    }

    public void seeMoreLogs(MouseEvent mouseEvent) {
        int size = logHistory.size();
        if (mouseEvent.getSource().equals(upArrowLogs)) {
            logIndex--;
            if (logIndex > 0) {
                logIndex = 0;
            }
        } else if (mouseEvent.getSource().equals(downArrowLogs)) {
            logIndex++;
            if (logIndex >= size - 2) {
                logIndex--;
            }
        }
        setLogPaneContents();
    }

    public void seeMorePictures(MouseEvent mouseEvent) {
        int size = 0;

        if (mouseEvent.getSource().equals(seeMoreProductsImageLog1)) {
            String buyLog = logHistory.get(logIndex);
            size = getBoughtProduct(buyLog).size();
            if (imagesLog1Index + 8 >= size + 1) return;
            imagesLog1Index++;
            setFirstImage();

        } else if (mouseEvent.getSource().equals(seeMoreProductsImageLog2)) {
            String buyLog = logHistory.get(logIndex + 1);
            size = getBoughtProduct(buyLog).size();
            if (imagesLog2Index + 8 >= size + 1) return;
            imagesLog2Index++;
            setSecondImage();

        } else if (mouseEvent.getSource().equals(seeLessProductsImageLog1)) {
            String buyLog = logHistory.get(logIndex);
            size = getBoughtProduct(buyLog).size();
            if (imagesLog1Index == 0) return;
            imagesLog1Index--;
            setFirstImage();

        } else if (mouseEvent.getSource().equals(seeLessProductsImageLog2)) {
            String buyLog = logHistory.get(logIndex + 1);
            size = getBoughtProduct(buyLog).size();
            if (imagesLog2Index == 0) return;
            imagesLog2Index--;
            setSecondImage();
        }
    }

    public void goToBuyHistoryPane(MouseEvent mouseEvent) {
        closeALlPanes();
        restartInsideOfBuyHistoryPane();
        imagesLog1Index = 0;
        imagesLog2Index = 0;
        logIndex = 0;
        setLogHistory();
        buyHistoryLabel.setVisible(true);
        buyHistoryLabel.setOpacity(1);
        buyHistoryPane.setDisable(false);
        buyHistoryPane.setVisible(true);
        int size = logHistory.size();
        if (size > 2) {
            upArrowLogs.setVisible(true);
            upArrowLogs.setDisable(false);
            downArrowLogs.setVisible(true);
            downArrowLogs.setDisable(false);
        } else if (size == 0) {
            buyHistoryIsEmptyPain.setVisible(true);
            buyHistoryIsEmptyPain.setDisable(false);
        }
        setLogPaneContents();
    }

    private void setLogPaneContents() {
        restartInsideOfBuyHistoryPane();
        int size = logHistory.size();
        if (size == 0) {
            return;
        }
        String buyLog1 = logHistory.get(logIndex);
        firstLogPane.setVisible(true);
        firstLogPane.setDisable(false);
        dateLabelLog1.setText(buyLog1.split("!@")[1]);
        totalPriceLog1.setText(buyLog1.split("!@")[2]);
        logIdLabelLog11.setText(buyLog1.split("!@")[0]);
        receiveStatusLog1.setText(buyLog1.split("!@")[6]);
        if (getBoughtProduct(buyLog1).size() < 9) {
            seeMoreProductsImageLog1.setDisable(true);
            seeMoreProductsImageLog1.setVisible(false);
            seeLessProductsImageLog1.setVisible(false);
            seeLessProductsImageLog1.setDisable(true);
        } else {
            seeMoreProductsImageLog1.setDisable(false);
            seeMoreProductsImageLog1.setVisible(true);
            seeLessProductsImageLog1.setVisible(true);
            seeLessProductsImageLog1.setDisable(false);
        }
        setFirstImage();

        if (size == 1) return;
        String buyLog2 = logHistory.get(logIndex + 1);

        secondLogPane.setVisible(true);
        secondLogPane.setDisable(false);
        dateLabelLog2.setText(buyLog2.split("!@")[1]);
        totalPriceLog2.setText(buyLog2.split("!@")[2]);
        logIdLabelLog2.setText(buyLog2.split("!@")[0]);
        receiveStatusLog2.setText(buyLog2.split("!@")[6]);
        if (getBoughtProduct(buyLog2).size() < 9) {
            seeMoreProductsImageLog2.setDisable(true);
            seeMoreProductsImageLog2.setVisible(false);
            seeLessProductsImageLog2.setVisible(false);
            seeLessProductsImageLog2.setDisable(true);
        } else {
            seeMoreProductsImageLog2.setDisable(false);
            seeMoreProductsImageLog2.setVisible(true);
            seeLessProductsImageLog2.setVisible(true);
            seeLessProductsImageLog2.setDisable(false);
        }
        setSecondImage();
    }

    private void setFirstImage() {
        clearImages1();
        ArrayList<String> products = getBoughtProduct(logHistory.get(logIndex));
        for (int i = 0; i < 8 && i < products.size() - 1; i++) {
            String product = products.get(i);
            String path = View.client.getProductImageAddress(product.split("!@")[0]).substring(19);
            if (i == 0) {
                setProductsImage(image11, path);
            } else if (i == 1) {
                setProductsImage(image12, path);
            } else if (i == 2) {
                setProductsImage(image13, path);
            } else if (i == 3) {
                setProductsImage(image14, path);
            } else if (i == 4) {
                setProductsImage(image15, path);
            } else if (i == 5) {
                setProductsImage(image16, path);
            } else if (i == 6) {
                setProductsImage(image17, path);
            } else if (i == 7) {
                setProductsImage(image18, path);
            }
        }
    }

    private void clearImages1() {
        image11.setImage(null);
        image12.setImage(null);
        image13.setImage(null);
        image14.setImage(null);
        image15.setImage(null);
        image16.setImage(null);
        image17.setImage(null);
        image18.setImage(null);
    }

    private void setSecondImage() {
        clearImages2();
        ArrayList<String> products = getBoughtProduct(logHistory.get(logIndex + 1));
        for (int i = 0; i < 8 && i < products.size() - 1; i++) {
            String product = products.get(i);
            String path = View.client.getProductImageAddress(product.split("!@")[0]).substring(19);
            if (i == 0) {
                setProductsImage(image21, path);
            } else if (i == 1) {
                setProductsImage(image22, path);
            } else if (i == 2) {
                setProductsImage(image23, path);
            } else if (i == 3) {
                setProductsImage(image24, path);
            } else if (i == 4) {
                setProductsImage(image25, path);
            } else if (i == 5) {
                setProductsImage(image26, path);
            } else if (i == 6) {
                setProductsImage(image27, path);
            } else if (i == 7) {
                setProductsImage(image28, path);
            }
        }
    }

    private void clearImages2() {
        image21.setImage(null);
        image22.setImage(null);
        image23.setImage(null);
        image24.setImage(null);
        image25.setImage(null);
        image26.setImage(null);
        image27.setImage(null);
        image28.setImage(null);
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

    private void setProductsImage(ImageView image, String address) {
        try {
            image.setImage(new Image(address));
            centerImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restartInsideOfBuyHistoryPane() {
        upArrowLogs.setVisible(false);
        upArrowLogs.setDisable(true);
        downArrowLogs.setVisible(false);
        downArrowLogs.setDisable(true);
        seeMoreProductsImageLog1.setDisable(true);
        seeMoreProductsImageLog1.setVisible(false);
        seeMoreProductsImageLog2.setDisable(true);
        seeMoreProductsImageLog2.setVisible(false);
        secondLogPane.setDisable(true);
        secondLogPane.setVisible(false);
        firstLogPane.setVisible(false);
        firstLogPane.setDisable(true);
    }

    private void closeALlPanes() {
        userInfoPane.setDisable(true);
        userInfoPane.setVisible(false);
        discountCodesLabel.setVisible(false);
        discountCodesPain.setVisible(false);
        discountCodesPain.setDisable(true);
        editPersonalInfoPane.setDisable(true);
        editPersonalInfoPane.setVisible(false);
        editPersonalInfoLabel.setDisable(true);
        editPersonalInfoLabel.setVisible(false);
        personalInfoLabel.setVisible(false);
        buyHistoryLabel.setVisible(false);
        buyHistoryPane.setVisible(false);
        buyHistoryPane.setDisable(true);
        buyHistoryIsEmptyPain.setVisible(false);
        buyHistoryIsEmptyPain.setDisable(true);
        noDiscountsYet.setVisible(false);
        imagesLog1Index = 0;
        imagesLog2Index = 0;
        logIndex = 0;
        discountCodesIndex = 0;

        supportLabel.setDisable(true);
        supportLabel.setVisible(false);
        supportPain.setDisable(true);
        supportPain.setVisible(false);

        setCostumer();
    }

    public void goToDiscountCodesPain(MouseEvent mouseEvent) {
        closeALlPanes();
        restartInsideDiscountPain();
        discountCodesLabel.setVisible(true);
        discountCodesPain.setVisible(true);
        discountCodesPain.setDisable(false);
        discountCodesIndex = 0;
        discountCodes = viewCostumerDiscountCodes();
        int size = discountCodes.size();
        if (size == 0) {
            noDiscountsYet.setVisible(true);
        }
        if (size > 2) {
            arrowAndRecForDiscounts.setDisable(false);
            arrowAndRecForDiscounts.setVisible(true);
        } else {
            arrowAndRecForDiscounts.setDisable(true);
            arrowAndRecForDiscounts.setVisible(false);
        }

        setDiscountCodesPaneContents();
    }

    private void setDiscountCodesPaneContents() {
        restartInsideDiscountPain();
        int size = discountCodes.size();
        if (size == 0) {
            return;
        }
        String[] first = discountCodes.get(discountCodesIndex).split(",");
        firstDiscountCode.setVisible(true);
        discountPercent1.setText(first[3]);
        startDiscount1.setText(first[1]);
        endDiscount1.setText(first[2]);
        maximumAmount1.setText(first[4]);
        usageDiscount1.setText(first[6]);
        countDiscount1.setText(first[5]);
        discountId1.setText(first[0]);

        if (discountCodesIndex > size + 2) return;
        String[] second = discountCodes.get(discountCodesIndex + 1).split(",");
        secondDiscountCode.setVisible(true);
        discountPercent2.setText(second[3]);
        startDiscount2.setText(second[1]);
        endDiscount2.setText(second[2]);
        maximumAmount2.setText(second[4]);
        usageDiscount2.setText(second[6]);
        countDiscount2.setText(second[5]);
        discountId2.setText(second[0]);

    }

    private void restartInsideDiscountPain() {
        firstDiscountCode.setVisible(false);
        secondDiscountCode.setVisible(false);
        noDiscountsYet.setVisible(false);
    }


    public void seeMoreDiscounts(MouseEvent mouseEvent) {
        int size = discountCodes.size();
        if (mouseEvent.getSource().equals(downArrowDiscounts)) {
            if (discountCodesIndex >= size - 2) return;
            discountCodesIndex += 2;
        } else if (mouseEvent.getSource().equals(upArrowDiscounts)) {
            if (discountCodesIndex == 0) return;
            discountCodesIndex -= 2;
        }
        setDiscountCodesPaneContents();
    }

    public void goBackToLastPaneFromCostumerArea(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((ImageView) mouseEvent.getSource()).getScene();
        View.setCurrentPane(View.getLastPane());
        scene.setRoot(View.getCurrentPane());
        stage.setScene(scene);
        stage.show();
    }

    public void goToCostumersCart(MouseEvent mouseEvent) {
        Scene scene = ((Label) mouseEvent.getSource()).getScene();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        View.setLastPane(View.getCurrentPane());
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/cartAndBuyPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScrollPane scrollPane = (ScrollPane) pane.getChildren().get(0);
        scrollPane.setPrefHeight(800);
        View.setCurrentPane(pane);
        scene.setRoot(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutCostumer(MouseEvent mouseEvent) {
        View.client.logout();
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((ImageView) mouseEvent.getSource()).getScene();
        Pane mainMenu = null;
        Pane mainBar = null;
        try {
            mainMenu = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainPage.fxml"));
            mainBar = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainBar.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        View.setInnerPaneForColor((Pane) ((ScrollPane) mainMenu.getChildren().get(0)).getContent());
        ScrollPane scrollPane = (ScrollPane) mainMenu.getChildren().get(0);
        scrollPane.setPrefHeight(800);
        mainMenu.getChildren().add(mainBar);
        View.setCurrentPane(mainMenu);
        scene.setRoot(View.getCurrentPane());
        stage.setScene(scene);
        stage.show();
    }

    public void goToSupportPain(MouseEvent mouseEvent) {
        closeALlPanes();
        supportLabel.setDisable(false);
        supportLabel.setVisible(true);
        supportPain.setDisable(false);
        supportPain.setVisible(true);
        setSupportsPainContent();
    }

    private void setSupportsPainContent() {
        supportsStatus.getItems().clear();
        ArrayList<String> supports = View.client.getSupportsForCostumer();
        for (String support : supports) {
            Label label = new Label();
            label.setPrefWidth(200);
            label.setPrefHeight(30);
            label.setText(support.split("!@")[0] + "-" + support.split("!@")[1]);
            if (support.split("!@")[0].equalsIgnoreCase("online")) {
                label.setStyle("-fx-border-color: Green;" + "-fx-border-radius: 3;" + "-fx-background-radius: 3");
                label.setTextFill(Color.GREEN);
            }
            supportsStatus.getItems().add(label);
        }

        chatMain.getItems().clear();
        chatType.setText("");
        chooseSupport.setText("");
    }

    public void refreshChat(MouseEvent mouseEvent) {
        if (supportUsernameForChat.equals("")) {
            return;
        }
        setChat();
        chatMain.getItems().clear();
        for (String s : chat) {
            Label label = new Label();
            label.setPrefHeight(30);
            label.setPrefWidth(620);
            label.setText(s);
            chatMain.getItems().add(label);
        }
    }

    public void startChat(MouseEvent mouseEvent) {
        if(chooseSupport.getText().equals("")) {
            return;
        }
        supportUsernameForChat = chooseSupport.getText();
        chooseSupport.setText("");
        View.client.startChatForCostumer(supportUsernameForChat);
        refreshChat(mouseEvent);
    }

    public void sendMessage(MouseEvent mouseEvent) {
        if (chatType.getText().equals("")) {
            refreshChat(mouseEvent);
            return;
        }
        View.client.sendMessageForCostumer("Costumer: " + chatType.getText(), supportUsernameForChat);
        chatType.setText("");
        refreshChat(mouseEvent);
    }
}
