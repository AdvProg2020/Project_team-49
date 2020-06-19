package Models;

import Models.User.User;

import java.io.Serializable;

public class Score  implements Serializable {
    private User buyer;
    private double score;
    private Product product;

    public Score(User buyer, double score, Product product) {
        this.buyer = buyer;
        this.score = score;
        this.product = product;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public double getScore() {
        return score;
    }
}
