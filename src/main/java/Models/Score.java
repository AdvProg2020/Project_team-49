package Models;

import Models.User.User;

public class Score {
    private User buyer;
    private int score;
    private Product product;

    public Score(User buyer, int score, Product product) {
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

    public int getScore() {
        return score;
    }
}
