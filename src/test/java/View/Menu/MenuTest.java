package View.Menu;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuTest {


    @Test
    public void testSetters() {
        Menu.setScanner(new Scanner(System.in));
        Matcher matcher = Pattern.compile("hello").matcher("hello");
        Assert.assertEquals(matcher.matches(), Menu.getMatcher("hello", "hello").matches());
    }
}
