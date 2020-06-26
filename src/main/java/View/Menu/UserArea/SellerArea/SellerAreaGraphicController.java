package View.Menu.UserArea.SellerArea;
import static Controller.DataBase.*;
import Controller.Controller;
import Controller.SellerAreaController;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
    public Button addOffButton;
    public Pane addOffLabel;
    public Pane addOffPane;
    public Rectangle addOffProducts;
    public Rectangle offPercentRec;
    public Rectangle endDateRec;
    public Rectangle startDateRec;
    public TextField startDateTextField;
    public Pane addOffButtonFinal;
    public TextField endDateTextField;
    public TextField offPercentTextField;
    public ListView addOffProductsList;
    public ImageView goBackToManageOffsArrow;

    public Pane firstProductPane;
    public ImageView productImage1;
    public Label productPrice1;
    public Label productBrand1;
    public Label productCount1;
    public Label productName1;
    public Label productId1;
    public Label productStatus1;
    public Label productExplanation1;
    public ImageView editProduct1;
    public ImageView deleteProduct1;
    public ImageView productImage2;
    public Label productPrice2;
    public Label productBrand2;
    public Label productCount2;
    public Label productName2;
    public Label productId2;
    public Label productStatus2;
    public Label productExplanation2;
    public ImageView editProduct2;
    public ImageView deleteProduct2;
    public ImageView upArrowProducts;
    public ImageView downArrowProducts;
    public Label manageProductsLabel;
    public Button addProductButton;
    public Pane manageProductsPane;
    public Pane secondProductPane;
    public Pane productsHistoryIsEmptyPain;
    public Pane editProductLabel;
    public ImageView goBackToManageProductsArrow;
    public Pane editProductPane;
    public TextField productNameTextField;
    public Rectangle productBrandRec;
    public TextField productBrandTextField;
    public Rectangle productPriceRec;
    public TextField productPriceTextField;
    public Rectangle productExplanationRec;
    public TextField productExplanationField;
    public Button checkProductInformationButton;
    public ImageView correctFormatEditImageProduct;
    public Pane submitProductInformationPane;
    public TextField emailTextField11;
    public Rectangle productCountRec;
    public TextField productCountTextField;
    public Rectangle productNameRec;
    public ImageView wrongFormatEditImageProduct;
    public Pane addProductLabel;
    public Pane addProductPane;
    public Rectangle priceRec;
    public Rectangle imageRec;
    public Rectangle countRec;
    public Rectangle nameRec;
    public Pane addProductButtonFinal;
    public TextField nameTextField;
    public TextField countTextField;
    public TextField priceTextField;
    public Rectangle brandRec;
    public TextField brandTextField;
    public Rectangle categoryRec;
    public TextField categoryTextField;
    public Rectangle explanationRec;
    public TextField explanationTextField;
    private Seller seller;
    private int offsIndex = 0;
    private int productsIndex = 0;
    private int imagesLog1Index = 0;
    private int imagesLog2Index = 0;
    private int imagesOff1Index = 0;
    private int imagesOff2Index = 0;
    private int logIndex = 0;
    private int editProductIndex = 0;
    ArrayList<SellLog> logHistory = new ArrayList<>();
    ArrayList<Off> offs = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    private SimpleDateFormat formatter;
    private Alert deleteProductAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeALlPanes();
        seller = (Seller) Controller.getCurrentUser();
        userInfoPane.setDisable(false);
        userInfoPane.setVisible(true);
        setPersonalInfoLabels();
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Controller.cancelSong();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startSong("src/main/resources/Sound/GeustArea/BackGround.mp3");
            }
        }).start();

    }


    private void setPersonalInfoLabels() {
        userNameLabel.setText(seller.getUsername());
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
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
        addOffButton.setDisable(true);
        addOffButton.setVisible(false);
        imagesOff1Index = 0;
        imagesOff2Index = 0;
        offsIndex = 0;

        addOffPane.setVisible(false);
        addOffPane.setDisable(true);
        addOffLabel.setVisible(false);
        addOffLabel.setDisable(true);

        manageProductsLabel.setVisible(false);
        manageProductsLabel.setDisable(true);
        manageProductsPane.setVisible(false);
        manageProductsPane.setDisable(true);
        addProductButton.setDisable(true);
        addProductButton.setVisible(false);

        editPersonalInfoPane.setDisable(true);
        editPersonalInfoPane.setVisible(false);
        editPersonalInfoLabel.setVisible(false);
        editPersonalInfoLabel.setDisable(true);

        addProductLabel.setDisable(true);
        addProductLabel.setVisible(false);
        addProductPane.setVisible(false);
        addProductPane.setDisable(true);
    }

    public void goToManageOffsPain(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        closeALlPanes();
        restartInsideOfManageOffsPain();
        imagesOff1Index = 0;
        imagesOff2Index = 0;
        offsIndex = 0;
        offs.clear();
        offs.addAll(seller.getOffs());
        addOffButton.setVisible(true);
        addOffButton.setDisable(false);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();

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

    public void goToAddOffPane(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        closeALlPanes();
        restartInsideOfAddOffPain();

        addOffLabel.setDisable(false);
        addOffLabel.setVisible(true);
        addOffPane.setDisable(false);
        addOffPane.setVisible(true);

        setAddOffPaneContents();
    }

    private void setAddOffPaneContents() {
        for (String s : SellerAreaController.getAvailableProductsForOff(seller.getUsername())) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(s);
            checkBox.setSelected(false);
            addOffProductsList.getItems().add(checkBox);
        }
    }

    private void restartInsideOfAddOffPain() {
        startDateRec.setStroke(Color.valueOf("#959595"));
        endDateRec.setStroke(Color.valueOf("#959595"));
        offPercentRec.setStroke(Color.valueOf("#959595"));
        addOffProducts.setStroke(Color.valueOf("#959595"));

        startDateTextField.setText("");
        endDateTextField.setText("");
        offPercentTextField.setText("");
        addOffProductsList.getItems().clear();


    }

    public void addOff(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        startDateRec.setStroke(Color.valueOf("#959595"));
        endDateRec.setStroke(Color.valueOf("#959595"));
        offPercentRec.setStroke(Color.valueOf("#959595"));
        addOffProducts.setStroke(Color.valueOf("#959595"));

        Date date = null;
        boolean errorFound = false;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(startDateTextField.getText());
        } catch (ParseException e) {
            startDateRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(endDateTextField.getText());
        } catch (ParseException e) {
            endDateRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!offPercentTextField.getText().matches("\\d+") || offPercentTextField.getText().equals("")) {
            offPercentRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        } else if ((Integer.parseInt(offPercentTextField.getText()) >= 100) || (Integer.parseInt(offPercentTextField.getText()) <= 0)) {
            offPercentRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (getSelectedProductsId().equals("")) {
            addOffProducts.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!errorFound) {
            ArrayList<String> info = new ArrayList<>();
            info.add(getSelectedProductsId());
            info.add(startDateTextField.getText());
            info.add(endDateTextField.getText());
            info.add(offPercentTextField.getText());
            SellerAreaController.addOff(info);
            goToManageOffsPain(mouseEvent);
        }
    }

    private String getSelectedProductsId() {
        String products = "";
        for (Object item : addOffProductsList.getItems()) {
            if (((CheckBox) item).isSelected()) {
                products += ((CheckBox) item).getText().split("_")[1] + " ";
            }
        }
        return products.trim();
    }

    public void goBackToManageOffs(MouseEvent mouseEvent) {
        goToManageOffsPain(mouseEvent);
    }

    public void goToManageProductsPain(MouseEvent mouseEvent) {
        closeALlPanes();
        restartInsideOfManageProductsPain();

        manageProductsLabel.setVisible(true);
        manageProductsLabel.setDisable(false);
        manageProductsPane.setVisible(true);
        manageProductsPane.setDisable(false);
        addProductButton.setDisable(false);
        addProductButton.setVisible(true);

        productsIndex = 0;
        products.clear();
        products.addAll(seller.getProductsForSale());
        int size = products.size();
        if (size > 2) {
            upArrowProducts.setVisible(true);
            upArrowProducts.setDisable(false);
            downArrowProducts.setVisible(true);
            downArrowProducts.setDisable(false);
        } else if (size == 0) {
            productsHistoryIsEmptyPain.setVisible(true);
            productsHistoryIsEmptyPain.setDisable(false);
        }

        setProductsPaneContents();
    }

    private void setProductsPaneContents() {
        int size = products.size();
        if (size == 0) {
            return;
        }
        Product product1 = products.get(productsIndex);
        firstProductPane.setVisible(true);
        firstProductPane.setDisable(false);
        productName1.setText(product1.getName());
        productId1.setText(String.valueOf(product1.getProductId()));
        productBrand1.setText(product1.getBrand());
        productPrice1.setText(String.valueOf(product1.getPrice(seller)));
        productCount1.setText(String.valueOf(product1.getRemainingItemsForSeller(seller)));
        productExplanation1.setText(product1.getExplanation());
        productImage1.setImage(new Image(product1.getImageAddress()));

        if (size == 1) return;
        Product product2 = products.get(productsIndex + 1);
        secondProductPane.setVisible(true);
        secondProductPane.setDisable(false);
        productName2.setText(product2.getName());
        productId2.setText(String.valueOf(product2.getProductId()));
        productBrand2.setText(product2.getBrand());
        productPrice2.setText(String.valueOf(product2.getPrice(seller)));
        productCount2.setText(String.valueOf(product2.getRemainingItemsForSeller(seller)));
        productExplanation2.setText(product2.getExplanation());
        productImage2.setImage(new Image(product2.getImageAddress()));
    }

    public void seeMoreProducts(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        int size = products.size();
        if (mouseEvent.getSource().equals(upArrowProducts)) {
            productsIndex--;
            if (productsIndex > 0) {
                productsIndex = 0;
            }
        } else if (mouseEvent.getSource().equals(downArrowProducts)) {
            productsIndex++;
            if (productsIndex >= size - 2) {
                productsIndex--;
            }
        }
        setProductsPaneContents();
    }

    private void restartInsideOfManageProductsPain() {
        upArrowProducts.setVisible(false);
        upArrowProducts.setDisable(true);
        downArrowProducts.setVisible(false);
        downArrowProducts.setDisable(true);
        secondProductPane.setDisable(true);
        secondProductPane.setVisible(false);
        firstProductPane.setVisible(false);
        firstProductPane.setDisable(true);
    }

    public void goToEditProductPane(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        closeALlPanes();
        editPersonalInfoPane.setDisable(false);
        editPersonalInfoPane.setVisible(true);
        editPersonalInfoLabel.setVisible(true);
        editPersonalInfoLabel.setDisable(false);
        restartInsideOfEditProductPain();
        if (mouseEvent.getSource().equals(editProduct1)) {
            setInsideOfEditProductPain(productsIndex);
            editProductIndex = productsIndex;
        } else {
            setInsideOfEditProductPain(productsIndex + 1);
            editProductIndex = productsIndex + 1;
        }
    }

    private void setInsideOfEditProductPain(int index) {
        productNameTextField.setText(products.get(index).getName());
        productBrandTextField.setText(products.get(index).getBrand());
        productPriceTextField.setText(String.valueOf(products.get(index).getPrice(seller)));
        productCountTextField.setText(String.valueOf(products.get(index).getRemainingItemsForSeller(seller)));
        productExplanationField.setText(products.get(index).getExplanation());
    }

    private void restartInsideOfEditProductPain() {
        productNameRec.setStroke(Color.valueOf("LIGHTGRAY"));
        productBrandRec.setStroke(Color.valueOf("LIGHTGRAY"));
        productPriceRec.setStroke(Color.valueOf("LIGHTGRAY"));
        productCountRec.setStroke(Color.valueOf("LIGHTGRAY"));
        productExplanationRec.setStroke(Color.valueOf("LIGHTGRAY"));

        submitProductInformationPane.setOpacity(0.5);
        submitProductInformationPane.setDisable(true);
        wrongFormatEditImageProduct.setVisible(false);
        correctFormatEditImageProduct.setVisible(false);
    }

    public void submitProductInformation(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        if (!products.get(editProductIndex).getName().equals(productNameTextField.getText())) {
            SellerAreaController.editProduct("name", productNameTextField.getText(), products.get(editProductIndex).getProductId());
        }
        if (products.get(editProductIndex).getPrice(seller) != Integer.parseInt(productNameTextField.getText())) {
            SellerAreaController.editProduct("price", productPriceTextField.getText(), products.get(editProductIndex).getProductId());
        }
        if (!products.get(editProductIndex).getBrand().equals(productBrandTextField.getText())) {
            SellerAreaController.editProduct("brand", productBrandTextField.getText(), products.get(editProductIndex).getProductId());
        }
        if (products.get(editProductIndex).getRemainingItemsForSeller(seller) != Integer.parseInt(productCountTextField.getText())) {
            SellerAreaController.editProduct("count", productCountTextField.getText(), products.get(editProductIndex).getProductId());
        }
        if (!products.get(editProductIndex).getExplanation().equals(productExplanationField.getText())) {
            SellerAreaController.editProduct("explanation", productExplanationField.getText(), products.get(editProductIndex).getProductId());
        }
        goBackToManageProducts(mouseEvent);
    }

    public void checkProductInformation(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        restartInsideOfEditProductPain();
        boolean errorFound = false;
        if (!productNameTextField.getText().matches("\\w+")) {
            productNameRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        } else if (SellerAreaController.hasProductWithName(productNameTextField.getText())) {
            productNameRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!productBrandTextField.getText().matches("\\w+")) {
            productBrandRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!productPriceTextField.getText().matches("\\d+")) {
            productPriceRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!productCountTextField.getText().matches("\\d+")) {
            productCountRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!productExplanationField.getText().matches("(\\w|\\s)+")) {
            productExplanationRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }

        if (errorFound) {
            wrongFormatEditImageProduct.setVisible(true);
        } else {
            submitProductInformationPane.setOpacity(1);
            submitProductInformationPane.setDisable(false);
            wrongFormatEditImageProduct.setVisible(false);
            correctFormatEditImageProduct.setVisible(true);
        }
    }

    public void doDeleteProduct(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        deleteProductAlert.show();
        if (deleteProductAlert.getResult().equals(ButtonType.CANCEL)) {
            return;
        }
        if (mouseEvent.getSource().equals(deleteProduct1)) {
            SellerAreaController.removeProduct(products.get(productsIndex).getProductId());
        } else {
            SellerAreaController.removeProduct(products.get(productsIndex + 1).getProductId());
        }
        goToManageProductsPain(mouseEvent);
    }

    public void goToAddProductPane(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        closeALlPanes();
        restartInsideOfAddProductPain();

        addProductLabel.setDisable(false);
        addProductLabel.setVisible(true);
        addProductPane.setVisible(true);
        addProductPane.setDisable(false);

    }

    private void restartInsideOfAddProductPain() {
        nameTextField.setText("");
        brandTextField.setText("");
        priceTextField.setText("");
        categoryTextField.setText("");
        countTextField.setText("");
        explanationTextField.setText("");

        nameRec.setStroke(Color.valueOf("#959595"));
        brandRec.setStroke(Color.valueOf("#959595"));
        priceRec.setStroke(Color.valueOf("#959595"));
        categoryRec.setStroke(Color.valueOf("#959595"));
        countRec.setStroke(Color.valueOf("#959595"));
        explanationRec.setStroke(Color.valueOf("#959595"));

        addProductButtonFinal.setOpacity(0.5);
    }

    public void goBackToManageProducts(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        goBackToManageProducts(mouseEvent);
    }

    public void addProduct(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
        restartInsideOfAddProductPain();
        boolean errorFound = false;
        if (SellerAreaController.hasProductWithName(nameTextField.getText())) {
            nameRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if(!isThereAnyCategoryWithName(categoryTextField.getText())){
            nameRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if(!countTextField.getText().matches("\\d+")){
            countRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if(!priceTextField.getText().matches("(\\d+)(.\\d+)?")){
            priceRec.setStroke(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if(errorFound){

        }else{
            addProductButtonFinal.setOpacity(1.0);
        }
    }

    public void goBackToLastPaneFromSellerArea(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((ImageView) mouseEvent.getSource()).getScene();
        Controller.setCurrentPane(Controller.getLastPane());
        scene.setRoot(Controller.getCurrentPane());
        stage.setScene(scene);
        stage.show();
    }

    public void logoutSeller(MouseEvent mouseEvent) {
        Controller.logout();
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
        Controller.setInnerPaneForColor((Pane) ((ScrollPane) mainMenu.getChildren().get(0)).getContent());
        ScrollPane scrollPane = (ScrollPane) mainMenu.getChildren().get(0);
        scrollPane.setPrefHeight(800);
        mainMenu.getChildren().add(mainBar);
        Controller.setCurrentPane(mainMenu);
        scene.setRoot(Controller.getCurrentPane());
        stage.setScene(scene);
        stage.show();
    }
}
