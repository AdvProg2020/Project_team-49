package View.Menu.UserArea;

import Controller.Controller;
import View.View;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuestAreaGraphicsController implements Initializable {
    public TextField usernameReg;
    public Label userLabel;
    public PasswordField passReg;
    public Label passLabel;
    public PasswordField rePassReg;
    public Label rePassLabel;
    public TextField firstNameReg;
    public Label fNameLabel;
    public TextField lastNameReg;
    public Label lNameLabel;
    public TextField emailReg;
    public Label emailLabel;
    public TextField phoneReg;
    public Label phoneLabel;
    public ComboBox accountTypeReg;
    public TextField extraReg;
    public Label extraLabel;
    public Button registerButton;
    public Label goLoginLabel;

    public void mouseClicked(MouseEvent mouseEvent) {
        reset();

        if (mouseEvent.getSource().equals(registerButton)) {
            register();
        }
        if (mouseEvent.getSource().equals(usernameReg)) {
            usernameReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            userLabel.setVisible(true);
            userLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(passReg)) {
            passReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            passLabel.setVisible(true);
            passLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(rePassReg)) {
            rePassReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            rePassLabel.setVisible(true);
            rePassLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(firstNameReg)) {
            firstNameReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            fNameLabel.setVisible(true);
            fNameLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(lastNameReg)) {
            lastNameReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            lNameLabel.setVisible(true);
            lNameLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(emailReg)) {
            emailReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            emailLabel.setVisible(true);
            emailLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(phoneReg)) {
            phoneReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            phoneLabel.setVisible(true);
            phoneLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(extraReg)) {
            extraReg.setStyle("-fx-border-color: #1a73e8;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            extraLabel.setVisible(true);
            extraLabel.setTextFill(Color.valueOf("#1a73e8"));
        }

    }

    private void register() {
        boolean errorFound = false;
        if (extraReg.getText().equals("")) {
            extraReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            extraLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (Controller.hasUserWithUsername(usernameReg.getText())) {
            usernameReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            userLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!usernameReg.getText().matches("\\w+")) {
            usernameReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            userLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passReg.getText().equals(rePassReg.getText())) {
            passReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            passLabel.setTextFill(Color.valueOf("#fb3449"));
            rePassReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            rePassLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passReg.getText().matches("\\w+")) {
            passReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            passLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!rePassReg.getText().matches("\\w+")) {
            rePassReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            rePassLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!firstNameReg.getText().matches("[a-zA-Z]+")) {
            firstNameReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            fNameLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!lastNameReg.getText().matches("[a-zA-Z]+")) {
            lastNameReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            lNameLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!emailReg.getText().matches("(\\w+)@(\\w+)")) {
            emailReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            emailLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!phoneReg.getText().matches("\\d+")) {
            phoneReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
            phoneLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (accountTypeReg.getValue().equals("Costumer")) {
            if (!extraReg.getText().matches("\\d+")) {
                extraReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
                extraLabel.setTextFill(Color.valueOf("#fb3449"));
                errorFound = true;
            }
        } else if (accountTypeReg.getValue().equals("Seller")) {
            if (!extraReg.getText().matches("\\w+")) {
                extraReg.setStyle("-fx-border-color: #fb3449;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
                extraLabel.setTextFill(Color.valueOf("#fb3449"));
                errorFound = true;
            }
        }
        if (!errorFound) {
            Controller.createAccount(getAccountInformation(usernameReg.getText(), (String) accountTypeReg.getValue()), (String) accountTypeReg.getValue());
        }
    }

    private ArrayList<String> getAccountInformation(String username, String type) {
        ArrayList<String> info = new ArrayList<>();
        info.add(username);
        info.add(firstNameReg.getText());
        info.add(lastNameReg.getText());
        info.add(emailReg.getText());
        info.add(phoneReg.getText());
        info.add(passReg.getText());
        if (!type.equals("Manager")) {
            info.add(extraReg.getText());
        }
        return info;
    }

    private void reset() {

        if (usernameReg.getText().equals("")) {
            userLabel.setVisible(false);
        }
        if (passReg.getText().equals("")) {
            passLabel.setVisible(false);
        }
        if (rePassReg.getText().equals("")) {
            rePassLabel.setVisible(false);
        }
        if (firstNameReg.getText().equals("")) {
            fNameLabel.setVisible(false);
        }
        if (lastNameReg.getText().equals("")) {
            lNameLabel.setVisible(false);
        }
        if (emailReg.getText().equals("")) {
            emailLabel.setVisible(false);
        }
        if (phoneReg.getText().equals("")) {
            phoneLabel.setVisible(false);
        }
        if (extraReg.getText().equals("")) {
            extraLabel.setVisible(false);
        }

        userLabel.setTextFill(Color.valueOf("#a9a9a9"));
        passLabel.setTextFill(Color.valueOf("#a9a9a9"));
        rePassLabel.setTextFill(Color.valueOf("#a9a9a9"));
        fNameLabel.setTextFill(Color.valueOf("#a9a9a9"));
        lNameLabel.setTextFill(Color.valueOf("#a9a9a9"));
        emailLabel.setTextFill(Color.valueOf("#a9a9a9"));
        phoneLabel.setTextFill(Color.valueOf("#a9a9a9"));
        extraLabel.setTextFill(Color.valueOf("#a9a9a9"));

        usernameReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
        passReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
        rePassReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
        firstNameReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
        lastNameReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
        emailReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
        phoneReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");
        extraReg.setStyle("-fx-border-color: darkgray;"+"-fx-border-radius: 8;"+"-fx-background-radius: 8");

    }

    public void setAccountType(ActionEvent actionEvent) {
        if (accountTypeReg.getValue().equals("Costumer")) {
            extraLabel.setText("initial credit:");
            extraReg.setPromptText("initial credit");
        } else {
            extraLabel.setText("company name:");
            extraReg.setPromptText("company name");
        }
    }

    public void goLogin(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();

        if (!Controller.getHasHeadManager()) {
            accountTypeReg.setValue("Manager");
            accountTypeReg.setDisable(true);
            extraReg.setDisable(true);
            extraReg.setVisible(false);
        } else {
            accountTypeReg.setValue("Costumer");
            accountTypeReg.getItems().add("Costumer");
            accountTypeReg.getItems().add("Seller");
        }
    }
}
