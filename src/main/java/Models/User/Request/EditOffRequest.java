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
    private String getOldContent() {
        if (field.equalsIgnoreCase("amount")) {
            return String.valueOf(off.getOffAmount());
        }
        return "";
    }

    @Override
    public String toString() {
        String info = requestId + "\n";
        info += getType() + "\n";
        info += field.toLowerCase() + "\n";
        info += getOldContent() + "\n";
        info += newContent + "\n";
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
