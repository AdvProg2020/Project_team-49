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
            Assert.assertEquals( "Show All Products",outputs);

            outputs = productsPage.getCommandKey("shoW all proDucTs");
            Assert.assertEquals( "Show All Products",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Show All Products    ");
//            Assert.assertEquals(outputs, "Show All Products");

            outputs = productsPage.getCommandKey("Show   All   Products");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show All Products asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show All Productsasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Show All Products");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdShow All Products");
            Assert.assertEquals( "invalid",outputs);
        }

        //Show Categories
        {
            outputs = productsPage.getCommandKey("Show Categories");
            Assert.assertEquals( "Show Categories",outputs);

            outputs = productsPage.getCommandKey("shoW caTegoRies");
            Assert.assertEquals( "Show Categories",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Show Categories    ");
//            Assert.assertEquals(outputs, "Show Categories");

            outputs = productsPage.getCommandKey("Show     Categories");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show Categories asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Show Categories");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show Categoriesasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdShow Categories");
            Assert.assertEquals( "invalid",outputs);
        }

        //Filtering
        {
            outputs = productsPage.getCommandKey("Filtering");
            Assert.assertEquals( "Filtering",outputs);

            outputs = productsPage.getCommandKey("fiLteRinG");
            Assert.assertEquals( "Filtering",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Filtering    ");
//            Assert.assertEquals(outputs, "Filtering");

            outputs = productsPage.getCommandKey("Filt   ering");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Filtering asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Filtering");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Filteringasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdFiltering");
            Assert.assertEquals( "invalid",outputs);
        }

        //Sorting
        {
            outputs = productsPage.getCommandKey("Sorting");
            Assert.assertEquals( "Sorting",outputs);

            outputs = productsPage.getCommandKey("sorTinG");
            Assert.assertEquals( "Sorting",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Sorting    ");
//            Assert.assertEquals(outputs, "Sorting");

            outputs = productsPage.getCommandKey("Sort  ing");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Sorting asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Sorting");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Sortingasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdSorting");
            Assert.assertEquals( "invalid",outputs);
        }

        //Show Product
        {
            DataBase.addNewProduct(new Product("Test","TestBrand",200,"Just For Test",null,null,1));
            outputs = productsPage.getCommandKey("show product 1");
            System.out.println(outputs);
            Assert.assertEquals( "Show Product",outputs);

            outputs = productsPage.getCommandKey("sHow proDuct 1");
            Assert.assertEquals("Show Product",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Show Product 123    ");
//            Assert.assertEquals(outputs, "Show Product");

            outputs = productsPage.getCommandKey("Show   Product   1");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show Product 1 asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Show Product 1");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show Product 1asd");
            Assert.assertEquals("invalid",outputs);

            outputs = productsPage.getCommandKey("asdShow Product 1");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show asdProduct 1");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Show Product 12");
            Assert.assertEquals( "invalid",outputs);
        }

        //Log In
        {
            outputs = productsPage.getCommandKey("Log In");
            Assert.assertEquals( "Log In",outputs);

            outputs = productsPage.getCommandKey("loG iN");
            Assert.assertEquals( "Log In",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Sorting    ");
//            Assert.assertEquals(outputs, "Sorting");

            outputs = productsPage.getCommandKey("Log    In");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Log In asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Log In");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Log Inasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdLog In");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Log asd In");
            Assert.assertEquals( "invalid",outputs);
        }

        //Log Out
        {
            outputs = productsPage.getCommandKey("Log Out");
            Assert.assertEquals( "Log Out",outputs);

            outputs = productsPage.getCommandKey("loG ouT");
            Assert.assertEquals( "Log Out",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Sorting    ");
//            Assert.assertEquals(outputs, "Sorting");

            outputs = productsPage.getCommandKey("Log    Out");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Log Out asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Log Out");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Log Outasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdLog Out");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Log asd Out");
            Assert.assertEquals( "invalid",outputs);
        }

        //Help
        {
            outputs = productsPage.getCommandKey("Help");
            Assert.assertEquals( "help",outputs);

            outputs = productsPage.getCommandKey("hElP");
            Assert.assertEquals( "help",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Sorting    ");
//            Assert.assertEquals(outputs, "Sorting");

            outputs = productsPage.getCommandKey("He  lp");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Help asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Help");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Helpasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdHelp");
            Assert.assertEquals( "invalid",outputs);
        }

        //Back
        {
            outputs = productsPage.getCommandKey("Back");
            Assert.assertEquals( "back",outputs);

            outputs = productsPage.getCommandKey("bACk");
            Assert.assertEquals( "back",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Sorting    ");
//            Assert.assertEquals(outputs, "Sorting");

            outputs = productsPage.getCommandKey("Ba   ck");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Back asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asd  Back");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("Backasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = productsPage.getCommandKey("asdBack");
            Assert.assertEquals( "invalid",outputs);
        }
    }

    //ino che konam?
    @Test
    public void Run(){

    }
}
