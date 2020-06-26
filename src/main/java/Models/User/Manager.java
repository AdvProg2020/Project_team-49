package Models.User;

import Controller.DataBase;
import Models.DiscountCode;
import Models.User.Request.Request;

import java.io.Serializable;
import java.util.ArrayList;

import static Controller.DataBase.*;

public class Manager extends User implements Serializable {

    public Manager(String username, String firstName, String lastName, String eMail, long phoneNumber, String password) {
        super(username, firstName, lastName, eMail, phoneNumber, password);
    }

    public static void answerRequest(String answer, long requestId) {
        getRequestById(requestId).setManagerAnswer(answer);
        if (answer.equals("accept")) {
            getRequestById(requestId).run();
        }
        answeredRequests.add(getRequestById(requestId));
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
        for (DiscountCode discountCode : allDiscountCodes) {
            if (discountCode.getDiscountId().equals(Id)) {
                return discountCode;
            }
        }
        return null;
    }

    public static ArrayList<Request> getAllActiveRequests() {
        return allActiveRequests;
    }

    public static void addDiscountCode(DiscountCode discountCode) {
        allDiscountCodes.add(discountCode);
    }

    public static void removeDiscountCode(String code) {
        for (DiscountCode discountCode : allDiscountCodes) {
            if (discountCode.getDiscountId().equals(code)) {
                for (User allUser : getAllUsers()) {
                    if (allUser.getType().equals("Costumer")) {
                        if (((Costumer) allUser).getDiscountCodeById(discountCode.getDiscountId()) != null) {
                            ((Costumer) allUser).removeDiscountCode(((Costumer) allUser).getDiscountCodeById(discountCode.getDiscountId()));
                        }
                    }
                }
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