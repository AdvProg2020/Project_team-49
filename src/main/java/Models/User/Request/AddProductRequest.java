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
        String info = requestId + "\n";
        info += getType() + "\n";
        info += product.getProductId() + "\n";
        info += product.getName() + "\n";
        info += product.getBrand() + "\n";
        info += product.getExplanation() + "\n";
        info += product.getPrice(product.getDefaultSeller()) + "\n";
        info += product.getDefaultSeller().getUsername() + "\n";
        return info;
    }

    @Override
    public void run() {
        this.product.setStatus("accepted");
        DataBase.addNewProduct(product);
    }
}
