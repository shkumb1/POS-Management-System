package com.JavaProject.Final.Pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderID;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_price")
    private double orderPrice;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "waiter_id")
    private Waiter waiter;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "table_id")
    private Tables table;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderProduct> orderProducts;


    public Order(Integer orderID, LocalDate orderDate, double orderPrice, Waiter waiter, Tables table, List<OrderProduct> orderProducts) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.waiter = waiter;
        this.table = table;
        this.orderProducts = orderProducts;
    }

    public Order() {
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
