package View.Menu.OffsAndProductsMenu;

import View.Menu.Menu;
import View.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ShowProductTest {
    private ShowProduct showProduct=new ShowProduct(null);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void run(){

        String input = "Digest";
        InputStream command = new ByteArrayInputStream(input.getBytes());
        System.setIn(command);
        showProduct.run("Show Product 123");

        Assert.assertEquals("asd",outContent.toString());
    }
}
