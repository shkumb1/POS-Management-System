package com.JavaProject.Final.Pojo.DTO;

import com.JavaProject.Final.Pojo.Product;

public class OrderProductDTO {
    private Product product;
    private int quantity;

    public OrderProductDTO(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
