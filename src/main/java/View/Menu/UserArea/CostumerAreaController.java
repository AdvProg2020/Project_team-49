package View.Menu.UserArea;

import Controller.Controller;
import Models.User.Costumer;
import Models.User.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class CostumerAreaController implements Initializable {

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
    private Costumer costumer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        costumer = (Costumer) Controller.getCurrentUser();
        setPersonalInfoLabels();


    }


    private void setPersonalInfoLabels() {
        firstNameLabel.setText(costumer.getFirstName());
        lastNameLabel.setText(costumer.getLastName());
        emailLabel.setText(costumer.getEMail());
        creditLabel.setText(String.valueOf(costumer.getCredit()));
        phoneNumberLabel.setText(String.valueOf(costumer.getPhoneNumber()));
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
        creditTextField.setPromptText(String.valueOf(costumer.getCredit()));
        firstNameTextField.setText("");
        firstNameTextField.setPromptText(costumer.getFirstName());
        lastNameTextField.setText("");
        lastNameTextField.setPromptText(costumer.getLastName());
        emailTextField.setText("");
        emailTextField.setPromptText(costumer.getEMail());
        correctFormatEditImage.setVisible(false);
        wrongFormatEditImage.setVisible(false);
        submitPersonalInformationPane.setDisable(true);
        submitPersonalInformationPane.setOpacity(0.5);
    }

    public void goBackToPersonalInfo(MouseEvent mouseEvent) {
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
            costumer.setCredit(Double.parseDouble(creditTextField.getText()));

        if (!emailTextField.getText().matches("(\\s+)?")) {
            costumer.setEMail(emailTextField.getText());
        }
        if (!firstNameTextField.getText().matches("(\\s+)?")) {
            costumer.setFirstName(firstNameTextField.getText());
        }
        if (!lastNameTextField.getText().matches("(\\s+)?")) {
            costumer.setLastName(lastNameTextField.getText());
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

}
