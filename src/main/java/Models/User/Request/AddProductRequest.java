package Models.User.Request;

import Controller.DataBase;
import Models.Product;

import java.io.Serializable;

public class AddProductRequest extends Request  implements Serializable {
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

    //kamel nist
    @Override
    public void run() {
        this.product.setStatus("accepted");
        DataBase.addNewProduct(product);
    }
}
