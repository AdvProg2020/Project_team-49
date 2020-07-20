package Models.User.Request;

import Models.Off;

import java.io.Serializable;

public class EditOffRequest extends Request  implements Serializable {
    private String field;
    private String newContent;
    private Off off;

    public EditOffRequest(String field, String newContent, Off off) {
        this.field = field;
        this.newContent = newContent;
        this.off = off;
    }

    @Override
    public String getType() {
        return "Edit Off";
    }

    //???
    public String getOldContent() {
        if (field.equalsIgnoreCase("amount")) {
            return String.valueOf(off.getOffAmount());
        }
        return "";
    }

    public Off getOff() {
        return off;
    }

    public String getNewContent() {
        return newContent;
    }

    @Override
    public String toString() {
        String info = requestId + "!@";
        info += getType() + "!@";
        info += field.toLowerCase() + "!@";
        info += getOldContent() + "!@";
        info += newContent;
        return info;
    }

    //????
    @Override
    public void run() {
        if (field.equalsIgnoreCase("amount")) {
            off.setOffAmount(Integer.parseInt(newContent));
        }
    }
}
