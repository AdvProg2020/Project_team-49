package Models.User.Request;

import Controller.DataBase;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Request  implements Serializable {
    protected String managerAnswer;
    protected long requestId;

    public Request() {
        this.managerAnswer = null;
        this.requestId = DataBase.getRequestId();
    }

    public void setManagerAnswer(String managerAnswer) {
        this.managerAnswer = managerAnswer;
    }

    public String getManagerAnswer() {
        return managerAnswer;
    }

    public long getRequestId() {
        return requestId;
    }

    public abstract String getType();

    public abstract void run();
}
