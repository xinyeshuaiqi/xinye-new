package pers.wmx.kafka;

/**
 * @author wangmingxin03
 * Created on 2021-12-15
 */
public class OrderCreateMsg {
    int orderId;

    long userId;

    public OrderCreateMsg() {
    }

    public OrderCreateMsg(int orderId, long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
