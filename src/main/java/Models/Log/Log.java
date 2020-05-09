package Models.Log;

import java.util.Date;

public abstract class Log {
    protected long logId;
    protected Date logDate;

    public long getLogId() {
        return logId;
    }

    public Date getLogDate() {
        return logDate;
    }
}
