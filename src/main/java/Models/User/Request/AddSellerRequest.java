package Models.User.Request;

import Models.User.Seller;

public class AddSellerRequest extends Request {
    private Seller seller;

    public AddSellerRequest(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String getType() {
        return "Add Seller";
    }

    @Override
    public String toString() {
        return "AddSellerRequest{" +
                "seller=" + seller +
                '}';
    }

    @Override
    public void run() {

    }
}
