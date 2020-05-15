package Models.Log;

import java.io.Serializable;

public enum DeliveryStatus implements Serializable {
    DELIVERED,
    DELIVERY_IN_PROGRESS,
    PREPARING_FOR_DELIVER
}
