package Models.User.Request;

import Controller.DataBase;
import Models.User.Seller;

import java.io.Serializable;

public class AddSellerRequest extends Request  implements Serializable {
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
        String info = requestId + "!@";
        info += getType() + "!@";
        info += seller.getUsername() + "!@";
        info += seller.getCompanyName();
        return info;
    }

    @Override
    public void run() {
        DataBase.addNewUser(seller);
    }
}
