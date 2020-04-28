package View.Menu;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MainMenuTest {
    private MainMenu mainMenu = new MainMenu();

    @Test
    public void testGetCommandKey() {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(mainMenu.getCommandKey("User Area"));
        Assert.assertEquals("User Area", outputs.get(0));
        outputs.add(mainMenu.getCommandKey("Products Page"));
        Assert.assertEquals("Products Page", outputs.get(1));
        outputs.add(mainMenu.getCommandKey("Offs"));
        Assert.assertEquals("Offs", outputs.get(2));
        outputs.add(mainMenu.getCommandKey("help"));
        Assert.assertEquals("help", outputs.get(3));
        outputs.add(mainMenu.getCommandKey("exit"));
        Assert.assertEquals("back", outputs.get(4));
        outputs.add(mainMenu.getCommandKey("sdioisf"));
        Assert.assertEquals("invalid", outputs.get(5));
    }
}
