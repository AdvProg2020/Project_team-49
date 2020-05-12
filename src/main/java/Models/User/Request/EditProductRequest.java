package Models.User.Request;

import Models.Product;

import java.util.ArrayList;

public class EditProductRequest extends Request {
    private String field;
    private String newContent;
    private Product product;

    public EditProductRequest(String field, String newContent, Product product) {
        this.field = field;
        this.newContent = newContent;
        this.product = product;
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

    }
}
