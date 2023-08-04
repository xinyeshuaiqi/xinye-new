package pers.wmx.eventbus.event;

/**
 * @author wangmingxin03
 * Created on 2021-12-13
 */
public class OrderPlaceEvent {
    private long userId;

    private long orderId;

    public OrderPlaceEvent(long userId, long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
