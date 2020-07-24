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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerAreaGraphicController implements Initializable {

    public Label userNameLabel;
    public Label managerRoleLabel;
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
    public Label personalInfoLabel;
    public Pane editPersonalInfoLabel;
    public ImageView goBackToPersonalInfoArrow;
    public Label buyHistoryLabel;
    public Pane firstUserPane;
    public ImageView userImage1;
    public Label userNameLabel1;
    public Label roleLabel1;
    public Pane companyNamePane1;
    public Label companyNameLabel1;
    public Label firstNameLabel1;
    public Label lastNameLabel1;
    public Label emailLabel1;
    public Label phoneNumberLabel1;
    public ImageView removeUser1;
    public Pane viewUsersPane;
    public Pane thereIsNoUsers;
    public ImageView userImage2;
    public Label userNameLabel2;
    public Label roleLabel2;
    public Pane companyNamePane2;
    public Label companyNameLabel2;
    public Label firstNameLabel2;
    public Label lastNameLabel2;
    public Label emailLabel2;
    public Label phoneNumberLabel2;
    public ImageView removeUser2;
    public Pane usersArrowAndRec;
    public ImageView downArrowUsers;
    public ImageView upArrowUsers;
    public Pane userInfoPane;
    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label emailLabel;
    public Label phoneNumberLabel;
    public Label roleLabelInPersonalInfo;
    public Pane goToEditInformationPane;
    public Pane secondUserPane;
    public Label discountCodesLabel;
    public Pane viewCategoriesPane;
    public Pane thereIsNoCategoriesPane;
    public Pane editAndSeeCategoriesPane;
    public Pane firstCategoryPane;
    public Label categoryName1;
    public Pane editCategoryPain1;
    public TextField newCategoryNameTextField1;
    public TextField newCategoryAttributeTextField1;
    public Label parentCategory1;
    public Label attributes1;
    public ImageView removeCategory1;
    public ImageView editCategory1;
    public Pane secondCategoryPane;
    public Label categoryName2;
    public Pane editCategoryPain2;
    public TextField newCategoryNameTextField2;
    public TextField newCategoryAttributeTextField2;
    public Label parentCategory2;
    public Label attributes2;
    public ImageView removeCategory2;
    public ImageView editCategory2;
    public Pane categoryButtonsPane;
    public Button addNewCategoryButton;
    public Button editCategoryButton;
    public ImageView goBackToCategoriesMenuImage;
    public Label categoriesLabel;
    public ImageView editTheChanges2;
    public ImageView editTheChanges1;
    public Pane categoryArrowAndRec;
    public ImageView downArrowCategory;
    public ImageView upArrowCategory;
    public Button checkInformationButtonForCreateCategory;
    public Pane createNewCategoryPain;
    public Rectangle newCategoryNameRec;
    public TextField newCategoryNameTextField;
    public Rectangle parentCategoryNameRec;
    public Rectangle attributeRec;
    public Pane submitNewCategoryButton;
    public TextField newAttributeTextField;
    public TextField newParentCategoryTextField;
    public Pane manageRequestsPane;
    public Pane seeRequests;
    public Pane requestPane;
    public Label requestId;
    public Pane editProductRequestPane;
    public Label editProductFieldLabel;
    public Label editProductOldContent;
    public Label editProductNewContent;
    public Pane addProductRequestPane;
    public Label addProductIdLabel;
    public Label addProductBrandLabel;
    public Label addProductNameLabel;
    public Label addProductPriceLabel;
    public Label addProductExpLabel;
    public Label addProductSellerLabel;
    public Pane addOffRequestPane;
    public Label addOffRequestAmountLabel;
    public Label addOffRequestSellerLabel;
    public Label addOffRequestStartLabel;
    public Label addOffRequestEndLabel;
    public Pane addSellerRequestPane;
    public Label addSellerRequestCompanyLabel;
    public Label addSellerRequestSellerLabel;
    public Pane editOffRequestPane;
    public Label editOffNewAmountLabel;
    public Label editOffOldAmountLabel;
    public Label editOffIdLabel;
    public ImageView upArrowRequest;
    public ImageView downArrowRequest;
    public Pane addManagerPane;
    public TextField usernameReg;
    public Label userLabel;
    public Button registerButton;
    public PasswordField passReg;
    public Label passLabel;
    public PasswordField rePassReg;
    public Label rePassLabel;
    public TextField firstNameReg;
    public Label fNameLabel;
    public TextField lastNameReg;
    public Label lNameLabel;
    public TextField emailReg;
    public Label emailLabel3;
    public TextField phoneReg;
    public Label phoneLabel;
    public Pane manageDiscountsPane;
    public Pane discountButtonsPane;
    public Button createDiscountButton;
    public Button viewDiscountsButton;
    public ImageView goBackToDiscountsMenuImage;
    public Pane viewDiscountsPane;
    public Pane firstDiscountCode;
    public Label discountPercent;
    public Label maximumAmount;
    public Label countDiscount;
    public Label usageDiscount;
    public Label startDiscount;
    public Label endDiscount;
    public Label discountId;
    public ImageView downArrowDiscount;
    public ImageView upArrowDiscount;
    public ImageView removeDiscountImage;
    public Rectangle newDiscountDurationRec;
    public TextField newDiscountDuration;
    public Pane submitNewDiscount;
    public Button checkInformationForNewDiscount;
    public TextField newDiscountCount;
    public Rectangle newDiscountCountRec;
    public TextField newDiscountAmount;
    public TextField newDiscountPercent;
    public Rectangle newDiscountPercentRec;
    public Pane newDiscountPane;
    public Rectangle newDiscountAmountRec;
    public Rectangle newDiscountIdRec;
    public TextField newDiscountId;
    public TextField newDiscountStartDate;
    public Rectangle newDiscountDurationRec1;
    public TextField newDiscountEndDate;
    public Pane addSupportPane;
    public TextField usernameSupReg;
    public Label userSupLabel;
    public Button registerSupButton;
    public PasswordField passSupReg;
    public Label passSupLabel;
    public PasswordField rePassSupReg;
    public Label rePassSupLabel;
    public TextField firstNameSupReg;
    public Label fNameSupLabel;
    public TextField lastNameSupReg;
    public Label lNameSupLabel;
    public TextField emailSupReg;
    public Label emailSupLabel;
    public TextField phoneSupReg;
    public Label phoneSupLabel;
    public Label userStatusLabel2;
    public Label userStatusLabel1;
    public Pane viewSoldProductsPane;
    public Pane firstProductPane;
    public Label ProductId1;
    public Label soldCount1;
    public Label ProductName1;
    public Label sellerName1;
    public Button setReceivedStatus1;
    public Pane thereIsNoUsers1;
    public Pane productsArrowAndRec;
    public ImageView downArrowProducts;
    public ImageView upArrowProducts;
    public Pane secondProductPane;
    public Label ProductId2;
    public Label soldCount2;
    public Label ProductName2;
    public Label sellerName2;
    public Button setReceivedStatus2;
    public Label buyerName2;
    public Label buyerName1;
    private ArrayList<String> manager = new ArrayList<>();
    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<String> allUsers = new ArrayList<>();
    private ArrayList<String> requests = new ArrayList<>();
    private ArrayList<String> discountCodes = new ArrayList<>();
    private ArrayList<String> soldHistory = new ArrayList<>();
    private int usersIndex = 0;
    private int categoriesIndex = 0;
    private int discountIndex = 0;
    private int requestsIndex = 0;
    private int soldHistoryIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setManager();
        setCategories();
        setAllUsers();
        setRequests();
        userNameLabel.setText(manager.get(0));
        closeALlPanes();
        setPersonalInfoLabels();
        userInfoPane.setVisible(true);
        userInfoPane.setDisable(false);

        View.client.cancelSong();
        //View.client.startSong("src/main/resources/Sound/ManagerArea/BackGround.mp3");

    }

    private void setManager() {
        manager = View.client.getCurrentUser();
    }

    private void setCategories() {
        categories = View.client.getCategories();
    }

    private void setAllUsers() {
        allUsers = View.client.getAllUsers();
    }

    private void setRequests() {
        requests = View.client.getRequests();
    }

    private void setSoldHistory() {
        soldHistory = View.client.getSoldHistory();
    }

    private void setDiscountCodes() {
        discountCodes = View.client.getDiscountCodes();
    }

    private void setPersonalInfoLabels() {
        firstNameLabel.setText(manager.get(2));
        lastNameLabel.setText(manager.get(3));
        emailLabel.setText(manager.get(4));
        roleLabelInPersonalInfo.setText("manager");
        phoneNumberLabel.setText(manager.get(5));
        userNameLabel.setText(manager.get(0));
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
        firstNameTextField.setText("");
        firstNameTextField.setPromptText(manager.get(2));
        lastNameTextField.setText("");
        lastNameTextField.setPromptText(manager.get(3));
        emailTextField.setText("");
        emailTextField.setPromptText(manager.get(4));
        passwordTextField.setText("");
        passwordTextField.setPromptText(manager.get(1));
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

    public void checkInformation(ActionEvent event) {
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

    private void restartTextFieldRecsInEditPane() {
        passwordRec.setStroke(Color.valueOf("#d3d3d3"));
        firstNameRec.setStroke(Color.valueOf("#d3d3d3"));
        lastNameRec.setStroke(Color.valueOf("#d3d3d3"));
        emailRec.setStroke(Color.valueOf("#d3d3d3"));
    }

    private void closeALlPanes() {
        manageDiscountsPane.setVisible(false);
        manageDiscountsPane.setDisable(true);
        addManagerPane.setVisible(false);
        addManagerPane.setDisable(true);
        manageRequestsPane.setVisible(false);
        manageRequestsPane.setDisable(true);
        seeRequests.setDisable(true);
        seeRequests.setVisible(false);
        thereIsNoUsers.setVisible(false);
        createNewCategoryPain.setDisable(true);
        createNewCategoryPain.setVisible(false);
        usersArrowAndRec.setVisible(false);
        usersArrowAndRec.setDisable(true);
        userInfoPane.setDisable(true);
        userInfoPane.setVisible(false);
//        discountCodesLabel.setVisible(false);
//        discountCodesPain.setVisible(false);
//        discountCodesPain.setDisable(true);
        editPersonalInfoPane.setDisable(true);
        editPersonalInfoPane.setVisible(false);
        editPersonalInfoLabel.setDisable(true);
        editPersonalInfoLabel.setVisible(false);
        personalInfoLabel.setVisible(false);
        buyHistoryLabel.setVisible(false);
        viewUsersPane.setVisible(false);
        viewUsersPane.setDisable(true);
        viewCategoriesPane.setVisible(false);
        viewCategoriesPane.setDisable(true);
        categoryButtonsPane.setVisible(false);
        categoryButtonsPane.setDisable(true);
        editAndSeeCategoriesPane.setVisible(false);
        editAndSeeCategoriesPane.setDisable(true);
        addSupportPane.setDisable(true);
        addSupportPane.setVisible(false);
        viewSoldProductsPane.setVisible(false);
        viewSoldProductsPane.setDisable(true);
//        noDiscountsYet.setVisible(false);
//        imagesLog1Index = 0;
//        imagesLog2Index = 0;
//        logIndex = 0;
//        discountCodesIndex = 0;

        setManager();
    }

    private void disableSomeStuffInEditPane() {
        wrongFormatEditImage.setVisible(true);
        correctFormatEditImage.setVisible(false);
        submitPersonalInformationPane.setDisable(true);
        submitPersonalInformationPane.setOpacity(0.5);
    }

    public void seeProducts(MouseEvent mouseEvent) {
    }

    public void seeCategories(MouseEvent mouseEvent) {
        categoriesIndex = 0;
        closeALlPanes();
        restartInsideCategoryPane();
        viewCategoriesPane.setDisable(false);
        viewCategoriesPane.setVisible(true);
        categoryButtonsPane.setVisible(true);
        categoryButtonsPane.setDisable(false);
        int size = categories.size();
        if (size == 0) {
//            thereIsNoCategoriesPane.setDisable(false);
//            thereIsNoCategoriesPane.setVisible(true);
            return;
        }
    }

    public void goToEditCategoryPane(ActionEvent event) {
        createNewCategoryPain.setVisible(false);
        createNewCategoryPain.setDisable(true);
        editCategoryPain1.setVisible(false);
        editCategoryPain1.setDisable(true);
        editCategoryPain2.setDisable(true);
        editCategoryPain2.setVisible(false);
        editTheChanges1.setVisible(false);
        editTheChanges1.setDisable(true);
        editTheChanges2.setDisable(true);
        editTheChanges2.setVisible(false);
        categoryButtonsPane.setVisible(false);
        categoryButtonsPane.setDisable(true);
        goBackToCategoriesMenuImage.setDisable(false);
        goBackToCategoriesMenuImage.setVisible(true);
        editAndSeeCategoriesPane.setVisible(true);
        editAndSeeCategoriesPane.setDisable(false);
        setEditCategoryPainContents();
    }

    public void goToAddNewCategoryPane(ActionEvent event) {
        closeALlPanes();
        viewCategoriesPane.setDisable(false);
        viewCategoriesPane.setVisible(true);
        createNewCategoryPain.setVisible(true);
        createNewCategoryPain.setDisable(false);
        goBackToCategoriesMenuImage.setDisable(false);
        goBackToCategoriesMenuImage.setVisible(true);
        restartInsideCreateCategoriesTextField();
    }

    private void restartInsideCreateCategoriesTextField() {
        newCategoryNameTextField.clear();
        newAttributeTextField.clear();
        newParentCategoryTextField.clear();
        newCategoryNameRec.setStroke(Color.valueOf("#d3d3d3"));
        parentCategoryNameRec.setStroke(Color.valueOf("#d3d3d3"));
        attributeRec.setStroke(Color.valueOf("#d3d3d3"));
        submitNewCategoryButton.setDisable(true);
        submitNewCategoryButton.setOpacity(0.6);
    }

    private void setEditCategoryPainContents() {
        setCategories();
        restartInsideEditCategoryContents();
        int size = categories.size();
        if (size == 0) return;
        if (size > 2) {
            categoryArrowAndRec.setVisible(true);
            categoryArrowAndRec.setDisable(false);
        } else {
            categoryArrowAndRec.setVisible(false);
            categoryArrowAndRec.setDisable(true);
        }

        firstCategoryPane.setVisible(true);
        firstCategoryPane.setDisable(false);

        String category = categories.get(categoriesIndex);
        categoryName1.setText(category.split("!@")[0]);
        String categoryP = category.split("!@")[2];
        if (categoryP == "null") {
            parentCategory1.setText("-");
        } else {
            parentCategory1.setText(categoryP);
        }
        attributes1.setText(category.split("!@")[1]);

        if (categoriesIndex > size - 2) return;

        secondCategoryPane.setDisable(false);
        secondCategoryPane.setVisible(true);
        category = categories.get(categoriesIndex + 1);
        categoryName2.setText(category.split("!@")[0]);
        categoryP = category.split("!@")[2];
        if (categoryP == "null") {
            parentCategory2.setText("-");
        } else {
            parentCategory2.setText(categoryP);
        }
        attributes2.setText(category.split("!@")[1]);

    }

    private void restartInsideEditCategoryContents() {
        categoryArrowAndRec.setDisable(true);
        categoryArrowAndRec.setVisible(false);
        firstCategoryPane.setVisible(false);
        firstCategoryPane.setDisable(true);
        secondCategoryPane.setDisable(true);
        secondCategoryPane.setVisible(false);
        createNewCategoryPain.setDisable(false);
        createNewCategoryPain.setVisible(false);


//        editCategoryPain1.setVisible(false);
//        editCategoryPain1.setDisable(true);
//        editCategoryPain2.setDisable(true);
//        editCategoryPain2.setVisible(false);

//        editCategory1.setVisible(true);
//        editCategory1.setDisable(false);
//        editCategory2.setDisable(false);
//        editCategory2.setVisible(true);
//
//        editTheChanges1.setVisible(false);
//        editTheChanges1.setDisable(true);
//
//        editTheChanges2.setDisable(true);
//        editTheChanges2.setVisible(false);

        removeCategory1.setDisable(false);
        removeCategory1.setVisible(true);
        removeCategory2.setDisable(false);
        removeCategory2.setVisible(true);
    }

    private void restartInsideCategoryPane() {
        goBackToCategoriesMenuImage.setVisible(false);
        goBackToCategoriesMenuImage.setDisable(true);
        editAndSeeCategoriesPane.setDisable(true);
        editAndSeeCategoriesPane.setVisible(false);
        categoryButtonsPane.setVisible(true);
        categoryButtonsPane.setDisable(false);
        categoryArrowAndRec.setVisible(false);
        categoryArrowAndRec.setDisable(true);
    }

    private void setUsersPaneContents() {
        restartInsideUsersPane();
        int size = allUsers.size();
        if (size == 0) return;
        String[] user = allUsers.get(usersIndex).split("!@");
        if (size > 2) {
            usersArrowAndRec.setVisible(true);
            usersArrowAndRec.setDisable(false);
        }

        firstUserPane.setDisable(false);
        firstUserPane.setVisible(true);

        firstNameLabel1.setText(user[2]);
        lastNameLabel1.setText(user[3]);
        emailLabel1.setText(user[4]);
        phoneNumberLabel1.setText(user[5]);
        userNameLabel1.setText(user[1]);
        roleLabel1.setText(user[0]);
        userStatusLabel1.setText(user[6]);
        if (user[6].equals("Offline")) {
            userStatusLabel1.setTextFill(Color.RED);
        } else {
            userStatusLabel1.setTextFill(Color.GREEN);
        }
        if (user[0].equalsIgnoreCase("seller")) {
            companyNamePane1.setVisible(true);
            companyNameLabel1.setText(user[7]);
        }
        //password ro hazf kardam
        if (user[1].equals(manager.get(0))) {
            removeUser1.setDisable(true);
            removeUser1.setVisible(false);

        } else {
            removeUser1.setDisable(false);
            removeUser1.setVisible(true);
        }


        if (usersIndex > size - 2) return;
        secondUserPane.setDisable(false);
        secondUserPane.setVisible(true);

        String[] user2 = allUsers.get(usersIndex + 1).split("!@");
        firstNameLabel2.setText(user2[2]);
        lastNameLabel2.setText(user2[3]);
        emailLabel2.setText(user2[4]);
        phoneNumberLabel2.setText(user2[5]);
        userNameLabel2.setText(user2[1]);
        roleLabel2.setText(user2[0]);
        userStatusLabel2.setText(user2[6]);
        if (user2[6].equals("Offline")) {
            userStatusLabel2.setTextFill(Color.RED);
        } else {
            userStatusLabel2.setTextFill(Color.GREEN);
        }
        if (user2[0].equalsIgnoreCase("seller")) {
            companyNamePane2.setVisible(true);
            companyNameLabel2.setText(user2[7]);
        }
        if (user2[1].equals(manager.get(0))) {
            removeUser2.setDisable(true);
            removeUser2.setVisible(false);
        } else {
            removeUser2.setDisable(false);
            removeUser2.setVisible(true);
        }

    }

    public void seeUsers(MouseEvent mouseEvent) {
        usersIndex = 0;
        closeALlPanes();
        restartInsideUsersPane();
        viewUsersPane.setVisible(true);
        viewUsersPane.setDisable(false);
        int size = allUsers.size();
        if (size == 0) {
            thereIsNoUsers.setVisible(true);
            thereIsNoUsers.setDisable(false);
            return;
        }
        setUsersPaneContents();
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

    private void restartInsideUsersPane() {
        companyNamePane1.setVisible(false);
        companyNamePane2.setVisible(false);
        firstUserPane.setDisable(true);
        firstUserPane.setVisible(false);
        secondUserPane.setDisable(true);
        secondUserPane.setVisible(false);
        thereIsNoUsers.setVisible(false);
        usersArrowAndRec.setVisible(false);
        usersArrowAndRec.setDisable(true);
        removeUser1.setDisable(true);
        removeUser1.setVisible(false);
        removeUser2.setDisable(true);
        removeUser2.setVisible(false);
    }

    public void removeUser(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(removeUser1)) {
            View.client.deleteUser(userNameLabel1.getText());
        } else if (mouseEvent.getSource().equals(removeUser2)) {
            View.client.deleteUser(userNameLabel2.getText());
        }
        setAllUsers();
        usersIndex = 0;
        setUsersPaneContents();
    }

    public void seeMoreUsers(MouseEvent mouseEvent) {
        int size = allUsers.size();
        if (mouseEvent.getSource().equals(downArrowUsers)) {
            if (usersIndex >= size - 2) return;
            usersIndex += 2;

        } else if (mouseEvent.getSource().equals(upArrowUsers)) {
            if (usersIndex == 0) return;
            usersIndex -= 2;
        }
        setUsersPaneContents();
    }

    public void goBackToCategoriesMenu(MouseEvent mouseEvent) {
        editAndSeeCategoriesPane.setDisable(true);
        editAndSeeCategoriesPane.setVisible(false);

        createNewCategoryPain.setDisable(true);
        createNewCategoryPain.setVisible(false);

        goBackToCategoriesMenuImage.setVisible(false);
        goBackToCategoriesMenuImage.setDisable(true);

        categoryButtonsPane.setVisible(true);
        categoryButtonsPane.setDisable(false);
    }

    public void removeCategory(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(removeCategory1)) {
            View.client.removeCategory(categoryName1.getText());
        } else if (mouseEvent.getSource().equals(removeCategory2)) {
            View.client.removeCategory(categoryName2.getText());
        }
        categoriesIndex = 0;
        setEditCategoryPainContents();
    }

    public void editCategory(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(editCategory1)) {
            editCategory1.setVisible(false);
            editCategory1.setDisable(true);
            editTheChanges1.setDisable(false);
            editTheChanges1.setVisible(true);
            editCategoryPain1.setVisible(true);
            editCategoryPain1.setDisable(false);

        } else if (mouseEvent.getSource().equals(editCategory2)) {
            editCategory2.setVisible(false);
            editCategory2.setDisable(true);
            editTheChanges2.setDisable(false);
            editTheChanges2.setVisible(true);
            editCategoryPain2.setVisible(true);
            editCategoryPain2.setDisable(false);
        } else if (mouseEvent.getSource().equals(editTheChanges1)) {
            editCategory1.setVisible(true);
            editCategory1.setDisable(false);
            editTheChanges1.setDisable(true);
            editTheChanges1.setVisible(false);

            String[] category = categories.get(categoriesIndex).split("!@");
            if (!(newCategoryNameTextField1.getText().matches("") || newCategoryNameTextField1.getText().matches("(\\s)+"))) {
                View.client.editCategory(category[0], "name", newCategoryNameTextField1.getText());
            }
            if (!(newCategoryAttributeTextField1.getText().matches("") || newCategoryAttributeTextField1.getText().matches("(\\s)+"))) {
                View.client.editCategory(category[0], "attributes", newCategoryAttributeTextField1.getText());
            }

            editCategoryPain1.setVisible(false);
            editCategoryPain1.setDisable(true);

        } else if (mouseEvent.getSource().equals(editTheChanges2)) {
            editCategory2.setVisible(true);
            editCategory2.setDisable(false);
            editTheChanges2.setDisable(true);
            editTheChanges2.setVisible(false);

            String[] category = categories.get(categoriesIndex + 1).split("!@");
            if (!(newCategoryNameTextField2.getText().matches("") || newCategoryNameTextField2.getText().matches("(\\s)+"))) {
                View.client.editCategory(category[0], "name", newCategoryNameTextField2.getText());
            }
            if (!(newCategoryAttributeTextField2.getText().matches("") || newCategoryAttributeTextField2.getText().matches("(\\s)+"))) {
                View.client.editCategory(category[0], "attribute", newCategoryAttributeTextField2.getText());
            }

            editCategoryPain2.setVisible(false);
            editCategoryPain2.setDisable(true);
        }
        restartEditCategoryTextFields();
        setEditCategoryPainContents();
    }

    private void restartEditCategoryTextFields() {
        newCategoryAttributeTextField1.clear();
        newCategoryAttributeTextField2.clear();
        newCategoryNameTextField1.clear();
        newCategoryNameTextField2.clear();
        newCategoryAttributeTextField1.deselect();
        newCategoryAttributeTextField2.deselect();
        newCategoryNameTextField1.deselect();
        newCategoryNameTextField2.deselect();
    }

    public void seeRequests(MouseEvent mouseEvent) {
        closeALlPanes();
        restartInsideOFManageRequest();
        requestsIndex = 0;
        manageRequestsPane.setVisible(true);
        manageRequestsPane.setDisable(false);

        int size = requests.size();
        System.out.println(size);
        if (size == 0) {

        } else if (size > 1) {
            turnOnOrOffRequestsArrows(true);
        }
        setRequestsPaneContent();
    }

    private void setRequestsPaneContent() {
        restartInsideOFManageRequest();
        int size = requests.size();
        if (size == 0) {
            return;
        }
        seeRequests.setVisible(true);
        seeRequests.setDisable(false);
        if (size > 1) turnOnOrOffRequestsArrows(true);
        requestPane.setDisable(false);
        requestPane.setVisible(true);
        String[] request = requests.get(requestsIndex).split("!@");
        System.out.println("salam"+request);
        requestId.setText(String.valueOf(request[0]));
        if (request[1].equalsIgnoreCase("Edit Off")) {
            editOffRequestPane.setVisible(true);
            editOffIdLabel.setText(request[5]);
            editOffOldAmountLabel.setText(request[3]);
            editOffNewAmountLabel.setText(request[4]);
        } else if (request[1].equalsIgnoreCase("Add Off")) {
            addOffRequestPane.setVisible(true);
            addOffRequestSellerLabel.setText(request[2]);
            addOffRequestAmountLabel.setText(request[3]);
            addOffRequestStartLabel.setText(request[4]);
            addOffRequestEndLabel.setText(request[5]);
        } else if (request[1].equals("Add Product")) {
            addProductRequestPane.setVisible(true);
            addProductBrandLabel.setText(request[4]);
            addProductExpLabel.setText(request[5]);
            addProductIdLabel.setText(request[2]);
            addProductNameLabel.setText(request[3]);
            addProductPriceLabel.setText(request[6]);
            addProductSellerLabel.setText(request[7]);
        } else if (request[1].equalsIgnoreCase("Add Seller")) {
            addSellerRequestPane.setVisible(true);
            addSellerRequestCompanyLabel.setText(request[3]);
            addSellerRequestSellerLabel.setText(request[2]);
        } else if (request[1].equalsIgnoreCase("Edit Product")) {
            editProductRequestPane.setVisible(true);
            editProductFieldLabel.setText(request[2]);
            editProductOldContent.setText(request[3]);
            editProductNewContent.setText(request[4]);
        }

    }

    private void restartInsideOFManageRequest() {
        editProductRequestPane.setVisible(false);
        addProductRequestPane.setVisible(false);
        editOffRequestPane.setVisible(false);
        addOffRequestPane.setVisible(false);
        addSellerRequestPane.setVisible(false);
        turnOnOrOffRequestsArrows(false);
    }

    private void turnOnOrOffRequestsArrows(boolean flag) {
        if (flag) {
            upArrowRequest.setVisible(true);
            upArrowRequest.setDisable(false);
            downArrowRequest.setVisible(true);
            downArrowRequest.setDisable(false);
        } else {
            upArrowRequest.setVisible(false);
            upArrowRequest.setDisable(true);
            downArrowRequest.setVisible(false);
            downArrowRequest.setDisable(true);
        }
    }

    public void seeMoreCategory(MouseEvent mouseEvent) {
        restartEditCategoryTextFields();
        editTheChanges1.setVisible(false);
        editTheChanges1.setDisable(true);
        editTheChanges2.setDisable(true);
        editTheChanges2.setVisible(false);
        editCategory1.setVisible(true);
        editCategory1.setDisable(false);
        editCategory2.setDisable(false);
        editCategory2.setVisible(true);
        editCategoryPain1.setDisable(true);
        editCategoryPain1.setVisible(false);
        editCategoryPain2.setDisable(true);
        editCategoryPain2.setVisible(false);
        int size = categories.size();
        if (mouseEvent.getSource().equals(downArrowCategory)) {
            if (categoriesIndex >= size - 2) return;
            categoriesIndex += 2;
        } else if (mouseEvent.getSource().equals(upArrowCategory)) {
            if (categoriesIndex == 0) return;
            categoriesIndex -= 2;
        }
        setEditCategoryPainContents();
    }

    public void submitNewCategory(MouseEvent mouseEvent) {
        if (newParentCategoryTextField.getText().equals("")) {
            View.client.addCategory(newCategoryNameTextField.getText(), newAttributeTextField.getText(), null);
        } else {
            View.client.addCategory(newCategoryNameTextField.getText(), newAttributeTextField.getText(), newParentCategoryTextField.getText());
        }

        restartInsideCreateCategoriesTextField();
        editAndSeeCategoriesPane.setDisable(true);
        editAndSeeCategoriesPane.setVisible(false);
        createNewCategoryPain.setDisable(true);
        createNewCategoryPain.setVisible(false);
        goBackToCategoriesMenuImage.setVisible(false);
        goBackToCategoriesMenuImage.setDisable(true);
        categoryButtonsPane.setVisible(true);
        categoryButtonsPane.setDisable(false);
    }

    public void checkInformationForCreateCategory(ActionEvent event) {
        if (View.client.isThereAnyCategoryWithName(newParentCategoryTextField.getText()) && !newCategoryNameTextField.getText().equals("")
                && !newCategoryNameTextField.getText().matches("(\\s)+") && !newAttributeTextField.getText().equals("") && !newAttributeTextField.getText().matches("(\\s)+")) {
            parentCategoryNameRec.setStroke(Color.valueOf("#00ff00"));
            submitNewCategoryButton.setDisable(false);
            submitNewCategoryButton.setOpacity(1.0);
        } else if (newParentCategoryTextField.getText().equals("") && !newAttributeTextField.getText().equals("") && !newAttributeTextField.getText().matches("(\\s)+")
                && !newCategoryNameTextField.getText().equals("")
                && !newCategoryNameTextField.getText().matches("(\\s)+")) {
            parentCategoryNameRec.setStroke(Color.valueOf("#00ff00"));
            submitNewCategoryButton.setDisable(false);
            submitNewCategoryButton.setOpacity(1.0);
        } else {
            submitNewCategoryButton.setDisable(true);
            submitNewCategoryButton.setOpacity(0.6);
            parentCategoryNameRec.setStroke(Color.valueOf("#fb3449"));
        }
    }

    public void acceptRequest(ActionEvent event) {
        View.client.answerRequest("accept", requests.get(requestsIndex).split("!@")[0]);

        requestsIndex = 0;
        setRequests();
        setRequestsPaneContent();
    }

    public void rejectRequest(ActionEvent event) {
        View.client.answerRequest("decline", requests.get(requestsIndex).split("!@")[0]);

        requestsIndex = 0;
        setRequests();
        setRequestsPaneContent();
    }

    public void seeMoreRequests(MouseEvent mouseEvent) {
        int size = requests.size();
        if (mouseEvent.getSource().equals(upArrowRequest)) {
            if (requestsIndex == 0) return;
            requestsIndex--;
        } else if (mouseEvent.getSource().equals(downArrowRequest)) {
            if (requestsIndex == size - 1) return;
            requestsIndex++;
        }
        setRequestsPaneContent();
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        reset();

        if (mouseEvent.getSource().equals(registerButton)) {
            register();
        }
        if (mouseEvent.getSource().equals(registerSupButton)) {
            registerSup();
        }
        if (mouseEvent.getSource().equals(usernameReg)) {
            usernameReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userLabel.setVisible(true);
            userLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(passReg)) {
            passReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passLabel.setVisible(true);
            passLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(rePassReg)) {
            rePassReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassLabel.setVisible(true);
            rePassLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(firstNameReg)) {
            firstNameReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            fNameLabel.setVisible(true);
            fNameLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(lastNameReg)) {
            lastNameReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            lNameLabel.setVisible(true);
            lNameLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(emailReg)) {
            emailReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            emailLabel.setVisible(true);
            emailLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(phoneReg)) {
            phoneReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            phoneLabel.setVisible(true);
            phoneLabel.setTextFill(Color.valueOf("#1a73e8"));
        }

        if (mouseEvent.getSource().equals(usernameSupReg)) {
            usernameSupReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userSupLabel.setVisible(true);
            userSupLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(passSupReg)) {
            passSupReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passSupLabel.setVisible(true);
            passSupLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(rePassSupReg)) {
            rePassSupReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassSupLabel.setVisible(true);
            rePassSupLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(firstNameSupReg)) {
            firstNameSupReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            fNameSupLabel.setVisible(true);
            fNameSupLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(lastNameSupReg)) {
            lastNameSupReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            lNameSupLabel.setVisible(true);
            lNameSupLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(emailSupReg)) {
            emailSupReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            emailSupLabel.setVisible(true);
            emailSupLabel.setTextFill(Color.valueOf("#1a73e8"));
        }
        if (mouseEvent.getSource().equals(phoneSupReg)) {
            phoneSupReg.setStyle("-fx-border-color: #1a73e8;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            phoneSupLabel.setVisible(true);
            phoneSupLabel.setTextFill(Color.valueOf("#1a73e8"));
        }

    }

    private void register() {
        boolean errorFound = false;
        if (View.client.hasUserWithUsername(usernameReg.getText())) {
            usernameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!usernameReg.getText().matches("\\w+")) {
            usernameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passReg.getText().equals(rePassReg.getText())) {
            passReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passLabel.setTextFill(Color.valueOf("#fb3449"));
            rePassReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passReg.getText().matches("\\w+")) {
            passReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!rePassReg.getText().matches("\\w+")) {
            rePassReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!firstNameReg.getText().matches("[a-zA-Z]+")) {
            firstNameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            fNameLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!lastNameReg.getText().matches("[a-zA-Z]+")) {
            lastNameReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            lNameLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!emailReg.getText().matches("(\\w+)@(\\w+)")) {
            emailReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            emailLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!phoneReg.getText().matches("\\d+")) {
            phoneReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            phoneLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!errorFound) {
            View.client.createAccount(getAccountInformation(usernameReg.getText(), "manager"), "manager");
        }
    }

    private ArrayList<String> getAccountInformation(String username, String type) {
        ArrayList<String> info = new ArrayList<>();
        info.add(username);
        info.add(passReg.getText());
        info.add(firstNameReg.getText());
        info.add(lastNameReg.getText());
        info.add(emailReg.getText());
        info.add(phoneReg.getText());
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

        userLabel.setTextFill(Color.valueOf("#a9a9a9"));
        passLabel.setTextFill(Color.valueOf("#a9a9a9"));
        rePassLabel.setTextFill(Color.valueOf("#a9a9a9"));
        fNameLabel.setTextFill(Color.valueOf("#a9a9a9"));
        lNameLabel.setTextFill(Color.valueOf("#a9a9a9"));
        emailLabel.setTextFill(Color.valueOf("#a9a9a9"));
        phoneLabel.setTextFill(Color.valueOf("#a9a9a9"));

        usernameReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        passReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        rePassReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        firstNameReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        lastNameReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        emailReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        phoneReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");

        if (usernameSupReg.getText().equals("")) {
            userSupLabel.setVisible(false);
        }
        if (passSupReg.getText().equals("")) {
            passSupLabel.setVisible(false);
        }
        if (rePassSupReg.getText().equals("")) {
            rePassSupLabel.setVisible(false);
        }
        if (firstNameSupReg.getText().equals("")) {
            fNameSupLabel.setVisible(false);
        }
        if (lastNameSupReg.getText().equals("")) {
            lNameSupLabel.setVisible(false);
        }
        if (emailSupReg.getText().equals("")) {
            emailSupLabel.setVisible(false);
        }
        if (phoneSupReg.getText().equals("")) {
            phoneSupLabel.setVisible(false);
        }

        userSupLabel.setTextFill(Color.valueOf("#a9a9a9"));
        passSupLabel.setTextFill(Color.valueOf("#a9a9a9"));
        rePassSupLabel.setTextFill(Color.valueOf("#a9a9a9"));
        fNameSupLabel.setTextFill(Color.valueOf("#a9a9a9"));
        lNameSupLabel.setTextFill(Color.valueOf("#a9a9a9"));
        emailSupLabel.setTextFill(Color.valueOf("#a9a9a9"));
        phoneSupLabel.setTextFill(Color.valueOf("#a9a9a9"));

        usernameSupReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        passSupReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        rePassSupReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        firstNameSupReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        lastNameSupReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        emailSupReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
        phoneSupReg.setStyle("-fx-border-color: darkgray;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
    }

    public void seeAddManagerPage(MouseEvent mouseEvent) {
        closeALlPanes();
        addManagerPane.setDisable(false);
        addManagerPane.setVisible(true);
    }

    public  void seeAddSupportPage(MouseEvent mouseEvent) {
        closeALlPanes();
        addSupportPane.setVisible(true);
        addSupportPane.setDisable(false);

        usernameSupReg.setText("");
        passSupReg.setText("");
        rePassSupReg.setText("");
        firstNameSupReg.setText("");
        lastNameSupReg.setText("");
        emailSupReg.setText("");
        phoneSupReg.setText("");
    }

    public void registerSup() {
        boolean errorFound = false;
        if (View.client.hasUserWithUsername(usernameSupReg.getText())) {
            usernameSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!usernameSupReg.getText().matches("\\w+")) {
            usernameSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            userSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passSupReg.getText().equals(rePassSupReg.getText())) {
            passSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passSupLabel.setTextFill(Color.valueOf("#fb3449"));
            rePassSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!passSupReg.getText().matches("\\w+")) {
            passSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            passSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!rePassSupReg.getText().matches("\\w+")) {
            rePassSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            rePassSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!firstNameSupReg.getText().matches("[a-zA-Z]+")) {
            firstNameSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            fNameSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!lastNameSupReg.getText().matches("[a-zA-Z]+")) {
            lastNameSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            lNameSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!emailSupReg.getText().matches("(\\w+)@(\\w+)")) {
            emailSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            emailSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!phoneSupReg.getText().matches("\\d+")) {
            phoneSupReg.setStyle("-fx-border-color: #fb3449;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8");
            phoneSupLabel.setTextFill(Color.valueOf("#fb3449"));
            errorFound = true;
        }
        if (!errorFound) {
            ArrayList<String> info = new ArrayList<>();
            info.add(usernameSupReg.getText());
            info.add(passSupReg.getText());
            info.add(firstNameSupReg.getText());
            info.add(lastNameSupReg.getText());
            info.add(emailSupReg.getText());
            info.add(phoneSupReg.getText());
            View.client.createAccount(info, "Support");
            setAllUsers();
        }
    }


    public void seeDiscountCodes(MouseEvent mouseEvent) {
        closeALlPanes();

        discountIndex = 0;
        manageDiscountsPane.setDisable(false);
        manageDiscountsPane.setVisible(true);
        manageDiscountsPane.toFront();
        setViewAndEditDiscountsPaneContent();
        restartInsideMainDiscountPane();
//        restartTextFieldForNewDiscount();
    }

    private void setViewAndEditDiscountsPaneContent() {
        handleDiscountArrows();
        restartInsideViewDiscounts();
        int size = discountCodes.size();
        if (size == 0) return;
        firstDiscountCode.setDisable(false);
        firstDiscountCode.setVisible(true);
        removeDiscountImage.setVisible(true);
        removeDiscountImage.setDisable(false);
        String[] discountCode = discountCodes.get(discountIndex).split("!@");
        discountId.setText(discountCode[0]);
        startDiscount.setText(discountCode[1]);
        endDiscount.setText(discountCode[2]);
        countDiscount.setText(discountCode[4]);
        maximumAmount.setText(discountCode[5]);
        discountPercent.setText(discountCode[3]);
    }

    private void restartInsideViewDiscounts() {
        firstDiscountCode.setDisable(true);
        firstDiscountCode.setVisible(false);
        removeDiscountImage.setVisible(false);
        removeDiscountImage.setDisable(true);
    }

    private void restartInsideMainDiscountPane() {
        newDiscountPane.setVisible(false);
        newDiscountPane.setDisable(true);

        discountButtonsPane.setDisable(false);
        discountButtonsPane.setVisible(true);
        goBackToDiscountsMenuImage.setDisable(true);
        goBackToDiscountsMenuImage.setVisible(false);
        viewDiscountsPane.setVisible(false);
        viewDiscountsPane.setDisable(true);
    }

    private void handleDiscountArrows() {
        int size = discountCodes.size();
        if (size == 0 || size == 1) {
            upArrowDiscount.setVisible(false);
            upArrowDiscount.setDisable(true);
            downArrowDiscount.setVisible(false);
            downArrowDiscount.setDisable(true);
        } else {
            upArrowDiscount.setVisible(true);
            upArrowDiscount.setDisable(false);
            downArrowDiscount.setVisible(true);
            downArrowDiscount.setDisable(false);
        }
    }

    public void goToCreateDiscountPane(ActionEvent event) {
        restartInsideMainDiscountPane();
        discountButtonsPane.setVisible(false);
        discountButtonsPane.setDisable(true);
        goBackToDiscountsMenuImage.setDisable(false);
        goBackToDiscountsMenuImage.setVisible(true);
        newDiscountPane.setVisible(true);
        newDiscountPane.setDisable(false);
//        restartTextFieldForNewDiscount();
    }

    private void restartTextFieldForNewDiscount() {
        newDiscountCount.clear();
        newDiscountAmount.clear();
        newDiscountDuration.clear();
        newDiscountPercent.clear();
        newDiscountCountRec.setStroke(Color.valueOf("d3d3d3"));
        newDiscountAmountRec.setStroke(Color.valueOf("d3d3d3"));
        newDiscountDurationRec.setStroke(Color.valueOf("d3d3d3"));
        newDiscountPercentRec.setStroke(Color.valueOf("d3d3d3"));
        submitNewDiscount.setOpacity(0.6);
        submitNewDiscount.setDisable(true);
    }

    public void goToViewDiscountsPane(ActionEvent event) {
        restartInsideMainDiscountPane();
        goBackToDiscountsMenuImage.setVisible(true);
        goBackToDiscountsMenuImage.setDisable(false);
        discountButtonsPane.setVisible(false);
        discountButtonsPane.setDisable(true);
        viewDiscountsPane.setVisible(true);
        viewDiscountsPane.setDisable(false);
        restartInsideViewDiscounts();
        setViewAndEditDiscountsPaneContent();
    }

    public void removeDiscountCode(MouseEvent mouseEvent) {
        View.client.removeDiscountCode(discountCodes.get(discountIndex).split("!@")[0]);
        discountIndex = 0;
        setViewAndEditDiscountsPaneContent();
    }

    public void goBackToDiscountsMenu(MouseEvent mouseEvent) {
        restartTextFieldForNewDiscount();
        restartInsideMainDiscountPane();
    }

    public void seeMoreDiscounts(MouseEvent mouseEvent) {
        int size = discountCodes.size();
        if (mouseEvent.getSource().equals(upArrowDiscount)) {
            if (discountIndex == 0) return;
            discountIndex--;
        } else if (mouseEvent.getSource().equals(downArrowDiscount)) {
            if (discountIndex == size - 1) return;
            discountIndex++;
        }
        setViewAndEditDiscountsPaneContent();
    }

    public void submitNewDiscount(MouseEvent mouseEvent) {
        if (submitNewDiscount.isDisable()) return;

        ArrayList<String> info = new ArrayList<>();
        info.add(newDiscountId.getText());
        info.add(newDiscountStartDate.getText());
        info.add(newDiscountEndDate.getText());
        info.add(newDiscountPercent.getText());
        info.add(newDiscountAmount.getText());
        info.add(newDiscountCount.getText());
        //info.add(ManagerAreaController.getAllCostumersForDiscount());
        //ManagerAreaController.createDiscountCode(info);

//        restartTextFieldForNewDiscount();
//        restartInsideMainDiscountPane();
    }

    public void checkInformationForNewDiscount(ActionEvent event) {
        boolean flag = true;
        if (!newDiscountDuration.getText().matches("\\d+")) {
            flag = false;
            newDiscountDurationRec.setStroke(Color.valueOf("#fb3449"));
        } else {
            newDiscountDurationRec.setStroke(Color.valueOf("#00eb00"));
        }
        if (!newDiscountAmount.getText().matches("\\d+")) {
            flag = false;
            newDiscountAmountRec.setStroke(Color.valueOf("#fb3449"));
        } else {
            newDiscountAmountRec.setStroke(Color.valueOf("#00eb00"));
        }

        if (!newDiscountCount.getText().matches("\\d+")) {
            flag = false;
            newDiscountCountRec.setStroke(Color.valueOf("#fb3449"));
        } else {
            newDiscountCountRec.setStroke(Color.valueOf("#00eb00"));
        }

        if (!newDiscountPercent.getText().matches("\\d+")) {
            flag = false;
            newDiscountPercentRec.setStroke(Color.valueOf("#fb3449"));
        } else {
            newDiscountPercentRec.setStroke(Color.valueOf("#00eb00"));
        }

        if (flag) {
            submitNewDiscount.setDisable(false);
            submitNewDiscount.setOpacity(1.0);
        } else {
            submitNewDiscount.setOpacity(0.6);
            submitNewDiscount.setDisable(true);
        }
    }

    public void goBackToLastPaneFromManagerArea(MouseEvent mouseEvent) {
        Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = ((ImageView) mouseEvent.getSource()).getScene();
        View.setCurrentPane(View.getLastPane());
        scene.setRoot(View.getCurrentPane());
        stage.setScene(scene);
        stage.show();
    }

    public void logoutManager(MouseEvent mouseEvent) {
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

    public void seeSoldProductsHistoryPage(MouseEvent mouseEvent) {
        closeALlPanes();
        setSoldHistory();
        soldHistoryIndex = 0;
        
        viewSoldProductsPane.setDisable(false);
        viewSoldProductsPane.setVisible(true);

        if (soldHistory.size() == 0) {
            return;
        }
        
        setSoldProductsHistoryPaneContents();
    }

    private void setSoldProductsHistoryPaneContents() {
        restartInsideUsersPane();
        int size = soldHistory.size();
        if (size == 0) return;
        String[] history1 = soldHistory.get(soldHistoryIndex).split("!@");
        if (size > 2) {
            productsArrowAndRec.setVisible(true);
            productsArrowAndRec.setDisable(false);
        }

        firstProductPane.setDisable(false);
        firstProductPane.setVisible(true);

        ProductName1.setText(history1[0]);
        ProductId1.setText(history1[0]);
        soldCount1.setText(history1[0]);
        sellerName1.setText(history1[0]);
        buyerName1.setText(history1[0]);


        if (soldHistoryIndex > size - 2) return;
        secondUserPane.setDisable(false);
        secondUserPane.setVisible(true);

        String[] user2 = soldHistory.get(soldHistoryIndex + 1).split("!@");
        firstNameLabel2.setText(user2[2]);
        lastNameLabel2.setText(user2[3]);
        emailLabel2.setText(user2[4]);
        phoneNumberLabel2.setText(user2[5]);
        userNameLabel2.setText(user2[1]);
        roleLabel2.setText(user2[0]);
        userStatusLabel2.setText(user2[6]);
        if (user2[1].equals(manager.get(0))) {
            removeUser2.setDisable(true);
            removeUser2.setVisible(false);
        } else {
            removeUser2.setDisable(false);
            removeUser2.setVisible(true);
        }
    }

    public void setReceivedStatus(MouseEvent mouseEvent) {
    }

    public void seeMoreHistory(MouseEvent mouseEvent) {
        int size = soldHistory.size();
        if (mouseEvent.getSource().equals(downArrowProducts)) {
            if (soldHistoryIndex >= size - 2) return;
            soldHistoryIndex += 2;

        } else if (mouseEvent.getSource().equals(downArrowProducts)) {
            if (soldHistoryIndex == 0) return;
            soldHistoryIndex -= 2;
        }
        setSoldProductsHistoryPaneContents();
    }
}
