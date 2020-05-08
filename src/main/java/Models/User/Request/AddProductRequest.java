package Models.User.Request;

import Controller.DataBase;
import Models.Product;

public class AddProductRequest extends Request {
    private Product product;

    public AddProductRequest(Product product) {
        this.product = product;
    }

    @Override
    public String getType() {
        return "Add Product";
    }

    @Override
    public String toString() {
        return "AddProductRequest{" +
                "product=" + product +
                '}';
    }

    @Override
    public void run() {
        this.product.setStatus("accepted");
        DataBase.addNewProduct(product);
    }
}
