package org.example.dto;


import org.example.dto.tm.PaymentCartTm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlacePaymentDto {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private List<PaymentCartTm> paymentCartTmList = new ArrayList<>();

    public PlacePaymentDto() {
    }

    public PlacePaymentDto(String orderId, LocalDate date, String customerId, List<PaymentCartTm> paymentCartTmList) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.paymentCartTmList = paymentCartTmList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<PaymentCartTm> getCartTmList() {
        return paymentCartTmList;
    }

    public void setCartTmList(List<PaymentCartTm> paymentCartTmList) {
        this.paymentCartTmList = paymentCartTmList;
    }

    @Override
    public String toString() {
        return "PlaceOrderDto{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                ", cartTmList=" + paymentCartTmList +
                '}';
    }
}
