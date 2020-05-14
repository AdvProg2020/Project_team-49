package Models.User;

import Models.DiscountCode;
import Models.User.Request.Request;

import java.io.Serializable;
import java.util.ArrayList;

public class Manager extends User implements Serializable {
    private static ArrayList<Request> allActiveRequests = new ArrayList<>();
    private static ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>();

    public Manager(String username, String firstName, String lastName, String eMail, long phoneNumber, String password) {
        super(username, firstName, lastName, eMail, phoneNumber, password);
    }

    public static void answerRequest(String answer, long requestId) {
        if (answer.equals("accept")) {
            getRequestById(requestId).run();
        }
        allActiveRequests.remove(getRequestById(requestId));
    }

    public static void addRequest(Request request) {
        allActiveRequests.add(request);
    }

    public static Request getRequestById(long Id) {
        for (Request activeRequest : allActiveRequests) {
            if (activeRequest.getRequestId() == Id) {
                return activeRequest;
            }
        }
        return null;
    }

    public static DiscountCode getDiscountCodeById(String Id) {
        return null;
    }

    public static ArrayList<Request> getAllActiveRequests() {
        return allActiveRequests;
    }

    public static void addDiscountCode(DiscountCode discountCode) {
        allDiscountCodes.add(discountCode);
    }

    public static void  editDiscountCode() {

    }

    public static void removeDiscountCode(String code) {
        for (DiscountCode discountCode : allDiscountCodes) {
            if (discountCode.getDiscountId().equals(code)) {
                allDiscountCodes.remove(discountCode);
                return;
            }
        }
    }

    public static ArrayList<DiscountCode> getAllDiscountCodes() {
        return allDiscountCodes;
    }

    @Override
    public String getType() {
        return "Manager";
    }
}