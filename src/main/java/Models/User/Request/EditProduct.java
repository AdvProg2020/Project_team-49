package Models.User.Request;

import Models.Product;

public class EditProduct extends Request {
    private String field;
    private String newContent;
    private Product product;

    public EditProduct(String field, String newContent, Product product) {
        this.field = field;
        this.newContent = newContent;
        this.product = product;
    }

    @Override
    public String getType() {
        return null;
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
