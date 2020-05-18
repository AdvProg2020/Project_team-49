package Models.User.Request;

import Controller.DataBase;
import Models.Off;
import Models.OffStatus;
import Models.Product;
import Models.User.Seller;

import java.io.Serializable;

public class AddOffRequest extends Request  implements Serializable {
    private Off off;
    private Seller seller;

    public AddOffRequest(Off off, Seller seller) {
        this.off = off;
        this.seller = seller;
    }

    @Override
    public String getType() {
        return "Add Off";
    }

    @Override
    public String toString() {
        return "AddOffRequest{" +
                "off=" + off +
                ", seller=" + seller +
                ", requestId=" + requestId +
                '}';
    }

    @Override
    public void run() {
        DataBase.createdOffsCount++;
        off.setOffId(DataBase.createdOffsCount);
        off.setOffStatus(OffStatus.confirmed);
        for (Product product : off.getProducts()) {
            product.setDoesItHaveOff(true);
            product.setPrice(product.getPrice(seller) * (1 - off.getOffAmount() / 100), seller);
        }
        seller.addOff(off);
    }
}
