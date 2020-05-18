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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public int getItemsByProductId(long productId, Seller seller) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == productId) {
                if (sellers.get(i).equals(seller)) {
                    return items.get(i);
                }
            }
        }
        return -1;
    }

    private void addItem(int item, Product product, Seller seller) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(product)) {
                if (sellers.get(i).equals(seller)) {
                    items.set(i, items.get(i) + item);
                    return;
                }
            }
        }
    }

    private void removeItem(Product product, Seller seller) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(product)) {
                if (sellers.get(i).equals(seller)) {
                    products.remove(i);
                    sellers.remove(i);
                    items.remove(i);
                    return;
                }
            }
        }
    }

    public void addProduct(Product product, Seller seller, int items) {
        for (int i = 0; i < this.products.size(); i++) {
            if (products.get(i).equals(product)) {
                if (sellers.get(i).equals(seller)) {
                    addItem(items, product, seller);
                    if (getItemsByProductId(product.getProductId(), seller) == 0) {
                        removeItem(product, seller);
                    }
                    return;
                }
            }
        }
        products.add(product);
        sellers.add(seller);
        this.items.add(items);
    }
}
