package Models.Log;

import java.io.Serializable;

public enum ReceiveStatus  implements Serializable {
    RECEIVED,
    RECEIVED_IN_PROGRESS,
    IN_PROGRESS_FOR_RECEIVE
}
