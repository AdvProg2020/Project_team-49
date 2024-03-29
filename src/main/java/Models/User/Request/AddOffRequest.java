package Models.User.Request;

import Controller.DataBase;
import Models.Off;
import Models.OffStatus;
import Models.Product;
import Models.User.Seller;

import java.io.Serializable;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String info = requestId + "!@";
        info += getType() + "!@";
        info += seller.getUsername() + "!@";
        info += off.getOffAmount() + "!@";
        info += formatter.format(off.getStartDate()) + "!@";
        info += formatter.format(off.getEndDate()) + "!@";
        for (Product product : off.getProducts()) {
            info += product.getName() + "_" + product.getProductId() + "/";
        }
        return info;
    }

    @Override
    public void run() {
        DataBase.createdOffsCount++;
        off.setOffId(DataBase.createdOffsCount);
        off.setOffStatus(OffStatus.confirmed);
        for (Product product : off.getProducts()) {
            product.setDoesItHaveOff(true);
            product.setOff(off);
            product.setPrice(product.getPrice(seller) * (1 - off.getOffAmount() / 100), seller);
        }
        seller.addOff(off);
    }
}
