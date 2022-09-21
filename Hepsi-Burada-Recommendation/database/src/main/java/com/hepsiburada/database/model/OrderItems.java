package com.hepsiburada.database.model;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItems {

    private int id;

    @Column(name = "order_id")
    private float orderId;

    @Column(name = "product_id")
    private String productId;

    private float quantity;

    @Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public float getOrderId() {
        return orderId;
    }

    public void setOrderId(float orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
