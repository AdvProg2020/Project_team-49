package Models.User.Request;

import Controller.DataBase;
import Models.Product;

import java.io.Serializable;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String info = requestId + "!@";
        info += getType() + "!@";
        info += product.getProductId() + "!@";
        info += product.getName() + "!@";
        info += product.getBrand() + "!@";
        info += product.getExplanation() + "!@";
        info += product.getPrice(product.getDefaultSeller()) + "!@";
        info += product.getDefaultSeller().getUsername();
        return info;
    }

    @Override
    public void run() {
        this.product.setStatus("accepted");
        DataBase.addNewProduct(product);
    }
}
