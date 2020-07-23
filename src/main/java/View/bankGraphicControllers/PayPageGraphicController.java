package View.bankGraphicControllers;

import View.View;
import com.sun.javafx.runtime.VersionInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class PayPageGraphicController {
    public ImageView goBackToMainMenuButton;
    public Label alertLabel;
    public Button payButton;
    public TextField receiptIDTextField;

    public void pay(MouseEvent event) {
        alertLabel.setTextFill(Color.RED);
        boolean errorFound = false;
        if (View.client.isTokenExpired()) {
            errorFound = true;
            goToSpecificPage("getToken", event);
            return;
        }
        if(!View.client.isThereAnyReceiptWithID(receiptIDTextField.getText())){
            alertLabel.setText("invalid receipt id");
            return;
        }
        String[] receiptDetails = View.client.getReceiptAndAccountDetailForPay(receiptIDTextField.getText());
        if(receiptDetails[2].equals("true")){
            alertLabel.setText("receipt is paid before");
            return;
        }
        double aMoney = Double.parseDouble(receiptDetails[1]);
        double rMoney = Double.parseDouble(receiptDetails[0]);
        if(rMoney > aMoney){
            alertLabel.setText("source account does not have enough money");
            return;
        }
        View.client.payThisReceipt(receiptIDTextField.getText());
        restartPage();
    }

    public void goBackToMainMenu(MouseEvent mouseEvent) {
        goToSpecificPage("bankMenu", mouseEvent);
    }

    private void goToSpecificPage(String pageName, MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((Node) mouseEvent.getSource()).getScene();
        Pane getToken = null;
        try {
            getToken = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/bank/" + pageName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(getToken);
        stage.setScene(scene);
        stage.show();
    }

    private void restartPage() {
        receiptIDTextField.clear();
        alertLabel.setVisible(false);
    }
}
