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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ManagerAreaController {

    public static String viewUser(String username) {
        User user = DataBase.getUserByUsername(username);
        String info = username + ",";
        info += user.getFirstName() + ",";
        info += user.getLastName() + ",";
        info += user.getEMail() + ",";
        info += user.getPhoneNumber();
        if (user.getType().equals("Seller")) {
            info += "," + ((Seller) user).getCompanyName();
        }
        info += "," + user.getType();
        return info;
    }

    public static String deleteUser(String username) {
        DataBase.removeUser(username);
        return "user deleted";
    }

    public static String deleteProduct(long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            DataBase.removeProduct(productId);
            return "product removed";
        }
    }

    public static String createDiscountCode(ArrayList<String> info) {
        ArrayList<String> allowedCostumersNames = new ArrayList<String>(Arrays.asList(info.get(6).split("\\s")));
        ArrayList<Costumer> allowedCostumers = new ArrayList<Costumer>();
        for (String allowedCostumersName : allowedCostumersNames) {
            if (DataBase.getUserByUsername(allowedCostumersName) == null) {
                return "invalid costumer name";
            }
            try {
                allowedCostumers.add((Costumer) DataBase.getUserByUsername(allowedCostumersName));
            } catch (Exception ClassCastException) {
                return allowedCostumersName + " is not a costumer";
            }
        }
        Date dateS;
        Date dateE;
        try {
            dateS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(info.get(1));
        } catch (Exception ParseException) {
            return "invalid start date";
        }
        try {
            dateE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(info.get(2));
        } catch (Exception ParseException) {
            return "invalid end date";
        }
        DataBase.addDiscountCode(new DiscountCode(info.get(0),
                dateS,
                dateE,
                Integer.parseInt(info.get(3)),
                Long.parseLong(info.get(4)),
                Integer.parseInt(info.get(5)),
                allowedCostumers));
        return "discount code created";
    }

    public static String  removeDiscountCode(String code) {

        if (Manager.getDiscountCodeById(code) == null) {
            return "discount code not exist";
        } else {
            Manager.removeDiscountCode(code);
            return  "discount code removed";
        }
    }

    public static String requestDetails(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            return "request not exist";
        } else {
            return Manager.getRequestById(requestId).toString();
        }
    }

    public static String acceptRequest(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            return "request not exist";
        } else {
            Manager.answerRequest("accept", requestId);
            return "request " + requestId + " accepted";
        }
    }

    public static String declineRequest(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            return "request not exist";
        } else {
            Manager.answerRequest("decline", requestId);
            return "request " + requestId + " declined";
        }
    }

    public static String editCategory(String name, String field, String newContent) {
        if (DataBase.getCategoryByName(name) == null) {
            return "category not exist";
        }
        if (field.toLowerCase().equals("attribute")) {
            DataBase.getCategoryByName(name).setSpecialAttributes(newContent);
        } else if (field.toLowerCase().equals("name")) {
            if (!newContent.matches("^\\w+$")) {
                return "invalid new name";
            } else if (DataBase.getCategoryByName(newContent) != null) {
                return "category exist with this new name";
            } else {
                DataBase.getCategoryByName(name).setName(newContent);
            }
        }
        return "field edited";
    }

    public static String addCategory(ArrayList<String> info) {
        if (DataBase.getCategoryByName(info.get(0)) != null) {
            return "category exist with this name";
        } else if ((!info.get(2).matches("(?i)null")) && (DataBase.getCategoryByName(info.get(2)) == null)) {
            return "invalid parent category";
        } else if (info.get(2).matches("(?i)null")) {
            DataBase.addCategory(new Category(info.get(0), info.get(1), null));
        } else {
            DataBase.addCategory(new Category(info.get(0), info.get(1), DataBase.getCategoryByName(info.get(2))));
        }
        return "category added";
    }

    public static String removeCategory(String name) {
        if (DataBase.getCategoryByName(name) == null) {
            return "category not exist";
        } else {
            DataBase.removeCategory(name);
            return name + " category removed";
        }
    }

    public static ArrayList<String> viewDiscountCode(String code) {
        ArrayList<String> info = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (Manager.getDiscountCodeById(code) == null) {
            info.add("discount code not exist");
        } else {
            DiscountCode discountCode = Manager.getDiscountCodeById(code);
            info.add(code);
            info.add(formatter.format(discountCode.getStartDate()));
            info.add(formatter.format(discountCode.getEndDate()));
            info.add(String.valueOf(discountCode.getDiscountPercent()));
            info.add(String.valueOf(discountCode.getMaximumDiscountAmount()));
            info.add(String.valueOf(discountCode.getDiscountCount()));
            info.add(discountCode.getAllowedCostumers().toString());//???
        }
        return info;
    }

    public static ArrayList<String> showAllUsers() {
        ArrayList<String> users = new ArrayList<>();
        for (User user : DataBase.allUsers) {
            String info = user.getUsername() + "," + user.getType();
            info += "," + user.getFirstName() + "," + user.getLastName();
            info += "," + user.getEMail() + "," + user.getPhoneNumber();
            users.add(info);
        }
        return users;
    }

    public static ArrayList<String> showAllProducts() {
        ArrayList<String> products = new ArrayList<>();
        for (Product product : DataBase.allProducts) {
            String info = product.getName() + "," + product.getProductId();
            info += "," + product.getBrand() + "," + product.getPrice(product.getDefaultSeller());
            info += "," + product.getAverageScore() + "," + product.getExplanation();
            products.add(info);
        }
        return products;
    }

    //allowed costumers
    public static ArrayList<String> showDiscountCodes() {
        ArrayList<String> discountCodes = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (DiscountCode discountCode : Manager.getAllDiscountCodes()) {
            String info = discountCode.getDiscountId() + "," + formatter.format(discountCode.getStartDate());
            info += "," + formatter.format(discountCode.getEndDate()) + "," + discountCode.getDiscountPercent();
            info += "," + discountCode.getMaximumDiscountAmount() + "," + discountCode.getDiscountCount();
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
            if (!newContent.matches("^\\d+$")) {
                return "invalid percent";
            }
            Manager.getDiscountCodeById(code).setDiscountPercent(Integer.parseInt(newContent));
        }
        if (field.toLowerCase().equals("maximum amount")) {
            if (!newContent.matches("^\\d+$")) {
                return "invalid amount";
            }
            Manager.getDiscountCodeById(code).setMaximumDiscountAmount(Long.parseLong(newContent));
        }
        return "field edited";
    }

    public static ArrayList<String> showRequests() {
        ArrayList<String> requests = new ArrayList<>();
        for (Request request : Manager.getAllActiveRequests()) {
            String info = request.getRequestId() + "," + request.getType();
            requests.add(info);
        }
        return requests;
    }

    //kamel nist
    public static String changeUserType(String username, String newType) {
        return "";
    }
    
    public static ArrayList<String> showCategories() {
        ArrayList<String> categories = new ArrayList<>();
        for (Category category : DataBase.allCategories) {
            String info = category.getName() + "," + category.getSpecialAttributes();
            if (category.getParentCategory() == null) {
                info += ",null";
            } else {
                info += "," + category.getParentCategory().getName();
            }
            categories.add(info);
        }
        return categories;
    }

    public static String getAllCostumersForDiscount() {
        String names = "";
        for (User allUser : DataBase.getAllUsers()) {
            if (allUser.getType().equals("Costumer")) {
                names += allUser.getUsername() + " ";
            }
        }
        return names.trim();
    }
}
