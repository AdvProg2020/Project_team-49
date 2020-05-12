package Models.User;

import Models.Product;

import java.io.Serializable;
import java.util.HashMap;

public class Guest extends User implements Serializable {
    private HashMap<Product, Integer> cart;

    public Guest() {
        super(null, null, null, null, 0, null);
        this.cart = new HashMap<Product,Integer>(   );
    }

    public void addProductToCart(Product product,Seller seller){}

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
