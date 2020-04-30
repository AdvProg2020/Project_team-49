package View.Menu.UserArea;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GuestAreaTest {
    private GuestArea guestArea = new GuestArea(null);

    @Test
    public void testGetCommandKey() {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(guestArea.getCommandKey("LoGIn ali"));
        Assert.assertEquals("Login Menu", outputs.get(0));
        outputs.add(guestArea.getCommandKey("LoGIn ali/d"));
        Assert.assertEquals("invalid", outputs.get(1));
        outputs.add(guestArea.getCommandKey("cReate aCcount manager ali"));
        Assert.assertEquals("Register Menu", outputs.get(2));
        outputs.add(guestArea.getCommandKey("cReate aCcount masnager ali"));
        Assert.assertEquals("invalid", outputs.get(3));
        outputs.add(guestArea.getCommandKey("cReate aCcount manager al/i"));
        Assert.assertEquals("invalid", outputs.get(4));
        outputs.add(guestArea.getCommandKey("kldfsdhf"));
        Assert.assertEquals("invalid", outputs.get(5));
        outputs.add(guestArea.getCommandKey("HeLp"));
        Assert.assertEquals("help", outputs.get(6));
        outputs.add(guestArea.getCommandKey("Back"));
        Assert.assertEquals("back", outputs.get(7));
    }

    @Test
    public void testLoginMenu() {

    }
}
