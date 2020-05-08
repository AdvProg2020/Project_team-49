package Models.User.Request;

import Models.Product;

import java.util.ArrayList;

public class EditProductRequest extends Request {
    private ArrayList<String> fields;
    private ArrayList<String> newContents;
    private Product product;

    public EditProductRequest(ArrayList<String> fields, ArrayList<String> newContents, Product product) {
        this.fields = fields;
        this.newContents = newContents;
        this.product = product;
    }

    @Override
    public String getType() {
        return "Edit Product";
    }

    @Override
    public String toString() {
        return "EditProduct{" +
                "field='" + fields + '\'' +
                ", newContent='" + newContents + '\'' +
                ", product=" + product +
                '}';
    }

    @Override
    public void run() {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).equals("")) {

            }
        }
    }
}
