package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import Controller.Filter;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsPageMenuFxmlController implements Initializable {
    public TextField searchBox;
    public ImageView i00;public ImageView i01;public ImageView i02;public ImageView i03;
    public ImageView i10;public ImageView i11;public ImageView i12;public ImageView i13;
    public ImageView i20;public ImageView i21;public ImageView i22;public ImageView i23;
    public ImageView i30;public ImageView i31;public ImageView i32;public ImageView i33;
    public ImageView i40;public ImageView i41;public ImageView i42;public ImageView i43;

    public Label n00;public Label n01;public Label n02;public Label n03;
    public Label n10;public Label n11;public Label n12;public Label n13;
    public Label n20;public Label n21;public Label n22;public Label n23;
    public Label n30;public Label n31;public Label n32;public Label n33;
    public Label n40;public Label n41;public Label n42;public Label n43;

    public Label nA00;public Label nA01;public Label nA02;public Label nA03;
    public Label nA10;public Label nA11;public Label nA12;public Label nA13;
    public Label nA20;public Label nA21;public Label nA22;public Label nA23;
    public Label nA30;public Label nA31;public Label nA32;public Label nA33;
    public Label nA40;public Label nA41;public Label nA42;public Label nA43;

    public Label p00;public Label p01;public Label p02;public Label p03;
    public Label p10;public Label p11;public Label p12;public Label p13;
    public Label p20;public Label p21;public Label p22;public Label p23;
    public Label p30;public Label p31;public Label p32;public Label p33;
    public Label p40;public Label p41;public Label p42;public Label p43;

    public Label pA00;public Label pA01;public Label pA02;public Label pA03;
    public Label pA10;public Label pA11;public Label pA12;public Label pA13;
    public Label pA20;public Label pA21;public Label pA22;public Label pA23;
    public Label pA30;public Label pA31;public Label pA32;public Label pA33;
    public Label pA40;public Label pA41;public Label pA42;public Label pA43;

    public GridPane g00;public GridPane g01;public GridPane g02;public GridPane g03;
    public GridPane g10;public GridPane g11;public GridPane g12;public GridPane g13;
    public GridPane g20;public GridPane g21;public GridPane g22;public GridPane g23;
    public GridPane g30;public GridPane g31;public GridPane g32;public GridPane g33;
    public GridPane g40;public GridPane g41;public GridPane g42;public GridPane g43;

    public CheckBox b00;public CheckBox b01;public CheckBox b02;public CheckBox b03;
    public CheckBox b10;public CheckBox b11;public CheckBox b12;public CheckBox b13;
    public CheckBox b20;public CheckBox b21;public CheckBox b22;public CheckBox b23;
    public CheckBox b30;public CheckBox b31;public CheckBox b32;public CheckBox b33;
    public CheckBox b40;public CheckBox b41;public CheckBox b42;public CheckBox b43;


    public HashMap<GridPane,ImageView> gridPaneToImageView =new HashMap<GridPane, ImageView>();
    public HashMap<GridPane,Label> gridPaneToPriceLabel =new HashMap<GridPane, Label>();
    public HashMap<GridPane,Label> gridPaneToNameLabel =new HashMap<GridPane, Label>();
    public HashMap<GridPane,Label> gridPaneToPriceAmount =new HashMap<GridPane, Label>();
    public HashMap<GridPane,Label> gridPaneToNameAmount =new HashMap<GridPane, Label>();
    public HashMap<GridPane,CheckBox> gridPaneToCheckBox=new HashMap<GridPane, CheckBox>();
    public HashMap<CheckBox,GridPane> checkBoxToGridPane=new HashMap<CheckBox, GridPane>();
    public HashMap<GridPane,Long> gridPaneToProductId =new HashMap<GridPane, Long>();
    public List<GridPane> gridPanes=new ArrayList<GridPane>();
    public List<CheckBox> checkBoxes=new ArrayList<CheckBox>();
    ArrayList<CheckBox> brandsCheckBoxes=new ArrayList<>();
    ArrayList<CheckBox> categoriesCheckBoxes=new ArrayList<>();


    public ListView brandFilter;
    public TextField nameFilter;
    public CheckBox offFilter;
    public CheckBox availabilityFilter;
    public ListView categoriesFilter;
    public long counter=0;
    public int currentSize=0;
    public Button compareButton;

    public void filter(MouseEvent mouseEvent) {

    }

    public void SearchFor(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPaneToImageView.put(g00,i00);gridPaneToImageView.put(g01,i01);gridPaneToImageView.put(g02,i02);gridPaneToImageView.put(g03,i03);
        gridPaneToImageView.put(g10,i10);gridPaneToImageView.put(g11,i11);gridPaneToImageView.put(g12,i12);gridPaneToImageView.put(g13,i13);
        gridPaneToImageView.put(g20,i20);gridPaneToImageView.put(g21,i21);gridPaneToImageView.put(g22,i22);gridPaneToImageView.put(g23,i23);
        gridPaneToImageView.put(g30,i30);gridPaneToImageView.put(g31,i31);gridPaneToImageView.put(g32,i32);gridPaneToImageView.put(g33,i33);
        gridPaneToImageView.put(g40,i40);gridPaneToImageView.put(g41,i41);gridPaneToImageView.put(g42,i42);gridPaneToImageView.put(g43,i43);

        gridPaneToNameLabel.put(g00,n00);gridPaneToNameLabel.put(g01,n01);gridPaneToNameLabel.put(g02,n02);gridPaneToNameLabel.put(g03,n03);
        gridPaneToNameLabel.put(g10,n10);gridPaneToNameLabel.put(g11,n11);gridPaneToNameLabel.put(g12,n12);gridPaneToNameLabel.put(g13,n13);
        gridPaneToNameLabel.put(g20,n20);gridPaneToNameLabel.put(g21,n21);gridPaneToNameLabel.put(g22,n22);gridPaneToNameLabel.put(g23,n23);
        gridPaneToNameLabel.put(g30,n30);gridPaneToNameLabel.put(g31,n31);gridPaneToNameLabel.put(g32,n32);gridPaneToNameLabel.put(g33,n33);
        gridPaneToNameLabel.put(g40,n40);gridPaneToNameLabel.put(g41,n41);gridPaneToNameLabel.put(g42,n42);gridPaneToNameLabel.put(g43,n43);

        gridPaneToPriceLabel.put(g00,p00);gridPaneToPriceLabel.put(g01,p01);gridPaneToPriceLabel.put(g02,p02);gridPaneToPriceLabel.put(g03,p03);
        gridPaneToPriceLabel.put(g10,p10);gridPaneToPriceLabel.put(g11,p11);gridPaneToPriceLabel.put(g12,p12);gridPaneToPriceLabel.put(g13,p13);
        gridPaneToPriceLabel.put(g20,p20);gridPaneToPriceLabel.put(g21,p21);gridPaneToPriceLabel.put(g22,p22);gridPaneToPriceLabel.put(g23,p23);
        gridPaneToPriceLabel.put(g30,p30);gridPaneToPriceLabel.put(g31,p31);gridPaneToPriceLabel.put(g32,p32);gridPaneToPriceLabel.put(g33,p33);
        gridPaneToPriceLabel.put(g40,p40);gridPaneToPriceLabel.put(g41,p41);gridPaneToPriceLabel.put(g42,p42);gridPaneToPriceLabel.put(g43,p43);

        gridPaneToNameAmount.put(g00,nA00);gridPaneToNameAmount.put(g01,nA01);gridPaneToNameAmount.put(g02,nA02);gridPaneToNameAmount.put(g03,nA03);
        gridPaneToNameAmount.put(g10,nA10);gridPaneToNameAmount.put(g11,nA11);gridPaneToNameAmount.put(g12,nA12);gridPaneToNameAmount.put(g13,nA13);
        gridPaneToNameAmount.put(g20,nA20);gridPaneToNameAmount.put(g21,nA21);gridPaneToNameAmount.put(g22,nA22);gridPaneToNameAmount.put(g23,nA23);
        gridPaneToNameAmount.put(g30,nA30);gridPaneToNameAmount.put(g31,nA31);gridPaneToNameAmount.put(g32,nA32);gridPaneToNameAmount.put(g33,nA33);
        gridPaneToNameAmount.put(g40,nA40);gridPaneToNameAmount.put(g41,nA41);gridPaneToNameAmount.put(g42,nA42);gridPaneToNameAmount.put(g43,nA43);

        gridPaneToPriceAmount.put(g00,pA00);gridPaneToPriceAmount.put(g01,pA01);gridPaneToPriceAmount.put(g02,pA02);gridPaneToPriceAmount.put(g03,pA03);
        gridPaneToPriceAmount.put(g10,pA10);gridPaneToPriceAmount.put(g11,pA11);gridPaneToPriceAmount.put(g12,pA12);gridPaneToPriceAmount.put(g13,pA13);
        gridPaneToPriceAmount.put(g20,pA20);gridPaneToPriceAmount.put(g21,pA21);gridPaneToPriceAmount.put(g22,pA22);gridPaneToPriceAmount.put(g23,pA23);
        gridPaneToPriceAmount.put(g30,pA30);gridPaneToPriceAmount.put(g31,pA31);gridPaneToPriceAmount.put(g33,pA32);gridPaneToPriceAmount.put(g33,pA33);
        gridPaneToPriceAmount.put(g40,pA40);gridPaneToPriceAmount.put(g41,pA41);gridPaneToPriceAmount.put(g43,pA42);gridPaneToPriceAmount.put(g43,pA43);


        gridPaneToCheckBox.put(g00,b00);gridPaneToCheckBox.put(g01,b01);gridPaneToCheckBox.put(g02,b02);gridPaneToCheckBox.put(g03,b03);
        gridPaneToCheckBox.put(g10,b10);gridPaneToCheckBox.put(g11,b11);gridPaneToCheckBox.put(g12,b12);gridPaneToCheckBox.put(g13,b13);
        gridPaneToCheckBox.put(g20,b20);gridPaneToCheckBox.put(g21,b21);gridPaneToCheckBox.put(g22,b22);gridPaneToCheckBox.put(g23,b23);
        gridPaneToCheckBox.put(g30,b30);gridPaneToCheckBox.put(g31,b31);gridPaneToCheckBox.put(g33,b32);gridPaneToCheckBox.put(g33,b33);
        gridPaneToCheckBox.put(g40,b40);gridPaneToCheckBox.put(g41,b41);gridPaneToCheckBox.put(g43,b42);gridPaneToCheckBox.put(g43,b43);

        checkBoxToGridPane.put(b00,g00);checkBoxToGridPane.put(b01,g01);checkBoxToGridPane.put(b02,g02);checkBoxToGridPane.put(b03,g03);
        checkBoxToGridPane.put(b10,g10);checkBoxToGridPane.put(b11,g11);checkBoxToGridPane.put(b12,g12);checkBoxToGridPane.put(b13,g13);
        checkBoxToGridPane.put(b20,g20);checkBoxToGridPane.put(b21,g21);checkBoxToGridPane.put(b22,g22);checkBoxToGridPane.put(b23,g23);
        checkBoxToGridPane.put(b30,g30);checkBoxToGridPane.put(b31,g31);checkBoxToGridPane.put(b33,g32);checkBoxToGridPane.put(b33,g33);
        checkBoxToGridPane.put(b40,g40);checkBoxToGridPane.put(b41,g41);checkBoxToGridPane.put(b43,g42);checkBoxToGridPane.put(b43,g43);

        gridPanes.add(g00);gridPanes.add(g01);gridPanes.add(g02);gridPanes.add(g03);
        gridPanes.add(g10);gridPanes.add(g11);gridPanes.add(g12);gridPanes.add(g13);
        gridPanes.add(g20);gridPanes.add(g21);gridPanes.add(g22);gridPanes.add(g23);
        gridPanes.add(g30);gridPanes.add(g31);gridPanes.add(g32);gridPanes.add(g33);
        gridPanes.add(g40);gridPanes.add(g41);gridPanes.add(g42);gridPanes.add(g43);

        checkBoxes.add(b00);checkBoxes.add(b01);checkBoxes.add(b02);checkBoxes.add(b03);
        checkBoxes.add(b10);checkBoxes.add(b11);checkBoxes.add(b12);checkBoxes.add(b13);
        checkBoxes.add(b20);checkBoxes.add(b21);checkBoxes.add(b22);checkBoxes.add(b23);
        checkBoxes.add(b30);checkBoxes.add(b31);checkBoxes.add(b32);checkBoxes.add(b33);
        checkBoxes.add(b40);checkBoxes.add(b41);checkBoxes.add(b42);checkBoxes.add(b43);




        loadProduct(counter);

    }

    public void click(MouseEvent mouseEvent) {
        GridPane GridPane=(GridPane) mouseEvent.getSource();
        System.out.println(GridPane.getId());
    }

    public void clear(){
        categoriesCheckBoxes.clear();
        brandsCheckBoxes.clear();
        brandFilter.getItems().clear();
        categoriesFilter.getItems().clear();
        for (GridPane gridPane : gridPanes) {
            gridPane.setVisible(false);
            gridPane.setDisable(true);
        }
    }
    //
    public void loadProduct(long start){
        clear();
        ArrayList<String> brands=new ArrayList<String>(Filter.getAvailableBrands());
        for (String brand : brands) {
            CheckBox checkBox=new CheckBox();
            checkBox.setText(brand);
            brandsCheckBoxes.add(checkBox);
            brandFilter.getItems().add(checkBox);
        }

        ArrayList<String> categories=new ArrayList<String>(Filter.showSubCategories());
        for (String category : categories) {
            CheckBox checkBox=new CheckBox();
            checkBox.setText(category);
            categoriesCheckBoxes.add(checkBox);
            categoriesFilter.getItems().add(checkBox);
        }

        int size=Controller.getHowMuchLeftForThisPage(counter);
        ArrayList<String> images = Controller.getProductImageForFxml(counter);
        ArrayList<Double> prices = Controller.getProductPriceForFxml(counter);
        ArrayList<String> names = Controller.getProductNameForFxml(counter);
        ArrayList<Long> productId=Controller.getProductIdForFxml(counter);
        for (int i = 0; i < size; i++) {
            gridPanes.get(i).setVisible(true);
            gridPanes.get(i).setDisable(false);
//            gridPaneToImageView.get(gridPanes).setImage(new Image(images.get(i)));
            gridPaneToNameAmount.get(gridPanes.get(i)).setText(names.get(i));
            gridPaneToPriceAmount.get(gridPanes.get(i)).setText(prices.get(i).toString());
            gridPaneToProductId.put(gridPanes.get(i),productId.get(i));
        }
        currentSize=size;
        counter=+size;
    }

    public void makeGridPaneCompareOn(MouseEvent mouseEvent) {
        GridPane gridPane=(GridPane) mouseEvent.getSource();
        gridPaneToCheckBox.get(gridPane).setVisible(true);
        gridPaneToCheckBox.get(gridPane).setDisable(false);
        DropShadow dropShadow=new DropShadow();
        gridPane.setEffect(dropShadow);
    }

    public void makeGridPaneCompareOff(MouseEvent mouseEvent) {
        GridPane gridPane=(GridPane) mouseEvent.getSource();
        if (gridPaneToCheckBox.get(gridPane).isSelected()){
            return;
        }
        gridPaneToCheckBox.get(gridPane).setVisible(false);
        gridPaneToCheckBox.get(gridPane).setDisable(true);
        gridPane.setEffect(null);
    }

    public void makeCompareVisible(MouseEvent mouseEvent) {
        int selected=0;
        for (int i = 0; i < currentSize; i++) {
            if (checkBoxes.get(i).isSelected()){
                selected++;
            }
        }
        if (selected>=2){
            compareButton.setVisible(true);
            compareButton.setDisable(false);
        }else {
            compareButton.setVisible(false);
            compareButton.setDisable(true);
        }
    }

    public void compare(ActionEvent actionEvent){
        List<Long> chosen=new ArrayList<Long>();
        for (int i = 0; i < currentSize; i++) {
            if (checkBoxes.get(i).isSelected()){
                chosen.add(gridPaneToProductId.get(checkBoxToGridPane.get(checkBoxes.get(i))));
            }
        }
        System.out.println(chosen);
    }
}

