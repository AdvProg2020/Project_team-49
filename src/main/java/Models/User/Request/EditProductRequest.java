package Models.User.Request;

import Models.Product;
import Models.User.Seller;

import java.io.Serializable;
import java.util.ArrayList;

public class EditProductRequest extends Request  implements Serializable {
    private String field;
    private String newContent;
    private Product product;
    private Seller seller;

    public EditProductRequest(String field, String newContent, Product product, Seller seller) {
        this.field = field;
        this.newContent = newContent;
        this.product = product;
        this.seller = seller;
    }

    @Override
    public String getType() {
        return "Edit Product";
    }

    @Override
    public String toString() {
        return "EditProduct{" +
                "field='" + field + '\'' +
                ", newContent='" + newContent + '\'' +
                ", product=" + product +
                '}';
    }

    @Override
    public void run() {
        if (field.equalsIgnoreCase("name")) {
            product.setName(newContent);
        } else if (field.equalsIgnoreCase("price")) {
            product.setPrice(Double.parseDouble(newContent), seller);
        } else if (field.equalsIgnoreCase("brand")) {
            product.setBrand(newContent);
        } else if (field.equalsIgnoreCase("explanation")) {
            product.setExplanation(newContent);
        }
    }
}
