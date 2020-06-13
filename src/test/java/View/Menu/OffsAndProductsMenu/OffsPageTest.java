package View.Menu.OffsAndProductsMenu;

import Controller.DataBase;
import Models.Product;
import org.junit.Assert;
import org.junit.Test;

public class OffsPageTest {
    private OffsPage OffsPage =new OffsPage(null);

    @Test
    public void testGetCommandKey(){
        String outputs ="";
        //Show All Products
        {
            outputs = OffsPage.getCommandKey("Show Off Products");
            Assert.assertEquals( "Show Off Products",outputs);

            outputs = OffsPage.getCommandKey("shoW oFF proDucTs");
            Assert.assertEquals( "Show Off Products",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Show All Products    ");
//            Assert.assertEquals( "Show All Products",outputs);

            outputs = OffsPage.getCommandKey("Show   All   Products");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show All Products asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show All Productsasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Show All Products");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdShow All Products");
            Assert.assertEquals( "invalid",outputs);
        }

        //Show Categories
        {
            outputs = OffsPage.getCommandKey("Show Categories");
            Assert.assertEquals( "Show Categories",outputs);

            outputs = OffsPage.getCommandKey("shoW caTegoRies");
            Assert.assertEquals( "Show Categories",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Show Categories    ");
//            Assert.assertEquals( "Show Categories",outputs,);

            outputs = OffsPage.getCommandKey("Show     Categories");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show Categories asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Show Categories");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show Categoriesasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdShow Categories");
            Assert.assertEquals( "invalid",outputs);
        }

        //Filtering
        {
            outputs = OffsPage.getCommandKey("Filtering");
            Assert.assertEquals( "Filtering",outputs);

            outputs = OffsPage.getCommandKey("fiLteRinG");
            Assert.assertEquals( "Filtering",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Filtering    ");
//            Assert.assertEquals( "Filtering",outputs);

            outputs = OffsPage.getCommandKey("Filt   ering");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Filtering asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Filtering");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Filteringasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdFiltering");
            Assert.assertEquals( "invalid",outputs);
        }

        //Sorting
        {
            outputs = OffsPage.getCommandKey("Sorting");
            Assert.assertEquals( "Sorting",outputs);

            outputs = OffsPage.getCommandKey("sorTinG");
            Assert.assertEquals( "Sorting",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Sorting    ");
//            Assert.assertEquals("Sorting",outputs);

            outputs = OffsPage.getCommandKey("Sort  ing");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Sorting asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Sorting");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Sortingasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdSorting");
            Assert.assertEquals( "invalid",outputs);
        }

        //Show Product
        {
            DataBase.addNewProduct(new Product("Test","TestBrand",200,"Just For Test",null,null,1,""));
            outputs = OffsPage.getCommandKey("show product 1");
            System.out.println(outputs);
            Assert.assertEquals( "Show Product",outputs);

            outputs = OffsPage.getCommandKey("sHow proDuct 1");
            Assert.assertEquals("Show Product",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Show Product 123    ");
//            Assert.assertEquals(outputs, "Show Product");

            outputs = OffsPage.getCommandKey("Show   Product   1");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show Product 1 asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Show Product 1");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show Product 1asd");
            Assert.assertEquals("invalid",outputs);

            outputs = OffsPage.getCommandKey("asdShow Product 1");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show asdProduct 1");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Show Product 12");
            Assert.assertEquals( "invalid",outputs);
        }

        //Log In
        {
            outputs = OffsPage.getCommandKey("Log In");
            Assert.assertEquals( "Log In",outputs);

            outputs = OffsPage.getCommandKey("loG iN");
            Assert.assertEquals( "Log In",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Log In    ");
//            Assert.assertEquals("Log In", outputs);

            outputs = OffsPage.getCommandKey("Log    In");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Log In asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Log In");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Log Inasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdLog In");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Log asd In");
            Assert.assertEquals( "invalid",outputs);
        }

        //Log Out
        {
            outputs = OffsPage.getCommandKey("Log Out");
            Assert.assertEquals( "Log Out",outputs);

            outputs = OffsPage.getCommandKey("loG ouT");
            Assert.assertEquals( "Log Out",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Log Out    ");
//            Assert.assertEquals("Log Out", outputs);

            outputs = OffsPage.getCommandKey("Log    Out");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Log Out asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Log Out");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Log Outasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdLog Out");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Log asd Out");
            Assert.assertEquals( "invalid",outputs);
        }

        //Help
        {
            outputs = OffsPage.getCommandKey("Help");
            Assert.assertEquals( "help",outputs);

            outputs = OffsPage.getCommandKey("hElP");
            Assert.assertEquals( "help",outputs);

            //to Menu Trim mishe
//            outputs = OffsPage.getCommandKey("   Help    ");
//            Assert.assertEquals(Help, "outputs");

            outputs = OffsPage.getCommandKey("He  lp");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Help asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Help");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Helpasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdHelp");
            Assert.assertEquals( "invalid",outputs);
        }

        //Back
        {
            outputs = OffsPage.getCommandKey("Back");
            Assert.assertEquals( "back",outputs);

            outputs = OffsPage.getCommandKey("bACk");
            Assert.assertEquals( "back",outputs);

            //to Menu Trim mishe
//            outputs = productsPage.getCommandKey("   Back    ");
//            Assert.assertEquals("back", outputs);

            outputs = OffsPage.getCommandKey("Ba   ck");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Back asd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asd  Back");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("Backasd");
            Assert.assertEquals( "invalid",outputs);

            outputs = OffsPage.getCommandKey("asdBack");
            Assert.assertEquals( "invalid",outputs);
        }
    }

    //ino che konam?
    @Test
    public void Run(){

    }
}
