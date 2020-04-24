package Models.User;

import Models.Product;

import java.util.HashMap;

public class Guest extends User {
    private HashMap<Product, Integer> cart;

    public Guest() {
        super(null, null, null, null, 0, null);
        this.cart = new HashMap<>();
    }

    public void addProductToCart(Product product){}

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void removeProductFromCart(Product product) {

    }

    @Override
    public String getType() {
        return "Guest";
    }
}
