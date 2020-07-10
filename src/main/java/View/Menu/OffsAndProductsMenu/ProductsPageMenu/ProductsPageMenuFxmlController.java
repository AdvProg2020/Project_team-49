package View.Menu.OffsAndProductsMenu.ProductsPageMenu;

import Controller.Controller;
import Controller.Filter;
import Controller.Sort;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsPageMenuFxmlController implements Initializable {
    public ImageView i00;public ImageView i01;public ImageView i02;public ImageView i03;
    public ImageView i10;public ImageView i11;public ImageView i12;public ImageView i13;
    public ImageView i20;public ImageView i21;public ImageView i22;public ImageView i23;
    public ImageView i30;public ImageView i31;public ImageView i32;public ImageView i33;
    public ImageView i40;public ImageView i41;public ImageView i42;public ImageView i43;

    public ImageView off00;public ImageView off01;public ImageView off02;public ImageView off03;
    public ImageView off10;public ImageView off11;public ImageView off12;public ImageView off13;
    public ImageView off20;public ImageView off21;public ImageView off22;public ImageView off23;
    public ImageView off30;public ImageView off31;public ImageView off32;public ImageView off33;
    public ImageView off40;public ImageView off41;public ImageView off42;public ImageView off43;

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
    public HashMap<GridPane,ImageView> gridPaneToOff =new HashMap<GridPane, ImageView>();
    public List<GridPane> gridPanes=new ArrayList<GridPane>();
    public List<CheckBox> checkBoxes=new ArrayList<CheckBox>();
    public Label categoriesLabel;
    public FlowPane brandFlowPane;

    public Label pageCounter;
    public ImageView nextPageImage;
    public ImageView prePageImage;

    public Slider priceFilterMin;
    public Slider priceFilterMax;
    public Label minPrice;
    public Label midPrice;
    public Label maxPrice;
    public double min;
    public double max;
    public double previousMinValue;
    public double previousMaxValue;

    public ImageView leftGreen5_00;public ImageView leftGreen5_01;public ImageView leftGreen5_02;public ImageView leftGreen5_03;
    public ImageView leftGreen4_00;public ImageView leftGreen4_01;public ImageView leftGreen4_02;public ImageView leftGreen4_03;;
    public ImageView leftGreen3_00;public ImageView leftGreen3_01;public ImageView leftGreen3_02;public ImageView leftGreen3_03;
    public ImageView leftGreen2_00;public ImageView leftGreen2_01;public ImageView leftGreen2_02;public ImageView leftGreen2_03;
    public ImageView leftGreen1_00;public ImageView leftGreen1_01;public ImageView leftGreen1_02;public ImageView leftGreen1_03;

    public ImageView rightGreen5_00;public ImageView rightGreen5_01;public ImageView rightGreen5_02;public ImageView rightGreen5_03;
    public ImageView rightGreen4_00;public ImageView rightGreen4_01;public ImageView rightGreen4_02;public ImageView rightGreen4_03;
    public ImageView rightGreen3_00;public ImageView rightGreen3_01;public ImageView rightGreen3_02;public ImageView rightGreen3_03;
    public ImageView rightGreen2_00;public ImageView rightGreen2_01;public ImageView rightGreen2_02;public ImageView rightGreen2_03;
    public ImageView rightGreen1_00;public ImageView rightGreen1_01;public ImageView rightGreen1_02;public ImageView rightGreen1_03;

    public ImageView halfLeft5_00;public ImageView halfLeft5_01;public ImageView halfLeft5_02;public ImageView halfLeft5_03;
    public ImageView halfLeft4_00;public ImageView halfLeft4_01;public ImageView halfLeft4_02;public ImageView halfLeft4_03;
    public ImageView halfLeft3_00;public ImageView halfLeft3_01;public ImageView halfLeft3_02;public ImageView halfLeft3_03;
    public ImageView halfLeft2_00;public ImageView halfLeft2_01;public ImageView halfLeft2_02;public ImageView halfLeft2_03;
    public ImageView halfLeft1_00;public ImageView halfLeft1_01;public ImageView halfLeft1_02;public ImageView halfLeft1_03;

    public ImageView halfRight5_00;public ImageView halfRight5_01;public ImageView halfRight5_02;public ImageView halfRight5_03;
    public ImageView halfRight4_00;public ImageView halfRight4_01;public ImageView halfRight4_02;public ImageView halfRight4_03;
    public ImageView halfRight3_00;public ImageView halfRight3_01;public ImageView halfRight3_02;public ImageView halfRight3_03;
    public ImageView halfRight2_00;public ImageView halfRight2_01;public ImageView halfRight2_02;public ImageView halfRight2_03;
    public ImageView halfRight1_00;public ImageView halfRight1_01;public ImageView halfRight1_02;public ImageView halfRight1_03;


    public ImageView leftGreen5_10;public ImageView leftGreen5_11;public ImageView leftGreen5_12;public ImageView leftGreen5_13;
    public ImageView leftGreen4_10;public ImageView leftGreen4_11;public ImageView leftGreen4_12;public ImageView leftGreen4_13;
    public ImageView leftGreen3_10;public ImageView leftGreen3_11;public ImageView leftGreen3_12;public ImageView leftGreen3_13;
    public ImageView leftGreen2_10;public ImageView leftGreen2_11;public ImageView leftGreen2_12;public ImageView leftGreen2_13;
    public ImageView leftGreen1_10;public ImageView leftGreen1_11;public ImageView leftGreen1_12;public ImageView leftGreen1_13;

    public ImageView rightGreen5_10;public ImageView rightGreen5_11;public ImageView rightGreen5_12;public ImageView rightGreen5_13;
    public ImageView rightGreen4_10;public ImageView rightGreen4_11;public ImageView rightGreen4_12;public ImageView rightGreen4_13;
    public ImageView rightGreen3_10;public ImageView rightGreen3_11;public ImageView rightGreen3_12;public ImageView rightGreen3_13;
    public ImageView rightGreen2_10;public ImageView rightGreen2_11;public ImageView rightGreen2_12;public ImageView rightGreen2_13;
    public ImageView rightGreen1_10;public ImageView rightGreen1_11;public ImageView rightGreen1_12;public ImageView rightGreen1_13;

    public ImageView halfLeft5_10;public ImageView halfLeft5_11;public ImageView halfLeft5_12;public ImageView halfLeft5_13;
    public ImageView halfLeft4_10;public ImageView halfLeft4_11;public ImageView halfLeft4_12;public ImageView halfLeft4_13;
    public ImageView halfLeft3_10;public ImageView halfLeft3_11;public ImageView halfLeft3_12;public ImageView halfLeft3_13;
    public ImageView halfLeft2_10;public ImageView halfLeft2_11;public ImageView halfLeft2_12;public ImageView halfLeft2_13;
    public ImageView halfLeft1_10;public ImageView halfLeft1_11;public ImageView halfLeft1_12;public ImageView halfLeft1_13;

    public ImageView halfRight5_10;public ImageView halfRight5_11;public ImageView halfRight5_12;public ImageView halfRight5_13;
    public ImageView halfRight4_10;public ImageView halfRight4_11;public ImageView halfRight4_12;public ImageView halfRight4_13;
    public ImageView halfRight3_10;public ImageView halfRight3_11;public ImageView halfRight3_12;public ImageView halfRight3_13;
    public ImageView halfRight2_10;public ImageView halfRight2_11;public ImageView halfRight2_12;public ImageView halfRight2_13;
    public ImageView halfRight1_10;public ImageView halfRight1_11;public ImageView halfRight1_12;public ImageView halfRight1_13;


    public ImageView leftGreen5_20;public ImageView leftGreen5_21;public ImageView leftGreen5_22;public ImageView leftGreen5_23;
    public ImageView leftGreen4_20;public ImageView leftGreen4_21;public ImageView leftGreen4_22;public ImageView leftGreen4_23;
    public ImageView leftGreen3_20;public ImageView leftGreen3_21;public ImageView leftGreen3_22;public ImageView leftGreen3_23;
    public ImageView leftGreen2_20;public ImageView leftGreen2_21;public ImageView leftGreen2_22;public ImageView leftGreen2_23;
    public ImageView leftGreen1_20;public ImageView leftGreen1_21;public ImageView leftGreen1_22;public ImageView leftGreen1_23;

    public ImageView rightGreen5_20;public ImageView rightGreen5_21;public ImageView rightGreen5_22;public ImageView rightGreen5_23;
    public ImageView rightGreen4_20;public ImageView rightGreen4_21;public ImageView rightGreen4_22;public ImageView rightGreen4_23;
    public ImageView rightGreen3_20;public ImageView rightGreen3_21;public ImageView rightGreen3_22;public ImageView rightGreen3_23;
    public ImageView rightGreen2_20;public ImageView rightGreen2_21;public ImageView rightGreen2_22;public ImageView rightGreen2_23;
    public ImageView rightGreen1_20;public ImageView rightGreen1_21;public ImageView rightGreen1_22;public ImageView rightGreen1_23;

    public ImageView halfLeft5_20;public ImageView halfLeft5_21;public ImageView halfLeft5_22;public ImageView halfLeft5_23;
    public ImageView halfLeft4_20;public ImageView halfLeft4_21;public ImageView halfLeft4_22;public ImageView halfLeft4_23;
    public ImageView halfLeft3_20;public ImageView halfLeft3_21;public ImageView halfLeft3_22;public ImageView halfLeft3_23;
    public ImageView halfLeft2_20;public ImageView halfLeft2_21;public ImageView halfLeft2_22;public ImageView halfLeft2_23;
    public ImageView halfLeft1_20;public ImageView halfLeft1_21;public ImageView halfLeft1_22;public ImageView halfLeft1_23;

    public ImageView halfRight5_20;public ImageView halfRight5_21;public ImageView halfRight5_22;public ImageView halfRight5_23;
    public ImageView halfRight4_20;public ImageView halfRight4_21;public ImageView halfRight4_22;public ImageView halfRight4_23;
    public ImageView halfRight3_20;public ImageView halfRight3_21;public ImageView halfRight3_22;public ImageView halfRight3_23;
    public ImageView halfRight2_20;public ImageView halfRight2_21;public ImageView halfRight2_22;public ImageView halfRight2_23;
    public ImageView halfRight1_20;public ImageView halfRight1_21;public ImageView halfRight1_22;public ImageView halfRight1_23;


    public ImageView leftGreen5_30;public ImageView leftGreen5_31;public ImageView leftGreen5_32;public ImageView leftGreen5_33;
    public ImageView leftGreen4_30;public ImageView leftGreen4_31;public ImageView leftGreen4_32;public ImageView leftGreen4_33;
    public ImageView leftGreen3_30;public ImageView leftGreen3_31;public ImageView leftGreen3_32;public ImageView leftGreen3_33;
    public ImageView leftGreen2_30;public ImageView leftGreen2_31;public ImageView leftGreen2_32;public ImageView leftGreen2_33;
    public ImageView leftGreen1_30;public ImageView leftGreen1_31;public ImageView leftGreen1_32;public ImageView leftGreen1_33;

    public ImageView rightGreen5_30;public ImageView rightGreen5_31;public ImageView rightGreen5_32;public ImageView rightGreen5_33;
    public ImageView rightGreen4_30;public ImageView rightGreen4_31;public ImageView rightGreen4_32;public ImageView rightGreen4_33;
    public ImageView rightGreen3_30;public ImageView rightGreen3_31;public ImageView rightGreen3_32;public ImageView rightGreen3_33;
    public ImageView rightGreen2_30;public ImageView rightGreen2_31;public ImageView rightGreen2_32;public ImageView rightGreen2_33;
    public ImageView rightGreen1_30;public ImageView rightGreen1_31;public ImageView rightGreen1_32;public ImageView rightGreen1_33;

    public ImageView halfLeft5_30;public ImageView halfLeft5_31;public ImageView halfLeft5_32;public ImageView halfLeft5_33;
    public ImageView halfLeft4_30;public ImageView halfLeft4_31;public ImageView halfLeft4_32;public ImageView halfLeft4_33;
    public ImageView halfLeft3_30;public ImageView halfLeft3_31;public ImageView halfLeft3_32;public ImageView halfLeft3_33;
    public ImageView halfLeft2_30;public ImageView halfLeft2_31;public ImageView halfLeft2_32;public ImageView halfLeft2_33;
    public ImageView halfLeft1_30;public ImageView halfLeft1_31;public ImageView halfLeft1_32;public ImageView halfLeft1_33;


    public ImageView halfRight5_30;public ImageView halfRight5_31;public ImageView halfRight5_32;public ImageView halfRight5_33;
    public ImageView halfRight4_30;public ImageView halfRight4_31;public ImageView halfRight4_32;public ImageView halfRight4_33;
    public ImageView halfRight3_30;public ImageView halfRight3_31;public ImageView halfRight3_32;public ImageView halfRight3_33;
    public ImageView halfRight2_30;public ImageView halfRight2_31;public ImageView halfRight2_32;public ImageView halfRight2_33;
    public ImageView halfRight1_30;public ImageView halfRight1_31;public ImageView halfRight1_32;public ImageView halfRight1_33;


    public ImageView leftGreen5_40;public ImageView leftGreen5_41;public ImageView leftGreen5_42;public ImageView leftGreen5_43;
    public ImageView leftGreen4_40;public ImageView leftGreen4_41;public ImageView leftGreen4_42;public ImageView leftGreen4_43;
    public ImageView leftGreen3_40;public ImageView leftGreen3_41;public ImageView leftGreen3_42;public ImageView leftGreen3_43;
    public ImageView leftGreen2_40;public ImageView leftGreen2_41;public ImageView leftGreen2_42;public ImageView leftGreen2_43;
    public ImageView leftGreen1_40;public ImageView leftGreen1_41;public ImageView leftGreen1_42;public ImageView leftGreen1_43;

    public ImageView rightGreen5_40;public ImageView rightGreen5_41;public ImageView rightGreen5_42;public ImageView rightGreen5_43;
    public ImageView rightGreen4_40;public ImageView rightGreen4_41;public ImageView rightGreen4_42;public ImageView rightGreen4_43;
    public ImageView rightGreen3_40;public ImageView rightGreen3_41;public ImageView rightGreen3_42;public ImageView rightGreen3_43;
    public ImageView rightGreen2_40;public ImageView rightGreen2_41;public ImageView rightGreen2_42;public ImageView rightGreen2_43;
    public ImageView rightGreen1_40;public ImageView rightGreen1_41;public ImageView rightGreen1_42;public ImageView rightGreen1_43;

    public ImageView halfLeft5_40;public ImageView halfLeft5_41;public ImageView halfLeft5_42;public ImageView halfLeft5_43;
    public ImageView halfLeft4_40;public ImageView halfLeft4_41;public ImageView halfLeft4_42;public ImageView halfLeft4_43;
    public ImageView halfLeft3_40;public ImageView halfLeft3_41;public ImageView halfLeft3_42;public ImageView halfLeft3_43;
    public ImageView halfLeft2_40;public ImageView halfLeft2_41;public ImageView halfLeft2_42;public ImageView halfLeft2_43;
    public ImageView halfLeft1_40;public ImageView halfLeft1_41;public ImageView halfLeft1_42;public ImageView halfLeft1_43;


    public ImageView halfRight5_40;public ImageView halfRight5_41;public ImageView halfRight5_42;public ImageView halfRight5_43;
    public ImageView halfRight4_40;public ImageView halfRight4_41;public ImageView halfRight4_42;public ImageView halfRight4_43;
    public ImageView halfRight3_40;public ImageView halfRight3_41;public ImageView halfRight3_42;public ImageView halfRight3_43;
    public ImageView halfRight2_40;public ImageView halfRight2_41;public ImageView halfRight2_42;public ImageView halfRight2_43;
    public ImageView halfRight1_40;public ImageView halfRight1_41;public ImageView halfRight1_42;public ImageView halfRight1_43;

    public ArrayList<ImageView> listOfRateStar00=new ArrayList<>();public ArrayList<ImageView> listOfRateStar01=new ArrayList<>();public ArrayList<ImageView> listOfRateStar02=new ArrayList<>();public ArrayList<ImageView> listOfRateStar03=new ArrayList<>();
    public ArrayList<ImageView> listOfRateStar10=new ArrayList<>();public ArrayList<ImageView> listOfRateStar11=new ArrayList<>();public ArrayList<ImageView> listOfRateStar12=new ArrayList<>();public ArrayList<ImageView> listOfRateStar13=new ArrayList<>();
    public ArrayList<ImageView> listOfRateStar20=new ArrayList<>();public ArrayList<ImageView> listOfRateStar21=new ArrayList<>();public ArrayList<ImageView> listOfRateStar22=new ArrayList<>();public ArrayList<ImageView> listOfRateStar23=new ArrayList<>();
    public ArrayList<ImageView> listOfRateStar30=new ArrayList<>();public ArrayList<ImageView> listOfRateStar31=new ArrayList<>();public ArrayList<ImageView> listOfRateStar32=new ArrayList<>();public ArrayList<ImageView> listOfRateStar33=new ArrayList<>();
    public ArrayList<ImageView> listOfRateStar40=new ArrayList<>();public ArrayList<ImageView> listOfRateStar41=new ArrayList<>();public ArrayList<ImageView> listOfRateStar42=new ArrayList<>();public ArrayList<ImageView> listOfRateStar43=new ArrayList<>();

    public HashMap<GridPane,ArrayList<ImageView>> gridPaneToRate=new HashMap<GridPane, ArrayList<ImageView>>();

    public Label offRemain_00;public Label offRemain_01;public Label offRemain_02;public Label offRemain_03;
    public Label offRemain_10;public Label offRemain_11;public Label offRemain_12;public Label offRemain_13;
    public Label offRemain_20;public Label offRemain_21;public Label offRemain_22;public Label offRemain_23;
    public Label offRemain_30;public Label offRemain_31;public Label offRemain_32;public Label offRemain_33;
    public Label offRemain_40;public Label offRemain_41;public Label offRemain_42;public Label offRemain_43;

    public Label offPercent_00;public Label offPercent_01;public Label offPercent_02;public Label offPercent_03;
    public Label offPercent_10;public Label offPercent_11;public Label offPercent_12;public Label offPercent_13;
    public Label offPercent_20;public Label offPercent_21;public Label offPercent_22;public Label offPercent_23;
    public Label offPercent_30;public Label offPercent_31;public Label offPercent_32;public Label offPercent_33;
    public Label offPercent_40;public Label offPercent_41;public Label offPercent_42;public Label offPercent_43;

    public HashMap<GridPane,Label> gridPaneToOffRemain =new HashMap<>();
    public HashMap<GridPane,Label> gridPaneToOffPercent=new HashMap<>();
    public Pane backpane;


    ArrayList<CheckBox> brandsCheckBoxes=new ArrayList<>();
    ArrayList<Label> categoriesCheckBoxes=new ArrayList<Label>();


    public ImageView viewButton;
    public Label viewLabel;
    public ImageView timeButton;
    public ImageView timeButtonClicked;
    public Label timeLabel;
    public ImageView scoreButton;
    public Label scoreLabel;
    public boolean timeClicked =false;
    public boolean scoreClicked=false;
    public boolean viewClicked=false;

    public Label saleLeft;
    public Label saleRight;


    public ListView brandFilter;
    public TextField nameFilter;
    public CheckBox offFilter;
    public CheckBox availabilityFilter;
    public ListView categoriesFilter;
    public long counter=0;
    public int currentSize=0;
    public ImageView compareButton;

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
        gridPaneToPriceAmount.put(g30,pA30);gridPaneToPriceAmount.put(g31,pA31);gridPaneToPriceAmount.put(g32,pA32);gridPaneToPriceAmount.put(g33,pA33);
        gridPaneToPriceAmount.put(g40,pA40);gridPaneToPriceAmount.put(g41,pA41);gridPaneToPriceAmount.put(g42,pA42);gridPaneToPriceAmount.put(g43,pA43);

        gridPaneToCheckBox.put(g00,b00);gridPaneToCheckBox.put(g01,b01);gridPaneToCheckBox.put(g02,b02);gridPaneToCheckBox.put(g03,b03);
        gridPaneToCheckBox.put(g10,b10);gridPaneToCheckBox.put(g11,b11);gridPaneToCheckBox.put(g12,b12);gridPaneToCheckBox.put(g13,b13);
        gridPaneToCheckBox.put(g20,b20);gridPaneToCheckBox.put(g21,b21);gridPaneToCheckBox.put(g22,b22);gridPaneToCheckBox.put(g23,b23);
        gridPaneToCheckBox.put(g30,b30);gridPaneToCheckBox.put(g31,b31);gridPaneToCheckBox.put(g32,b32);gridPaneToCheckBox.put(g33,b33);
        gridPaneToCheckBox.put(g40,b40);gridPaneToCheckBox.put(g41,b41);gridPaneToCheckBox.put(g42,b42);gridPaneToCheckBox.put(g43,b43);

        checkBoxToGridPane.put(b00,g00);checkBoxToGridPane.put(b01,g01);checkBoxToGridPane.put(b02,g02);checkBoxToGridPane.put(b03,g03);
        checkBoxToGridPane.put(b10,g10);checkBoxToGridPane.put(b11,g11);checkBoxToGridPane.put(b12,g12);checkBoxToGridPane.put(b13,g13);
        checkBoxToGridPane.put(b20,g20);checkBoxToGridPane.put(b21,g21);checkBoxToGridPane.put(b22,g22);checkBoxToGridPane.put(b23,g23);
        checkBoxToGridPane.put(b30,g30);checkBoxToGridPane.put(b31,g31);checkBoxToGridPane.put(b32,g32);checkBoxToGridPane.put(b33,g33);
        checkBoxToGridPane.put(b40,g40);checkBoxToGridPane.put(b41,g41);checkBoxToGridPane.put(b42,g42);checkBoxToGridPane.put(b43,g43);

        gridPaneToOff.put(g00,off00);gridPaneToOff.put(g01,off01);gridPaneToOff.put(g02,off02);gridPaneToOff.put(g03,off03);
        gridPaneToOff.put(g10,off10);gridPaneToOff.put(g11,off11);gridPaneToOff.put(g12,off12);gridPaneToOff.put(g13,off13);
        gridPaneToOff.put(g20,off20);gridPaneToOff.put(g21,off21);gridPaneToOff.put(g22,off22);gridPaneToOff.put(g23,off23);
        gridPaneToOff.put(g30,off30);gridPaneToOff.put(g31,off31);gridPaneToOff.put(g32,off32);gridPaneToOff.put(g33,off33);
        gridPaneToOff.put(g40,off40);gridPaneToOff.put(g41,off41);gridPaneToOff.put(g42,off42);gridPaneToOff.put(g43,off43);


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


        listOfRateStar00.add(leftGreen1_00);listOfRateStar00.add(leftGreen2_00);listOfRateStar00.add(leftGreen3_00);listOfRateStar00.add(leftGreen4_00);listOfRateStar00.add(leftGreen5_00);
        listOfRateStar01.add(leftGreen1_01);listOfRateStar01.add(leftGreen2_01);listOfRateStar01.add(leftGreen3_01);listOfRateStar01.add(leftGreen4_01);listOfRateStar01.add(leftGreen5_01);
        listOfRateStar02.add(leftGreen1_02);listOfRateStar02.add(leftGreen2_02);listOfRateStar02.add(leftGreen3_02);listOfRateStar02.add(leftGreen4_02);listOfRateStar02.add(leftGreen5_02);
        listOfRateStar03.add(leftGreen1_03);listOfRateStar03.add(leftGreen2_03);listOfRateStar03.add(leftGreen3_03);listOfRateStar03.add(leftGreen4_03);listOfRateStar03.add(leftGreen5_03);

        listOfRateStar10.add(leftGreen1_10);listOfRateStar10.add(leftGreen2_10);listOfRateStar10.add(leftGreen3_10);listOfRateStar10.add(leftGreen4_10);listOfRateStar10.add(leftGreen5_10);
        listOfRateStar11.add(leftGreen1_11);listOfRateStar11.add(leftGreen2_11);listOfRateStar11.add(leftGreen3_11);listOfRateStar11.add(leftGreen4_11);listOfRateStar11.add(leftGreen5_11);
        listOfRateStar12.add(leftGreen1_12);listOfRateStar12.add(leftGreen2_12);listOfRateStar12.add(leftGreen3_12);listOfRateStar12.add(leftGreen4_12);listOfRateStar12.add(leftGreen5_12);
        listOfRateStar13.add(leftGreen1_13);listOfRateStar13.add(leftGreen2_13);listOfRateStar13.add(leftGreen3_13);listOfRateStar13.add(leftGreen4_13);listOfRateStar13.add(leftGreen5_13);

        listOfRateStar20.add(leftGreen1_20);listOfRateStar20.add(leftGreen2_20);listOfRateStar20.add(leftGreen3_20);listOfRateStar20.add(leftGreen4_20);listOfRateStar20.add(leftGreen5_20);
        listOfRateStar21.add(leftGreen1_21);listOfRateStar21.add(leftGreen2_21);listOfRateStar21.add(leftGreen3_21);listOfRateStar21.add(leftGreen4_21);listOfRateStar21.add(leftGreen5_21);
        listOfRateStar22.add(leftGreen1_22);listOfRateStar22.add(leftGreen2_22);listOfRateStar22.add(leftGreen3_22);listOfRateStar22.add(leftGreen4_22);listOfRateStar22.add(leftGreen5_22);
        listOfRateStar23.add(leftGreen1_23);listOfRateStar23.add(leftGreen2_23);listOfRateStar23.add(leftGreen3_23);listOfRateStar23.add(leftGreen4_23);listOfRateStar23.add(leftGreen5_23);

        listOfRateStar30.add(leftGreen1_30);listOfRateStar30.add(leftGreen2_30);listOfRateStar30.add(leftGreen3_30);listOfRateStar30.add(leftGreen4_30);listOfRateStar30.add(leftGreen5_30);
        listOfRateStar31.add(leftGreen1_31);listOfRateStar31.add(leftGreen2_31);listOfRateStar31.add(leftGreen3_31);listOfRateStar31.add(leftGreen4_31);listOfRateStar31.add(leftGreen5_31);
        listOfRateStar32.add(leftGreen1_32);listOfRateStar32.add(leftGreen2_32);listOfRateStar32.add(leftGreen3_32);listOfRateStar32.add(leftGreen4_32);listOfRateStar32.add(leftGreen5_32);
        listOfRateStar33.add(leftGreen1_33);listOfRateStar33.add(leftGreen2_33);listOfRateStar33.add(leftGreen3_33);listOfRateStar33.add(leftGreen4_33);listOfRateStar33.add(leftGreen5_33);

        listOfRateStar40.add(leftGreen1_40);listOfRateStar40.add(leftGreen2_40);listOfRateStar40.add(leftGreen3_40);listOfRateStar40.add(leftGreen4_40);listOfRateStar40.add(leftGreen5_40);
        listOfRateStar41.add(leftGreen1_41);listOfRateStar41.add(leftGreen2_41);listOfRateStar41.add(leftGreen3_41);listOfRateStar41.add(leftGreen4_41);listOfRateStar41.add(leftGreen5_41);
        listOfRateStar42.add(leftGreen1_42);listOfRateStar42.add(leftGreen2_42);listOfRateStar42.add(leftGreen3_42);listOfRateStar42.add(leftGreen4_42);listOfRateStar42.add(leftGreen5_42);
        listOfRateStar43.add(leftGreen1_43);listOfRateStar43.add(leftGreen2_43);listOfRateStar43.add(leftGreen3_43);listOfRateStar43.add(leftGreen4_43);listOfRateStar43.add(leftGreen5_43);


        listOfRateStar00.add(rightGreen1_00);listOfRateStar00.add(rightGreen2_00);listOfRateStar00.add(rightGreen3_00);listOfRateStar00.add(rightGreen4_00);listOfRateStar00.add(rightGreen5_00);
        listOfRateStar01.add(rightGreen1_01);listOfRateStar01.add(rightGreen2_01);listOfRateStar01.add(rightGreen3_01);listOfRateStar01.add(rightGreen4_01);listOfRateStar01.add(rightGreen5_01);
        listOfRateStar02.add(rightGreen1_02);listOfRateStar02.add(rightGreen2_02);listOfRateStar02.add(rightGreen3_02);listOfRateStar02.add(rightGreen4_02);listOfRateStar02.add(rightGreen5_02);
        listOfRateStar03.add(rightGreen1_03);listOfRateStar03.add(rightGreen2_03);listOfRateStar03.add(rightGreen3_03);listOfRateStar03.add(rightGreen4_03);listOfRateStar03.add(rightGreen5_03);

        listOfRateStar10.add(rightGreen1_10);listOfRateStar10.add(rightGreen2_10);listOfRateStar10.add(rightGreen3_10);listOfRateStar10.add(rightGreen4_10);listOfRateStar10.add(rightGreen5_10);
        listOfRateStar11.add(rightGreen1_11);listOfRateStar11.add(rightGreen2_11);listOfRateStar11.add(rightGreen3_11);listOfRateStar11.add(rightGreen4_11);listOfRateStar11.add(rightGreen5_11);
        listOfRateStar12.add(rightGreen1_12);listOfRateStar12.add(rightGreen2_12);listOfRateStar12.add(rightGreen3_12);listOfRateStar12.add(rightGreen4_12);listOfRateStar12.add(rightGreen5_12);
        listOfRateStar13.add(rightGreen1_13);listOfRateStar13.add(rightGreen2_13);listOfRateStar13.add(rightGreen3_13);listOfRateStar13.add(rightGreen4_13);listOfRateStar13.add(rightGreen5_13);

        listOfRateStar20.add(rightGreen1_20);listOfRateStar20.add(rightGreen2_20);listOfRateStar20.add(rightGreen3_20);listOfRateStar20.add(rightGreen4_20);listOfRateStar20.add(rightGreen5_20);
        listOfRateStar21.add(rightGreen1_21);listOfRateStar21.add(rightGreen2_21);listOfRateStar21.add(rightGreen3_21);listOfRateStar21.add(rightGreen4_21);listOfRateStar21.add(rightGreen5_21);
        listOfRateStar22.add(rightGreen1_22);listOfRateStar22.add(rightGreen2_22);listOfRateStar22.add(rightGreen3_22);listOfRateStar22.add(rightGreen4_22);listOfRateStar22.add(rightGreen5_22);
        listOfRateStar23.add(rightGreen1_23);listOfRateStar23.add(rightGreen2_23);listOfRateStar23.add(rightGreen3_23);listOfRateStar23.add(rightGreen4_23);listOfRateStar23.add(rightGreen5_23);

        listOfRateStar30.add(rightGreen1_30);listOfRateStar30.add(rightGreen2_30);listOfRateStar30.add(rightGreen3_30);listOfRateStar30.add(rightGreen4_30);listOfRateStar30.add(rightGreen5_30);
        listOfRateStar31.add(rightGreen1_31);listOfRateStar31.add(rightGreen2_31);listOfRateStar31.add(rightGreen3_31);listOfRateStar31.add(rightGreen4_31);listOfRateStar31.add(rightGreen5_31);
        listOfRateStar32.add(rightGreen1_32);listOfRateStar32.add(rightGreen2_32);listOfRateStar32.add(rightGreen3_32);listOfRateStar32.add(rightGreen4_32);listOfRateStar32.add(rightGreen5_32);
        listOfRateStar33.add(rightGreen1_33);listOfRateStar33.add(rightGreen2_33);listOfRateStar33.add(rightGreen3_33);listOfRateStar33.add(rightGreen4_33);listOfRateStar33.add(rightGreen5_33);

        listOfRateStar40.add(rightGreen1_40);listOfRateStar40.add(rightGreen2_40);listOfRateStar40.add(rightGreen3_40);listOfRateStar40.add(rightGreen4_40);listOfRateStar40.add(rightGreen5_40);
        listOfRateStar41.add(rightGreen1_41);listOfRateStar41.add(rightGreen2_41);listOfRateStar41.add(rightGreen3_41);listOfRateStar41.add(rightGreen4_41);listOfRateStar41.add(rightGreen5_41);
        listOfRateStar42.add(rightGreen1_42);listOfRateStar42.add(rightGreen2_42);listOfRateStar42.add(rightGreen3_42);listOfRateStar42.add(rightGreen4_42);listOfRateStar42.add(rightGreen5_42);
        listOfRateStar43.add(rightGreen1_43);listOfRateStar43.add(rightGreen2_43);listOfRateStar43.add(rightGreen3_43);listOfRateStar43.add(rightGreen4_43);listOfRateStar43.add(rightGreen5_43);


        listOfRateStar00.add(halfLeft1_00);listOfRateStar00.add(halfLeft2_00);listOfRateStar00.add(halfLeft3_00);listOfRateStar00.add(halfLeft4_00);listOfRateStar00.add(halfLeft5_00);
        listOfRateStar01.add(halfLeft1_01);listOfRateStar01.add(halfLeft2_01);listOfRateStar01.add(halfLeft3_01);listOfRateStar01.add(halfLeft4_01);listOfRateStar01.add(halfLeft5_01);
        listOfRateStar02.add(halfLeft1_02);listOfRateStar02.add(halfLeft2_02);listOfRateStar02.add(halfLeft3_02);listOfRateStar02.add(halfLeft4_02);listOfRateStar02.add(halfLeft5_02);
        listOfRateStar03.add(halfLeft1_03);listOfRateStar03.add(halfLeft2_03);listOfRateStar03.add(halfLeft3_03);listOfRateStar03.add(halfLeft4_03);listOfRateStar03.add(halfLeft5_03);

        listOfRateStar10.add(halfLeft1_10);listOfRateStar10.add(halfLeft2_10);listOfRateStar10.add(halfLeft3_10);listOfRateStar10.add(halfLeft4_10);listOfRateStar10.add(halfLeft5_10);
        listOfRateStar11.add(halfLeft1_11);listOfRateStar11.add(halfLeft2_11);listOfRateStar11.add(halfLeft3_11);listOfRateStar11.add(halfLeft4_11);listOfRateStar11.add(halfLeft5_11);
        listOfRateStar12.add(halfLeft1_12);listOfRateStar12.add(halfLeft2_12);listOfRateStar12.add(halfLeft3_12);listOfRateStar12.add(halfLeft4_12);listOfRateStar12.add(halfLeft5_12);
        listOfRateStar13.add(halfLeft1_13);listOfRateStar13.add(halfLeft2_13);listOfRateStar13.add(halfLeft3_13);listOfRateStar13.add(halfLeft4_13);listOfRateStar13.add(halfLeft5_13);

        listOfRateStar20.add(halfLeft1_20);listOfRateStar20.add(halfLeft2_20);listOfRateStar20.add(halfLeft3_20);listOfRateStar20.add(halfLeft4_20);listOfRateStar20.add(halfLeft5_20);
        listOfRateStar21.add(halfLeft1_21);listOfRateStar21.add(halfLeft2_21);listOfRateStar21.add(halfLeft3_21);listOfRateStar21.add(halfLeft4_21);listOfRateStar21.add(halfLeft5_21);
        listOfRateStar22.add(halfLeft1_22);listOfRateStar22.add(halfLeft2_22);listOfRateStar22.add(halfLeft3_22);listOfRateStar22.add(halfLeft4_22);listOfRateStar22.add(halfLeft5_22);
        listOfRateStar23.add(halfLeft1_23);listOfRateStar23.add(halfLeft2_23);listOfRateStar23.add(halfLeft3_23);listOfRateStar23.add(halfLeft4_23);listOfRateStar23.add(halfLeft5_23);

        listOfRateStar30.add(halfLeft1_30);listOfRateStar30.add(halfLeft2_30);listOfRateStar30.add(halfLeft3_30);listOfRateStar30.add(halfLeft4_30);listOfRateStar30.add(halfLeft5_30);
        listOfRateStar31.add(halfLeft1_31);listOfRateStar31.add(halfLeft2_31);listOfRateStar31.add(halfLeft3_31);listOfRateStar31.add(halfLeft4_31);listOfRateStar31.add(halfLeft5_31);
        listOfRateStar32.add(halfLeft1_32);listOfRateStar32.add(halfLeft2_32);listOfRateStar32.add(halfLeft3_32);listOfRateStar32.add(halfLeft4_32);listOfRateStar32.add(halfLeft5_32);
        listOfRateStar33.add(halfLeft1_33);listOfRateStar33.add(halfLeft2_33);listOfRateStar33.add(halfLeft3_33);listOfRateStar33.add(halfLeft4_33);listOfRateStar33.add(halfLeft5_33);

        listOfRateStar40.add(halfLeft1_40);listOfRateStar40.add(halfLeft2_40);listOfRateStar40.add(halfLeft3_40);listOfRateStar40.add(halfLeft4_40);listOfRateStar40.add(halfLeft5_40);
        listOfRateStar41.add(halfLeft1_41);listOfRateStar41.add(halfLeft2_41);listOfRateStar41.add(halfLeft3_41);listOfRateStar41.add(halfLeft4_41);listOfRateStar41.add(halfLeft5_41);
        listOfRateStar42.add(halfLeft1_42);listOfRateStar42.add(halfLeft2_42);listOfRateStar42.add(halfLeft3_42);listOfRateStar42.add(halfLeft4_42);listOfRateStar42.add(halfLeft5_42);
        listOfRateStar43.add(halfLeft1_43);listOfRateStar43.add(halfLeft2_43);listOfRateStar43.add(halfLeft3_43);listOfRateStar43.add(halfLeft4_43);listOfRateStar43.add(halfLeft5_43);


        listOfRateStar00.add(halfRight1_00);listOfRateStar00.add(halfRight2_00);listOfRateStar00.add(halfRight3_00);listOfRateStar00.add(halfRight4_00);listOfRateStar00.add(halfRight5_00);
        listOfRateStar01.add(halfRight1_01);listOfRateStar01.add(halfRight2_01);listOfRateStar01.add(halfRight3_01);listOfRateStar01.add(halfRight4_01);listOfRateStar01.add(halfRight5_01);
        listOfRateStar02.add(halfRight1_02);listOfRateStar02.add(halfRight2_02);listOfRateStar02.add(halfRight3_02);listOfRateStar02.add(halfRight4_02);listOfRateStar02.add(halfRight5_02);
        listOfRateStar03.add(halfRight1_03);listOfRateStar03.add(halfRight2_03);listOfRateStar03.add(halfRight3_03);listOfRateStar03.add(halfRight4_03);listOfRateStar03.add(halfRight5_03);

        listOfRateStar10.add(halfRight1_10);listOfRateStar10.add(halfRight2_10);listOfRateStar10.add(halfRight3_10);listOfRateStar10.add(halfRight4_10);listOfRateStar10.add(halfRight5_10);
        listOfRateStar11.add(halfRight1_11);listOfRateStar11.add(halfRight2_11);listOfRateStar11.add(halfRight3_11);listOfRateStar11.add(halfRight4_11);listOfRateStar11.add(halfRight5_11);
        listOfRateStar12.add(halfRight1_12);listOfRateStar12.add(halfRight2_12);listOfRateStar12.add(halfRight3_12);listOfRateStar12.add(halfRight4_12);listOfRateStar12.add(halfRight5_12);
        listOfRateStar13.add(halfRight1_13);listOfRateStar13.add(halfRight2_13);listOfRateStar13.add(halfRight3_13);listOfRateStar13.add(halfRight4_13);listOfRateStar13.add(halfRight5_13);

        listOfRateStar20.add(halfRight1_20);listOfRateStar20.add(halfRight2_20);listOfRateStar20.add(halfRight3_20);listOfRateStar20.add(halfRight4_20);listOfRateStar20.add(halfRight5_20);
        listOfRateStar21.add(halfRight1_21);listOfRateStar21.add(halfRight2_21);listOfRateStar21.add(halfRight3_21);listOfRateStar21.add(halfRight4_21);listOfRateStar21.add(halfRight5_21);
        listOfRateStar22.add(halfRight1_22);listOfRateStar22.add(halfRight2_22);listOfRateStar22.add(halfRight3_22);listOfRateStar22.add(halfRight4_22);listOfRateStar22.add(halfRight5_22);
        listOfRateStar23.add(halfRight1_23);listOfRateStar23.add(halfRight2_23);listOfRateStar23.add(halfRight3_23);listOfRateStar23.add(halfRight4_23);listOfRateStar23.add(halfRight5_23);

        listOfRateStar30.add(halfRight1_30);listOfRateStar30.add(halfRight2_30);listOfRateStar30.add(halfRight3_30);listOfRateStar30.add(halfRight4_30);listOfRateStar30.add(halfRight5_30);
        listOfRateStar31.add(halfRight1_31);listOfRateStar31.add(halfRight2_31);listOfRateStar31.add(halfRight3_31);listOfRateStar31.add(halfRight4_31);listOfRateStar31.add(halfRight5_31);
        listOfRateStar32.add(halfRight1_32);listOfRateStar32.add(halfRight2_32);listOfRateStar32.add(halfRight3_32);listOfRateStar32.add(halfRight4_32);listOfRateStar32.add(halfRight5_32);
        listOfRateStar33.add(halfRight1_33);listOfRateStar33.add(halfRight2_33);listOfRateStar33.add(halfRight3_33);listOfRateStar33.add(halfRight4_33);listOfRateStar33.add(halfRight5_33);

        listOfRateStar40.add(halfRight1_40);listOfRateStar40.add(halfRight2_40);listOfRateStar40.add(halfRight3_40);listOfRateStar40.add(halfRight4_40);listOfRateStar40.add(halfRight5_40);
        listOfRateStar41.add(halfRight1_41);listOfRateStar41.add(halfRight2_41);listOfRateStar41.add(halfRight3_41);listOfRateStar41.add(halfRight4_41);listOfRateStar41.add(halfRight5_41);
        listOfRateStar42.add(halfRight1_42);listOfRateStar42.add(halfRight2_42);listOfRateStar42.add(halfRight3_42);listOfRateStar42.add(halfRight4_42);listOfRateStar42.add(halfRight5_42);
        listOfRateStar43.add(halfRight1_43);listOfRateStar43.add(halfRight2_43);listOfRateStar43.add(halfRight3_43);listOfRateStar43.add(halfRight4_43);listOfRateStar43.add(halfRight5_43);

        gridPaneToRate.put(g00,listOfRateStar00);gridPaneToRate.put(g01,listOfRateStar01);gridPaneToRate.put(g02,listOfRateStar02);gridPaneToRate.put(g03,listOfRateStar03);
        gridPaneToRate.put(g10,listOfRateStar10);gridPaneToRate.put(g11,listOfRateStar11);gridPaneToRate.put(g12,listOfRateStar12);gridPaneToRate.put(g13,listOfRateStar13);
        gridPaneToRate.put(g20,listOfRateStar20);gridPaneToRate.put(g21,listOfRateStar21);gridPaneToRate.put(g22,listOfRateStar22);gridPaneToRate.put(g23,listOfRateStar23);
        gridPaneToRate.put(g30,listOfRateStar30);gridPaneToRate.put(g31,listOfRateStar31);gridPaneToRate.put(g32,listOfRateStar32);gridPaneToRate.put(g33,listOfRateStar33);
        gridPaneToRate.put(g40,listOfRateStar40);gridPaneToRate.put(g41,listOfRateStar41);gridPaneToRate.put(g42,listOfRateStar42);gridPaneToRate.put(g43,listOfRateStar43);

        gridPaneToOffRemain.put(g00,offRemain_00);gridPaneToOffRemain.put(g01,offRemain_01);gridPaneToOffRemain.put(g02,offRemain_02);gridPaneToOffRemain.put(g03,offRemain_03);
        gridPaneToOffRemain.put(g10,offRemain_10);gridPaneToOffRemain.put(g11,offRemain_11);gridPaneToOffRemain.put(g12,offRemain_12);gridPaneToOffRemain.put(g13,offRemain_13);
        gridPaneToOffRemain.put(g20,offRemain_20);gridPaneToOffRemain.put(g21,offRemain_21);gridPaneToOffRemain.put(g22,offRemain_22);gridPaneToOffRemain.put(g23,offRemain_23);
        gridPaneToOffRemain.put(g30,offRemain_30);gridPaneToOffRemain.put(g31,offRemain_31);gridPaneToOffRemain.put(g32,offRemain_32);gridPaneToOffRemain.put(g33,offRemain_33);
        gridPaneToOffRemain.put(g40,offRemain_40);gridPaneToOffRemain.put(g41,offRemain_41);gridPaneToOffRemain.put(g42,offRemain_42);gridPaneToOffRemain.put(g43,offRemain_43);

        gridPaneToOffPercent.put(g00,offPercent_00);gridPaneToOffPercent.put(g01,offPercent_01);gridPaneToOffPercent.put(g02,offPercent_02);gridPaneToOffPercent.put(g03,offPercent_03);
        gridPaneToOffPercent.put(g10,offPercent_10);gridPaneToOffPercent.put(g11,offPercent_11);gridPaneToOffPercent.put(g12,offPercent_12);gridPaneToOffPercent.put(g13,offPercent_13);
        gridPaneToOffPercent.put(g20,offPercent_20);gridPaneToOffPercent.put(g21,offPercent_21);gridPaneToOffPercent.put(g22,offPercent_22);gridPaneToOffPercent.put(g23,offPercent_23);
        gridPaneToOffPercent.put(g30,offPercent_30);gridPaneToOffPercent.put(g31,offPercent_31);gridPaneToOffPercent.put(g32,offPercent_32);gridPaneToOffPercent.put(g33,offPercent_33);
        gridPaneToOffPercent.put(g40,offPercent_40);gridPaneToOffPercent.put(g41,offPercent_41);gridPaneToOffPercent.put(g42,offPercent_42);gridPaneToOffPercent.put(g43,offPercent_43);


//        Controller.check();
//        Controller.restartSortedOrFilteredProduct();
        loadProduct(counter);


        ArrayList<String> brands=new ArrayList<String>(Filter.getAvailableBrands()  );
        for (String brand : brands) {
            CheckBox checkBox=new CheckBox();
            checkBox.setText(brand);
            checkBox.setOnMouseClicked(this::filterByBrand);
            checkBox.setMinWidth(170.3);
            brandsCheckBoxes.add(checkBox);
            brandFilter.getItems().add(checkBox);
        }
        if(brandsCheckBoxes.size()>4){
            brandFilter.setPrefHeight(4*24);
        }else {
            brandFilter.setPrefHeight(brandsCheckBoxes.size() * 24);
        }

        Controller.cancelSong();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startSong("src/main/resources/Sound/ProductsMenu/BackGround.mp3");
            }
        }).start();
    }

    public void click(MouseEvent mouseEvent) {
        GridPane gridPane=(GridPane) mouseEvent.getSource();
        Controller.setSelectedProduct(Controller.getProductById(gridPaneToProductId.get(gridPane)));
        Scene scene=gridPane.getScene();
        Stage stage=(Stage) (scene.getWindow());
        Controller.setLastPane(Controller.getCurrentPane());
        Pane pane = null;
        Pane minibar = null;
        try {
            pane= FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ProductPage.fxml"));
            minibar=FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainBar.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScrollPane scrollPane = (ScrollPane) pane.getChildren().get(0);
        scrollPane.setPrefHeight(800);
        pane.getChildren().add(minibar);
        Controller.setCurrentPane(pane);
        scene.setRoot(pane);
        stage.setScene(scene);
        stage.show();

    }

    public void clear(){
        categoriesCheckBoxes.clear();
//        brandsCheckBoxes.clear();
//        brandFilter.getItems().clear();
        gridPaneToProductId.clear();
        categoriesFilter.getItems().clear();
        for (GridPane gridPane : gridPanes) {
            gridPane.setVisible(false);
            gridPane.setDisable(true);
            gridPaneToCheckBox.get(gridPane).setSelected(false);
            gridPane.setEffect(null);
            gridPaneToOff.get(gridPane).setVisible(false);
            gridPaneToOff.get(gridPane).setDisable(true);
            setStart(gridPane,-1);
            gridPaneToOffPercent.get(gridPane).setText("");
            gridPaneToOffRemain.get(gridPane).setText("");
            gridPane.setOpacity(1);
        }
    }

    public void loadProduct(long start){
        clear();

        if (Controller.isDoesItOffPage()){
            Filter.filterByOffs();
            saleLeft.setVisible(true);
            saleRight.setVisible(true);
            saleRight.setDisable(false);
            saleLeft.setDisable(false);
            offFilter.setSelected(true);
        }else{
            saleLeft.setVisible(false);
            saleRight.setVisible(false);
            saleRight.setDisable(true);
            saleLeft.setDisable(true);
        }

        ArrayList<String> categories=new ArrayList<String>(Filter.showSubCategories());
        for (String category : categories) {
            Label label=new Label();
            label.setOnMouseClicked(this::filterByCategory);
            label.setText(category);
            label.setMinWidth(170.3);
            categoriesCheckBoxes.add(label);
            categoriesFilter.getItems().add(label);
        }
        if (categoriesCheckBoxes.isEmpty()){
            categoriesLabel.setDisable(true);
            categoriesLabel.setVisible(false);
            categoriesLabel.setMinWidth(0);
        }else {
            categoriesLabel.setDisable(false);
            categoriesLabel.setVisible(true);
            if (categoriesCheckBoxes.size()>3){
                categoriesFilter.setPrefHeight(3 * 25);
            }else {
                categoriesFilter.setPrefHeight(categoriesCheckBoxes.size()* 25);
            }
        }

        int size=Controller.getHowMuchLeftForThisPage(counter);


        ArrayList<String> images = Controller.getProductImageForFxml(counter);
        ArrayList<Double> prices = Controller.getProductPriceForFxml(counter);
        ArrayList<String> names = Controller.getProductNameForFxml(counter);
        ArrayList<Long> productId=Controller.getProductIdForFxml(counter);
        ArrayList<Boolean> isOff=Controller.getOffForFxml(counter);
        ArrayList<Double> score=Controller.getProductScoreForFxml(counter);
        ArrayList<Double> remainOffTimeLeft=Controller.getProductOffRemainForFxml(counter);
        ArrayList<Integer> offPercent=Controller.getProductOffPercentForFxml(counter);
        ArrayList<Integer> remainingItems=Controller.getProductRemainForFxml(counter);
        for (int i = 0; i < size; i++) {
            gridPanes.get(i).setVisible(true);
            gridPanes.get(i).setDisable(false);
            Image image=new Image(images.get(i));
            GridPane gridPane=gridPanes.get(i);
            gridPaneToImageView.get(gridPane).setImage(image);
            gridPaneToNameAmount.get(gridPane).setText(names.get(i));
            gridPaneToPriceAmount.get(gridPane).setText(prices.get(i).toString());
            gridPaneToProductId.put(gridPane,productId.get(i));
            if (remainOffTimeLeft.get(i)!=-1.0){
                gridPaneToOffRemain.get(gridPane).setText(String.valueOf(remainOffTimeLeft.get(i)));
                gridPaneToOffPercent.get(gridPane).setText(String.valueOf(offPercent.get(i))+"%");
            }
            if (remainingItems.get(i)==0){
                gridPane.setOpacity(0.5);
            }

            setStart(gridPane,score.get(i));

            if (isOff.get(i)){
                gridPaneToOff.get(gridPanes.get(i)).setVisible(true);
                gridPaneToOff.get(gridPanes.get(i)).setDisable(false);
            }
        }

        max=0;
        for (Double price : prices) {
            if (price>=max){
                max=price;
            }
        }
        min=max;
        for (Double price : prices) {
            if (price<=min){
                min=price;
            }
        }


        int mid= (int) ((max-min)/2);
        minPrice.setText(String.valueOf(min));
        if (mid==0){
            midPrice.setText("");
        }else {
            midPrice.setText(String.valueOf(mid));
        }
        maxPrice.setText(String.valueOf(max));

        for (GridPane gridPane : gridPanes) {

            if (compareIds.contains(gridPaneToProductId.get(gridPane))){
                gridPaneToCheckBox.get(gridPane).setVisible(true);
                gridPaneToCheckBox.get(gridPane).setDisable(false);
                gridPaneToCheckBox.get(gridPane).setSelected(true);
                DropShadow dropShadow=new DropShadow();
                gridPane.setEffect(dropShadow);
            }
        }

        currentSize=size;
        counter=+size;

        int pageNumber= (int) (counter/20+1);
        int totalPage=Controller.getAllPageNumber();
        pageCounter.setText(".. Page: "+pageNumber+" of "+totalPage+" ..");
        if (pageNumber!=1){
            prePageImage.setVisible(true);
            prePageImage.setDisable(false);
        }else {
            prePageImage.setVisible(false);
            prePageImage.setDisable(true);
        }
        if (pageNumber!=totalPage){
            nextPageImage.setDisable(false);
            nextPageImage.setVisible(true);
        }else {
            nextPageImage.setDisable(true);
            nextPageImage.setVisible(false);
        }


    }

    public void setStart(GridPane gridPane ,double score){
        ImageView halfLeft1 = null;ImageView halfLeft2 = null;ImageView halfLeft3 = null;ImageView halfLeft4 = null;ImageView halfLeft5 = null;
        ImageView halfRight1 = null;ImageView halfRight2 = null;ImageView halfRight3 = null;ImageView halfRight4 = null;ImageView halfRight5 = null;

        ImageView leftGreen1 = null;ImageView leftGreen2 = null;ImageView leftGreen3 = null;ImageView leftGreen4 = null;ImageView leftGreen5 = null;
        ImageView rightGreen1 = null;ImageView rightGreen2 = null;ImageView rightGreen3 = null;ImageView rightGreen4 = null;ImageView rightGreen5 = null;

        ArrayList<ImageView> test =gridPaneToRate.get(gridPane);

        for (ImageView imageView : gridPaneToRate.get(gridPane)) {
            if (imageView.getId().startsWith("halfLeft1")){
                halfLeft1=imageView;
                continue;
            }if (imageView.getId().startsWith("halfLeft2")){
                halfLeft2=imageView;
                continue;
            }if (imageView.getId().startsWith("halfLeft3")){
                halfLeft3=imageView;
                continue;
            }if (imageView.getId().startsWith("halfLeft4")){
                halfLeft4=imageView;
                continue;
            }if (imageView.getId().startsWith("halfLeft5")){
                halfLeft5=imageView;
                continue;
            }
            if (imageView.getId().startsWith("halfRight1")){
                halfRight1=imageView;
                continue;
            }if (imageView.getId().startsWith("halfRight2")){
                halfRight2=imageView;
                continue;
            }if (imageView.getId().startsWith("halfRight3")){
                halfRight3=imageView;
                continue;
            }if (imageView.getId().startsWith("halfRight4")){
                halfRight4=imageView;
                continue;
            }if (imageView.getId().startsWith("halfRight5")){
                halfRight5=imageView;
                continue;
            }
            if (imageView.getId().startsWith("leftGreen1")){
                leftGreen1=imageView;
                continue;
            }if (imageView.getId().startsWith("leftGreen2")){
                leftGreen2=imageView;
                continue;
            }if (imageView.getId().startsWith("leftGreen3")){
                leftGreen3=imageView;
                continue;
            }if (imageView.getId().startsWith("leftGreen4")){
                leftGreen4=imageView;
                continue;
            }if (imageView.getId().startsWith("leftGreen5")){
                leftGreen5=imageView;
                continue;
            }
            if (imageView.getId().startsWith("rightGreen1")){
                rightGreen1=imageView;
                continue;
            }if (imageView.getId().startsWith("rightGreen2")){
                rightGreen2=imageView;
                continue;
            }if (imageView.getId().startsWith("rightGreen3")){
                rightGreen3=imageView;
                continue;
            }if (imageView.getId().startsWith("rightGreen4")){
                rightGreen4=imageView;
                continue;
            }if (imageView.getId().startsWith("rightGreen5")){
                rightGreen5=imageView;
                continue;
            }
        }
        halfLeft1.toFront();halfRight1.toFront();
        halfLeft2.toFront();halfRight2.toFront();
        halfLeft3.toFront();halfRight3.toFront();
        halfLeft4.toFront();halfRight4.toFront();
        halfLeft5.toFront();halfRight5.toFront();
        if (score==-1){
            return;
        }
        if (score>0){
            leftGreen1.toFront();
        }
        if (score>=0.5){
            rightGreen1.toFront();
        }
        if (score>=1){
            leftGreen2.toFront();
        }
        if (score>=1.5){
            rightGreen2.toFront();
        }
        if (score>=2){
            leftGreen3.toFront();
        }
        if (score>=2.5){
            rightGreen3.toFront();
        }
        if (score>=3){
            leftGreen4.toFront();
        }
        if (score>=3.5){
            rightGreen4.toFront();
        }
        if (score>=4){
            leftGreen5.toFront();
        }
        if (score>=4.5){
            rightGreen5.toFront();
        }
    }


    ArrayList<Long> compareIds=new ArrayList<Long>();

    private boolean fourCompareChecker=false;

    public void makeGridPaneCompareOn(MouseEvent mouseEvent) {
        GridPane gridPane=(GridPane) mouseEvent.getSource();
        if (!fourCompareChecker) {
            gridPaneToCheckBox.get(gridPane).setVisible(true);
            gridPaneToCheckBox.get(gridPane).setDisable(false);
        }
        DropShadow dropShadow=new DropShadow();
        gridPane.setEffect(dropShadow);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.startClickSound();
            }
        }).start();
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
        CheckBox checkBox=(CheckBox)mouseEvent.getSource();
        if (checkBox.isSelected()){
            compareIds.add(gridPaneToProductId.get(checkBoxToGridPane.get(checkBox)));
        }else {
            compareIds.remove(gridPaneToProductId.get(checkBoxToGridPane.get(checkBox)));
        }
        if (compareIds.size()==4){
            fourCompareChecker=true;
        }else {
            fourCompareChecker=false;
        }

        if (compareIds.size()>=2){
            compareButton.setVisible(true);
            compareButton.setDisable(false);
        }else {
            compareButton.setVisible(false);
            compareButton.setDisable(true);
        }
    }

    public void compare(MouseEvent mouseEvent){
//        ArrayList<Long> chosen=new ArrayList<>();
//        for (GridPane gridPane : gridPanes) {
//            if (gridPaneToCheckBox.get(gridPane).isSelected()){
//                chosen.add(gridPaneToProductId.get(gridPane));
//            }
//        }
        System.out.println(compareIds);
    }


    public void filterByCategory(MouseEvent mouseEvent) {

        for (CheckBox brandsCheckBox : brandsCheckBoxes) {
            Filter.disableBrandFilter(brandsCheckBox.getText());
        }

        if (mouseEvent.getSource() instanceof Label){
            Label label=(Label) mouseEvent.getSource();
            Filter.filterByCategory(label.getText());
        }else{
            return;
        }

        compareButton.setVisible(false);
        compareButton.setDisable(true);

        for (GridPane gridPane : gridPanes) {
            gridPaneToCheckBox.get(gridPane).setSelected(false);
        }
        compareIds.clear();

        brandsCheckBoxes.clear();
        brandFilter.getItems().clear();

        ArrayList<String> brands=new ArrayList<String>(Filter.getAvailableBrands()  );
        for (String brand : brands) {
            CheckBox checkBox=new CheckBox();
            checkBox.setText(brand);
            checkBox.setOnMouseClicked(this::filterByBrand);
            checkBox.setMinWidth(170.3);
            brandsCheckBoxes.add(checkBox);
            brandFilter.getItems().add(checkBox);
        }
        brandFilter.setPrefHeight(brandsCheckBoxes.size()*25);

        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isSelected()){
                checkBoxToGridPane.get(checkBoxes.get(i)).setEffect(null);
                checkBoxes.get(i).setVisible(false);
                checkBoxes.get(i).setDisable(true);
            }
        }

        counter=0;
        loadProduct(counter);
    }

    public void filterByBrand(MouseEvent mouseEvent) {


        for (CheckBox brandsCheckBox : brandsCheckBoxes) {
            if (brandsCheckBox.isSelected()){
                Filter.setIsItFilteredByBrand(true);
                Filter.addBrand(brandsCheckBox.getText());
                Filter.filter();
            }else{
                Filter.disableBrandFilter(brandsCheckBox.getText());
            }

        }


        counter=0;
        loadProduct(counter);
    }

    public void FilterByName(ActionEvent actionEvent) {
        if (nameFilter.getText().isEmpty()){
            Filter.disableNameFilter();

        }else {
            Filter.filterByName(nameFilter.getText());
        }
        counter=0;
        loadProduct(counter);
    }

    public void offFilter(ActionEvent actionEvent) {
        if (offFilter.isSelected()){
            Filter.filterByOffs();
            Controller.setDoesItOffPage(true);
        }else{
            Filter.disableOffsFilter();
            Controller.setDoesItOffPage(false);
        }

        counter=0;
        loadProduct(counter);
    }

    public void availabilityFilter(ActionEvent actionEvent) {
        if (availabilityFilter.isSelected()){
            Filter.filterByAvailability();
        }else {
            Filter.disableAvailabilityFilter();
        }
        counter=0;
        loadProduct(counter);
    }


    public void scoreButtonClicked(MouseEvent mouseEvent) {
        if (!scoreClicked){

            scoreButton.setDisable(false);
            scoreButton.setVisible(true);
            scoreClicked=true;
            viewClicked=false;
            timeClicked=false;

            timeButton.setDisable(true);
            timeButton.setVisible(false);
            timeLabel.setDisable(false);
            timeLabel.setVisible(true);

            viewButton.setDisable(true);
            viewButton.setVisible(false);
            viewLabel.setDisable(false);
            viewLabel.setVisible(true);

            Sort.sortByScore();

            counter=0;
            loadProduct(0);

        }else {
            Sort.disableSort();
            counter=0;
            loadProduct(0);
            scoreClicked=false;
        }

    }

    public void scoreButtonOn(MouseEvent mouseEvent) {
        scoreButton.setDisable(false);
        scoreButton.setVisible(true);
        scoreLabel.setDisable(true);
        scoreLabel.setVisible(false);

    }

    public void scoreButtonOff(MouseEvent mouseEvent) {
        if (!scoreClicked) {
            scoreButton.setDisable(true);
            scoreButton.setVisible(false);
            scoreLabel.setDisable(false);
            scoreLabel.setVisible(true);
        }
    }

    public void viewButtonClick(MouseEvent mouseEvent) {
        if (!viewClicked){

            viewButton.setDisable(false);
            viewButton.setVisible(true);
            viewClicked=true;
            scoreClicked=false;
            timeClicked=false;

            timeButton.setDisable(true);
            timeButton.setVisible(false);
            timeLabel.setDisable(false);
            timeLabel.setVisible(true);

            scoreButton.setDisable(true);
            scoreButton.setVisible(false);
            scoreLabel.setDisable(false);
            scoreLabel.setVisible(true);

            Sort.sortByView();

            counter=0;
            loadProduct(0);
        }else {
            Sort.disableSort();
            counter=0;
            loadProduct(0);
            viewClicked=false;
        }

    }

    public void viewButtonOn(MouseEvent mouseEvent) {
        viewButton.setDisable(false);
        viewButton.setVisible(true);
        viewLabel.setDisable(true);
        viewLabel.setVisible(false);
    }

    public void viewButtonOff(MouseEvent mouseEvent) {
        if (!viewClicked) {
            viewButton.setDisable(true);
            viewButton.setVisible(false);
            viewLabel.setDisable(false);
            viewLabel.setVisible(true);
        }
    }

    public void timeButtonClick(MouseEvent mouseEvent) {
        if (!timeClicked) {

            timeButton.setDisable(false);
            timeButton.setVisible(true);
            timeClicked =true;
            scoreClicked=false;
            viewClicked=false;

            viewButton.setDisable(true);
            viewButton.setVisible(false);
            viewLabel.setDisable(false);
            viewLabel.setVisible(true);

            scoreButton.setDisable(true);
            scoreButton.setVisible(false);
            scoreLabel.setDisable(false);
            scoreLabel.setVisible(true);

            Sort.sortByTime();

            counter=0;
            loadProduct(counter);

        }else {
            Sort.disableSort();
            counter=0;
            loadProduct(counter);
            timeClicked =false;
        }

    }

    public void timeButtonOn(MouseEvent mouseEvent) {
        timeButton.setDisable(false);
        timeButton.setVisible(true);
        timeLabel.setDisable(true);
        timeLabel.setVisible(false);
    }

    public void timeButtonOff(MouseEvent mouseEvent) {
        if (!timeClicked) {
            timeButton.setDisable(true);
            timeButton.setVisible(false);
            timeLabel.setDisable(false);
            timeLabel.setVisible(true);
        }

    }


    public void nextPage(MouseEvent mouseEvent) {
        loadProduct(counter);
    }

    public void prePage(MouseEvent mouseEvent) {
        counter=counter-currentSize;
        loadProduct(counter);
    }


    public void scrollStarted(MouseEvent scrollEvent) {

    }

    public void scrollFinished(MouseEvent mouseEvent) {
        double minPercentage=priceFilterMin.getValue();
        double maxPercentage=100-priceFilterMax.getValue();
        if (minPercentage>maxPercentage){
            priceFilterMin.setValue(previousMinValue);
            priceFilterMax.setValue(previousMaxValue);
            return;
        }
        System.out.println(minPercentage);
        System.out.println(maxPercentage);
        double maxPrice = (maxPercentage * max) / 100;
        double minPrice = (minPercentage * max) / 100;

        System.out.println(minPrice);
        System.out.println(maxPrice);

        if (maxPercentage==100){
            maxPrice=999999999;
        }
        if (minPercentage==0){
            minPrice=0;
        }

        Filter.disablePriceFilter();

        Filter.setIsItFilteredByPrice(true);
        Filter.setMinPrice(minPrice);
        Filter.setMaxPrice(maxPrice);
        Filter.filter();

        previousMinValue=priceFilterMin.getValue();
        previousMaxValue=priceFilterMax.getValue();

        counter=counter-(long)currentSize;
        loadProduct(counter);
    }
}



