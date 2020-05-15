package Models.User;

import Models.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart  implements Serializable {
    private ArrayList<Product> products;
    private ArrayList<Seller> sellers;
    private ArrayList<Integer> items;

    public Cart() {
        this.products = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public Seller getSellerByProductId(long productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return sellers.get(products.indexOf(product));
            }
        }
        return null;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getItemsByProductId(long productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return items.get(products.indexOf(product));
            }
        }
        return -1;
    }
}
