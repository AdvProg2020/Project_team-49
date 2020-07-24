package View.Menu.UserArea;

import View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupportAreaGraphicController implements Initializable {
    public Label userNameLabel;
    public Label managerRoleLabel;
    public Label personalInfoLabel;
    public Pane editPersonalInfoLabel;
    public ImageView goBackToPersonalInfoArrow;
    public Pane userInfoPane;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label emailLabel;
    public Label phoneNumberLabel;
    public Label roleLabelInPersonalInfo;
    public Pane goToEditInformationPane;
    public Pane editPersonalInfoPane;
    public Rectangle firstNameRec;
    public TextField firstNameTextField;
    public Rectangle lastNameRec;
    public TextField lastNameTextField;
    public Rectangle emailRec;
    public TextField emailTextField;
    public Rectangle passwordRec;
    public TextField passwordTextField;
    public Button checkInformationButton;
    public ImageView correctFormatEditImage;
    public ImageView wrongFormatEditImage;
    public Pane submitPersonalInformationPane;
    public Button startChatButton;
    public TextField chooseContact;
    public ImageView sendMessageImage;
    public TextField chatType;
    public ListView chatMain;
    public ListView contactsStatus;
    public Pane supportPain;
    private ArrayList<String> support = new ArrayList<>();
    private ArrayList<String> chat = new ArrayList<>();
    private String contactForChat = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSupport();

        userNameLabel.setText(support.get(0));
        closeALlPanes();
        setPersonalInfoLabels();
        userInfoPane.setVisible(true);
        userInfoPane.setDisable(false);

        View.client.cancelSong();
        //View.client.startSong("src/main/resources/Sound/ManagerArea/BackGround.mp3");

    }

    private void setChat() {
        chat = View.client.getChatForSupport(contactForChat);
    }

    private void setPersonalInfoLabels() {
        firstNameLabel.setText(support.get(2));
        lastNameLabel.setText(support.get(3));
        emailLabel.setText(support.get(4));
        roleLabelInPersonalInfo.setText("support");
        phoneNumberLabel.setText(support.get(5));
        userNameLabel.setText(support.get(0));
    }

    private void closeALlPanes() {
        editPersonalInfoPane.setDisable(true);
        editPersonalInfoPane.setVisible(false);
        editPersonalInfoLabel.setDisable(true);
        editPersonalInfoLabel.setVisible(false);
        personalInfoLabel.setVisible(false);
        supportPain.setVisible(false);
        supportPain.setDisable(true);

        setSupport();
    }

    private void setSupport() {
        support = View.client.getCurrentUser();
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

    public void checkInformation(ActionEvent actionEvent) {
        boolean flag = false;
        restartTextFieldRecsInEditPane();
        if (passwordTextField.getText().length() < 5 && !passwordTextField.getText().equals("")) {
            passwordRec.setStroke(Color.valueOf("#fb3449"));
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

    private void restartInsideEditPersonalPane() {
        firstNameTextField.setText("");
        firstNameTextField.setPromptText(support.get(2));
        lastNameTextField.setText("");
        lastNameTextField.setPromptText(support.get(3));
        emailTextField.setText("");
        emailTextField.setPromptText(support.get(4));
        passwordTextField.setText("");
        passwordTextField.setPromptText(support.get(1));
        restartTextFieldRecsInEditPane();
        correctFormatEditImage.setVisible(false);
        wrongFormatEditImage.setVisible(false);
        submitPersonalInformationPane.setDisable(true);
        submitPersonalInformationPane.setOpacity(0.5);
    }

    private void restartTextFieldRecsInEditPane() {
        passwordRec.setStroke(Color.valueOf("#d3d3d3"));
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

    public void submitPersonalInformation(MouseEvent mouseEvent) {
        if (!passwordTextField.getText().matches("(\\s+)?")) {
            View.client.setUserInfo("Password", passwordTextField.getText());
        }
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

    public void logoutSupport(MouseEvent mouseEvent) {
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

    public void goBackToLastPaneFromSupportArea(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((ImageView) mouseEvent.getSource()).getScene();
        View.setCurrentPane(View.getLastPane());
        scene.setRoot(View.getCurrentPane());
        stage.setScene(scene);
        stage.show();
    }

    public void refreshChat(MouseEvent mouseEvent) {
        if (contactForChat.equals("")) {
            return;
        }
        setChat();
        chatMain.getItems().clear();
        for (String s : chat) {
            chatMain.getItems().add(s);
        }
    }

    public void sendMessage(MouseEvent mouseEvent) {
        View.client.sendMessageForSupport("Support: " + chatType.getText(), contactForChat);
        chatType.setText("");
        refreshChat(mouseEvent);
    }

    public void startChat(MouseEvent mouseEvent) {
        contactForChat = chooseContact.getText();
        chooseContact.setText("");
        refreshChat(mouseEvent);
    }

    public void goToChatsPain(MouseEvent mouseEvent) {
        closeALlPanes();
        supportPain.setVisible(true);
        supportPain.setDisable(false);
        setSupportsPainContent();
    }

    private void setSupportsPainContent() {
        contactsStatus.getItems().clear();
        ArrayList<String> contacts = View.client.getContactsForSupport();
        for (String contact : contacts) {
            Label label = new Label();
            label.setPrefWidth(200);
            label.setPrefHeight(30);
            label.setText(contact);
            contactsStatus.getItems().add(label);
        }

        chatMain.getItems().clear();
        chatType.setText("");
        chooseContact.setText("");
    }
}
