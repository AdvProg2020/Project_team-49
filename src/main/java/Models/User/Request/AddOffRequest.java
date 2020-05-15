package Models.User.Request;

import Models.Off;
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
        seller.addOff(off);
    }
}
