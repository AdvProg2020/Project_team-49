package View.Menu.UserArea;

import Controller.DataBase;
import Controller.Controller;
import Models.Category;
import Models.Off;
import Models.User.Manager;
import Controller.ManagerAreaController;
import Models.User.Request.*;
import Models.User.Seller;
import Models.User.User;
import View.Menu.UserArea.ManagerArea.ManagerArea;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.xml.crypto.Data;
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
    public Pane ManageManagersPain;
    public Button addManagerButton;
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
    private Manager manager;
    private int usersIndex = 0;
    private int categoriesIndex = 0;
    private int requestsIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager = (Manager) Controller.getCurrentUser();
        String[] info = Controller.getCurrentUserSpecifications().split(",");
        userNameLabel.setText(info[0]);
        closeALlPanes();
        setPersonalInfoLabels();
        userInfoPane.setVisible(true);
        userInfoPane.setDisable(false);

    }

    private void setPersonalInfoLabels() {
        String[] info = Controller.getCurrentUserSpecifications().split(",");
        firstNameLabel.setText(info[1]);
        lastNameLabel.setText(info[2]);
        emailLabel.setText(info[3]);
        roleLabelInPersonalInfo.setText("manager");
        phoneNumberLabel.setText(info[4]);
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
        String[] info = Controller.getCurrentUserSpecifications().split(",");
        firstNameTextField.setText("");
        firstNameTextField.setPromptText(info[1]);
        lastNameTextField.setText("");
        lastNameTextField.setPromptText(info[2]);
        emailTextField.setText("");
        emailTextField.setPromptText(info[3]);
        passwordTextField.setText("");
        passwordTextField.setPromptText(manager.getPassword());
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
            manager.setPassword(passwordTextField.getText());
        }
        if (!emailTextField.getText().matches("(\\s+)?")) {
            manager.setEMail(emailTextField.getText());
        }
        if (!firstNameTextField.getText().matches("(\\s+)?")) {
            manager.setFirstName(firstNameTextField.getText());
        }
        if (!lastNameTextField.getText().matches("(\\s+)?")) {
            manager.setLastName(lastNameTextField.getText());
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
//        noDiscountsYet.setVisible(false);
//        imagesLog1Index = 0;
//        imagesLog2Index = 0;
//        logIndex = 0;
//        discountCodesIndex = 0;
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
        int size = DataBase.getAllCategories().size();
        if (size == 0) {
            thereIsNoCategoriesPane.setVisible(true);
            thereIsNoCategoriesPane.setDisable(false);
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
        restartInsideEditCategoryContents();
        int size = DataBase.getAllCategories().size();
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

        Category category = DataBase.getAllCategories().get(categoriesIndex);
        categoryName1.setText(category.getName());
        Category categoryP = category.getParentCategory();
        if (categoryP == null) {
            parentCategory1.setText("-");
        } else {
            parentCategory1.setText(categoryP.getName());
        }
        attributes1.setText(category.getSpecialAttributes());

        if (categoriesIndex > size - 2) return;

        secondCategoryPane.setDisable(false);
        secondCategoryPane.setVisible(true);
        category = DataBase.getAllCategories().get(categoriesIndex + 1);
        categoryName2.setText(category.getName());
        categoryP = category.getParentCategory();
        if (categoryP == null) {
            parentCategory2.setText("-");
        } else {
            parentCategory2.setText(categoryP.getName());
        }
        attributes2.setText(category.getSpecialAttributes());

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
        int size = DataBase.getAllUsers().size();
        if (size == 0) return;
        User user = DataBase.getAllUsers().get(usersIndex);
        if (size > 2) {
            usersArrowAndRec.setVisible(true);
            usersArrowAndRec.setDisable(false);
        }

        firstUserPane.setDisable(false);
        firstUserPane.setVisible(true);

        firstNameLabel1.setText(user.getFirstName());
        lastNameLabel1.setText(user.getLastName());
        emailLabel1.setText(user.getEMail());
        phoneNumberLabel1.setText(String.valueOf(user.getPhoneNumber()));
        userNameLabel1.setText(user.getUsername());
        roleLabel1.setText(user.getType());
        if (user.getType().equalsIgnoreCase("seller")) {
            companyNamePane1.setVisible(true);
            companyNameLabel1.setText(((Seller) user).getCompanyName());
        }
        if ((user.getUsername().equals(manager.getUsername()) && user.getPassword().equals(manager.getPassword()))) {
            removeUser1.setDisable(true);
            removeUser1.setVisible(false);

        } else {
            removeUser1.setDisable(false);
            removeUser1.setVisible(true);
        }


        if (usersIndex > size - 2) return;
        secondUserPane.setDisable(false);
        secondUserPane.setVisible(true);

        User user2 = DataBase.getAllUsers().get(usersIndex + 1);
        firstNameLabel2.setText(user2.getFirstName());
        lastNameLabel2.setText(user2.getLastName());
        emailLabel2.setText(user2.getEMail());
        phoneNumberLabel2.setText(String.valueOf(user2.getPhoneNumber()));
        userNameLabel2.setText(user2.getUsername());
        roleLabel2.setText(user2.getType());
        if (user2.getType().equalsIgnoreCase("seller")) {
            companyNamePane2.setVisible(true);
            companyNameLabel2.setText(((Seller) user2).getCompanyName());
        }
        if ((user2.getUsername().equals(manager.getUsername()) && user2.getPassword().equals(manager.getPassword()))) {
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
        int size = DataBase.getAllUsers().size();
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

    public void seeDiscountCodes(MouseEvent mouseEvent) {
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
            ManagerAreaController.deleteUser(userNameLabel1.getText());
        } else if (mouseEvent.getSource().equals(removeUser2)) {
            ManagerAreaController.deleteUser(userNameLabel2.getText());
        }
        usersIndex = 0;
        setUsersPaneContents();
    }

    public void seeMoreUsers(MouseEvent mouseEvent) {
        int size = DataBase.getAllUsers().size();
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
            ManagerAreaController.removeCategory(categoryName1.getText());
        } else if (mouseEvent.getSource().equals(removeCategory2)) {
            ManagerAreaController.removeCategory(categoryName2.getText());
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

            Category category = DataBase.getAllCategories().get(categoriesIndex);
            if (!(newCategoryNameTextField1.getText().matches("") || newCategoryNameTextField1.getText().matches("(\\s)+"))) {
                category.setName(newCategoryNameTextField1.getText());
            }
            if (!(newCategoryAttributeTextField1.getText().matches("") || newCategoryAttributeTextField1.getText().matches("(\\s)+"))) {
                category.setSpecialAttributes(newCategoryAttributeTextField1.getText());
            }

            editCategoryPain1.setVisible(false);
            editCategoryPain1.setDisable(true);

        } else if (mouseEvent.getSource().equals(editTheChanges2)) {
            editCategory2.setVisible(true);
            editCategory2.setDisable(false);
            editTheChanges2.setDisable(true);
            editTheChanges2.setVisible(false);

            Category category = DataBase.getAllCategories().get(categoriesIndex + 1);
            if (!(newCategoryNameTextField2.getText().matches("") || newCategoryNameTextField2.getText().matches("(\\s)+"))) {
                category.setName(newCategoryNameTextField2.getText());
            }
            if (!(newCategoryAttributeTextField2.getText().matches("") || newCategoryAttributeTextField2.getText().matches("(\\s)+"))) {
                category.setSpecialAttributes(newCategoryAttributeTextField2.getText());
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

        int size = DataBase.getAllActiveRequests().size();
        if (size == 0) {

        } else if (size > 1) {
            turnOnOrOffRequestsArrows(true);
        }
        setRequestsPaneContent();
    }

    private void setRequestsPaneContent() {
        restartInsideOFManageRequest();
        int size = DataBase.getAllActiveRequests().size();
        if (size == 0) {
            return;
        }
        if (size > 1) turnOnOrOffRequestsArrows(true);
        requestPane.setDisable(false);
        requestPane.setVisible(true);
        Request request = DataBase.getAllActiveRequests().get(requestsIndex);
        requestId.setText(String.valueOf(request.getRequestId()));
        if (request.getType().equals("Edit Off")) {
            editOffRequestPane.setVisible(true);
            EditOffRequest info = ((EditOffRequest) request);
            Off off = info.getOff();
            editOffIdLabel.setText(String.valueOf(off.getOffId()));
            editOffOldAmountLabel.setText(info.getOldContent());
            editOffNewAmountLabel.setText(info.getNewContent());
        } else if (request.getType().equals("Add Off")) {
            String[] info = ((AddOffRequest) request).toString().split("\n");
            addOffRequestPane.setVisible(true);
            addOffRequestSellerLabel.setText(info[2]);
            addOffRequestAmountLabel.setText(info[3]);
            addOffRequestStartLabel.setText(info[4]);
            addOffRequestEndLabel.setText(info[5]);
        } else if (request.getType().equals("Add Product")) {
            String[] info = ((AddProductRequest) request).toString().split("\n");
            addProductRequestPane.setVisible(true);
            addProductBrandLabel.setText(info[4]);
            addProductExpLabel.setText(info[5]);
            addProductIdLabel.setText(info[2]);
            addProductNameLabel.setText(info[3]);
            addProductPriceLabel.setText(info[6]);
            addProductSellerLabel.setText(info[7]);
        } else if (request.getType().equals("Add Seller")) {
            String[] info = ((AddSellerRequest) request).toString().split("\n");
            addSellerRequestPane.setVisible(true);
            addSellerRequestCompanyLabel.setText(info[3]);
            addSellerRequestSellerLabel.setText(info[2]);
        } else if (request.getType().equals("Edit Product")) {
            String[] info = ((EditProductRequest) request).toString().split("\n");
            editProductRequestPane.setVisible(true);
            editProductFieldLabel.setText(info[2]);
            editProductOldContent.setText(info[3]);
            editProductNewContent.setText(info[4]);
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

    public void manageManagers(MouseEvent mouseEvent) {
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
        int size = DataBase.getAllCategories().size();
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
            DataBase.addCategory(new Category(newCategoryNameTextField.getText(), newAttributeTextField.getText(),
                    null));
        } else {
            DataBase.addCategory(new Category(newCategoryNameTextField.getText(), newAttributeTextField.getText(),
                    DataBase.getCategoryByName(newParentCategoryTextField.getText())));
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
        if (DataBase.isThereAnyCategoryWithName(newParentCategoryTextField.getText()) && !newCategoryNameTextField.getText().equals("")
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

    public void goToAddManagerPane(ActionEvent event) {
    }

    public void acceptRequest(ActionEvent event) {



        requestsIndex = 0;
        setRequestsPaneContent();
    }

    public void rejectRequest(ActionEvent event) {



        requestsIndex = 0;
        setRequestsPaneContent();
    }

    public void seeMoreRequests(MouseEvent mouseEvent) {
        int size = DataBase.getAllActiveRequests().size();
        if (mouseEvent.getSource().equals(upArrowRequest)) {
            if (requestsIndex == 0) return;
            requestsIndex--;
        } else if (mouseEvent.getSource().equals(downArrowRequest)) {
            if (requestsIndex == size - 1) return;
            requestsIndex++;
        }
        setRequestsPaneContent();
    }
}
