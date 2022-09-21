package com.hepsiburada.database.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Products {

    @Column(name = "product_id")
    @Id
    private String productId;

    @Column(name = "category_id")
    private String categoryId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(productId, products.productId) && Objects.equals(categoryId, products.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, categoryId);
    }
}
