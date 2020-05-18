package Models;

import Models.User.Costumer;
import Models.User.Manager;
import Models.User.Seller;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScoreTest {
    static Seller seller1 = new Seller("amiri77", "amir", "amiri", "amiri@gmail.com", 3, "amiri", "amiriI");
    static Category mother = new Category("xx", null, null);
    static Category phone = new Category("mobile", "", mother);
    static Product a = new Product("phone", "apple", 1000.0, "nothing", phone, seller1, 1000);
    static Manager manager1 = new Manager("izadiii", "mohammad", "izadi", "izadi@gmail.com", 12, "izadizad");
    static Manager manager2 = new Manager("AbaM", "jj", "abam", "abam@gmail.com", 13, "abamiad");
    Score score1 = new Score(manager1, 3, a);

    @Test
    public void TestScoreItSelf() {
        assertTrue(score1 instanceof Score);
    }

    @Test
    public void TestGetBuyer() {
        assertEquals(manager1, score1.getBuyer());
    }

    @Test
    public void TestSetBuyer() {
        score1.setBuyer(manager2);
        assertEquals(manager2, score1.getBuyer());
    }

    @Test
    public void TestGetScore() {
        assertEquals(3, score1.getScore());
    }
}
