package Models.Log;

import java.io.Serializable;
import java.util.Date;

public abstract class Log  implements Serializable {
    protected long logId;
    protected Date logDate;

    public long getLogId() {
        return logId;
    }

    public Date getLogDate() {
        return logDate;
    }
}
