package Controller;

import Models.Category;
import Models.DiscountCode;
import Models.Product;
import Models.User.Costumer;
import Models.User.Manager;
import Models.User.Request.Request;
import Models.User.Seller;
import Models.User.User;
import View.View;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagerAreaController {

    public static String viewUser(String username) {
        User user = DataBase.getUserByUsername(username);
        String info = username + " ";
        info += user.getFirstName() + " ";
        info += user.getLastName() + " ";
        info += user.getEMail() + " ";
        info += user.getPhoneNumber() + " ";
        if (user.getType().equals("Seller")) {
            info += ((Seller) user).getCompanyName() + " ";
        }
        info += user.getType() + " ";
        return info;
    }

    public static void deleteUser(String username) {
        DataBase.removeUser(username);
    }

    public static String deleteProduct(long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            DataBase.removeProduct(productId);
            return "product removed";
        }
    }

    //date structure
    public static String createDiscountCode(ArrayList<String> info) throws ParseException {
        ArrayList<String> allowedCostumersNames = new ArrayList<String>(Arrays.asList(info.get(6).split("\\s")));
        ArrayList<Costumer> allowedCostumers = new ArrayList<Costumer>();
        for (String allowedCostumersName : allowedCostumersNames) {
            if (DataBase.getUserByUsername(allowedCostumersName) == null) {
                return "invalid costumer name";
            }
            allowedCostumers.add((Costumer) DataBase.getUserByUsername(allowedCostumersName));
        }
        DiscountCode.addDiscountCode(new DiscountCode(info.get(0),
                new SimpleDateFormat("date structure").parse(info.get(1)),
                new SimpleDateFormat("date structure").parse(info.get(2)),
                Integer.parseInt(info.get(3)),
                Long.parseLong(info.get(4)),
                Integer.parseInt(info.get(5)),
                allowedCostumers));
        return "discount code created";
    }

    public static void removeDiscountCode(String code) {
        if (Manager.getDiscountCodeById(code) == null) {
            View.printString("discount code not exist");
        } else {
            Manager.removeDiscountCode(code);
            View.printString("discount code removed");
        }
    }

    public static void requestDetails(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            View.printString("request not exist");
        } else {
            View.printString(Manager.getRequestById(requestId).getType());
            View.printString(Manager.getRequestById(requestId).toString());
        }
    }

    public static void acceptRequest(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            View.printString("request not exist");
        } else {
            Manager.answerRequest("accept", requestId);
            View.printString("request" + requestId + "accepted");
        }
    }

    public static void declineRequest(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            View.printString("request not exist");
        } else {
            Manager.answerRequest("decline", requestId);
            View.printString("request" + requestId + "declined");
        }
    }

    public static String editCategory(String name, String field, String newContent) {
        if (DataBase.getCategoryByName(name) == null) {
            return "category not exist";
        }
        if (field.toLowerCase().equals("attribute")) {
            DataBase.getCategoryByName(name).setSpecialAttributes(newContent);
        } else if (field.toLowerCase().equals("name")) {
            if (!newContent.matches("\\w+")) {
                return "invalid new name";
            } else if (DataBase.getCategoryByName(newContent) != null) {
                return "category exist with this new name";
            } else {
                DataBase.getCategoryByName(name).setName(newContent);
            }
        }
        return "field edited";
    }

    public static void addCategory(ArrayList<String> info) {
        if (DataBase.getCategoryByName(info.get(0)) != null) {
            View.printString("category exist with this name");
        } else if ((!info.get(2).matches("(?i)null")) || (DataBase.getCategoryByName(info.get(0)) == null)) {
            View.printString("invalid parent category");
        } else if (info.get(2).matches("(?i)null")) {
            DataBase.addCategory(new Category(info.get(0), info.get(1), null));
        } else {
            DataBase.addCategory(new Category(info.get(0), info.get(1), DataBase.getCategoryByName(info.get(2))));
        }
        View.printString("category added");
    }

    public static void removeCategory(String name) {
        if (DataBase.getCategoryByName(name) == null) {
            View.printString("category not exist");
        } else {
            DataBase.removeCategory(name);
            View.printString(name + "category removed");
        }
    }

    public static void viewDiscountCode(String code) {
        if (Manager.getDiscountCodeById(code) == null) {
            View.printString("discount code not exist");
        } else {
            ArrayList<String> info = new ArrayList<>();
            DiscountCode discountCode = Manager.getDiscountCodeById(code);
            info.add(code);
            info.add(discountCode.getStartDate().toString());
            info.add(discountCode.getEndDate().toString());
            info.add(String.valueOf(discountCode.getDiscountPercent()));
            info.add(String.valueOf(discountCode.getMaximumDiscountAmount()));
            info.add(String.valueOf(discountCode.getDiscountCount()));
            info.add(discountCode.getAllowedCostumers().toString());
            View.printArrayList(info);
        }
    }

    public static ArrayList<String> showAllUsers() {
        ArrayList<String> users = new ArrayList<>();
        for (User user : DataBase.allUsers) {
            String info = user.getUsername() + " " + user.getType();
            info += " " + user.getFirstName() + " " + user.getLastName();
            info += " " + user.getEMail() + " " + user.getPhoneNumber();
            users.add(info);
        }
        return users;
    }

    public static ArrayList<String> showAllProducts() {
        ArrayList<String> products = new ArrayList<>();
        for (Product product : DataBase.allProducts) {
            String info = product.getName() + " " + product.getProductId();
            info += " " + product.getBrand() + " " + product.getPrice();
            info += " " + product.getAverageScore() + " " + product.getExplanation();
            products.add(info);
        }
        return products;
    }

    //allowed costumers
    public static ArrayList<String> showDiscountCodes() {
        ArrayList<String> discountCodes = new ArrayList<>();
        for (DiscountCode discountCode : Manager.getAllDiscountCodes()) {
            String info = discountCode.getDiscountId() + " " + discountCode.getStartDate();
            info += " " + discountCode.getEndDate() + " " + discountCode.getDiscountPercent();
            info += " " + discountCode.getMaximumDiscountAmount() + " " + discountCode.getDiscountCount();
            discountCodes.add(info);
        }
        return discountCodes;
    }

    //kamel nist (allowed costumers)
    public static String editDiscountCode(String code, String field, String newContent) {
        if (Manager.getDiscountCodeById(code) == null) {
            return "discount code not exist";
        }
        if (field.toLowerCase().equals("percent")) {
            if (!newContent.matches("\\d+")) {
                return "invalid percent";
            }
            Manager.getDiscountCodeById(code).setDiscountPercent(Integer.parseInt(newContent));
        }
        if (field.toLowerCase().equals("maximum amount")) {
            if (!newContent.matches("\\d+")) {
                return "invalid amount";
            }
            Manager.getDiscountCodeById(code).setMaximumDiscountAmount(Long.parseLong(newContent));
        }
        return "field edited";
    }

    public static ArrayList<String> showRequests() {
        ArrayList<String> requests = new ArrayList<>();
        for (Request request : Manager.getAllActiveRequests()) {
            String info = request.getRequestId() + " " + request.getType();
            requests.add(info);
        }
        return requests;
    }

    //kamel nist
    public static void changeUserType(String username, String newType) {

    }
    
    public static ArrayList<String> showCategories() {
        ArrayList<String> categories = new ArrayList<>();
        for (Category category : DataBase.allCategories) {
            String info = category.getName() + " " + category.getSpecialAttributes();
            if (category.getParentCategory() == null) {
                info += " null";
            } else {
                info += " " + category.getParentCategory().getName();
            }
            categories.add(info);
        }
        return categories;
    }
}
