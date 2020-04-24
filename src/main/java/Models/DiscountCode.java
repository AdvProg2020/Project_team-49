package Models;

import Models.User.Costumer;
import java.util.ArrayList;
import java.util.Date;

public class DiscountCode {
    private static ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>();
    private String DiscountId;
    private Date startDate;
    private Date endDate;
    private int discountPercent;
    private long maximumDiscountAmount;
    private int discountCount;
    private ArrayList<Costumer> allowedCostumers = new ArrayList<Costumer>();

    public DiscountCode(String discountId, Date startDate, Date endDate, int discountPercent, long maximumDiscountAmount, int discountCount, ArrayList<Costumer> allowedCostumers) {
        DiscountId = discountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.maximumDiscountAmount = maximumDiscountAmount;
        this.discountCount = discountCount;
        this.allowedCostumers = allowedCostumers;
    }
//    all setter and getter

    public String getDiscountId(){
    return DiscountId;
    }

    public Date getStartDate () {
    return startDate;
}

    public Date getEndDate () {
    return endDate;
}

    public int getDiscountPercent () {
    return discountPercent;
}

    public long getMaximumDiscountAmount () {
    return maximumDiscountAmount;
}

    public int getDiscountCount () {
    return discountCount;
}

    public ArrayList<Costumer> getAllowedCostumers() {
    return allowedCostumers;
}

    public void setDiscountId (String discountId){
    DiscountId = discountId;
}

    public void setStartDate (Date startDate){
    this.startDate = startDate;
}

    public void setEndDate (Date endDate){
    this.endDate = endDate;
}

    public void setDiscountPercent ( int discountPercent){
    this.discountPercent = discountPercent;
}

    public void setMaximumDiscountAmount ( long maximumDiscountAmount){
    this.maximumDiscountAmount = maximumDiscountAmount;
}

    public void setDiscountCount ( int discountCount){
    this.discountCount = discountCount;
}

    public void setAllowedCostumers(ArrayList < Costumer > allowedCostumers) {
    this.allowedCostumers = allowedCostumers;
}

//    add new allowed costumer
    public void addAllowedCustomer(Costumer costumer){
        this.allowedCostumers.add(costumer);
        this.allowedCostumers = allowedCostumers;
    }

    public static void addDiscountCode(DiscountCode discountCode) {

    }
}
