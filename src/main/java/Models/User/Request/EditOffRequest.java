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

    @Override
    public String toString() {
        return "EditOff{" +
                "field='" + field + '\'' +
                ", newContent='" + newContent + '\'' +
                ", off=" + off +
                '}';
    }

    @Override
    public void run() {
        if (field.equalsIgnoreCase("amount")) {
            off.setOffAmount(Integer.parseInt(newContent));
        }
    }
}
