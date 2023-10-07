package com.hit.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String orderId;
    private Timestamp orderCreatedAt; // Change to Timestamp
    private String vendorId;
    private String bidId;
    private String tenderId;
    private int orderAmount;
    private String orderPaymentId;
    private String orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getorderCreatedAt() {
        return orderCreatedAt;
    }

    public void setorderCreatedAt(Timestamp orderCreatedAt) { // Change the parameter type to Timestamp
        this.orderCreatedAt = orderCreatedAt;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderPaymentId() {
        return orderPaymentId;
    }

    public void setOrderPaymentId(String orderPaymentId) {
        this.orderPaymentId = orderPaymentId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentBean(String orderId, String sorderCreatedAt, String vendorId, String bidId, String tenderId,
            String orderAmount, String orderPaymentId, String orderStatus) {
        // TODO
        super();
        this.orderId = orderId;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Timestamp orderCreatedAt = null;

        try {
            Date date = sdf.parse(sorderCreatedAt);
            orderCreatedAt = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.orderCreatedAt = orderCreatedAt;
        this.vendorId = vendorId;
        this.bidId = bidId;
        this.tenderId = tenderId;

        this.orderAmount = Integer.parseInt(orderAmount);
        this.orderPaymentId = orderPaymentId;
        this.orderStatus = orderStatus;
    }

    public PaymentBean() {
        super();
    }
}
