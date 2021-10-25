package br.com.alura.studykafka.model;

import java.math.BigDecimal;

public class Order {
    private String orderId, userId;
    private BigDecimal value;

    public Order(String orderId, String userId, BigDecimal value) {
        this.orderId = orderId;
        this.userId = userId;
        this.value = value;
    }

}
