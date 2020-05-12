package Models.User.Request;

import Models.Off;

public class AddOffRequest extends Request {
    private Off off;

    public AddOffRequest(Off off) {
        this.off = off;
    }

    @Override
    public String getType() {
        return "Add Off";
    }

    @Override
    public String toString() {
        return "AddOff{" +
                "off=" + off +
                '}';
    }

    @Override
    public void run() {

    }
}
