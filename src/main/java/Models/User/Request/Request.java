package Models.User.Request;

import java.util.ArrayList;

public abstract class Request {
    private static ArrayList<Request> allRequests = new ArrayList<>();
    protected String managerAnswer;
    protected long requestId;

    public Request() {
        this.managerAnswer = null;
        this.requestId = allRequests.size() + 1;
        allRequests.add(this);
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
