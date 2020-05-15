package Models;

import Models.User.User;

import java.io.Serializable;

public class Comment  implements Serializable {
    private User userWhoComment;
    private Product product;
    private String note;
    private String title;

    private enum commentStatus {REVIEWFORCONFIRMATION, ACCEPTED, NOTCONFIRMEDFROMMANAGER}

    ;
    private boolean isUserBuyThisProduct;

    public Comment(User user, Product product, String title, String note) {
        this.userWhoComment = user;
        this.product = product;
        this.note = note;
        this.isUserBuyThisProduct = isUserBuyThisProduct();
    }

    public User getUserWhoComment() {
        return userWhoComment;
    }

    public Product getProduct() {
        return product;
    }

    public String getNote() {
        return note;
    }

    public boolean isUserBuyThisProduct() {
        return isUserBuyThisProduct;
    }
}
