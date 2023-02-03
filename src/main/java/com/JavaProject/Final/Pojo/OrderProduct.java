package com.JavaProject.Final.Pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="orderproduct_id")
    private Integer orderProductID;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;


    @ManyToOne
    @JsonBackReference
    private Order order;

    public OrderProduct(Integer orderProductID, int quantity, Product product, Order order) {
        this.orderProductID = orderProductID;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public OrderProduct() {
    }

    public Integer getOrderProductID() {
        return orderProductID;
    }

    public void setOrderProductID(Integer orderProductID) {
        this.orderProductID = orderProductID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

