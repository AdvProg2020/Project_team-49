package Models.User.Request;

import Models.Off;

public class AddOff extends Request {
    private Off off;

    public AddOff(Off off) {
        this.off = off;
    }

    @Override
    public String getType() {
        return null;
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
