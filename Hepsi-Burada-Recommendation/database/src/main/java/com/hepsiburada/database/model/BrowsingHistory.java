package com.hepsiburada.database.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "browsing_history")
public class BrowsingHistory {

    private long id;
    private String userId;
    private String productId;
    private Date produceTime;

    public BrowsingHistory() {
    }

    public BrowsingHistory(String userId, String productId, Date produceTime) {
        this.userId = userId;
        this.productId = productId;
        this.produceTime = produceTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }
}
