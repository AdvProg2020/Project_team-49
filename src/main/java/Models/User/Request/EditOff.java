package Models.User.Request;

import Models.Off;

public class EditOff extends Request {
    private String field;
    private String newContent;
    private Off off;

    public EditOff(String field, String newContent, Off off) {
        this.field = field;
        this.newContent = newContent;
        this.off = off;
    }

    @Override
    public String getType() {
        return null;
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

    }
}
