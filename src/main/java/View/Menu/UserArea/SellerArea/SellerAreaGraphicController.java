package View.Menu.UserArea.SellerArea;

import Controller.Controller;
import Models.Log.BuyLog;
import Models.Log.Log;
import Models.Log.SellLog;
import Models.Off;
import Models.Product;
import Models.User.Costumer;
import Controller.CostumerAreaController;
import Models.User.Guest;
import Models.User.Seller;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Controller.CostumerAreaController.*;

public class SellerAreaGraphicController implements Initializable {

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
    public Pane sellHistoryPane;
    public Label sellHistoryLabel;
    public ImageView seeMoreProductsImageLog1;
    public ImageView seeMoreProductsImageLog2;
    public ImageView seeLessProductsImageLog2;
    public ImageView seeLessProductsImageLog1;
    public Pane sellHistoryIsEmptyPain;
    public ImageView costumerProfileImage;
    
    public Label manageOffsLabel;
    public Pane manageOffsPane;
    public ImageView image41;
    public ImageView image42;
    public ImageView image43;
    public ImageView image44;
    public ImageView image45;
    public ImageView image46;
    public ImageView image47;
    public ImageView image48;
    public Label offPercentage2;
    public Label offIdLabel2;
    public Label offStatus2;
    public ImageView seeMoreProductsImageOff2;
    public ImageView seeLessProductsImageOff2;
    public Label startTime2;
    public Label endTime2;
    public ImageView image31;
    public ImageView image32;
    public ImageView image33;
    public ImageView image34;
    public ImageView image35;
    public Label offPercentage1;
    public Label offIdLabel1;
    public Label offStatus1;
    public ImageView seeMoreProductsImageOff1;
    public ImageView seeLessProductsImageOff1;
    public Label startTime1;
    public Label endTime1;
    public ImageView upArrowOffs;
    public ImageView downArrowOffs;
    public Pane offHistoryIsEmptyPain;
    public Pane secondOffPane;
    public Pane firstOffPane;
    public ImageView image36;
    public ImageView image37;
    public ImageView image38;
    private Seller seller;
    private int offsIndex = 0;
    private int imagesLog1Index = 0;
    private int imagesLog2Index = 0;
    private int imagesOff1Index = 0;
    private int imagesOff2Index = 0;
    private int logIndex = 0;
    ArrayList<SellLog> logHistory = new ArrayList<>();
    ArrayList<Off> offs = new ArrayList<>();
    private SimpleDateFormat formatter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeALlPanes();
        seller = (Seller) Controller.getCurrentUser();
        userInfoPane.setDisable(false);
        userInfoPane.setVisible(true);
        setPersonalInfoLabels();
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }


    private void setPersonalInfoLabels() {
        firstNameLabel.setText(seller.getFirstName());
        lastNameLabel.setText(seller.getLastName());
        emailLabel.setText(seller.getEMail());
        creditLabel.setText(String.valueOf(seller.getCredit()));
        phoneNumberLabel.setText(String.valueOf(seller.getPhoneNumber()));
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
        creditTextField.setPromptText(String.valueOf(seller.getCredit()));
        firstNameTextField.setText("");
        firstNameTextField.setPromptText(seller.getFirstName());
        lastNameTextField.setText("");
        lastNameTextField.setPromptText(seller.getLastName());
        emailTextField.setText("");
        emailTextField.setPromptText(seller.getEMail());
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
            seller.setCredit(Double.parseDouble(creditTextField.getText()));

        if (!emailTextField.getText().matches("(\\s+)?")) {
            seller.setEMail(emailTextField.getText());
        }
        if (!firstNameTextField.getText().matches("(\\s+)?")) {
            seller.setFirstName(firstNameTextField.getText());
        }
        if (!lastNameTextField.getText().matches("(\\s+)?")) {
            seller.setLastName(lastNameTextField.getText());
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
            SellLog sellLog = logHistory.get(logIndex);
            size = sellLog.getSoldProduct().size();
            if (imagesLog1Index + 8 >= size + 1) return;
            imagesLog1Index++;
            setFirstImage();

        } else if (mouseEvent.getSource().equals(seeMoreProductsImageLog2)) {
            SellLog sellLog = logHistory.get(logIndex + 1);
            size = sellLog.getSoldProduct().size();
            if (imagesLog2Index + 8 >= size + 1) return;
            imagesLog2Index++;
            setSecondImage();

        } else if (mouseEvent.getSource().equals(seeLessProductsImageLog1)) {
            SellLog sellLog = logHistory.get(logIndex);
            size = sellLog.getSoldProduct().size();
            if (imagesLog1Index == 0) return;
            imagesLog1Index--;
            setFirstImage();

        } else if (mouseEvent.getSource().equals(seeLessProductsImageLog2)) {
            SellLog sellLog = logHistory.get(logIndex + 1);
            size = sellLog.getSoldProduct().size();
            if (imagesLog2Index == 0) return;
            imagesLog2Index--;
            setSecondImage();
        }
    }

    public void goToSellHistoryPane(MouseEvent mouseEvent) {
        closeALlPanes();
        restartInsideOfSellHistoryPane();
        imagesLog1Index = 0;
        imagesLog2Index = 0;
        logIndex = 0;
        logHistory.clear();
        logHistory.addAll(seller.getSellHistory());
        sellHistoryLabel.setVisible(true);
        sellHistoryLabel.setOpacity(1);
        sellHistoryPane.setDisable(false);
        sellHistoryPane.setVisible(true);
        int size = logHistory.size();
        if (size > 2) {
            upArrowLogs.setVisible(true);
            upArrowLogs.setDisable(false);
            downArrowLogs.setVisible(true);
            downArrowLogs.setDisable(false);
        } else if (size == 0) {
            sellHistoryIsEmptyPain.setVisible(true);
            sellHistoryIsEmptyPain.setDisable(false);
        }
        setLogPaneContents();
    }

    private void setLogPaneContents() {
        restartInsideOfSellHistoryPane();
        int size = logHistory.size();
        if (size == 0) {
            return;
        }
        SellLog sellLog1 = logHistory.get(logIndex);
        firstLogPane.setVisible(true);
        firstLogPane.setDisable(false);
        dateLabelLog1.setText(formatter.format(sellLog1.getLogDate()));
        totalPriceLog1.setText(String.valueOf(sellLog1.getReceivedAmount()));
        logIdLabelLog11.setText(String.valueOf(sellLog1.getLogId()));
        receiveStatusLog1.setText(String.valueOf(sellLog1.getDeliveryStatus()));
        if (sellLog1.getSoldProduct().size() < 9) {
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
        SellLog sellLog2 = logHistory.get(logIndex + 1);

        secondLogPane.setVisible(true);
        secondLogPane.setDisable(false);
        dateLabelLog2.setText(formatter.format(sellLog2.getLogDate()));
        totalPriceLog2.setText(String.valueOf(sellLog2.getReceivedAmount()));
        logIdLabelLog2.setText(String.valueOf(sellLog2.getLogId()));
        receiveStatusLog2.setText(String.valueOf(sellLog2.getDeliveryStatus()));
        if (sellLog2.getSoldProduct().size() < 9) {
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
        ArrayList<Product> products = logHistory.get(logIndex).getSoldProduct();
        for (int i = 0; i < 8 && i < products.size() - 1; i++) {
            Product product = products.get(i);
            if (i == 0) {
                setProductsImage(image11, product.getImageAddress());
            } else if (i == 1) {
                setProductsImage(image12, product.getImageAddress());
            } else if (i == 2) {
                setProductsImage(image13, product.getImageAddress());
            } else if (i == 3) {
                setProductsImage(image14, product.getImageAddress());
            } else if (i == 4) {
                setProductsImage(image15, product.getImageAddress());
            } else if (i == 5) {
                setProductsImage(image16, product.getImageAddress());
            } else if (i == 6) {
                setProductsImage(image17, product.getImageAddress());
            } else if (i == 7) {
                setProductsImage(image18, product.getImageAddress());
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
        ArrayList<Product> products = logHistory.get(logIndex + 1).getSoldProduct();
        for (int i = 0; i < 8 && i < products.size() - 1; i++) {
            Product product = products.get(i);
            if (i == 0) {
                setProductsImage(image21, product.getImageAddress());
            } else if (i == 1) {
                setProductsImage(image22, product.getImageAddress());
            } else if (i == 2) {
                setProductsImage(image23, product.getImageAddress());
            } else if (i == 3) {
                setProductsImage(image24, product.getImageAddress());
            } else if (i == 4) {
                setProductsImage(image25, product.getImageAddress());
            } else if (i == 5) {
                setProductsImage(image26, product.getImageAddress());
            } else if (i == 6) {
                setProductsImage(image27, product.getImageAddress());
            } else if (i == 7) {
                setProductsImage(image28, product.getImageAddress());
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

    private void restartInsideOfSellHistoryPane() {
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
        manageOffsLabel.setVisible(false);
        editPersonalInfoPane.setDisable(true);
        editPersonalInfoPane.setVisible(false);
        editPersonalInfoLabel.setDisable(true);
        editPersonalInfoLabel.setVisible(false);
        personalInfoLabel.setVisible(false);

        sellHistoryLabel.setVisible(false);
        sellHistoryPane.setVisible(false);
        sellHistoryPane.setDisable(true);
        sellHistoryIsEmptyPain.setVisible(false);
        sellHistoryIsEmptyPain.setDisable(true);
        imagesLog1Index = 0;
        imagesLog2Index = 0;
        logIndex = 0;

        manageOffsLabel.setVisible(false);
        manageOffsPane.setVisible(false);
        manageOffsPane.setDisable(true);
        offHistoryIsEmptyPain.setVisible(false);
        offHistoryIsEmptyPain.setDisable(true);
        imagesOff1Index = 0;
        imagesOff2Index = 0;
        offsIndex = 0;
    }

    public void goToManageOffsPain(MouseEvent mouseEvent) {
        closeALlPanes();
        restartInsideOfManageOffsPain();
        imagesOff1Index = 0;
        imagesOff2Index = 0;
        offsIndex = 0;
        offs.clear();
        offs.addAll(seller.getOffs());
        manageOffsLabel.setVisible(true);
        manageOffsLabel.setOpacity(1);
        manageOffsPane.setDisable(false);
        manageOffsPane.setVisible(true);
        int size = offs.size();
        if (size > 2) {
            upArrowOffs.setVisible(true);
            upArrowOffs.setDisable(false);
            downArrowOffs.setVisible(true);
            downArrowOffs.setDisable(false);
        } else if (size == 0) {
            offHistoryIsEmptyPain.setVisible(true);
            offHistoryIsEmptyPain.setDisable(false);
        }
        setOffPaneContents();
    }

    private void setOffPaneContents() {
        restartInsideOfManageOffsPain();
        int size = offs.size();
        if (size == 0) {
            return;
        }
        Off off1 = offs.get(offsIndex);
        firstOffPane.setVisible(true);
        firstOffPane.setDisable(false);
        startTime2.setText(formatter.format(off1.getStartDate()));
        endTime2.setText(formatter.format(off1.getEndDate()));
        offPercentage2.setText(String.valueOf(off1.getOffAmount()));
        offIdLabel2.setText(String.valueOf(off1.getOffId()));
        offStatus2.setText(String.valueOf(off1.getOffStatus()));
        if (off1.getProducts().size() < 9) {
            seeMoreProductsImageOff2.setDisable(true);
            seeMoreProductsImageOff2.setVisible(false);
            seeLessProductsImageOff2.setVisible(false);
            seeLessProductsImageOff2.setDisable(true);
        } else {
            seeMoreProductsImageOff2.setDisable(false);
            seeMoreProductsImageOff2.setVisible(true);
            seeLessProductsImageOff2.setVisible(true);
            seeLessProductsImageOff2.setDisable(false);
        }
        setFirstImageOff();

        if (size == 1) return;
        Off off2 = offs.get(offsIndex + 1);
        secondOffPane.setVisible(true);
        secondOffPane.setDisable(false);
        startTime1.setText(formatter.format(off2.getStartDate()));
        endTime1.setText(formatter.format(off2.getEndDate()));
        offPercentage1.setText(String.valueOf(off2.getOffAmount()));
        offIdLabel1.setText(String.valueOf(off2.getOffId()));
        offStatus1.setText(String.valueOf(off2.getOffStatus()));
        if (off2.getProducts().size() < 9) {
            seeMoreProductsImageOff1.setDisable(true);
            seeMoreProductsImageOff1.setVisible(false);
            seeLessProductsImageOff1.setVisible(false);
            seeLessProductsImageOff1.setDisable(true);
        } else {
            seeMoreProductsImageOff1.setDisable(false);
            seeMoreProductsImageOff1.setVisible(true);
            seeLessProductsImageOff1.setVisible(true);
            seeLessProductsImageOff1.setDisable(false);
        }
        setSecondImageOff();
    }

    private void setSecondImageOff() {
        clearOffImages2();
        ArrayList<Product> products = offs.get(offsIndex + 1).getProducts();
        for (int i = 0; i < 8 && i < products.size() - 1; i++) {
            Product product = products.get(i);
            if (i == 0) {
                setProductsImage(image31, product.getImageAddress());
            } else if (i == 1) {
                setProductsImage(image32, product.getImageAddress());
            } else if (i == 2) {
                setProductsImage(image33, product.getImageAddress());
            } else if (i == 3) {
                setProductsImage(image34, product.getImageAddress());
            } else if (i == 4) {
                setProductsImage(image35, product.getImageAddress());
            } else if (i == 5) {
                setProductsImage(image36, product.getImageAddress());
            } else if (i == 6) {
                setProductsImage(image37, product.getImageAddress());
            } else if (i == 7) {
                setProductsImage(image38, product.getImageAddress());
            }
        }
    }

    private void clearOffImages2() {
        image31.setImage(null);
        image32.setImage(null);
        image33.setImage(null);
        image34.setImage(null);
        image35.setImage(null);
        image36.setImage(null);
        image37.setImage(null);
        image38.setImage(null);
    }

    private void setFirstImageOff() {
        clearOffImages1();
        ArrayList<Product> products = offs.get(offsIndex).getProducts();
        for (int i = 0; i < 8 && i < products.size() - 1; i++) {
            Product product = products.get(i);
            if (i == 0) {
                setProductsImage(image41, product.getImageAddress());
            } else if (i == 1) {
                setProductsImage(image42, product.getImageAddress());
            } else if (i == 2) {
                setProductsImage(image43, product.getImageAddress());
            } else if (i == 3) {
                setProductsImage(image44, product.getImageAddress());
            } else if (i == 4) {
                setProductsImage(image45, product.getImageAddress());
            } else if (i == 5) {
                setProductsImage(image46, product.getImageAddress());
            } else if (i == 6) {
                setProductsImage(image47, product.getImageAddress());
            } else if (i == 7) {
                setProductsImage(image48, product.getImageAddress());
            }
        }
    }

    private void clearOffImages1() {
        image41.setImage(null);
        image42.setImage(null);
        image43.setImage(null);
        image44.setImage(null);
        image45.setImage(null);
        image46.setImage(null);
        image47.setImage(null);
        image48.setImage(null);
    }

    private void restartInsideOfManageOffsPain() {
        upArrowOffs.setVisible(false);
        upArrowOffs.setDisable(true);
        downArrowOffs.setVisible(false);
        downArrowOffs.setDisable(true);
        seeMoreProductsImageOff1.setDisable(true);
        seeMoreProductsImageOff1.setVisible(false);
        seeMoreProductsImageOff2.setDisable(true);
        seeMoreProductsImageOff2.setVisible(false);
        secondOffPane.setDisable(true);
        secondOffPane.setVisible(false);
        firstOffPane.setVisible(false);
        firstOffPane.setDisable(true);
    }

    public void seeMoreOffs(MouseEvent mouseEvent) {
        int size = offs.size();
        if (mouseEvent.getSource().equals(upArrowOffs)) {
            offsIndex--;
            if (offsIndex > 0) {
                offsIndex = 0;
            }
        } else if (mouseEvent.getSource().equals(downArrowOffs)) {
            offsIndex++;
            if (offsIndex >= size - 2) {
                offsIndex--;
            }
        }
        setOffPaneContents();
    }

    public void seeMorePicturesOff(MouseEvent mouseEvent) {
        int size = 0;

        if (mouseEvent.getSource().equals(seeMoreProductsImageOff2)) {
            Off off = offs.get(offsIndex);
            size = off.getProducts().size();
            if (imagesOff1Index + 8 >= size + 1) return;
            imagesOff1Index++;
            setFirstImageOff();

        } else if (mouseEvent.getSource().equals(seeMoreProductsImageOff1)) {
            Off off = offs.get(offsIndex + 1);
            size = off.getProducts().size();
            if (imagesOff2Index + 8 >= size + 1) return;
            imagesOff2Index++;
            setSecondImageOff();

        } else if (mouseEvent.getSource().equals(seeLessProductsImageOff2)) {
            Off off = offs.get(offsIndex);
            size = off.getProducts().size();
            if (imagesOff1Index == 0) return;
            imagesOff1Index--;
            setFirstImageOff();

        } else if (mouseEvent.getSource().equals(seeLessProductsImageOff1)) {
            Off off = offs.get(offsIndex + 1);
            size = off.getProducts().size();
            if (imagesOff2Index == 0) return;
            imagesOff2Index--;
            setSecondImageOff();
        }
    }

    public void goToManageProductsPain(MouseEvent mouseEvent) {
        closeALlPanes();
        restartInsideOfManageProductsPain();
    }

    private void restartInsideOfManageProductsPain() {
    }
}
