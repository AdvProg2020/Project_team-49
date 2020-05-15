package View.Menu.OffsAndProductsMenu;

import Controller.DataBase;
import Models.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ProductsPageTest {
    private ProductsPage productsPage=new ProductsPage(null);

    @Test
    public void testGetCommandKey(){
        String outputs ="";
        //Show All Products
        {
            outputs = productsPage.getCommandKey("Show All Products");
            Assert.assertEquals(outputs, "Show All Products");

            outputs = productsPage.getCommandKey("shoW all proDucTs");
            Assert.assertEquals(outputs, "Show All Products");

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Show All Products    ");
//            Assert.assertEquals(outputs, "Show All Products");

            outputs = productsPage.getCommandKey("Show   All   Products");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Show All Products asd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Show All Productsasd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asd  Show All Products");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asdShow All Products");
            Assert.assertEquals(outputs, "invalid");
        }

        //Show Categories
        {
            outputs = productsPage.getCommandKey("Show Categories");
            Assert.assertEquals(outputs, "Show Categories");

            outputs = productsPage.getCommandKey("shoW caTegoRies");
            Assert.assertEquals(outputs, "Show Categories");

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Show Categories    ");
//            Assert.assertEquals(outputs, "Show Categories");

            outputs = productsPage.getCommandKey("Show     Categories");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Show Categories asd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asd  Show Categories");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Show Categoriesasd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asdShow Categories");
            Assert.assertEquals(outputs, "invalid");
        }

        //Filtering
        {
            outputs = productsPage.getCommandKey("Filtering");
            Assert.assertEquals(outputs, "Filtering");

            outputs = productsPage.getCommandKey("fiLteRinG");
            Assert.assertEquals(outputs, "Filtering");

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Filtering    ");
//            Assert.assertEquals(outputs, "Filtering");

            outputs = productsPage.getCommandKey("Filt   ering");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Filtering asd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asd  Filtering");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Filteringasd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asdFiltering");
            Assert.assertEquals(outputs, "invalid");
        }

        //Sorting
        {
            outputs = productsPage.getCommandKey("Sorting");
            Assert.assertEquals(outputs, "Sorting");

            outputs = productsPage.getCommandKey("sorTinG");
            Assert.assertEquals(outputs, "Sorting");

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Sorting    ");
//            Assert.assertEquals(outputs, "Sorting");

            outputs = productsPage.getCommandKey("Sort  ing");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Sorting asd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asd  Sorting");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("Sortingasd");
            Assert.assertEquals(outputs, "invalid");

            outputs = productsPage.getCommandKey("asdSorting");
            Assert.assertEquals(outputs, "invalid");
        }

        //Show Product
        {
            DataBase.addNewProduct(new Product("Test","TestBrand",200,"Just For Test",null,null,1));
            outputs = productsPage.getCommandKey("show product 1");
            System.out.println(outputs);
            Assert.assertEquals(outputs, "Show Product");

//            outputs = productsPage.getCommandKey("sHow proDuct 123");
//            Assert.assertEquals(outputs, "Show Product");
//
//            //to Menu Trim mishe
////            outputs = productsPage.getCommandKey("   Show Product 123    ");
////            Assert.assertEquals(outputs, "Show Product");
//
//            outputs = productsPage.getCommandKey("Show   Product   123");
//            Assert.assertEquals(outputs, "invalid");
//
//            outputs = productsPage.getCommandKey("Show Product 123 asd");
//            Assert.assertEquals(outputs, "invalid");
//
//            outputs = productsPage.getCommandKey("asd  Show Product 123");
//            Assert.assertEquals(outputs, "invalid");
//
//            outputs = productsPage.getCommandKey("Show Product 123asd");
//            Assert.assertEquals(outputs, "invalid");
//
//            outputs = productsPage.getCommandKey("asdShow Product 123");
//            Assert.assertEquals(outputs, "invalid");
//
//            outputs = productsPage.getCommandKey("Show asdProduct 123");
//            Assert.assertEquals(outputs, "invalid");
        }


    }
}
