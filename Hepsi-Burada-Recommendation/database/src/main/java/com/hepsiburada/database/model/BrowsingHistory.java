package com.hepsiburada.database.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrowsingHistory that = (BrowsingHistory) o;
        return id == that.id && Objects.equals(userId, that.userId) && Objects.equals(productId, that.productId) && Objects.equals(produceTime, that.produceTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, produceTime);
    }
}
