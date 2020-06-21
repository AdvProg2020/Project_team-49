package Models.User;

import Models.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<Product> products;
    private ArrayList<Seller> sellers;
    private ArrayList<Integer> items;

    public Cart() {
        this.products = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public double calculatePrice() {
        double sum = 0;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getDoesItHaveOff()) {
                sum += 1.0 * products.get(i).getPrice(sellers.get(i)) * items.get(i) / (1.0 - product.getOffPercentage());

            } else {
                sum += 1.0 * products.get(i).getPrice(sellers.get(i)) * items.get(i);
            }
        }

        return sum;
    }

    public double calculateOffPrice() {
        return calculatePrice() - calculateFinalPrice();
    }

    public double calculateFinalPrice() {
        double sum = 0;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            sum += 1.0 * products.get(i).getPrice(sellers.get(i)) * items.get(i);
        }

        return sum;
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
                if (sellers.get(i).getUsername().equals(seller.getUsername())) {
                    return items.get(i);
                }
            }
        }
        return -1;
    }

    public void addItem(int item, Product product, Seller seller) {

        if (items.get(products.indexOf(product)) + item > product.getRemainingItemsForSeller(seller)) return;

        if (items.get(products.indexOf(product)) + item < 0) return;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                if (sellers.get(i).getUsername().equals(seller.getUsername())) {
                    items.set(i, items.get(i) + item);
                    return;
                }
            }
        }
    }


    public void removeItem(Product product, Seller seller) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                if (sellers.get(i).getUsername().equals(seller.getUsername())) {
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
            if (products.get(i).getProductId() == product.getProductId()) {
                if (sellers.get(i).getUsername().equals(seller.getUsername())) {
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
